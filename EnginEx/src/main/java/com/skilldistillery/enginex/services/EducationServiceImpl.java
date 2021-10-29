package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.DeveloperEducation;
import com.skilldistillery.enginex.repositories.DeveloperRepository;
import com.skilldistillery.enginex.repositories.EducationRepository;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	EducationRepository edRepo;
	@Autowired
	DeveloperRepository devRepo;
	
	@Override
	public List<DeveloperEducation> findByDevId(int devId, String username) {
		if(username.equals(devRepo.findById(devId).get().getUser().getUsername())) {
			return edRepo.findByDeveloperId(devId);
		} else {
			return null;
		}
	}

	@Override
	public DeveloperEducation create(int devId, DeveloperEducation edu, String username) {
		if(username.equals(devRepo.findById(devId).get().getUser().getUsername())){
			edu.setDeveloper(devRepo.findById(devId).get());
			return edRepo.saveAndFlush(edu);
		}
		return null;
	}

	@Override
	public DeveloperEducation edit(int devId, DeveloperEducation edu, String username, int eduId) {
		DeveloperEducation eduDb = null;
		if(username.equals(devRepo.findById(devId).get().getUser().getUsername())){
			Optional<DeveloperEducation> opt = edRepo.findById(eduId);
			if(opt.isPresent()) {
				eduDb = opt.get();
				eduDb.setCompleteDate(edu.getCompleteDate());
				eduDb.setDegreeCertificateName(edu.getDegreeCertificateName());
				eduDb.setEducationType(edu.getEducationType());
				eduDb.setInstitutionName(edu.getInstitutionName());
				eduDb = edRepo.saveAndFlush(eduDb);
				return eduDb;
			}
			return eduDb;
		}
		return eduDb;
	}

	@Override
	public void delete(int devId, String username, int eduId) {
		if(username.equals(devRepo.findById(devId).get().getUser().getUsername())){
			edRepo.deleteById(devId);
		}
	}

	@Override
	public List<DeveloperEducation> index() {
		return edRepo.findAll();
	}

}
