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

    @ManyToMany(mappedBy = "owners",fetch = FetchType.EAGER)
    private Set<Paper> papers;

    @ManyToMany(mappedBy = "reviewers",fetch = FetchType.EAGER)
    private Set<Conference> reviewer;

    @ManyToMany(mappedBy = "speakers", fetch = FetchType.EAGER)
    private Set<Conference> speaker;

    @ManyToMany(mappedBy = "attendanceUsers", fetch = FetchType.EAGER)
    private Set<Conference> attendance;

    @OneToMany(mappedBy = "owner")
    private Set<Conference> conferenceOwner;

    public User() {}

    public User(String name, String password, String username, Date registerdate, String email, String type) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.registerdate = registerdate;
        this.email = email;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    public Set<Conference> getReviewer() {
        return reviewer;
    }

    public void setReviewer(Set<Conference> reviewer) {
        this.reviewer = reviewer;
    }

    public Set<Conference> getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Set<Conference> speaker) {
        this.speaker = speaker;
    }

    public Set<Conference> getAttendance() {
        return attendance;
    }

    public void setAttendance(Set<Conference> attendance) {
        this.attendance = attendance;
    }

    public Set<Conference> getConferenceOwner() {
        return conferenceOwner;
    }

    public void setConferenceOwner(Set<Conference> conferenceOwner) {
        this.conferenceOwner = conferenceOwner;
    }
}
