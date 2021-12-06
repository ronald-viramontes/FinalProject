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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "job_application")
public class JobApplication {

	public JobApplication() {
		
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "application_date")
	private LocalDate date;
	
	@JsonIgnore
	@Column(name = "decision_date")
	private LocalDate decisionDate;
	

	@JsonIgnoreProperties({"status", "type"})
	@ManyToOne
	@JoinColumn(name = "job_post_id")
	private JobPost jobPost;

	@JsonIgnoreProperties({ "sentMessages", "receivedMessages", 
							"applications", "posts"})
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JsonManagedReference(value="comment")
	@OneToMany(mappedBy = "application")
	private List<JobApplicationComment> comments;

	@JsonBackReference(value="detail")
	@OneToOne(mappedBy="application")
	private JobDetail detail;

	@JsonIgnoreProperties({"applications"})
	@ManyToOne
	@JoinColumn(name="app_status_id")
	private AppStatus appStatus;
	
	

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(LocalDate decisionDate) {
		this.decisionDate = decisionDate;
	}

	public JobPost getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<JobApplicationComment> getComments() {
		return comments;
	}

	public void setComments(List<JobApplicationComment> comments) {
		this.comments = comments;
	}

	public JobDetail getDetail() {
		return detail;
	}

	public void setDetail(JobDetail detail) {
		this.detail = detail;
	}

	public AppStatus getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(AppStatus appStatus) {
		this.appStatus = appStatus;
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
		JobApplication other = (JobApplication) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "JobApplication [id=" + id + ", date=" + date + ", decisionDate=" + decisionDate
				+ ", detail=" + detail + ", appStatus=" + appStatus + "]";
	}

}
