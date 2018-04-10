package wikira.paiapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import wikira.paiapp.dto.ThingDto;
import wikira.paiapp.model.ThingToDo;

@Component
public class ThingToDto implements Converter<ThingToDo, ThingDto> {

    @Override
    public ThingDto convert(ThingToDo thingToDo) {

        if(thingToDo == null){
            return null;
        }

        ThingDto thingDto = new ThingDto();

        thingDto.setId(thingToDo.getId());
        thingDto.setCompletionCondition(thingToDo.getCompletionCondition());
        thingDto.setDeadline(thingToDo.getDeadline());
        thingDto.setDescription(thingToDo.getDescription());
        thingDto.setUserId(thingToDo.getUser().getId());

        return thingDto;

    }
}
