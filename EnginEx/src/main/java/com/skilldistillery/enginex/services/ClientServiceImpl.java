package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

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
	public Client update(Client client, String username, int clientId) {
		User user = userRepo.findByUsername(username);

		if (client.getUser() == user) {
			clientRepo.save(client);
			return client;

		} else {
			return client = null;
		}

	}

	@Override
	public List<Client> getAllClients() {
		return clientRepo.findAll();
	}

	@Override
	public Boolean delete(String username, int clientId) {
		User user = userRepo.findByUsername(username);
		Client client = clientRepo.findById(clientId);
		if (client.getUser() == user) {
			clientRepo.delete(client);
			return true;

		} else {
			return false;
		}

	}

	@Override
	public Client getClientById(int clientId) {

		Client client = clientRepo.findById(clientId);
		if (!clientRepo.existsById(clientId)) {
			return client = null;
		} else {
			return client;

		}
	}

	@Override
	public Client create(Client client, int userId) {
		User user = null;
		Optional<User> u = userRepo.findById(userId);
		if (u.isPresent()) {
			user = u.get();
			client.setUser(user);
			return clientRepo.saveAndFlush(client);

		} else {
			return null;
		}

	}

}
