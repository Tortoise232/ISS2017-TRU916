package ro.tru916.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import ro.tru916.core.model.Paper;
import ro.tru916.core.service.PaperService;
import ro.tru916.web.dto.ConferencesDto;
import ro.tru916.web.dto.PapersDto;
import ro.tru916.web.dto.EmptyJsonResponse;
import ro.tru916.web.dto.PaperDto;
import ro.tru916.web.converter.PaperConverter;

import java.util.List;
import java.util.Map;


/**
 * Created by cata on 04.06.2017.
 */
@RestController
public class PaperController {
    private static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperConverter paperConverter;

    @RequestMapping(value = "/paperadd",method = RequestMethod.POST)
    public ResponseEntity addPaper(@RequestBody final Map<String,PaperDto> paperDtoMap)
    {
        System.out.println("Sunt in controller");
        ResponseEntity response;
        PaperDto paperDto=paperDtoMap.get("paper");
        System.out.println(paperDto);
        try{
            paperService.addPaper(paperDto.getName(),paperDto.getPath(),paperDto.getUser(),paperDto.getConference());
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.CREATED);

        }
        catch(RuntimeException e)
        {
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.IM_USED);

        }
        catch(Exception e)
        {
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.IM_USED);
        }
        return response;
    }


    @RequestMapping(value = "/listpapers", method  = RequestMethod.GET)
    public PapersDto getPapers() {
        log.trace("getPapers");
        List<Paper> papers = paperService.findAll();
        log.trace("getPapers: papers={}", papers);
        return new PapersDto(paperConverter.convertModelsToDtos(papers));
    }

    @RequestMapping(value = "/listpapers/{name}",method = RequestMethod.GET)
    public PapersDto getPapersByConferenceName(@PathVariable final String name){
        log.trace("getPapersByConf: name={}", name);
        List<Paper> papers = paperService.findAllFromConference(name);
        log.trace("getPapersByConf: papers={}", papers);
        return new PapersDto(paperConverter.convertModelsToDtos(papers));
    }

    @RequestMapping(value = "/listpapersforuser/{name}",method = RequestMethod.GET)
    public PapersDto getPapersByUser(@PathVariable final String name){
        log.trace("getPapersByUser: name={}", name);
        List<Paper> papers = paperService.findAllByUser(name);
        log.trace("getPapersByUser: papers={}", papers);
        return new PapersDto(paperConverter.convertModelsToDtos(papers));
    }

    @RequestMapping(value = "/paperchangestatus",method = RequestMethod.POST)
    public void changePaperStatus(@RequestBody final Map<String,String> paperName){
        log.trace("changePaperStatusController");
        paperService.changePaperStatus(paperName.get("paperName"));
    }


}
