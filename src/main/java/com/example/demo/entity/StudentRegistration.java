package com.example.demo.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity

@Table(name="student_registration")
public class StudentRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer Id;
	
	@NotNull
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=30,message="Last name should have atleast 2 characters")
    @Column(name = "fullname")
	private String fullName;
	
	@NotNull
	@NotBlank(message="Please enter your username")
	@Size(min=2, max=30,message="Last name should have atleast 2 characters")
    @Column(name = "username")
	private String userName;
	
	@NotNull
	@NotBlank(message="Please enter your email")
    @Email(message = "Email should be valid")
	@Pattern(regexp="[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+")
    @Column(name = "student_email")
	private String studentEmail;
	
	@NotNull
	@NotBlank(message="Please enter parents email")
    @Email(message = "Email should be valid")
	@Pattern(regexp="[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+")
    @Column(name = "parent_email")
	private String parentEmail;
	
	@NotNull
	@NotBlank(message="Please enter parents phone number")
	@Pattern(regexp="([7-9][0-9]{9})")
    @Column(name = "parent_mobile")
	private String parentMobile;
	
	@NotNull
	@NotBlank(message="Please enter password")
    @Column(name = "student_password")
	private String studentPassword;
	
	@Column(name = "status")
	private String status;
	
	private long rollNo;
	
	private Long std;
	
	public StudentRegistration(Integer id,
			@NotNull @NotBlank(message = "Please enter your name") @Size(min = 2, max = 30, message = "Last name should have atleast 2 characters") String fullName,
			@NotNull @NotBlank(message = "Please enter your username") @Size(min = 2, max = 30, message = "Last name should have atleast 2 characters") String userName,
			@NotNull @NotBlank(message = "Please enter your email") @Email(message = "Email should be valid") @Pattern(regexp = "[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+") String studentEmail,
			@NotNull @NotBlank(message = "Please enter parents email") @Email(message = "Email should be valid") @Pattern(regexp = "[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+") String parentEmail,
			@NotNull @NotBlank(message = "Please enter parents phone number") @Pattern(regexp = "([7-9][0-9]{9})") String parentMobile,
			@NotNull @NotBlank(message = "Please enter password") String studentPassword, String status, long rollNo,
			Long std) {
	
		Id = id;
		this.fullName = fullName;
		this.userName = userName;
		this.studentEmail = studentEmail;
		this.parentEmail = parentEmail;
		this.parentMobile = parentMobile;
		this.studentPassword = studentPassword;
		this.status = status;
		this.rollNo = rollNo;
		this.std = std;
	}
	public StudentRegistration() {
	
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public String getParentMobile() {
		return parentMobile;
	}
	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getRollNo() {
		return rollNo;
	}
	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
	}
	public Long getStd() {
		return std;
	}
	public void setStd(Long std) {
		this.std = std;
	}
	
	
   


}