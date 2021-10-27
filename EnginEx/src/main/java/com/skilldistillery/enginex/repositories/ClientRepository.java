package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	Client findById(int id);

}
