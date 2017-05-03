package ro.tru916.core.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cata on 27.04.2017.
 */
@Entity
@Table(name = "Userss")
//@Data
public class User extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "userame", nullable = false, unique = true)
    private String username;

    @Column(name = "registerdate", nullable = false)
    private Date registerdate;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "type", nullable = false)
    private String type;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="PAPER_USER")
    private Set<Paper> papers=new HashSet<Paper>();


    @ManyToOne
    @JoinTable(name = "CONFERENCE_REVIEWERS")
    private Conference reviewer;


    @ManyToOne
    @JoinTable(name = "CONFERENCE_SPEAKERS")
    private Conference speaker;

    public User() {
    }

    public User(String name, String password, String username, Date registerdate, String email, String type) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.registerdate = registerdate;
        this.email = email;
        this.type = type;

    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    public Conference getReviewer() {
        return reviewer;
    }

    public void setReviewer(Conference reviewer) {
        this.reviewer = reviewer;
    }

    public Conference getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Conference speaker) {
        this.speaker = speaker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;
        if (!username.equals(user.username)) return false;
        if (!registerdate.equals(user.registerdate)) return false;
        if (!email.equals(user.email)) return false;
        return type.equals(user.type);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + registerdate.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", registerdate=" + registerdate +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
