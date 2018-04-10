package wikira.paiapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import wikira.paiapp.dto.ThingDto;
import wikira.paiapp.model.User;
import wikira.paiapp.services.ThingService;
import wikira.paiapp.services.UserService;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class ThingsController {

    private final UserService userService;
    private final ThingService thingService;

    public ThingsController(UserService userService, ThingService thingService) {
        this.userService = userService;
        this.thingService = thingService;
    }

    @GetMapping("/things/thingsform")
    public String showAddThingForm(Model model, Principal principal){
        ThingDto thingDto = new ThingDto();
        User user = userService.findByUsername(principal.getName());
        thingDto.setUserId(user.getId());
        model.addAttribute("thing", thingDto);
        return "/things/thingsform";
    }

    @PostMapping("things/thingsform")
    public String saveThingToDo(@Valid @ModelAttribute("thing") ThingDto thingDto, BindingResult result){

        ThingDto savedThing = thingService.saveThingDto(thingDto);

        return "/hello";

    }

    @GetMapping("/things/show")
    public String showThings(Model model, Principal principal){

        model.addAttribute("things", userService.findByUsername(principal.getName()).getThings());

        return "/things/showthings";
    }

}
