package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ChatBox {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Long std;
	private String subject;
	private Long rollNo;
	private Long teacher_id;
	private String question;
	private String answer;
	
	public ChatBox() {
		super();
	}

	public ChatBox(Integer id, Long std, String subject, Long rollNo, Long teacher_id, String question, String answer) {
		super();
		this.id = id;
		this.std = std;
		this.subject = subject;
		this.rollNo = rollNo;
		this.teacher_id = teacher_id;
		this.question = question;
		this.answer = answer;
	}

	public ChatBox(Long std, String subject, Long rollNo, Long teacher_id, String question, String answer) {
		super();
		this.std = std;
		this.subject = subject;
		this.rollNo = rollNo;
		this.teacher_id = teacher_id;
		this.question = question;
		this.answer = answer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getStd() {
		return std;
	}

	public void setStd(Long std) {
		this.std = std;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getRollNo() {
		return rollNo;
	}

	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}

	public Long getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "ChatBox [id=" + id + ", std=" + std + ", subject=" + subject + ", rollNo=" + rollNo + ", teacher_id="
				+ teacher_id + ", question=" + question + ", answer=" + answer + "]";
	}
}
