package com.thanglong.chonlichthilai.login;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@GetMapping("")
	public String home() {
		return "Hello, home";
	}
	@GetMapping("/secured")
	public String secured() {
		return "Hello, secured";
	}
	@GetMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	@GetMapping("/login/oauth2/code/google")
	public String google() {
		return "dashboard.html";
	}
	
}
