package wikira.paiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wikira.paiapp.converters.DtoToThing;
import wikira.paiapp.converters.ThingToDto;
import wikira.paiapp.dto.ThingDto;
import wikira.paiapp.model.ThingToDo;
import wikira.paiapp.model.User;
import wikira.paiapp.repositories.ThingRepository;
import wikira.paiapp.repositories.UserRepository;

import java.util.Optional;

@Service
public class ThingService implements IThingService {

    @Autowired
    private UserRepository userRepository;

    private final DtoToThing dtoToThing;
    private final ThingToDto thingToDto;

    public ThingService(DtoToThing dtoToThing, ThingToDto thingToDto) {
        this.dtoToThing = dtoToThing;
        this.thingToDto = thingToDto;
    }

    @Override
    public ThingDto saveThingDto(ThingDto thing) {

        if(thing == null){
            return null;
        }

        Optional<User> userOptional = userRepository.findById(thing.getUserId());

        if(userOptional.isPresent()){
            User user = userOptional.get();

            ThingToDo thingToDo = dtoToThing.convert(thing);
            thingToDo.setUser(user);
            user.addThing(thingToDo);

            User savedUser = userRepository.save(user);

            Optional<ThingToDo> savedThingOptional = savedUser
                    .getThings()
                    .stream()
                    .filter(userThings -> userThings.getCompletionCondition().equals(thing.getCompletionCondition()))
                    .findFirst();

            return thingToDto.convert(savedThingOptional.get());
        }else {
            return new ThingDto();
        }

    }
}
