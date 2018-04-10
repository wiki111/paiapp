package wikira.paiapp.services;

import wikira.paiapp.dto.ThingDto;
import wikira.paiapp.model.ThingToDo;

public interface IThingService {

    ThingDto saveThingDto(ThingDto thing);

    void deleteById(Long userId, Long thingId);

}
