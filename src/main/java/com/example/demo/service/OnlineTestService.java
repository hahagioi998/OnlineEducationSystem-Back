package com.example.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.AttemptedTest;
import com.example.demo.entity.OnlineTest;
import com.example.demo.entity.QuestionDesc;
import com.example.demo.entity.TeacherRegistration;

public interface OnlineTestService {
	public void saveTest( OnlineTest test);
	public Boolean login(String username, String password);
	public TeacherRegistration getTeacher( String username,String password);
	public int countOfUnAttemptedTest(Long std,String subject );
	public int countOfAttemptedTest(Long std,String subject );
	public void deletequestion( long  tid);
	public void deletequestion(long  tid,int  questionNo,int  marks);
	public QuestionDesc updatetest( long  tid, int  questionNo,QuestionDesc desc);
	public QuestionDesc getquestion( long  tid, int  questionNo);
	public List<OnlineTest> getall(long  teacherId);
	public List<QuestionDesc> getTest(long tid);
	public List<AttemptedTest> getAllTestReport(Long std, long rollNo);
}
