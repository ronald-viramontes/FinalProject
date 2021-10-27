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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="job_application_comment")
public class JobApplicationComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	
	@Column(name="comment_date")
	private LocalDate date;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="job_application_id")
	private JobApplication application;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="in_reply_to_comment_id")
	private JobApplicationComment baseComment;
	
	@OneToMany(mappedBy="baseComment")
	private List<JobApplicationComment> replies;
	
	//Methods

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public JobApplication getApplication() {
		return application;
	}

	public void setApplication(JobApplication application) {
		this.application = application;
	}

	public JobApplicationComment getBaseComment() {
		return baseComment;
	}

	public void setBaseComment(JobApplicationComment baseComment) {
		this.baseComment = baseComment;
	}

	public List<JobApplicationComment> getReplies() {
		return replies;
	}

	public void setReplies(List<JobApplicationComment> replies) {
		this.replies = replies;
	}

	public JobApplicationComment() {
		super();
	}

	@Override
	public String toString() {
		return "JobApplicationComment [id=" + id + ", comment=" + comment + ", date=" + date + "]";
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
		JobApplicationComment other = (JobApplicationComment) obj;
		return id == other.id;
	}
	
	
}
