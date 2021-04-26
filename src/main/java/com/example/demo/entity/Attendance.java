package com.example.demo.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.convertor.AttendanceConvertor;
import com.example.demo.convertor.QuestionDescConvertor;

@Entity
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long attId;
	Long std;
	String date;
	String subjectName;
	
	
	@Convert(converter = AttendanceConvertor.class)
	@Column(columnDefinition = "json")
	private List<MarkAttendance> attendancesheet=new ArrayList<>();
	
	public Attendance() {
		
		// TODO Auto-generated constructor stub
	}
	public Attendance(Long std, String date, String subjectName) {
		
		this.std = std;
		this.date = date;
		this.subjectName = subjectName;
	}
	
	public Attendance(long attId, Long std, String date, String subjectName, List<MarkAttendance> attendancesheet) {
		
		this.attId = attId;
		this.std = std;
		this.date = date;
		this.subjectName = subjectName;
		
		this.attendancesheet=attendancesheet;
	}
	

	public List<MarkAttendance> getAttendancesheet() {
		return attendancesheet;
	}

	public void setAttendancesheet(List<MarkAttendance> attendancesheet) {
		this.attendancesheet = attendancesheet;
	}

	public long getAttId() {
		return attId;
	}
	public void setAttId(long attId) {
		this.attId = attId;
	}
	
	public Long getStd() {
		return std;
	}

	public void setStd(Long std) {
		this.std = std;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	

}