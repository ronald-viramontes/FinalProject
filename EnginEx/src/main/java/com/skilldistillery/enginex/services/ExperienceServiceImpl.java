package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.WorkExperience;
import com.skilldistillery.enginex.repositories.DeveloperRepository;
import com.skilldistillery.enginex.repositories.ExperienceRepository;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	ExperienceRepository expRepo;

	@Autowired
	DeveloperRepository devRepo;

	@Override
	public List<WorkExperience> findByDevId(int devId, String username) {
		if (username.equals(devRepo.findById(devId).get().getUser().getUsername())) {
			return expRepo.findByDeveloperId(devId);
		} else {
			return null;
		}
	}

	@Override
	public WorkExperience create(int devId, WorkExperience edu, String username) {
		if (username.equals(devRepo.findById(devId).get().getUser().getUsername())) {
			edu.setDeveloper(devRepo.findById(devId).get());
			return expRepo.saveAndFlush(edu);
		}
		return null;
	}

	@Override
	public WorkExperience edit(int devId, WorkExperience exp, String username, int expId) {
		WorkExperience expDb = null;
		if (username.equals(devRepo.findById(devId).get().getUser().getUsername())) {
			Optional<WorkExperience> opt = expRepo.findById(expId);
			if (opt.isPresent()) {
				expDb = opt.get();
				expDb.setJobTitle(exp.getJobTitle());
				expDb.setCompanyName(exp.getCompanyName());
				expDb.setStartDate(exp.getStartDate());
				expDb.setEndDate(exp.getEndDate());

				expDb = expRepo.saveAndFlush(expDb);
				return expDb;
			}
			return expDb;
		}
		return expDb;
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
