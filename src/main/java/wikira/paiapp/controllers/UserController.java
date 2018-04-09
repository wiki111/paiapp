package wikira.paiapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import wikira.paiapp.dto.UserDto;
import wikira.paiapp.model.User;
import wikira.paiapp.services.IUserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping(value = "/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "/user/registrationform";
    }

    @PostMapping("/user/registrationform")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto,
                                            BindingResult result, WebRequest request, Errors errors){
        User registered = new User();
        if(!result.hasErrors()){
            registered = createUserAccount(accountDto, result);
        }

        if(registered == null){
            result.rejectValue("email", "message.regError");
        }

        return new ModelAndView("/user/successRegister", "user", accountDto);

    }

    private User createUserAccount(UserDto accountDto, BindingResult result){

        User registered = service.registerNewAccount(accountDto);

        return registered;

    }

}
