package ro.tru916.core.service;

import ro.tru916.core.model.Paper;

import java.util.List;

/**
 * Created by cata on 06.06.2017.
 */
public interface PaperService {
    void addPaper(String name, String path, String user, String conference) throws RuntimeException;

    List<Paper> findAll();

    List<Paper> findAllFromConference(String conferenceName);

    List<Paper> findAllByUser(String userName);

    void changePaperStatus(String paperName);
}
