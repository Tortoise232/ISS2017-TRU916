package ro.tru916.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.tru916.core.model.User;
import ro.tru916.core.service.UserService;
import ro.tru916.web.dto.UserDto;

import java.util.List;

/**
 * Created by cata on 28.04.2017.
 */
@RestController
public class UserController {
    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "users")
    public UserDto getUsers()
    {
        log.trace("getClients----entered");

        List<User> users = userService.findAll();

        log.trace("getUsers:users{}",users);

        return new UserDto(users);
    }
}
