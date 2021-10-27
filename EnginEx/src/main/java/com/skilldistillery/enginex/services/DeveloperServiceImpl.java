package com.skilldistillery.enginex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Developer;
import com.skilldistillery.enginex.repositories.DeveloperRepository;

@Service
public class DeveloperServiceImpl implements DeveloperService {
	@Autowired
	private DeveloperRepository devRepo;


}
