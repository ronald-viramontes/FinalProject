package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.Client;

public interface ClientService {
	
	Client getClientById(String username, int clientId);
	Client createClient(Client client);
	Client updateClient(Client client, String username, int clientId);
	Boolean deleteClient(String username, int clientId);
	List<Client> getAllClients();
	

}
