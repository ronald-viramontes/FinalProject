package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.Client;

public interface ClientService {

	Client getClientById(int clientId);

	Client create(String username, Client client);

	Client update(String username, Client client, int clientId);

	Boolean delete(String username, int clientId);

	List<Client> getAllClients();

}
