package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Developer;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.DeveloperRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class DeveloperServiceImpl implements DeveloperService {
	
	@Autowired
	private DeveloperRepository devRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Developer> findAll() {
		return devRepo.findAll();
	}

	@Override
	public Developer findById(int id) {
		Optional<Developer> opt = devRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public Developer create(Developer dev, int userId) {
		User user = userRepo.getById(userId);
		dev.setUser(user);
		return devRepo.saveAndFlush(dev);
	}

	@Override
	public boolean delete(String username, int id) {
		User user = userRepo.findByUsername(username);
		Optional<Developer> opt = devRepo.findById(id);
		if(opt.isPresent() && opt.get().getUser() == user) {
			devRepo.delete(opt.get());
			return true;
		}
		return false;
	}

	@Override
	public Developer edit(Developer edit, int userId, int devId) {
		Optional<Developer> opt = devRepo.findById(devId);
		Developer dev = null;
		if(opt.get()!= null && opt.get().getUser().getId() == userId) {
			dev = opt.get();
			dev.setEmail(edit.getEmail());
			dev.setFirstName(edit.getFirstName());
			dev.setLastName(edit.getLastName());
			dev.setImageUrl(edit.getImageUrl());
			dev.setPhoneNumber(edit.getPhoneNumber());
			return devRepo.saveAndFlush(dev);
		}
		return dev;
	}


}
