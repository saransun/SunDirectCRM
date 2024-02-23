package com.sundirect.crm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class indexClass {

	
	@GetMapping("/")
    public String viewHomePage(Model model) {
		model.addAttribute("message", "Hello Thymeleaf");
        return "index";
    }
}
