package ro.tru916.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tru916.core.model.User;
import ro.tru916.core.repository.UserRepository;

import java.util.List;

/**
 * Created by cata on 28.04.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        log.trace("finAll--method entered");
        return userRepository.findAll();

    }

//    @Override
//    public void addUser(User u) {
//        userRepository.save(u);
//    }


}
