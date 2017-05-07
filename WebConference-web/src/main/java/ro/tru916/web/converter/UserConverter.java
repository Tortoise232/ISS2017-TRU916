package ro.tru916.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.tru916.core.model.User;
import ro.tru916.web.dto.UserDto;

/**
 * Created by Laura on 5/2/2017.
 */
@Component
public class UserConverter extends BaseConverter<User, UserDto> {
    private static final Logger log = LoggerFactory.getLogger(UserConverter.class);

    @Override
    public UserDto convertModelToDto(User user) {
        byte[] password = new byte[6];
        UserDto userDto = new UserDto(user.getName(), password, user.getUsername(), user.getEmail());
        userDto.setId(user.getId());
        return userDto;
    }
}
