package com.skilldistillery.enginex.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.services.UserService;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userServ;

	@GetMapping(path = "users")
	private List<User> index(HttpServletRequest req, HttpServletResponse res) {
		return userServ.index();
	}

	@GetMapping(path = "users/{id}")
	private User show(@PathVariable int id) {
		return userServ.show(id);
	}

	@DeleteMapping(path = "/users/{id}")
	public void delete(@PathVariable int id) {
		userServ.destroy(id);
	}

	@PutMapping(path = "/users/{id}")
	public User update(@PathVariable int id, @RequestBody User user) {
		user = userServ.update(id, user);
		return user;
	}
}
