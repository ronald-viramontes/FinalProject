package com.skilldistillery.enginex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.JobType;
import com.skilldistillery.enginex.repositories.JobTypeRepository;
@Service
public class JobTypeServiceImpl implements JobTypeService {

	@Autowired
	private JobTypeRepository jobTypeRepo;
	
	@Override
	public List<JobType> getall() {
		// TODO Auto-generated method stub
		return jobTypeRepo.findAll();
	}

}
