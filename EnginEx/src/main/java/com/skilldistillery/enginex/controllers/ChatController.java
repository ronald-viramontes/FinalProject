package com.skilldistillery.enginex.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enginex.entities.Chat;
import com.skilldistillery.enginex.services.ChatService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class ChatController {

	@Autowired
	private ChatService chatSvc;


	@GetMapping("chats/users/{userId}")
	public List<Chat> indexBySender(HttpServletRequest req, HttpServletResponse res, 
									Principal principal, @PathVariable Integer userId) {
		
		return chatSvc.findBySenderId(principal.getName(), userId, userId);
	}
	@GetMapping("chats/{userId}/{receiverId}")
	public List<Chat> indexByReceiver(HttpServletRequest req, HttpServletResponse res, 
			Principal principal, @PathVariable Integer userId, @PathVariable Integer receiverId) {
		
		return chatSvc.findByReceiverId(principal.getName(), userId, receiverId);
	}


	
	@GetMapping("chats/users/{userId}/chats/{chatId}")
	public Chat getChatById(HttpServletRequest req, HttpServletResponse res, 
												@PathVariable int userId, @PathVariable int chatId,
												Principal principal) {
	
		Chat chat = chatSvc.retrieveByChatId(principal.getName(), userId, chatId);
		if(chat != null) {
			res.setStatus(200);
			return chat;
		} else {
			res.setStatus(404);
			return null;
		}
	}
	
	

	@PostMapping("chats/users/{userId}/{sentToUsername}")
	public Chat create(HttpServletRequest req, HttpServletResponse res,
											@PathVariable String sentToUsername, 
											@PathVariable Integer userId, 
											@RequestBody Chat chat, 
											Principal principal) {
		
		chat = chatSvc.createChat(principal.getName(), chat, userId, sentToUsername);
		
		if(chat != null) {
			res.setStatus(200);
			return chat;
		} else {
			res.setStatus(400);
			return null;
		}
		
	}

	@PostMapping("chats/{userId}/chats/{chatId}")
	public Chat createReply(HttpServletRequest req, HttpServletResponse res,
							@PathVariable Integer userId, 
							@PathVariable Integer chatId, 
							@RequestBody Chat reply, 
							Principal principal) {
		
		reply = chatSvc.replyToChat(principal.getName(), reply, userId, chatId);
		
		if(reply != null) {
			res.setStatus(200);
			return reply;
		} else {
			res.setStatus(400);
			return null;
		}
		
	}
	
	
	@DeleteMapping("chats/{userId}/{chatId}")
	public void delete(HttpServletRequest req, HttpServletResponse res,
						@PathVariable int chatId, @PathVariable int userId,  Principal principal) {
		
		chatSvc.deleteChat(principal.getName(), chatId, userId);
	}

	@PutMapping("chats/users/{userId}/receiver/{receiverId}/{chatId}")
	public Chat edit(HttpServletRequest req, HttpServletResponse res,
						@PathVariable int receiverId, @PathVariable int chatId, 
						@PathVariable int userId, 
						@RequestBody Chat chat,  Principal principal) {
		
		
		chat = chatSvc.editChat(principal.getName(), chat, chatId, userId, receiverId);
		if(chat != null) {
			res.setStatus(200);
			return chat;
		} else {
			res.setStatus(400);
			return null;
		}
	}
	
//	@GetMapping("chats/read/{userId}/{chatId}")
//	public Chat updateReadStatus(@PathVariable int chatId, 
//			@PathVariable int userId, Principal principal) {
//		
//		Chat chat = chatSvc.readChat(chatId, userId);
//		return chat;
//	}

}
