package com.skilldistillery.enginex.entities;


import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="app_status")
public class AppStatus {
	
	public AppStatus() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@JsonIgnoreProperties({"user", "posts", "jobPosts"})
	@OneToMany(mappedBy="appStatus")
	private List<JobApplication> applications;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<JobApplication> getApplications() {
		return applications;
	}


	public void setApplication(List<JobApplication> applications) {
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
		AppStatus other = (AppStatus) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "AppStatus [id=" + id + ", name=" + name + "]";
	}
	

	
}
