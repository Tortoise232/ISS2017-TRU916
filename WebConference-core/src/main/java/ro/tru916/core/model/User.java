package ro.tru916.core.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cata on 27.04.2017.
 */
@Entity
@Table(name = "Userss")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CONFERENCE_REVIEWERS")
    private Set<Conference> reviewer;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CONFERENCE_SPEAKERS")
    private Set<Conference> speaker;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ATTENDANCE_CONFERENCE")
    private Set<Conference> attendance;


    @OneToMany(mappedBy = "owner")
    private Set<Conference> conferenceOwner;

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



}
