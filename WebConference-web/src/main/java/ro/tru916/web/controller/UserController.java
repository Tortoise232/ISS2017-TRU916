package ro.tru916.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.tru916.core.service.UserService;
import ro.tru916.web.converter.UserConverter;
import ro.tru916.web.dto.EmptyJsonResponse;
import ro.tru916.web.dto.UserDto;

import java.util.HashMap;
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
    public ResponseEntity registerUser(@RequestBody final Map<String, UserDto> userDtoMap){
        log.trace("registerUser: userDtoMap={}", userDtoMap);

        ResponseEntity response;
        UserDto userDto = userDtoMap.get("user");
        try {
            userService.addUser(userDto.getName(), userDto.getPassword(), userDto.getUsername(), userDto.getEmail());
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.CREATED);
            log.trace("registerUser: successful");
        }catch (RuntimeException e){
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.IM_USED);
            log.trace("registerUser: failed");
        }
        return response;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody final Map<String, UserDto> userDtoMap){
        log.trace("authenticateUser: userDtoMap={}", userDtoMap);

        ResponseEntity response;
        UserDto userDto = userDtoMap.get("user");
        try {
            userService.authenticateUser(userDto.getUsername(), userDto.getPassword());
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
            log.trace("authenticateUser: successful");
        }catch (RuntimeException e){
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.UNAUTHORIZED);
            log.trace("authenticateUser: failed");
        }

        return response;
    }
}
