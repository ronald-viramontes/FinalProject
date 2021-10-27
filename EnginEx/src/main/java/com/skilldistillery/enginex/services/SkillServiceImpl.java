package com.skilldistillery.enginex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.DeveloperSkill;
import com.skilldistillery.enginex.repositories.SkillRepository;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class SkillServiceImpl implements SkillService {
	
	@Autowired
	private SkillRepository skillRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public List<DeveloperSkill> findAll() {
		return skillRepo.findAll();
	}



}
