package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.DeveloperSkill;
import com.skilldistillery.enginex.entities.User;
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


	@Override
	public List<DeveloperSkill> findByDevId(int userId) {
		return skillRepo.findByUserId(userId);
	}


	@Override
	public DeveloperSkill create(DeveloperSkill newSkill, String username) {
		User user = userRepo.findByUsername(username);
		newSkill.setUser(user);
		return skillRepo.saveAndFlush(newSkill);
	}


	@Override
	public DeveloperSkill edit(DeveloperSkill edit, int userId, int skillId) {
		Optional<DeveloperSkill> opt = skillRepo.findById(skillId);
		DeveloperSkill skill = null;
		if(opt.isPresent() && opt.get().getUser().getId() == userId) {
			skill = opt.get();
			skill.setSkillLevel(edit.getSkillLevel());
			skill.setSkillTitle(edit.getSkillTitle());
			return skillRepo.saveAndFlush(skill);
		}
		return skill;
	}


	@Override
	public boolean delete(int skillId, int userId) {
		Optional<DeveloperSkill> opt = skillRepo.findById(skillId);
		if(opt.isPresent() && opt.get().getUser().getId() == userId) {
			DeveloperSkill skill = opt.get();
			skillRepo.delete(skill);
			return true;
		}
		return false;
	}



}
