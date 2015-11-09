package namoo.security.v3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    @RequestMapping("/")
    public String home() {
        //
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String getloginForm() {
        //
        return "login";
    }

}
