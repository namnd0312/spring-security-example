package com.namnd.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/namnd")
public class TestController {
	
	@GetMapping("/user")
	public String userAccess() {
		return ">>> User Contents!";
	}


	@GetMapping("admin")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
}
