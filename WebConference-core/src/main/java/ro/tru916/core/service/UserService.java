package ro.tru916.core.service;

/**
 * Created by Laura on 4/30/2017.
 */
public interface UserService {
    void addUser(String name, byte[] password, String username, String email) throws RuntimeException;
    void authenticateUser(String username, byte[] password) throws RuntimeException;
}
