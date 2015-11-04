package namoo.security.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping("/login/success")
	public String loginSuccess() {
		return "success";
	}
	
	@RequestMapping("/denied")
    public String getDeniedPage() {
        return "/security/deniedPage";
    }
	
}
