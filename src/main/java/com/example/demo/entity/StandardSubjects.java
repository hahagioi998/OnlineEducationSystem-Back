package com.example.demo.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="standard_subjects")
public class StandardSubjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne()
	@JoinColumn(name = "std_id")
	private Standard standard;
	
	@ManyToOne()
	@JoinColumn(name = "sub_id")
	private Subject subject;
	
	@ManyToOne()
	@JoinColumn(name = "teacher_id")
	private TeacherRegistrationKomal teacher;
	
	@Column(name="status")
	private String status;
	
	public StandardSubjects() {
		
	}
	public StandardSubjects( StandardSubjectDO stdDo) {
		this.status=stdDo.getStatus();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Standard getStandard() {
		return standard;
	}
	public void setStandard(Standard standard) {
		this.standard = standard;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public TeacherRegistrationKomal getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherRegistrationKomal teacher) {
		this.teacher = teacher;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
