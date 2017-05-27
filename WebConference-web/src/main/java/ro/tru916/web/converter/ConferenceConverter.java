package ro.tru916.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.tru916.core.model.Conference;
import ro.tru916.web.dto.ConferenceDto;

/**
 * Created by tudor on 06-May-17.
 */
@Component
public class ConferenceConverter extends BaseConverter<Conference, ConferenceDto> {
    private static final Logger log = LoggerFactory.getLogger(ConferenceConverter.class);

    @Override
    public ConferenceDto convertModelToDto(Conference conference) {
        ConferenceDto conferenceDto = new ConferenceDto(conference.getName(), conference.getDate().toString(),
                                                        conference.getDeadline().toString(),conference.getOwner().getUsername());
        conferenceDto.setId(conference.getId());
        return conferenceDto;
    }

}
