package com.example.demo.entity;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.demo.convertor.AnswerSheetConvertor;

@Entity
public class AttemptedTest {
	
	@Id
	@GeneratedValue
	private Integer id;
	private Long testId;
	private Long std;
	private String subject;
	private Long rollNo;
	private int totalScore;
	@Convert(converter = AnswerSheetConvertor.class)
	@Column(columnDefinition = "json")
	private List<AnswerSheet> answerSheet = new ArrayList<AnswerSheet>();
	
	private Integer score;
	private String status;
	
	public AttemptedTest() {
		super();
	}

	public AttemptedTest(Integer id, Long testId, Long std, String subject, Long rollNo,
			List<AnswerSheet> answerSheet, Integer score, String status,int totalScore) {
		super();
		this.totalScore=totalScore;
		this.id = id;
		this.testId = testId;
		this.std = std;
		this.subject = subject;
		this.rollNo = rollNo;
		this.answerSheet = answerSheet;
		this.score = score;
		this.status = status;
	}

	public AttemptedTest(Long testId, Long std, String subject, Long rollNo, List<AnswerSheet> answerSheet,
			Integer score, String status) {
		super();
		this.testId = testId;
		this.std = std;
		this.subject = subject;
		this.rollNo = rollNo;
		this.answerSheet = answerSheet;
		this.score = score;
		this.status = status;
	}

	
	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
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

	public List<AnswerSheet> getAnswerSheet() {
		return answerSheet;
	}

	public void setAnswerSheet(List<AnswerSheet> answerSheet) {
		this.answerSheet = answerSheet;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "AttemptedTest [id=" + id + ", testId=" + testId + ", std=" + std + ", subject=" + subject + ", rollNo="
				+ rollNo + ", answerSheet=" + answerSheet + ", score=" + score + ", status= "+status+"]";
	}
		
}
