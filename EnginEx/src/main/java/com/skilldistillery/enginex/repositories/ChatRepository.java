package com.skilldistillery.enginex.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enginex.entities.Chat;
import com.skilldistillery.enginex.entities.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	List<Chat> findBySender(User sender);
	List<Chat> findByReceiver(User receiver);
	Chat findById(int chatId);
	
}
