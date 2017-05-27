package ro.tru916.web.dto;

        import java.util.Date;

/**
 * Created by tudor on 06-May-17.
 */
public class ConferenceDto extends BaseDto {
    private String name;
    private String date;
    private String deadline;
    private String ownerUsername;

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

    @Override
    public String toString() {
        return "ConferenceDto{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", deadline='" + deadline + '\'' +
                ", ownerUsername='" + ownerUsername + '\'' +
                '}';
    }
}
