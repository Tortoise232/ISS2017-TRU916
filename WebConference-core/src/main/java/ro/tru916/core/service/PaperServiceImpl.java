package ro.tru916.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tru916.core.model.Conference;
import ro.tru916.core.model.Paper;
import ro.tru916.core.model.User;
import ro.tru916.core.repository.ConferenceRepository;
import ro.tru916.core.repository.PaperRepository;
import ro.tru916.core.repository.UserRepository;

/**
 * Created by cata on 06.06.2017.
 */
@Service
public class PaperServiceImpl implements PaperService {

    private static final Logger log = LoggerFactory.getLogger(PaperServiceImpl.class);

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    @Transactional
    public void addPaper(String name,  String path,String user,String conference)throws RuntimeException {

        try{

            Conference conference1=conferenceRepository.findAll().stream().filter(c->c.getName().equals(conference)).findAny().get();

            User user1=userRepository.findAll().stream().filter(u->u.getUsername().equals(user)).findAny().get();

            Paper paper=new Paper(name,new Float(0),path,conference1);
            paper.getOwners().add(user1);
//            paper.setPaper();
            try{
                paperRepository.save(paper);
//                userRepository.findOne(new Long(1));

            }catch (Exception e)
            {
//                e.printStackTrace();
                throw  new RuntimeException("Problem at save");
            }
        }

        catch(Exception e)
        {
//            e.printStackTrace();
            throw  new RuntimeException("Problem at save");
        }
    }
}
