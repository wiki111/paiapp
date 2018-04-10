package wikira.paiapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import wikira.paiapp.dto.ThingDto;
import wikira.paiapp.model.ThingToDo;
import wikira.paiapp.model.User;

@Component
public class DtoToThing implements Converter<ThingDto, ThingToDo> {

    @Synchronized
    @Nullable
    @Override
    public ThingToDo convert(ThingDto thingDto) {

        if(thingDto == null){
            return null;
        }

        final ThingToDo thingToDo = new ThingToDo();
        thingToDo.setId(thingDto.getId());

        if(thingDto.getUserId() != null){
            User user = new User();
            user.setId(thingDto.getUserId());
            thingToDo.setUser(user);
            user.addThing(thingToDo);
        }

        thingToDo.setCompletionCondition(thingDto.getCompletionCondition());
        thingToDo.setDeadline(thingDto.getDeadline());
        thingToDo.setDescription(thingDto.getDescription());
        thingToDo.setDone(thingDto.isDone());

        return thingToDo;
    }
}
