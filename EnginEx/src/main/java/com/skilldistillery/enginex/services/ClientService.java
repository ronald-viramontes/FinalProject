package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.Client;

public interface ClientService {

	Client getClientById(int clientId);

	Client create(Client client, int userId);

	Client update(Client client, String username, int clientId);

	Boolean delete(String username, int clientId);

	List<Client> getAllClients();

}
