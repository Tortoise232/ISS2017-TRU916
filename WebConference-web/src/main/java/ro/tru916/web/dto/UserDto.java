package ro.tru916.web.dto;

import ro.tru916.core.model.User;

import java.util.List;

/**
 * Created by cata on 28.04.2017.
 */
public class UserDto {
    private List<User> users;

    public UserDto() {
    }

    public UserDto(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
