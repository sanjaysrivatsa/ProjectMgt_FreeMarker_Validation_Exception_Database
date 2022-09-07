package com.ProjectManagement.ProjectManagement.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.management.relation.RelationTypeSupport;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.persistence.Id;
//Map or link this class to the table in the database. 
//Use @Entity for linking a class to a table in the database
import javax.persistence.Table;

//Map or link the fields in this class to the fields in the database.
//Use  @Column for linking a field in the class to a field in the database.

@Entity
@Table(name="projects")

public class Project /*extends ResourceSupport*/ implements Serializable {
	private static final long serialVersionUID = 1L; //The class version starts from 1.
	//projectId should be in the range of 100 to 10000
	@Range(min=100, max=10000, message="projectID must be between 100 and 10000")
	@NotNull(message="projectId is mandatory")
	@Id //Id means, in the table, projectId is primary key
	@Column(name="projectId")
	int projectId;
	@Size(min=5, message="projectName must be equal to 5 or more characters.")
	@Column(name="projectName")
	String  projectName;

	//@NotNull will work with dates, strings and numbers. For @NotNull, validator is not required.
	@NotEmpty(message="startDate is mandatory")
	@Column(name="startDate")
	String	startDate;
	//@NotEmpty works only with Strings and collections
	@NotEmpty(message="endDate is mandatory")
	@Column(name="endDate")
	String	endDate;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Project [projectName=" + projectName + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}
