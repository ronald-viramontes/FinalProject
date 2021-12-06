package com.skilldistillery.enginex.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	public User() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	private Boolean enabled;

	private String role;
	
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="image_url")
	private String imageUrl;

	private String employer;

	@OneToMany(mappedBy="user")
	private List<JobApplication> applications;

	
	@OneToMany(mappedBy="user")
	private List<DeveloperEducation> educations;
	
	@OneToMany(mappedBy="user")
	private List<DeveloperSkill> skills;
	
	@OneToMany(mappedBy="user")
	private List<WorkExperience> experiences;

	@OneToMany(mappedBy="user")
	private List<JobPost> posts;
	
	@OneToMany(mappedBy="sender")
	private List<Chat> sentMessages;
	
	@OneToMany(mappedBy="receiver")
	private List<Chat> receivedMessages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public List<JobApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<JobApplication> applications) {
		this.applications = applications;
	}

	public List<DeveloperEducation> getEducations() {
		return educations;
	}

	public void setEducations(List<DeveloperEducation> educations) {
		this.educations = educations;
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

	public List<JobPost> getPosts() {
		return posts;
	}

	public void setPosts(List<JobPost> posts) {
		this.posts = posts;
	}

	public List<Chat> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Chat> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Chat> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Chat> receivedMessages) {
		this.receivedMessages = receivedMessages;
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", imageUrl=" + imageUrl + ", employer=" + employer + "]";
	}

		
}
