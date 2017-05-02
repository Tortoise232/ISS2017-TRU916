package ro.tru916.core.service;

import org.springframework.stereotype.Service;
import ro.tru916.core.model.User;

import java.util.List;

/**
 * Created by cata on 28.04.2017.
 */
public interface UserService {

    List<User> findAll();
    //void addUser(User u);
}
