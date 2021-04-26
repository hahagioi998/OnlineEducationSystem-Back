
package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.AttemptedTest;
import com.example.demo.entity.ChatBox;
import com.example.demo.entity.OnlineTest;
import com.example.demo.entity.StudentRegistration;
import com.example.demo.exception.StudentException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.AttemptedTestRepo;
import com.example.demo.repository.ChatBoxRepo;
import com.example.demo.repository.StudentRegistrationRepo;
import com.example.demo.service.MailStudentService;
import com.example.demo.service.OnlineTestserviceImpl;
import com.example.demo.service.StudentService;
import com.example.demo.service.StudentServiceImpl;
import com.example.demo.util.GlobalResources;

import org.slf4j.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/student")
public class StudentController {
	private Logger logger = GlobalResources.getLogger(AdminController.class);

	@Autowired
	private StudentRegistrationRepo srepo;

	@Autowired
	private ChatBoxRepo chatBoxRepo;

	@Autowired
	private AttemptedTestRepo attemptedTestRepo;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentServiceImpl ssi;
	@Autowired
	private MailStudentService notificationService;
	@Autowired
	private OnlineTestserviceImpl ots;

	// Get Student Details for Student Dashboard
	@GetMapping("/details/{userName}/{studentPassword}")
	public StudentRegistration getStudentDetails(@PathVariable("userName") String userName,
			@PathVariable("studentPassword") String studentPassword) {
		String methodName = "StudentRegistration() inside StudentController class";
		logger.info(methodName + "called");
		return studentService.getDetails(userName, studentPassword);
	}

	// Get the list of all scheduled-tests for the student
	@GetMapping("/scheduledtest/{std}/{rollNo}")
	public List<OnlineTest> getScheduledTest(@PathVariable("std") Long std, @PathVariable("rollNo") Long rollNo)
			throws StudentException {
		String methodName = "getScheduledTest() inside StudentController class";
		logger.info(methodName + "called");
		return studentService.getTestList(std, rollNo);
	}

	// Get the selected online test
	@GetMapping("/onlinetest/{testId}")
	public OnlineTest getOnlineTest(@PathVariable("testId") long testId) {
		String methodName = "getOnlineTest() inside StudentController class";
		logger.info(methodName + "called");
		return studentService.findOnlineTest(testId);
	}

	// Post the Attempted Test by the student to database
	@PostMapping("/attempttest")
	public ResponseEntity<AttemptedTest> attemptTest(@RequestBody AttemptedTest submittedTest) {
		String methodName = "attemptTest() inside StudentController class";
		logger.info(methodName + "called");
		return new ResponseEntity<AttemptedTest>(studentService.addAttemptedTest(submittedTest), HttpStatus.CREATED);
	}

	// block student
	@GetMapping("/block/{std}/{rollno}")
	public void getBlockStudent(@PathVariable Long std, @PathVariable long rollno) {
		studentService.getBlockStudent(std, rollno);

	}

	// unblock student
	@GetMapping("/unblock/{std}/{rollno}")
	public void getUnBlockStudent(@PathVariable Long std, @PathVariable long rollno) {
		studentService.getUnBlockStudent(std, rollno);
	}

	@PostMapping("/askquery")
	public ResponseEntity<ChatBox> askQuery(@RequestBody ChatBox query) {
		return new ResponseEntity<ChatBox>(chatBoxRepo.saveAndFlush(query), HttpStatus.CREATED);
	}

	// Get all the tests attempted in the past
	@GetMapping("/testhistory/{std}/{rollNo}")
	public List<AttemptedTest> getTestHistory(@PathVariable("std") Long std, @PathVariable("rollNo") Long rollNo) {
		String methodName = "getTestHistory() inside StudentController class";
		logger.info(methodName + "called");
		return studentService.getHistory(std, rollNo);
	}

//	@GetMapping("/teststatus/{rollNo}/{testId}")
//	public String getTestStatus(@PathVariable("rollNo") Long rollNo, @PathVariable("testId") Long testId) {
//		AttemptedTest test = attemptedTestRepo.findByRollNoAndTestId(rollNo, testId);
//		String status = test.getStatus();
//		return status;
//	}
	
	
	
	

	// to get test report at teacher side

	@GetMapping("/gettestreporttoteacher/{std}/{rollNo}")
	public List<AttemptedTest> getAllTestReport(@PathVariable Long std, @PathVariable long rollNo) {
		String methodName = "getAllTestReport() inside StudentController class";
		logger.info(methodName + "called");
		return ots.getAllTestReport(std, rollNo);
	}

	// Student login

	@GetMapping("/login/{username}/{password}")
	public String getStudent(@PathVariable String username, @PathVariable String password) {
		return ssi.getStudent(username, password);
	}

