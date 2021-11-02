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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "job_application")
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "id")
public class JobApplication {

	public JobApplication() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "application_approval")
	private boolean approved;

	@Column(name = "application_status")
	private String status;

	@Column(name = "application_date")
	private LocalDate date;

	@Column(name = "decision_date")
	private LocalDate decisionDate;

	@JsonIgnoreProperties({ "applications" })
	@ManyToOne
	@JoinColumn(name = "job_post_id")
	private JobPost jobPost;

	@JsonIgnoreProperties({ "user", "applications", "posts" })
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "application")
	private List<JobApplicationComment> comments;

	@OneToOne(mappedBy="application")
	private JobDetail detail;

	// Methods

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "JobApplication [id=" + id + ", approved=" + approved + ", status=" + status + ", date=" + date
				+ ", decisionDate=" + decisionDate + "]";
	}

	


}
