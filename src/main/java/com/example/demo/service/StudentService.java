package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AttemptedTest;
import com.example.demo.entity.OnlineTest;
import com.example.demo.entity.StudentRegistration;
import com.example.demo.exception.StudentException;

public interface StudentService {
	public Integer registerStudent(StudentRegistration student ) throws StudentException;
	public StudentRegistration getDetails(String userName, String studentPassword);
	public List<OnlineTest> getTestList(Long std, Long rollNo) throws StudentException;
	public List<AttemptedTest> getHistory(Long std, Long rollNo);
	public AttemptedTest addAttemptedTest(AttemptedTest test);
	public OnlineTest findOnlineTest(Long testId);
	public void getBlockStudent(Long std, long rollno);
	public void getUnBlockStudent(Long std, long rollno);
	public String getStudent(String username, String password);
}