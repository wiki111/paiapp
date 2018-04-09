package wikira.paiapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/home", "/"})
    public String getHome(){
        return "home";
    }

    @GetMapping("/user/login")
    public String getLogin(){
        return "/user/login";
    }

    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

}
