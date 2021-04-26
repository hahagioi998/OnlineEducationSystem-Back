package com.example.demo.controller;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.MailReportService;
import com.example.demo.entity.OnlineTest;

import com.example.demo.entity.QuestionDesc;
import com.example.demo.entity.StudentRegistration;
import com.example.demo.entity.TeacherRegistration;
import com.example.demo.exception.StudentException;
import com.example.demo.repository.OnlineTestRepo;
import com.example.demo.repository.TeacherRegistrationRepo;
import com.example.demo.service.MailNewReportService;
import com.example.demo.service.OnlineTestserviceImpl;
import com.example.demo.util.GlobalResources;

import org.slf4j.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {
	private Logger logger = GlobalResources.getLogger(TeacherController.class);
//create test
	@Autowired
	private OnlineTestRepo orepo;
	@Autowired
	private OnlineTestserviceImpl os;

	@PostMapping("/save")
	public void saveTest(@RequestBody OnlineTest test) {

		String methodName = "saveTest() inside TeacherController";
		logger.info(methodName + "called");
		os.saveTest(test);

	}

	// Login check code
	@Autowired
	private TeacherRegistrationRepo trepo;

	@GetMapping("/login/{username}/{password}")
	public Boolean login(@PathVariable String username, @PathVariable String password) {
		String methodName = "login() inside TeacherController";
		logger.info(methodName + "called");
		return os.login(username, password);

	}

//get teacher info from registration table
	@GetMapping("/getbyup/{username}/{password}")
	public TeacherRegistration getTeacher(@PathVariable String username, @PathVariable String password) {
		String methodName = " getTeacher() inside TeacherController";
		logger.info(methodName + "called");
		return os.getTeacher(username, password);

	}

	// get teacher by id

	@GetMapping("/get/{tid}")
	public TeacherRegistration getTeacherByid(@PathVariable long tid) {
		String methodName = " getTeacherByid() inside TeacherController";
		logger.info(methodName + "called");
		return trepo.findById(tid).get();

	}

	// all questions inside particular test

	@GetMapping("/gettest/{tid}")
	public List<QuestionDesc> getTest(@PathVariable long tid) {
		String methodName = " getTest() inside TeacherController";
		logger.info(methodName + "called");
		return os.getTest(tid);
	}

	// get all tests created by particular teacher

	@GetMapping("/all/{teacherId}")
	public List<OnlineTest> getall(@PathVariable long teacherId) {
		String methodName = " getall() inside TeacherController";
		logger.info(methodName + "called");
		return os.getall(teacherId);

	}

	// get particular question from test

	@GetMapping("/getquestion/{tid}/{questionNo}")
	public QuestionDesc getquestion(@PathVariable long tid, @PathVariable int questionNo) {
		String methodName = " getquestion() inside TeacherController";
		logger.info(methodName + "called");
		return os.getquestion(tid, questionNo);
	}

	// edit questions inside test

	@PutMapping("/edittest/{tid}/{questionNo}")
	public QuestionDesc updatetest(@PathVariable long tid, @PathVariable int questionNo,
			@RequestBody QuestionDesc desc) {

		String methodName = " updatetest() inside TeacherController";
		logger.info(methodName + "called");
		return os.updatetest(tid, questionNo, desc);
	}

	// after deleting question updation in mark
	@GetMapping("/deletequestion/{tid}/{questionNo}/{marks}")
	public void deletequestion(@PathVariable long tid, @PathVariable int questionNo, @PathVariable int marks) {
		String methodName = " deletequestion() inside TeacherController";
		logger.info(methodName + "called");
		os.deletequestion(tid, questionNo, marks);

	}

	// after deleting particular question setting question no
	@GetMapping("/initial/{tid}")
	public void deletequestion(@PathVariable long tid) {
		os.deletequestion(tid);
	}

	// get test with non-attempted status
	@GetMapping("/countoftest/{std}/{subject}")
	public int countOfAttemptedTest(@PathVariable Long std, @PathVariable String subject) {
		return os.countOfAttemptedTest(std, subject);

	}

	// get test with attempted status
	@GetMapping("/countofutest/{std}/{subject}")
	public int countOfUnAttemptedTest(@PathVariable Long std, @PathVariable String subject) {
		return os.countOfUnAttemptedTest(std, subject);

	}

	// send mail of report
	@Autowired
	MailNewReportService reportmail;

	@PostMapping("/reportbymail")
	public void sendMailReport(@RequestBody MailReportService report) {
		String methodName = " sendMailReport() inside TeacherController";
		logger.info(methodName + "called");
		try {
			reportmail.sendEmail(report);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}

	}

}
