package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.Developer;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.entities.WorkExperience;
import com.skilldistillery.enginex.repositories.DeveloperRepository;
import com.skilldistillery.enginex.repositories.ExperienceRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	ExperienceRepository expRepo;

	@Autowired
	DeveloperRepository devRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<WorkExperience> findByDevId(int devId, String username) {
		if (username.equals(devRepo.findById(devId).get().getUser().getUsername())) {
			return expRepo.findByDeveloperId(devId);
		} else {
			return null;
		}
	}

	@Override
	public WorkExperience create(WorkExperience exp, String username) {
		Developer dev = userRepo.findByUsername(username).getDeveloper();
		exp.setDeveloper(dev);
		
			return expRepo.saveAndFlush(exp);
	}

	@Override
	public WorkExperience edit(int devId, WorkExperience exp, String username, int expId) {
		Optional<WorkExperience> opt = expRepo.findById(expId);
		User user = userRepo.findByUsername(username);
		WorkExperience expDb = null;
		if(user.getDeveloper().getId() == devId) {
		
			if (opt.isPresent()) {
				expDb = opt.get();
				expDb.setJobTitle(exp.getJobTitle());
				expDb.setCompanyName(exp.getCompanyName());
				expDb.setStartDate(exp.getStartDate());
				expDb.setEndDate(exp.getEndDate());

				expDb = expRepo.saveAndFlush(expDb);
				
			}
				return expDb;
		
		} return null;
	}

	@Override
	public void delete(int devId, String username, int expId) {
		if (username.equals(devRepo.findById(devId).get().getUser().getUsername())) {

			expRepo.deleteById(expId);
		}
	}

	@Override
	public List<WorkExperience> index() {
		return expRepo.findAll();
	}

}
