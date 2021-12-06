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
@Table(name="job_type")
public class JobType {

	public JobType() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@JsonIgnoreProperties({ "user", "applications", "jobPosts"})
	@OneToMany(mappedBy="type")
	private List<JobPost> jobPosts;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JobPost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
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
		JobType other = (JobType) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "JobType [id=" + id + ", name=" + name + "]";
	}
	
}
