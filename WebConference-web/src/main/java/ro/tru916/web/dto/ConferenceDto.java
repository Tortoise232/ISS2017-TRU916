package ro.tru916.web.dto;

import java.util.Set;

/**
 * Created by tudor on 06-May-17.
 */
public class ConferenceDto extends BaseDto {
    private String name;
    private String date;
    private String deadline;
    private String ownerUsername;
    private Set<UserDto> reviewers;
    private Set<UserDto> attenders;
    private Set<UserDto> speakers;

    public ConferenceDto(String name, String date, String deadline, String ownerUsername) {
        this.name = name;
        this.date = date;
        this.deadline = deadline;
        this.ownerUsername = ownerUsername;
    }

    public ConferenceDto() {}

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDeadline() { return deadline; }

    public String getOwnerUsername(){ return ownerUsername; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {this.date = date; }

    public void setDeadline(String deadline) { this.deadline = deadline; }

    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }

    public Set<UserDto> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<UserDto> reviewers) {
        this.reviewers = reviewers;
    }

    public Set<UserDto> getAttenders() {
        return attenders;
    }

    public void setAttenders(Set<UserDto> attenders) {
        this.attenders = attenders;
    }

    public Set<UserDto> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<UserDto> speakers) {
        this.speakers = speakers;
    }

    @Override
    public String toString() {
        return "ConferenceDto{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", deadline='" + deadline + '\'' +
                ", ownerUsername='" + ownerUsername + '\'' +
                ", reviewers=" + reviewers +
                ", attenders=" + attenders +
                ", speakers=" + speakers +
                "} " + super.toString();
    }
}