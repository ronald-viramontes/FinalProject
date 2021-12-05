package com.skilldistillery.enginex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enginex.entities.User;
import com.skilldistillery.enginex.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
//	private JobPostRepository jobRepo;
//	@Autowired
//	private SkillRepository sRepo;
//	@Autowired
//	private ExperienceRepository expRepo;
//	@Autowired
//	private ChatRepository chatRepo;
//	@Autowired
//	private EducationRepository eduRepo;
//	@Autowired
//	private JobApplicationRepository appRepo;
//	@Autowired
//	private JobDetailRepository detailRepo;
//	@Autowired
//	private JobApplicationCommentRepository appCommentRepo;
	

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User show(int id) {
		Optional<User> receivedUser = userRepo.findById(id);
		return receivedUser.get();
	}

	@Override
	public void destroy(String username, int id) {
//		User admin = userRepo.findByUsername(username);
		Optional<User> u = userRepo.findById(id);
		User delUser = u.get();
				
			userRepo.delete(delUser);
		
	}

	@Override
	public User update(String username, int id, User user) {
		Optional<User> u = userRepo.findById(id);
		User existingUser = u.get();
		User subUser = userRepo.findByUsername(username);
		if (subUser.getUsername().equals(existingUser.getUsername())) {
			if (existingUser != null) {
				existingUser.setUsername(user.getUsername());
				existingUser.setPassword(user.getPassword());
				existingUser.setEnabled(user.isEnabled());
				existingUser.setRole(user.getRole());
				existingUser.setFirstName(user.getFirstName());
				existingUser.setLastName(user.getLastName());
				existingUser.setEmail(user.getEmail());
				existingUser.setPhoneNumber(user.getPhoneNumber());
				existingUser.setImageUrl(user.getImageUrl());
				userRepo.saveAndFlush(existingUser);
				return existingUser;
			}
		}
		return null;
	}
	
	

	@Override
	public User showUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> findBySkill(String skill) {
		skill = "%"+skill+"%";
		return userRepo.findBySkills_skillTitleLike(skill);
	}

	@Override
	public User disableAccount(String username, int userId) {
		User disabledUser = userRepo.findByUsername(username);
						
		disabledUser.setEnabled(false);

		return userRepo.saveAndFlush(disabledUser);
	}

	@Override
	public void removeUserAccount(String sysadmin, String username) {
		User systemAdmin = userRepo.findByUsername(sysadmin);
		User removeUser = userRepo.findByUsername(username);
		User newUser = new User();
		
		if (systemAdmin.getRole() == "ADMIN") {
				removeUser = newUser;
//				removeUser.setApplications(emptyUser.getApplications());
//				removeUser.setCompany(emptyUser.getCompany());
//				removeUser.setEducations(emptyUser.getEducations());
//				removeUser.setEmail("");				
//				removeUser.setExperiences(emptyUser.getExperiences());
//				removeUser.setFirstName("");
//				removeUser.setLastName("");
//				removeUser.setImageUrl("");
//				removeUser.setPassword("");
//				removeUser.setPhoneNumber("");
//				removeUser.setPosts(emptyUser.getPosts());
//				removeUser.setReceivedMessages(emptyUser.getReceivedMessages());
//				removeUser.setRole("");
//				removeUser.setSentMessages(emptyUser.getSentMessages());
//				removeUser.setSkills(emptyUser.getSkills());
//				removeUser.setUsername("");
//				removeUser.setEnabled(false);
				userRepo.saveAndFlush(removeUser);
			
				userRepo.delete(removeUser);
		}
				
					
	}

	@Override
	public User enableOrDisableAccount(String username, User user) {
//		User dbUser = userRepo.findByUsername(user.getUsername());
//		if (dbUser != null) {
//			dbUser.setEnabled(user.isEnabled());
//			dbUser = userRepo.saveAndFlush(dbUser);
//			return dbUser;
//		}
		user = userRepo.saveAndFlush(user);
		
		return user;
	}
}
