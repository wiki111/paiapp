package wikira.paiapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import wikira.paiapp.converters.ThingToDto;
import wikira.paiapp.dto.ThingDto;
import wikira.paiapp.model.User;
import wikira.paiapp.services.IThingService;
import wikira.paiapp.services.IUserService;
import wikira.paiapp.services.ThingService;
import wikira.paiapp.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@Controller
public class ThingsController {

    private final IUserService userService;
    private final IThingService thingService;

    private static final String THING_FORM_URL = "/things/form";
    private static final String THINGS_SHOW_URL = "/things/show";

    @Autowired
    private ThingToDto thingToDto;

    public ThingsController(UserService userService, ThingService thingService) {
        this.userService = userService;
        this.thingService = thingService;
    }

    @GetMapping(THING_FORM_URL)
    public String showThingForm(Model model, Principal principal){

        model.addAttribute("thing", getUserThings(principal));

        return THING_FORM_URL;
    }

    @GetMapping("/user/{userId}/things/{thingId}/update")
    public String updateThingForm(@PathVariable String userId, @PathVariable String thingId,
                                  Model model, Principal principal){

        model.addAttribute("thing", getSelectedThing(
                Long.valueOf(userId),
                Long.valueOf(thingId),
                principal)
        );

        return THING_FORM_URL;
    }

    @PostMapping(THING_FORM_URL)
    public String saveThingToDo(@Valid @ModelAttribute("thing") ThingDto thingDto, BindingResult result){

        if(result.hasErrors()){
            return THING_FORM_URL;
        }

        ThingDto savedThing = thingService.saveThingDto(thingDto);

        return "redirect:" + THINGS_SHOW_URL;

    }

    @GetMapping(THINGS_SHOW_URL)
    public String showThings(Model model, Principal principal){

        model.addAttribute("things", userService.findByUsername(principal.getName()).getThings());

        return THINGS_SHOW_URL;
    }

    @GetMapping("/user/{userId}/things/{thingId}/delete")
    public String deleteThing(@PathVariable String userId, @PathVariable String thingId, Model model){

        thingService.deleteById(Long.valueOf(userId), Long.valueOf(thingId));

        return "redirect:" + THINGS_SHOW_URL;
    }


    private ThingDto getSelectedThing(Long userId, Long thingId, Principal principal){

        User user = userService.findById(Long.valueOf(userId));

        Optional<ThingDto> thingDtoOptional = user.getThings().stream()
                .filter(thing -> thing.getId().equals(Long.valueOf(thingId)))
                .map(thing -> thingToDto.convert(thing)).findFirst();

        if(!thingDtoOptional.isPresent()){
            throw new RuntimeException();
        }

        return thingDtoOptional.get();

    }

    private ThingDto getUserThings(Principal principal){

        ThingDto thingDto = new ThingDto();
        User user = userService.findByUsername(principal.getName());
        thingDto.setUserId(user.getId());

        return thingDto;
    }

}
