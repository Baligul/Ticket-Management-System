package com.intuit.tms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

	@GetMapping("/api/home")
	public ResponseEntity<Object> getUserHomePage() {

		return new ResponseEntity<Object>("Hello I am normal user", HttpStatus.OK);

	}

	@GetMapping("/admin/api/home")
	public ResponseEntity<Object> getAdminHomePage() {

		return new ResponseEntity<Object>("Hello I am admin", HttpStatus.OK);

	}
	
	@PostMapping("/admin/api/home")
	public ResponseEntity<Object> postAdminHomePage() {

		return new ResponseEntity<Object>("Hello I am admin post", HttpStatus.OK);

	}
}
