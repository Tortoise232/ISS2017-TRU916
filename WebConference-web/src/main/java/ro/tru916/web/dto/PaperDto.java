package ro.tru916.web.dto;

/**
 * Created by cata on 05.06.2017.
 */
public class PaperDto {

    private String name;
    private String user;
    private Float rating;
    private String path;
    private String conference;

    public PaperDto() {
    }

    public PaperDto(String name, String user, Float rating, String path, String conference) {
        this.name = name;
        this.user = user;
        this.rating = rating;
        this.path = path;
        this.conference = conference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaperDto paperDto = (PaperDto) o;

        if (name != null ? !name.equals(paperDto.name) : paperDto.name != null) return false;
        if (user != null ? !user.equals(paperDto.user) : paperDto.user != null) return false;
        if (rating != null ? !rating.equals(paperDto.rating) : paperDto.rating != null) return false;
        if (path != null ? !path.equals(paperDto.path) : paperDto.path != null) return false;
        return conference != null ? conference.equals(paperDto.conference) : paperDto.conference == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (conference != null ? conference.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaperDto{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", rating=" + rating +
                ", path='" + path + '\'' +
                ", conference='" + conference + '\'' +
                '}';
    }
}
