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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.Client;
import com.skilldistillery.enginex.services.ClientService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class ClientController {
	
	@Autowired
	private ClientService clientSvc;
	
	@GetMapping("users/clients/ping")
	public String ping() {
		return "pong";
	}
	
	@GetMapping("clients")
	public List<Client> getClients(HttpServletRequest req, HttpServletResponse res){
		
		return clientSvc.getAllClients();
	}
	
	@GetMapping("clients/{clientId}")
	public Client getClient(@PathVariable Integer clientId,
							HttpServletRequest req, 
							HttpServletResponse res) {
		Client client = clientSvc.getClientById(clientId);
		if (client == null) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return client;
		}
		
	}

	@PostMapping("clients/{userId}")
	public Client createClient(@RequestBody Client client, 
								HttpServletRequest req, 
								HttpServletResponse res,
								@PathVariable Integer userId
								) {
		
		client = clientSvc.create(client, userId);
		if( client == null) {
			res.setStatus(406);
			return null;
		} else {
			res.setStatus(201);
			return client;
		}
	
	}
	
	@PutMapping("users/{userId}/clients/{clientId}")
	public Client updateClient(@PathVariable Integer userId, 
								@PathVariable Integer clientId,
								@RequestBody Client client, 
								HttpServletRequest req, 
								HttpServletResponse res,
								Principal principal) {
			
		client = clientSvc.update(client, clientId, userId);
		
		
		return client;
	}
	
	@DeleteMapping("clients/{clientId}")
	public void deleteClient(@PathVariable Integer clientId,
								HttpServletRequest req, 
								HttpServletResponse res,
								Principal principal) {
			if(clientSvc.delete(principal.getName(), clientId)) {
				res.setStatus(200);
			} else {
				res.setStatus(400);
			}
		
	}



}
