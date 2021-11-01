package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.entities.WorkExperience;
import com.skilldistillery.enginex.repositories.ExperienceRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	ExperienceRepository expRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<WorkExperience> findByDevId(int userId, String username) {
		if (username.equals(userRepo.findById(userId).get().getUsername())) {
			return expRepo.findByUserId(userId);
		} else {
			return null;
		}
	}

	@Override
	public WorkExperience create(WorkExperience exp, String username) {
		User user = userRepo.findByUsername(username);
		exp.setUser(user);
		
			return expRepo.saveAndFlush(exp);
	}

	@Override
	public WorkExperience edit(int userId, WorkExperience exp, String username, int expId) {
		Optional<WorkExperience> opt = expRepo.findById(expId);
		User user = userRepo.findByUsername(username);
		WorkExperience expDb = null;
		if(user.getId() == userId) {
		
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
	public void delete(int userId, String username, int expId) {
		if (username.equals(userRepo.findById(userId).get().getUsername())) {

			expRepo.deleteById(expId);
		}
	}

	@Override
	public List<WorkExperience> index() {
		return expRepo.findAll();
	}

}
