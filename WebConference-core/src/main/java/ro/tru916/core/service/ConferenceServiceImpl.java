package ro.tru916.core.service;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.User;
import ro.tru916.core.model.Conference;
import ro.tru916.core.repository.ConferenceRepository;
import ro.tru916.core.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
    public void addConference(String name, String date, String deadline, String ownerUsername) throws RuntimeException {
        log.trace("addConference: name={}, date={}", name, date, ownerUsername);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date confDate = format.parse(date);
            Date confDeadline = format.parse(deadline);
            User confOwner = findUser(ownerUsername);
            Conference conference = new Conference(name, confDate, confDeadline, confOwner);

            try {
                conferenceRepository.save(conference);
            } catch (ConstraintViolationException e) {
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

}
