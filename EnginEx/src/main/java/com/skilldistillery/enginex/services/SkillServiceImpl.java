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
	public List<DeveloperSkill> index() {
		return skillRepo.findAll();
	}


	@Override
	public List<DeveloperSkill> findByDevId(int userId, String username) {
		if (username.equals(userRepo.findById(userId).get().getUsername())) {
			return skillRepo.findByUserId(userId);
		} else {
			return null;
		}
				
	}


	@Override
	public DeveloperSkill create(DeveloperSkill newSkill, String username) {
		User user = userRepo.findByUsername(username);
		newSkill.setUser(user);
		return skillRepo.saveAndFlush(newSkill);
	}


	@Override
	public DeveloperSkill edit(int userId, DeveloperSkill edit, String username, int skillId) {
		Optional<DeveloperSkill> opt = skillRepo.findById(skillId);
		User user = userRepo.findByUsername(username);
		DeveloperSkill skill = null;
		if(user.getId() == userId) {
		
		if(opt.isPresent()) {
			skill = opt.get();
			skill.setSkillLevel(edit.getSkillLevel());
			skill.setSkillTitle(edit.getSkillTitle());
			
			skill = skillRepo.saveAndFlush(skill);
		}
			return skill;
		}
			return null;
		
		}


	@Override
	public void delete(int userId, String username, int skillId) {
		if(username.equals(userRepo.findById(userId).get().getUsername())) {
			skillRepo.deleteById(skillId);
		}
	}



}
