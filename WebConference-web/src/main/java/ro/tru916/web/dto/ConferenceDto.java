package ro.tru916.web.dto;

        import java.util.Date;

/**
 * Created by tudor on 06-May-17.
 */
public class ConferenceDto extends BaseDto {
    private String name;
    private String date;

    public ConferenceDto(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public ConferenceDto() {}

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {this.date = date;}

    @Override
    public String toString() {
        return "ConferenceDto{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
