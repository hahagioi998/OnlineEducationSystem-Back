package com.example.demo.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="admin")
@Entity
public class Admin
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminid")
	private Integer adminId;
	
	@NotNull
	@NotBlank(message="Please enter your name")
	@Size(min=2, max=30,message="Last name should have atleast 2 characters")
    @Column(name = "adminname")
	private String adminName;
	
	@NotNull
	@NotBlank(message="Please enter your username")
	@Size(min=2, max=30,message="Last name should have atleast 2 characters")
    @Column(name = "adminusername")
	private String adminuserName;
	
	@NotNull
	@NotBlank(message="Please enter your email")
    @Email(message = "Email should be valid")
	@Pattern(regexp="[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+")
    @Column(name = "admin_email")
	private String adminEmail;
	
	
	@NotNull
	@NotBlank(message="Please enter password")
    @Column(name = "admin_password")
	private String adminPassword;


	public Integer getAdminId() {
		return adminId;
	}


	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	public String getAdminuserName() {
		return adminuserName;
	}


	public void setAdminuserName(String adminuserName) {
		this.adminuserName = adminuserName;
	}


	public String getAdminEmail() {
		return adminEmail;
	}


	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}


	public String getAdminPassword() {
		return adminPassword;
	}


	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


	public Admin(Integer adminId,
			@NotNull @NotBlank(message = "Please enter your name") @Size(min = 2, max = 30, message = "Last name should have atleast 2 characters") String adminName,
			@NotNull @NotBlank(message = "Please enter your username") @Size(min = 2, max = 30, message = "Last name should have atleast 2 characters") String adminuserName,
			@NotNull @NotBlank(message = "Please enter your email") @Email(message = "Email should be valid") @Pattern(regexp = "[A-Za-z]+[0-9]*@[a-zA-Z]+.[a-zA-A]+") String adminEmail,
			@NotNull @NotBlank(message = "Please enter password") String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminuserName = adminuserName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminuserName=" + adminuserName
				+ ", adminEmail=" + adminEmail + ", adminPassword=" + adminPassword + "]";
	}
	
	public Admin() {
		
	}
  

}