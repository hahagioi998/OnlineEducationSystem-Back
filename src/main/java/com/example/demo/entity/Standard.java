package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Standard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="std_id")
	private Long stdId;
	
	@Column(name="std_name",unique = true)
	private String stdName;
	
	
	@OneToMany(mappedBy = "standard",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<StandardSubjects> standardSubjects = new HashSet<StandardSubjects>();
	
	public Standard() {
	
	}

	public Standard(String stdName) {
		super();
		this.stdName = stdName;
	}

	public Set<StandardSubjects> getStandardSubjects() {
		return standardSubjects;
	}
	public void setStandardSubjects(Set<StandardSubjects> standardSubjects) {
		this.standardSubjects = standardSubjects;
	}
	public Long getStdId() {
		return stdId;
	}

	public void setStdId(Long stdId) {
		this.stdId = stdId;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public Standard(Long stdId, String stdName) {
		super();
		this.stdId = stdId;
		this.stdName = stdName;
	}
}
