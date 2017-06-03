package ro.tru916.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tru916.core.model.Conference;
import ro.tru916.core.service.ConferenceService;
import ro.tru916.web.converter.ConferenceConverter;
import ro.tru916.web.dto.EmptyJsonResponse;
import ro.tru916.web.dto.ConferenceDto;
import ro.tru916.web.dto.ConferencesDto;

import java.util.Map;
import java.util.List;

/**
 * Created by tudor on 06-May-17.
 */
@RestController
public class ConferenceController {
    private static final Logger log = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ConferenceConverter conferenceConverter;

    @RequestMapping(value = "/registerconf", method = RequestMethod.POST)
    public ResponseEntity registerConference(@RequestBody final Map<String, ConferenceDto> conferenceDtoMap){
        log.trace("registerConference: conferenceDtoMap={}", conferenceDtoMap);

        ResponseEntity response;
        ConferenceDto conferenceDto = conferenceDtoMap.get("conference");
        try {
            conferenceService.addConference(conferenceDto.getName(),conferenceDto.getDate(),conferenceDto.getDeadline(),conferenceDto.getOwnerUsername());
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.CREATED);
            log.trace("registerConference: successful");
        }catch (RuntimeException e){
            response = new ResponseEntity(new EmptyJsonResponse(), HttpStatus.IM_USED);
            log.trace("registerConference: failed");
        }
        return response;
    }


    @RequestMapping(value = "/conferences/{name}", method = RequestMethod.GET)
    public ConferenceDto getConference(@PathVariable final String name) {
        log.trace("getConference: name={}", name);
        Conference conference = conferenceService.findOne(name);
        log.trace("getConference: conference={}", conference);
        return conferenceConverter.convertModelToDto(conference);

    @RequestMapping(value = "/listconf", method  = RequestMethod.GET)
    public ConferencesDto getConferences() {
        log.trace("getConferences");
        List<Conference> conferences = conferenceService.findAll();
        log.trace("getConferences: conferences={}", conferences);
        return new ConferencesDto(conferences);

    }
}