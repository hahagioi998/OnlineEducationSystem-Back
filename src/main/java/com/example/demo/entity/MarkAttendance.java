


package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class MarkAttendance {
	

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
	int rollNo;
	String studentName;
	String parentEmail;
	String studentEmail;
	String parentMobile;
	
	String status;
	
	public MarkAttendance() {
		
		// TODO Auto-generated constructor stub
	}

	
    
	

	public MarkAttendance(int rollNo, String studentName, String parentEmail, String studentEmail, String parentMobile,
			String status) {
		
		this.rollNo = rollNo;
		this.studentName = studentName;
		this.parentEmail = parentEmail;
		this.studentEmail = studentEmail;
		this.parentMobile = parentMobile;
		this.status = status;
	}





	public String getParentEmail() {
		return parentEmail;
	}





	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}





	public String getStudentEmail() {
		return studentEmail;
	}





	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}





	public String getParentMobile() {
		return parentMobile;
	}





	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}





	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}