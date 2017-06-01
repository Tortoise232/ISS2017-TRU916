package ro.tru916.web.dto;

import ro.tru916.core.model.Conference;

import java.util.List;

/**
 * Created by tudor on 27-May-17.
 */
public class ConferencesDto {
    private List<Conference> conferences;

    public ConferencesDto(){}

    public ConferencesDto(List<Conference> conferences){
        this.conferences = conferences;
    }

    public List<Conference> getConferences(){
        return this.conferences;
    }

    public void setConferences(List<Conference> conferences){
        this.conferences = conferences;
    }
}