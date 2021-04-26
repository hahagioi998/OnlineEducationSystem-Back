package com.example.demo.entity;

public class MailReportService {
	private String email;
	private String subject;
private String message;
public MailReportService(String email, String subject, String message) {
	
	this.email = email;
	this.subject = subject;
	this.message = message;
}
public MailReportService() {
	
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}


}
