package ro.tru916.core.service;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.Conference;
import ro.tru916.core.model.User;
import ro.tru916.core.repository.ConferenceRepository;
import ro.tru916.core.repository.UserRepository;

import javax.persistence.RollbackException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
        log.trace("ADD CINFERENCE");
        log.trace("\n\n\naddConference:owner={} \n\n\n"+ownerUsername);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date confDate = format.parse(date);
            Date confDeadline = format.parse(deadline);
            User confOwner = findUser(ownerUsername);
            Conference conference = new Conference(name, confDate,confDeadline,confOwner);//Am pus aici ca deadline-ul sa fie chiar in data de incepere a conferintei
            //dar trebue setat de user
            //de asemenea trebe sa

            try {
                conferenceRepository.saveAndFlush(conference);
            }
//             catch ( ConstraintViolationException e) {
//                log.trace("CONFERENCE ERROR SAVE");
//
//            }
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
}
