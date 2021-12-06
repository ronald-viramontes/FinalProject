package com.skilldistillery.enginex.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chat {
		
	public Chat() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String subject;
	
	private String message;
	
		
	@Column(name="send_timestamp")
	private LocalDateTime dateTimeStamp;
	
//	@JsonIgnoreProperties({ "applications", "educations", 
//		"skills", "experiences", "posts" })
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User sender;
	
//	@JsonIgnoreProperties({ "applications", "educations", 
//		"skills", "experiences", "posts" })
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="reply_user_id")
	private User receiver;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="in_reply_to_chat_id")
	private Chat reply;
	
//	@JsonIgnore
	@OneToMany(mappedBy="reply")
	private List<Chat> replies;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTimeStamp() {
		return dateTimeStamp;
	}

	public void setDateTimeStamp(LocalDateTime dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Chat getReply() {
		return reply;
	}

	public void setReply(Chat reply) {
		this.reply = reply;
	}

	public List<Chat> getReplies() {
		return replies;
	}

	public void setReplies(List<Chat> replies) {
		this.replies = replies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", subject=" + subject + ", message=" + message + ", dateTimeStamp=" + dateTimeStamp + ", sender=" + sender + ", receiver=" + receiver + "]";
	}
	
}
