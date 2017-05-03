package ro.tru916.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.tru916.core.service.UserService;
import ro.tru916.web.converter.UserConverter;
import ro.tru916.web.dto.UserDto;

import java.util.Map;

/**
 * Created by Laura on 4/30/2017.
 */
@RestController
public class UserController {
    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody final Map<String, UserDto> userDtoMap){
        log.trace("registerUser: userDtoMap={}", userDtoMap);

        String message;
        UserDto userDto = userDtoMap.get("user");
        try {
            userService.addUser(userDto.getName(), userDto.getPassword(), userDto.getUsername(), userDto.getEmail());
            message = "Account successfully created.";
            log.trace("registerUser: successful");
        }catch (RuntimeException e){
            message = e.getMessage();
            log.trace("registerUser: failed");
        }
        return message;
    }
}
