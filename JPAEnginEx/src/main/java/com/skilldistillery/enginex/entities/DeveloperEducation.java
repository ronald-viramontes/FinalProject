package com.skilldistillery.enginex.entities;

import java.time.LocalDate;
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
@Table(name="developer_education")
public class DeveloperEducation {

	public DeveloperEducation() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="education_type")
	private String educationType;
	
	@Column(name="institution_name")
	private String institutionName;
	
	@Column(name="degree_certificate_name")
	private String degreeCertificateName;
	
	@Column(name="complete_date")
	private LocalDate completeDate;
	
	@JsonIgnoreProperties({"company", "sentMessages", "receivedMessages", 
		"educations", "skills", "experiences" , "posts", "applications"})
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDegreeCertificateName() {
		return degreeCertificateName;
	}

	public void setDegreeCertificateName(String degreeCertificateName) {
		this.degreeCertificateName = degreeCertificateName;
	}

	public LocalDate getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(LocalDate completeDate) {
		this.completeDate = completeDate;
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
		DeveloperEducation other = (DeveloperEducation) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "DeveloperEducation [id=" + id + ", educationType=" + educationType + ", institutionName="
				+ institutionName + ", degreeCertificateName=" + degreeCertificateName + ", completeDate="
				+ completeDate + "]";
	}
	
}
