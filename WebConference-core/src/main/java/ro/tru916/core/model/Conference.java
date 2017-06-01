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
@Table(name = "Conference")
public class Conference extends BaseEntity<Long> {

    @Column(name = "name", nullable = false,unique = true)
    private String name;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "deadline",nullable=false)
    private Date deadline;



    @OneToMany(mappedBy = "paper")
    private Set<Paper> papers = new HashSet<>();


    @ManyToMany(mappedBy = "reviewer",fetch = FetchType.EAGER)
    private Set<User> reviewers = new HashSet<>();


    @ManyToMany(mappedBy = "speaker",fetch = FetchType.EAGER)
    private Set<User> speakers = new HashSet<>();

    @OneToMany(mappedBy = "accepted",fetch = FetchType.EAGER)
    private Set<Paper> acceptedpapers = new HashSet<>();

    @ManyToMany(mappedBy = "attendance",fetch = FetchType.EAGER)
    private Set<User> attendanceUsers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "CONFERENCE_OWNER")
    private User owner;




    public Conference() {
    }

    public Conference(String name, Date date,Date deadline,User owner) {
        this.name = name;
        this.date = date;
        this.deadline=deadline;
        this.owner=owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    public Set<User> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<User> reviewers) {
        this.reviewers = reviewers;
    }

    public Set<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<User> speakers) {
        this.speakers = speakers;
    }

    public Set<Paper> getAcceptedpapers() {
        return acceptedpapers;
    }

    public void setAcceptedpapers(Set<Paper> acceptedpapers) {
        this.acceptedpapers = acceptedpapers;
    }

    public Set<User> getAttendanceUsers() {
        return attendanceUsers;
    }

    public void setAttendanceUsers(Set<User> attendanceUsers) {
        this.attendanceUsers = attendanceUsers;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (deadline != null ? !deadline.equals(that.deadline) : that.deadline != null) return false;
        if (papers != null ? !papers.equals(that.papers) : that.papers != null) return false;
        if (reviewers != null ? !reviewers.equals(that.reviewers) : that.reviewers != null) return false;
        if (speakers != null ? !speakers.equals(that.speakers) : that.speakers != null) return false;
        if (acceptedpapers != null ? !acceptedpapers.equals(that.acceptedpapers) : that.acceptedpapers != null)
            return false;
        if (attendanceUsers != null ? !attendanceUsers.equals(that.attendanceUsers) : that.attendanceUsers != null)
            return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (papers != null ? papers.hashCode() : 0);
        result = 31 * result + (reviewers != null ? reviewers.hashCode() : 0);
        result = 31 * result + (speakers != null ? speakers.hashCode() : 0);
        result = 31 * result + (acceptedpapers != null ? acceptedpapers.hashCode() : 0);
        result = 31 * result + (attendanceUsers != null ? attendanceUsers.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", deadline=" + deadline +
                ", papers=" + papers +
                ", reviewers=" + reviewers +
                ", speakers=" + speakers +
                ", acceptedpapers=" + acceptedpapers +
                ", attendanceUsers=" + attendanceUsers +
                ", owner=" + owner +
                '}';
    }
}