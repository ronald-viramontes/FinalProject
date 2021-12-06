package com.skilldistillery.enginex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.AppStatus;

public interface AppStatusRepository extends JpaRepository<AppStatus, Integer> {

	AppStatus findByName(String name);
	AppStatus findById(int statusId);
}
