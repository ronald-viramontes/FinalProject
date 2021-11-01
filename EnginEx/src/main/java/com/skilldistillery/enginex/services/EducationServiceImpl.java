package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.DeveloperEducation;
import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.EducationRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	EducationRepository edRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public List<DeveloperEducation> findByDevId(int userId) {
		return edRepo.findByUserId(userId);
	}

	@Override
	public DeveloperEducation create(int userId, DeveloperEducation edu, String username) {
		if (username.equals(userRepo.findById(userId).get().getUsername())) {
			edu.setUser(userRepo.findById(userId).get());
			return edRepo.saveAndFlush(edu);
		}
		return null;
	}

	@Override
	public DeveloperEducation edit(int userId, DeveloperEducation edu, String username, int eduId) {
		Optional<DeveloperEducation> opt = edRepo.findById(eduId);
		DeveloperEducation eduDb = null;
		User user = userRepo.findByUsername(username);
		if (user.getId() == userId) {
			if (opt.isPresent()) {
				eduDb = opt.get();
				eduDb.setCompleteDate(edu.getCompleteDate());
				eduDb.setDegreeCertificateName(edu.getDegreeCertificateName());
				eduDb.setEducationType(edu.getEducationType());
				eduDb.setInstitutionName(edu.getInstitutionName());
				eduDb = edRepo.saveAndFlush(eduDb);
			}
			return eduDb;
		}
		return null;
	}

	@Override
	public void delete(int userId, String username, int eduId) {
		if (username.equals(userRepo.findById(userId).get().getUsername())) {
			edRepo.deleteById(eduId);
		}
	}

	@Override
	public List<DeveloperEducation> index() {
		return edRepo.findAll();
	}

}
