package wikira.paiapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wikira.paiapp.converters.DtoToThing;
import wikira.paiapp.converters.ThingToDto;
import wikira.paiapp.dto.ThingDto;
import wikira.paiapp.model.ThingToDo;
import wikira.paiapp.model.User;
import wikira.paiapp.repositories.UserRepository;

import java.util.Optional;

//TODO refactor
//TODO error handling

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
    public ThingDto saveThingDto(ThingDto thingDto) {

        if(thingDto == null){
            return null;
        }

        Optional<User> userOptional = userRepository.findById(thingDto.getUserId());

        if(userOptional.isPresent()){

            User user = userOptional.get();

            addOrUpdateThing(user, thingDto);

            User savedUser = userRepository.save(user);

            Optional<ThingToDo> savedThingOptional = savedUser
                    .getThings()
                    .stream()
                    .filter(userThings -> userThings.getCompletionCondition().equals(thingDto.getCompletionCondition()))
                    .findFirst();

            return thingToDto.convert(savedThingOptional.get());
        }else {
            return new ThingDto();
        }
    }

    private void addOrUpdateThing(User user, ThingDto thingDto){
        Optional<ThingToDo> thingToDoOptional = user.getThings()
                .stream()
                .filter(things -> things.getId().equals(thingDto.getId()))
                .findFirst();

        if(thingToDoOptional.isPresent()){
            updateThing(thingDto, thingToDoOptional);
        }else{
            addThing(thingDto, user);
        }
    }

    private void updateThing(ThingDto thingDto, Optional<ThingToDo> thingToDoOptional){

        ThingToDo thingFound = thingToDoOptional.get();
        thingFound.setDescription(thingDto.getDescription());
        thingFound.setDeadline(thingDto.getDeadline());
        thingFound.setCompletionCondition(thingDto.getCompletionCondition());

    }

    private void addThing(ThingDto thingDto, User user){

        ThingToDo thingToDo = dtoToThing.convert(thingDto);
        thingToDo.setUser(user);
        user.addThing(thingToDo);

    }

    @Override
    public void deleteById(Long userId, Long thingId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            Optional<ThingToDo> thingToDoOptional = user.getThings()
                    .stream()
                    .filter(things -> things.getId().equals(thingId))
                    .findFirst();

            if(thingToDoOptional.isPresent()){
                ThingToDo thingToDelete = thingToDoOptional.get();
                user.getThings().remove(thingToDelete);
                thingToDelete.setUser(null);
                userRepository.save(user);
            }
        }

    }


}
