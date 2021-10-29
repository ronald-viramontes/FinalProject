package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.Client;

public interface ClientService {

	Client getClientById(int clientId);

	Client create(Client client, String username);

	Client update(Client client, int clientId, int userId);

	Boolean delete(String username, int clientId);

	List<Client> getAllClients();

}
