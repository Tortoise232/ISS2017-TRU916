package ro.tru916.core.service;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.User;
import ro.tru916.core.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Laura on 4/30/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addUser(String name, byte[] password, String username, String email) throws RuntimeException {
        Date registerdate = Calendar.getInstance().getTime();
        String type = "basic";
        String decodedPassword = new String(password, StandardCharsets.UTF_8);

        log.trace("addUser: name={}, password={}, username={}, registerdate={}, email={}, type={}",
                name, password, username, registerdate, email, type);

        User user = new User(name, decodedPassword, username, registerdate, email, type);
        try {
            userRepository.save(user);
        }catch(ConstraintViolationException e){
            throw new RuntimeException("Username must be unique.");

        }

        log.trace("addUser: user={}", user);
    }

    @Override
    @Transactional
    public void authenticateUser(String username, byte[] password) throws RuntimeException {
        String decodedPassword = new String(password, StandardCharsets.UTF_8);
        log.trace("authenticateUser: username={}, password={}", username, password);

        Iterable<User> users = this.userRepository.findAll();
        User foundUser = new User();
        for (User user: users) {
            if(user.getUsername().equals(username))
                foundUser = user;
        }
        if(!foundUser.getPassword().equals(decodedPassword))
            throw new RuntimeException("Failed authentication.");

        log.trace("authenticateUser end");
    }

    @Override
    @Transactional
    public User getUserByName(String username) {
        log.trace("THE NAME OF THE USER IS {}",username);
        List<User> userl = this.userRepository.findAll();
        for (User userr : userl)
        {

            if(userr.getUsername().equals(username))
                return userr;
        }
        return new User();
    }

}