	// student registration
	@PostMapping("/register")
	public String registerStudent(@Valid @RequestBody StudentRegistration student) {
		try {
			Integer stat = studentService.registerStudent(student);
			if (stat == 1) {
				try {
					notificationService.sendEmail(student);
				} catch (MailException mailException) {
					System.out.println(mailException);
				}

				return "student:" + student.getFullName() + " added to database";
			} else {

				return "Unable to add student to database";
			}
		} catch (StudentException studentException) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, studentException.getMessage());
		}
	}

	// get all students whose status is blocked

	@GetMapping("/getallblock")
	public List<StudentRegistration> getAllBlockStudent() {
		return srepo.findByStatus("blocked");
	}

	@GetMapping("/{std}/{rollNo}")
	public ResponseEntity<StudentRegistration> getStudentByStd(@PathVariable Long std, @PathVariable long rollNo) {
		List<StudentRegistration> slist = srepo.findByStd(std);
		int j = 0;
		for (int i = 0; i < slist.size(); i++) {
			j++;
			if (slist.get(i).getRollNo() == rollNo) {
				return ResponseEntity.ok(slist.get(i));
			}

		}

		if (j == slist.size()) {
			throw new StudentNotFoundException("No Student Found with roll no" + " " + rollNo);
		}
		return ResponseEntity.ok(null);

	}

	@GetMapping("/getallstudentfromstandard/{std}")
	public List<StudentRegistration> getAllStudentFromStd(@PathVariable Long std) {
		return srepo.findByStd(std);
	}

}// package com.example.demo.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailException;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.example.demo.entity.AttemptedTest;
//import com.example.demo.entity.ChatBox;
//import com.example.demo.entity.OnlineTest;
//import com.example.demo.entity.StudentRegistration;
//import com.example.demo.exception.StudentException;
//import com.example.demo.exception.StudentNotFoundException;
//import com.example.demo.repository.AttemptedTestRepo;
//import com.example.demo.repository.ChatBoxRepo;
//import com.example.demo.repository.OnlineTestRepo;
//import com.example.demo.repository.StudentRegistrationRepo;
//import com.example.demo.service.MailStudentService;
//import com.example.demo.service.StudentService;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping(value = "/api/student")
//public class StudentController {
//	@Autowired
//	private StudentRegistrationRepo srepo;
//
//	@Autowired
//	private OnlineTestRepo onlineTestRepo;
//
//	@Autowired
//	private ChatBoxRepo chatBoxRepo;
//
//	@Autowired
//	private AttemptedTestRepo attemptedTestRepo;
//
//	@Autowired
//	private StudentService studentService;
//	@Autowired
//	private MailStudentService notificationService;
//
//	@PostMapping("/register")
//	public String registerStudent(@Valid @RequestBody StudentRegistration student) {
//		try {
//			Integer stat = studentService.registerStudent(student);
//			if (stat == 1) {
//				try {
//					notificationService.sendEmail(student);
//				} catch (MailException mailException) {
//					System.out.println(mailException);
//				}
//
//				return "student:" + student.getFullName() + " added to database";
//			} else {
//
//				return "Unable to add student to database";
//			}
//		} catch (StudentException studentException) {
//
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, studentException.getMessage());
//		}
//	}
//
//	@GetMapping("/login/{username}/{password}")
//	public String getStudent(@PathVariable String username, @PathVariable String password) {
//		List<StudentRegistration> tlist = srepo.findAll();
//		int j = 0;
//		for (int i = 0; i < tlist.size(); i++) {
//			j++;
//			if (tlist.get(i).getUserName().equals(username) && tlist.get(i).getStudentPassword().equals(password))
//				if (!tlist.get(i).getStatus().equals("blocked")) {
//					tlist.get(i).setStatus("login");
//					srepo.saveAll(tlist);
//					return "Login succesfully";
//
//				} else if (tlist.get(i).getStatus().equals("blocked")) {
//					return "Blocked by higher Authority can't access site";
//				}
//		}
//
//		if (j == tlist.size()) {
//			return "Username / password Incorrect";
//		}
//		return "";
//	}
//
//	@GetMapping("/getallblock")
//	public List<StudentRegistration> getAllBlockStudent() {
//		return srepo.findByStatus("blocked");
//	}
//
//	@GetMapping("/{std}/{rollNo}")
//	public ResponseEntity<StudentRegistration> getStudentByStd(@PathVariable Long std, @PathVariable long rollNo) {
//		List<StudentRegistration> slist = srepo.findByStd(std);
//		int j = 0;
//		for (int i = 0; i < slist.size(); i++) {
//			j++;
//			if (slist.get(i).getRollNo() == rollNo) {
//				return ResponseEntity.ok(slist.get(i));
//			}
//
//		}
//
//		if (j == slist.size()) {
//			throw new StudentNotFoundException("No Student Found with roll no" + " " + rollNo);
//		}
//		return ResponseEntity.ok(null);
//
//	}
//
//	@GetMapping("/block/{std}/{rollno}")
//	public void getBlockStudent(@PathVariable Long std, @PathVariable long rollno) {
//		List<StudentRegistration> slist = srepo.findByStd(std);
//		for (int i = 0; i < slist.size(); i++) {
//			if (slist.get(i).getRollNo() == rollno) {
//				slist.get(i).setStatus("blocked");
//				srepo.saveAll(slist);
//			}
//		}
//
//	}
//
//	@GetMapping("/unblock/{std}/{rollno}")
//	public void getUnBlockStudent(@PathVariable Long std, @PathVariable long rollno) {
//		List<StudentRegistration> slist = srepo.findByStd(std);
//		for (int i = 0; i < slist.size(); i++) {
//			if (slist.get(i).getRollNo() == rollno) {
//				slist.get(i).setStatus("register");
//				srepo.saveAll(slist);
//			}
//		}
//
//	}
//
//	@GetMapping("/getallstudentfromstandard/{std}")
//	public List<StudentRegistration> getAllStudentFromStd(@PathVariable Long std) {
//		return srepo.findByStd(std);
//	}
//
//	@GetMapping("/details/{userName}/{studentPassword}")
//	public StudentRegistration getStudentDetails(@PathVariable("userName") String userName,
//			@PathVariable("studentPassword") String studentPassword) {
//		StudentRegistration student = srepo.findByUserNameAndStudentPassword(userName, studentPassword);
//		return student;
//	}
//
//	@GetMapping("/scheduledtest/{std}")
//	public List<OnlineTest> getScheduledTest(@PathVariable("std") int std) {
//		List<OnlineTest> onlineTestList = onlineTestRepo.findAll();
//		List<OnlineTest> scheduledTestList = new ArrayList<OnlineTest>();
//		for (OnlineTest onlineTest : onlineTestList) {
//			if (onlineTest.getStd() == std) {
//				scheduledTestList.add(onlineTest);
//			}
//		}
//		return scheduledTestList;
//	}
//
//	@GetMapping("/onlinetest/{testId}")
//	public OnlineTest getOnlineTest(@PathVariable("testId") long testId) {
//		return onlineTestRepo.findByTestId(testId);
//	}
//
//	@PostMapping("/attempttest")
//	public ResponseEntity<AttemptedTest> attemptTest(@RequestBody AttemptedTest submittedTest) {
//		return new ResponseEntity<AttemptedTest>(attemptedTestRepo.saveAndFlush(submittedTest), HttpStatus.CREATED);
//	}
//
//	@PostMapping("/askquery")
//	public ResponseEntity<ChatBox> askQuery(@RequestBody ChatBox query) {
//		return new ResponseEntity<ChatBox>(chatBoxRepo.saveAndFlush(query), HttpStatus.CREATED);
//	}
//
//	
//	
//	
//	@GetMapping("/testhistory/{std}/{rollNo}")
//    public List<AttemptedTest> getTestHistory(@PathVariable("std")Long std, @PathVariable("rollNo") Long rollNo) {
//        List<AttemptedTest> allAttemptedTests = attemptedTestRepo.findByStdAndRollNo(std, rollNo);
//        return allAttemptedTests;
//    }
//
////	@GetMapping("/teststatus/{rollNo}/{testId}")
////	public String getTestStatus(@PathVariable("rollNo") Long rollNo, @PathVariable("testId") Long testId) {
////		AttemptedTest test = attemptedTestRepo.findByRollNoAndTestId(rollNo, testId);
////		String status = test.getStatus();
////		return status;
////	}
//
//	// to get test reportr at teacher side
//
//	@GetMapping("/gettestreporttoteacher/{std}/{rollNo}")
//	public List<AttemptedTest> getAllTestReport(@PathVariable Long std, @PathVariable long rollNo) {
//		List<AttemptedTest> test = attemptedTestRepo.findByStd(std);
//		List<AttemptedTest> ftest = new ArrayList<>();
//		for (int i = 0; i < test.size(); i++) {
//			if (test.get(i).getRollNo() == rollNo) {
//				ftest.add(test.get(i));
//			}
//		}
//
//		return ftest;
//	}
//
//	@GetMapping("/scheduledtest/{std}/{rollNo}")
//	public List<OnlineTest> getScheduledTest(@PathVariable("std") Long std, @PathVariable("rollNo") Long rollNo)
//			throws StudentException {
//		List<OnlineTest> onlineTestList = onlineTestRepo.findAll();
//		List<OnlineTest> scheduledTestList = new ArrayList<OnlineTest>();
//		for (OnlineTest onlineTest : onlineTestList) {
//			AttemptedTest submittedTest = attemptedTestRepo.findByRollNoAndTestId(rollNo, onlineTest.getTestId());
//			if (submittedTest == null) {
//				if (onlineTest.getStd() == std) {
//					scheduledTestList.add(onlineTest);
//				}
//			} else {
//				continue;
//			}
//		}
//		if (!(scheduledTestList.isEmpty())) {
//			return scheduledTestList;
//		} else {
//			throw new StudentException("You have no pending tests");
//		}
//	}
//
//}
