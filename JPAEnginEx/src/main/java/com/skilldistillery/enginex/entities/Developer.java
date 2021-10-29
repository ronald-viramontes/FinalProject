package com.skilldistillery.enginex.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Developer {

	public Developer() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;

	
	@Column(name="image_url")
	private String imageUrl;
	
	@JsonManagedReference(value="userToDeveloper")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonManagedReference(value="devToSkill")
	@OneToMany(mappedBy="developer")
	private List<DeveloperSkill> skills;
	
	@OneToMany(mappedBy="developer")
	private List<WorkExperience> experiences;
	
	@OneToMany(mappedBy="developer")
	private List<DeveloperEducation> educations;
	
	@JsonIgnore
	@OneToMany(mappedBy="developer")
	private List<JobApplication> applications;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<DeveloperSkill> getSkills() {
		return skills;
	}


	public void setSkills(List<DeveloperSkill> skills) {
		this.skills = skills;
	}


	public List<WorkExperience> getExperiences() {
		return experiences;
	}


	public void setExperiences(List<WorkExperience> experiences) {
		this.experiences = experiences;
	}


	public List<DeveloperEducation> getEducations() {
		return educations;
	}


	public void setEducations(List<DeveloperEducation> educations) {
		this.educations = educations;
	}


	public List<JobApplication> getApplications() {
		return applications;
	}


	public void setApplications(List<JobApplication> applications) {
		this.applications = applications;
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
		Developer other = (Developer) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "DeveloperAccount [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", imageUrl=" + imageUrl + "]";
	}
	
	
	
	
	
	

}
