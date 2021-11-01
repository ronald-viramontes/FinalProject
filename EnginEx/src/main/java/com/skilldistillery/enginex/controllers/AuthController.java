package com.skilldistillery.enginex.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost:4200" })
public class AuthController {

	@Autowired
	private AuthService authSvc;

	@PostMapping(path = "/register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		System.out.println(user);
		if (user == null) {
			res.setStatus(400);
		}
		user = authSvc.register(user);
		if(user == null) {
			res.setStatus(500);
			return user;
		}

		return user;
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public User authenticate(Principal principal) {
//	    return principal;
		return authSvc.getUser(principal.getName());
	}
	
	@GetMapping("/retrieve")
	public User getUser(Principal principal) {
		return authSvc.getUser(principal.getName());
	}
}
