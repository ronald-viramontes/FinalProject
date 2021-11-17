package com.skilldistillery.enginex.entities;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="developer_skill")
public class DeveloperSkill {

	public DeveloperSkill() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="skill_title")
	private String skillTitle;

	@Column(name="skill_level")
	private String skillLevel;
	
//	@JsonIgnore
//	@JsonBackReference
	@JsonIgnoreProperties({ "applications","company", "educations", 
		"skills", "experiences", "posts", "sentMessages", "receivedMessages" })
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSkillTitle() {
		return skillTitle;
	}

	public void setSkillTitle(String skillTitle) {
		this.skillTitle = skillTitle;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		DeveloperSkill other = (DeveloperSkill) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "DeveloperSkill [id=" + id + ", skillTitle=" + skillTitle + ", skillLevel=" + skillLevel + "]";
	}
	
}

