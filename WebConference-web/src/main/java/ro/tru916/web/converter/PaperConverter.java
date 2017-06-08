package ro.tru916.web.converter;

import org.springframework.stereotype.Component;
import ro.tru916.core.model.User;
import ro.tru916.core.model.Paper;
import ro.tru916.web.dto.PaperDto;
import ro.tru916.web.dto.UserDto;

import java.util.stream.Collectors;

/**
 * Created by tudor on 07-Jun-17.
 */
@Component
public class PaperConverter extends BaseConverter<Paper, PaperDto> {

    @Override
    public PaperDto convertModelToDto(Paper paper) {
        User owner = paper.getOwners().iterator().next();
        String status;
        if (paper.getAccepted() == null) status="pending";
        else status="accepted";
        //Actually getting conference here
        String conference = paper.getPaper().getName();

        PaperDto paperDto = new PaperDto(paper.getName(),owner.getName(),paper.getGrade(),paper.getPath(),conference,status);
        paperDto.setId(paper.getId());
        return paperDto;
    /*
        PaperDto paperDto = new PaperDto(conference.getName(), conference.getDate().toString(),
                conference.getDeadline().toString(),conference.getOwner().getUsername());
        conferenceDto.setId(conference.getId());
        byte[] p = new byte[6];
        conferenceDto.setReviewers(conference.getReviewers().stream()
                .map(u -> new UserDto(u.getName(),p,u.getUsername(),u.getEmail())).collect(Collectors.toSet()));

        conferenceDto.setAttenders(conference.getAttendanceUsers().stream()
                .map(u -> new UserDto(u.getName(),p,u.getUsername(),u.getEmail())).collect(Collectors.toSet()));
        conferenceDto.setSpeakers(conference.getSpeakers().stream()
                .map(u -> new UserDto(u.getName(),p,u.getUsername(),u.getEmail())).collect(Collectors.toSet()));
        return paperDto;
     */
    }
}
