package ro.tru916.core.service;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.User;
import ro.tru916.core.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;

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
        String decodedPassword = new String( Base64.decodeBase64(password));

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


}
