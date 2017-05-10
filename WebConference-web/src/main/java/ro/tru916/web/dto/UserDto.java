package ro.tru916.web.dto;

/**
 * Created by Laura on 5/2/2017.
 */
public class UserDto extends BaseDto {
    private String name;
    private byte[] password;
    private String username;
    private String email;


    public UserDto(String name, byte[] password, String username, String email) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public UserDto() {}


    public String getName() {
        return name;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", password='*****"+ '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
