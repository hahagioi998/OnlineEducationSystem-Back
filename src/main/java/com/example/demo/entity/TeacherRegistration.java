package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TeacherRegistration {
	@Id
	@GeneratedValue
	long teacher_id;
	String fullname;
	String username;
	String email;
	String password;
	String subject;
	String status;

	public TeacherRegistration() {

	}

	public TeacherRegistration(long teacher_id, String fullname, String username, String email, String password,
			String subject, String status) {

		this.teacher_id = teacher_id;
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.subject = subject;
		this.status = status;
	}

	public long getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
