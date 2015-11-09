package namoo.security.v3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/communication/talks/home") 
    public String index()
    {
        // 기본사용자 홈페이지 전환 TODO 로직구현
        return "home";
    }
}
