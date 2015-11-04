package namoo.security.exam.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	
	@RequestMapping("/product/list")
	public String findProducts() {
		return "/product/list";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/admin/product")
	public String productAdminPage() {
		return "/product/admin";
	}
	
}
