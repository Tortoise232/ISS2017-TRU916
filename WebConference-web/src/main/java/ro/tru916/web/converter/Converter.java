package ro.tru916.web.converter;

import ro.tru916.core.model.BaseEntity;
import ro.tru916.web.dto.BaseDto;

/**
 * Created by Laura on 4/30/2017.
 */
public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
