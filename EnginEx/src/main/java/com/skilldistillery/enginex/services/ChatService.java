package com.skilldistillery.enginex.services;

import java.util.List;

import com.skilldistillery.enginex.entities.Chat;

public interface ChatService {
	
	
	List<Chat> findBySenderId(String username, int userId, int senderId);
	List<Chat> findByReceiverId(String username, int userId, int receiverId);
	Chat retrieveByChatId(String username, int userId, int chatId);
	Chat createChat(String username, Chat chat, int senderId, int appId);
	Chat replyToChat(String username, Chat chat, int senderId, int receiverId);
	void deleteChat(String username, int chatId, int senderId);
	Chat editChat(String username, Chat chat, int userId, int receiverId);

}
