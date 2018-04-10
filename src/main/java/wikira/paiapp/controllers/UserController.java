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

    private static final String USER_REGISTRATION_URL = "/user/form";
    private static final String USER_REGISTER_SUCCESS_URL = "/user/successRegister";

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping(value = "/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {

        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);

        return USER_REGISTRATION_URL;
    }

    @PostMapping("/user/form")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto,
                                            BindingResult result, WebRequest request, Errors errors){

        User registered = new User();

        if(!result.hasErrors()){
            registered = service.registerNewAccount(accountDto);
        }

        if(registered == null){
            result.rejectValue("email", "message.regError");
        }

        return new ModelAndView(USER_REGISTER_SUCCESS_URL, "user", accountDto);

    }

}
