package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
class HomeController {
	@GetMapping("/home")
	public String home(Principal principal) {
		return "home";
	}

	@GetMapping("/")
	public String hello(Principal principal) {
		return "home";
	}

	@GetMapping("/admin")
	public String helloAdmin(Principal principal) {
		return "admin";
	}

}
