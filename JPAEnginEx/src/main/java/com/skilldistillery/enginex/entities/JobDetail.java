package com.skilldistillery.enginex.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_detail")
public class JobDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="finish_date")
	private LocalDate finishDate;
	
	@Column(name="job_rating")
	private int rating;
	
	@Column(name="job_rating_comment")
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public JobDetail() {
		super();
	}

	@Override
	public String toString() {
		return "JobDetail [id=" + id + ", startDate=" + startDate + ", finishDate=" + finishDate + ", rating=" + rating
				+ ", comment=" + comment + "]";
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
		JobDetail other = (JobDetail) obj;
		return id == other.id;
	}
	
	
}