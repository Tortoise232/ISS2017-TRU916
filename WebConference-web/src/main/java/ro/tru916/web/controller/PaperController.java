package ro.tru916.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.tru916.core.service.PaperService;
import ro.tru916.web.dto.EmptyJsonResponse;
import ro.tru916.web.dto.PaperDto;

import java.util.Map;


/**
 * Created by cata on 04.06.2017.
 */
@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

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
}
