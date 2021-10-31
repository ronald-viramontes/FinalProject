package com.skilldistillery.enginex.entities;

import java.time.LocalDate;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="job_post")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class JobPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="job_requirements")
	private String jobRequirements;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="completion_date")
	private LocalDate completionDate;
	
	@Column(name="developers_needed")
	private int developersNeeded;
	
	@Column(name="job_active")
	private boolean jobActive;

	@Column(name="date_posted")
	private LocalDate datePosted;
	
	@Column(name="date_closed")
	private LocalDate dateClosed;
	
	@ManyToOne
	@JoinColumn(name="job_type_id")
	private JobType type;
	
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="job_status_id")
	private JobStatus status;
	
	
	@OneToMany(mappedBy="jobPost")
	private List<JobApplication> applications;
	
	//Methods

	public JobPost() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobRequirements() {
		return jobRequirements;
	}

	public void setJobRequirements(String jobRequirements) {
		this.jobRequirements = jobRequirements;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public int getDevelopersNeeded() {
		return developersNeeded;
	}

	public void setDevelopersNeeded(int developersNeeded) {
		this.developersNeeded = developersNeeded;
	}

	public boolean isJobActive() {
		return jobActive;
	}

	public void setJobActive(boolean jobActive) {
		this.jobActive = jobActive;
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public LocalDate getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(LocalDate dateClosed) {
		this.dateClosed = dateClosed;
	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public List<JobApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<JobApplication> applications) {
		this.applications = applications;
	}

	@Override
	public String toString() {
		return "JobPost [id=" + id + ", jobRequirements=" + jobRequirements + ", startDate=" + startDate
				+ ", completionDate=" + completionDate + ", developersNeeded=" + developersNeeded + ", jobActive="
				+ jobActive + ", datePosted=" + datePosted + ", dateClosed=" + dateClosed + "]";
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
		JobPost other = (JobPost) obj;
		return id == other.id;
	}
}
