package namoo.security.v3.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicantController {

    @RequestMapping("/applicant")
    public String index() {
        // 관리자 페이지로 전환 TODO 로직구현
        return "applicant";
    }
}
