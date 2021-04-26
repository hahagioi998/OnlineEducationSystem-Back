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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "teacher_registration_komal")
public class TeacherRegistrationKomal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="teacher_id")
	private Long id;
	
	@NotNull
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=30,message="Name should have atleast 2 characters")
	private String fullname;
	
	
	@NotNull
	@NotBlank(message="Please enter username")
	@Size(min=2, max=30,message="Username should have atleast 2 characters")
	private String username;
	
	@NotNull
	@NotBlank(message="Please enter your email")
	private String email;
	
	@NotNull
	@NotBlank(message="Please enter password")
	private String password;
	
	@NotNull
	@NotBlank(message="Please enter qualification")
	@Size(min=2, max=30,message="Qualification should have atleast 2 characters")
	private String qualification;

	
	
	
	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<StandardSubjects> standardSubjects = new HashSet<StandardSubjects>();
	
	public TeacherRegistrationKomal() {
		
	}
	
	public TeacherRegistrationKomal(Long id, String fullname, String username, String email, String password,
			String qualification) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.qualification = qualification;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	
}
