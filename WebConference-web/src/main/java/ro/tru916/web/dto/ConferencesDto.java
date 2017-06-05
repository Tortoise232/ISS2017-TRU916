package ro.tru916.web.dto;

import java.util.Set;

/**
 * Created by tudor on 27-May-17.
 */
public class ConferencesDto {
    private Set<ConferenceDto> conferences;

    public ConferencesDto(Set<ConferenceDto> conferences) {
        this.conferences = conferences;
    }

    public ConferencesDto() {
    }

    public Set<ConferenceDto> getConferences() {
        return conferences;
    }

    public void setConferences(Set<ConferenceDto> conferences) {
        this.conferences = conferences;
    }
}