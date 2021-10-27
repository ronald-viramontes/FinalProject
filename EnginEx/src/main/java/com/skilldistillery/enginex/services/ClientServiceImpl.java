package com.skilldistillery.enginex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Client;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.ClientRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	


	@Override
	public Client updateClient(Client client, String username, int clientId) {
		User user = userRepo.findByUsername(username);
		
		if(client.getUser() == user) {
			clientRepo.save(client);
			return client;
	
		}	else {
			return client = null;
		}
		
	}



	@Override
	public List<Client> getAllClients() {
		return clientRepo.findAll();
	}

	@Override
	public Boolean deleteClient(String username, int clientId) {
		User user = userRepo.findByUsername(username);
		Client client = clientRepo.findById(clientId);
		if(client.getUser() == user) {
			clientRepo.delete(client);
			return true;
	
		}	else {
			return false;
		}
				
	}



	@Override
	public Client getClientById(String username, int clientId) {
		
		User user = userRepo.findByUsername(username);
		Client client = clientRepo.findById(clientId);
		client.setUser(user);
		return client;
	}



	@Override
	public Client createClient(Client client) {
				
		return clientRepo.saveAndFlush(client);
	}

}
