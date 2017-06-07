package ro.tru916.core.service;

/**
 * Created by cata on 06.06.2017.
 */
public interface PaperService {

    void addPaper(String name,  String path,String user,String conference) throws RuntimeException;
}
