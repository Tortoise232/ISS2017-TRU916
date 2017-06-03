package ro.tru916.core.service;

import ro.tru916.core.model.Conference;

import java.util.Date;
import java.util.List;

/**
 * Created by tudor on 06-May-17.
 */
public interface ConferenceService {
    void addConference(String name, String date,String deadline,String ownerUsername) throws RuntimeException;
    List<Conference> findAll();
    Conference findOne(String name);
}
