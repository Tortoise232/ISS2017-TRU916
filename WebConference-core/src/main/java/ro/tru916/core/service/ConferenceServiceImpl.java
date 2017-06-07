package ro.tru916.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.Conference;
import ro.tru916.core.model.User;
import ro.tru916.core.repository.ConferenceRepository;
import ro.tru916.core.repository.UserRepository;
import ro.tru916.core.util.EmailSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Created by tudor on 06-May-17.
 */
@Service
public class ConferenceServiceImpl implements ConferenceService {

    private static final Logger log = LoggerFactory.getLogger(ConferenceService.class);

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addConference(String name, String date,String deadline,String ownerUsername) throws RuntimeException {
        log.trace("addConference:owner={}" + ownerUsername);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date confDate = format.parse(date);
            Date confDeadline = format.parse(deadline);
            User confOwner = findUser(ownerUsername);
            Conference conference = new Conference(name, confDate,confDeadline,confOwner);
            try {
                conferenceRepository.saveAndFlush(conference);
                EmailSender.conferenceRegistrationSuccess(confOwner,conference);
            }
            catch(Exception e)
            {
                throw new RuntimeException("Conference must be unique.");
            }
            log.trace("addConference: conference={}", conference);
        }
        catch(ParseException e){
            throw new RuntimeException("Date format invalid.");
        }
    }

    private User findUser(String username){
        Iterable<User> users = this.userRepository.findAll();
        for (User user: users) {
            if(user.getUsername().equals(username))
                return user;
        }
        throw new RuntimeException("User not found");
    }

    @Override
    @Transactional
    public List<Conference> findAll(){
        log.trace("findAllConferences");
        List<Conference> conferences = conferenceRepository.findAll();
        log.trace("findAll: conferences={}",conferences);
        return conferences;
    }

    @Override
    @Transactional
    public Conference findOne(String name) {
        log.trace("findConference: name={}", name);
        List<Conference> conferences = conferenceRepository.findAll();
        Conference conference = new Conference();
        for (Conference c : conferences) {
            if(c.getName().equals(name)){
                conference = c;
                break;
            }
        }
        log.trace("findConference: conference={}", conference);
        return conference;
    }

    @Override
    @Transactional
    public void updateConference(String oldName, String name, String date, String deadline) {
        log.trace("updateConference: name={}, date={}, deadline={}", name, date, deadline);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Conference conference = findOne(oldName);
        try {
            conference.setName(name);
            Date confDate = format.parse(date);
            Date confDeadline = format.parse(deadline);
            conference.setDate(confDate);
            conference.setDate(confDeadline);
        }catch(ParseException e){
            throw new RuntimeException("Date format invalid.");
        }
        log.trace("updateConference: conference={}", conference);
    }

    @Override
    @Transactional
    public void addReviewer(String conferenceName, String userName) {
        log.trace("addReviewer begin: conferenceName={}, userName={}", conferenceName, userName);
        Conference conference = findOne(conferenceName);
        User user = findUser(userName);
        Set<User> reviewers = conference.getReviewers();
        if(reviewers.contains(user))
            throw new RuntimeException("The user is already a reviewer!");
        reviewers.add(user);
        EmailSender.conferenceReviewing(user, conference);
        conference.setReviewers(reviewers);
        log.trace("addReviewer end: conference={}", conference);
    }

    @Override
    @Transactional
    public void removeReviewer(String conferenceName, String userName) {
        log.trace("removeReviewer begin: conferenceName={}, userName={}", conferenceName, userName);
        Conference conference = findOne(conferenceName);
        User user = findUser(userName);
        Set<User> reviewers = conference.getReviewers();
        reviewers.remove(user);
        conference.setReviewers(reviewers);

        log.trace("removeReviewer: conference={}", conference);
    }

    @Override
    @Transactional
    public void addAttender(String conferenceName, String userName) {
        log.trace("addAttender: conferenceName={}, userName={}", conferenceName, userName);
        Conference conference = findOne(conferenceName);
        User user = findUser(userName);
        Set<User> attenders = conference.getAttendanceUsers();
        if(attenders.contains(user))
            throw new RuntimeException("The user is already an attender!");
        attenders.add(user);
        EmailSender.conferenceAttending(user,conference);
        conference.setAttendanceUsers(attenders);
        log.trace("addAttender: conference={}", conference);
    }

    @Override
    @Transactional
    public void removeAttender(String conferenceName, String userName) {
        log.trace("removeAttender begin: conferenceName={}, userName={}", conferenceName, userName);
        Conference conference = findOne(conferenceName);
        User user = findUser(userName);
        Set<User> attenders = conference.getAttendanceUsers();
        attenders.remove(user);
        conference.setAttendanceUsers(attenders);
        log.trace("removeAttender end: conference={}", conference);
    }
}
