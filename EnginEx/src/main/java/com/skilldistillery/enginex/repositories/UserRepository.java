package com.skilldistillery.enginex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	List<User> findBySkills_skillTitleLike(String keyword);
}
