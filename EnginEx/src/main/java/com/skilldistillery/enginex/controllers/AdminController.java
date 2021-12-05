package com.skilldistillery.enginex.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.services.AuthService;
import com.skilldistillery.enginex.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost:4300" })
@RequestMapping("api")
public class AdminController {

	@Autowired
	private AuthService authSvc;
	
	@Autowired
	private UserService userSvc;
	
	
	@GetMapping(path = "/admin/users")
	private List<User> index(HttpServletRequest req, HttpServletResponse res) {
		return userSvc.index();
	}
	
	@GetMapping(path = "/admin/user/{userId}")
	private User show(@PathVariable int userId, HttpServletRequest req, HttpServletResponse res) {
		
		User user = userSvc.show(userId);
		if (user == null) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return user;
		}
	}
	
	@GetMapping("/admin/username/{username}")
	public User showUsername(@PathVariable String username, HttpServletRequest req, 
							 HttpServletResponse res, Principal principal) {
		
		
			User user = userSvc.showUsername(username);
			if(user == null) {
				res.setStatus(401);
				return null;
			} else {
				return user;
			}
	}
	
	@PutMapping("/admin/eord/{username}")
	public User enableOrDisableAcct(@RequestBody User user, @PathVariable String username, 
									HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			user = userSvc.enableOrDisableAccount(username, user);
			
		} catch (Exception e) {

		System.out.println("Something went wrong" + ": " + e);
		
		
		} 
		if (user == null) {
			res.setStatus(400);
			return null;
		} else {
			res.setStatus(200);
			return user;
			
		}
	
		
	}
	
	@GetMapping("/retrieve")
	public User getUser(Principal principal) {
		return authSvc.getUser(principal.getName());
	}
	
	@DeleteMapping("/admin/delete/{userId}")
	public void removeUser(@PathVariable int userId, HttpServletRequest req,
										HttpServletResponse res, Principal principal) {
		userSvc.destroy(principal.getName(), userId);

		
		
		
		
	}
	
}
