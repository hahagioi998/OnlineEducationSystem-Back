package com.example.demo.service;

import org.slf4j.Logger;
import static com.example.demo.exception.AppConstants.Email_Exist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.AttemptedTest;
import com.example.demo.entity.OnlineTest;
import com.example.demo.entity.StudentRegistration;
import com.example.demo.exception.EmailAlreadyExistException;
import com.example.demo.exception.StudentException;
import com.example.demo.repository.AttemptedTestRepo;
import com.example.demo.repository.OnlineTestRepo;
import com.example.demo.repository.StudentRegistrationRepo;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.GlobalResources;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	private Logger logger = GlobalResources.getLogger(StudentServiceImpl.class);
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private OnlineTestRepo onlineTestRepo;

	@Autowired
	private AttemptedTestRepo attemptedTestRepo;

	@Autowired
	private StudentRegistrationRepo srepo;

	// register student

	@Override
	public Integer registerStudent(StudentRegistration student) throws StudentException {

		String methodName = "registerStudent() from StudentServiceImpl class";
		logger.info(methodName + "called");
		try {

			Optional<StudentRegistration> studentEx = studentRepository.findByEmailId(student.getStudentEmail());
			if (!studentEx.isPresent()) {
				studentRepository.save(student);
				return 1;
			} else {
				throw new EmailAlreadyExistException(Email_Exist);
			}

		} catch (DataAccessException dataAccessException) {
			throw new StudentException(dataAccessException.getMessage(), dataAccessException);
		} catch (Exception exception) {
			throw new StudentException(exception.getMessage(), exception);
		}
	}

	// Student login
	@Override

	public String getStudent(String username, String password) {
		List<StudentRegistration> tlist = srepo.findAll();
		int j = 0;
		for (int i = 0; i < tlist.size(); i++) {
			j++;
			if (tlist.get(i).getUserName().equals(username) && tlist.get(i).getStudentPassword().equals(password))
				if (!tlist.get(i).getStatus().equals("blocked")) {
					tlist.get(i).setStatus("login");
					srepo.saveAll(tlist);
					return "Login succesfully";

				} else if (tlist.get(i).getStatus().equals("blocked")) {
					return "Blocked by higher Authority can't access site";
				}
		}

		if (j == tlist.size()) {
			return "Username / password Incorrect";
		}
		return "";
	}

	// Get student Details
	public StudentRegistration getDetails(String userName, String studentPassword) {
		String methodName = "getDetails() from StudentServiceImpl class";
		logger.info(methodName + "called");
		StudentRegistration student = srepo.findByUserNameAndStudentPassword(userName, studentPassword);
		return student;
	}

	// Get scheduled tests
	@Override
	public List<OnlineTest> getTestList(Long std, Long rollNo) throws StudentException {
		String methodName = "getTestList() from StudentServiceImpl class";
		logger.info(methodName + "called");
		List<OnlineTest> onlineTestList = onlineTestRepo.findAll();
		List<OnlineTest> scheduledTestList = new ArrayList<OnlineTest>();
		for (OnlineTest onlineTest : onlineTestList) {
			AttemptedTest submittedTest = attemptedTestRepo.findByRollNoAndTestId(rollNo, onlineTest.getTestId());
			if (submittedTest == null) {
				if (onlineTest.getStd() == std) {
					scheduledTestList.add(onlineTest);
				}
			} else {
				continue;
			}
		}
		if (!(scheduledTestList.isEmpty())) {
			return scheduledTestList;
		} else {
			throw new StudentException("You have no pending tests");
		}
	}

	// Get test-history
	@Override
	public List<AttemptedTest> getHistory(Long std, Long rollNo) {
		String methodName = "getHistory() from StudentServiceImpl class";
		logger.info(methodName + "called");
		List<AttemptedTest> allAttemptedTests = attemptedTestRepo.findByStdAndRollNo(std, rollNo);
		return allAttemptedTests;
	}

	// Submit attempted Test
	@Override
	public AttemptedTest addAttemptedTest(AttemptedTest test) {
		String methodName = "addAttemptedTest() from StudentServiceImpl class";
		logger.info(methodName + "called");
		AttemptedTest submittedTest = attemptedTestRepo.saveAndFlush(test);
		return submittedTest;
	}

	// Get test by id
	@Override
	public OnlineTest findOnlineTest(Long testId) {
		String methodName = "findOnlineTest() from StudentServiceImpl class";
		logger.info(methodName + "called");
		return onlineTestRepo.findByTestId(testId);
	}

	// block student
	@Override
	public void getBlockStudent(Long std, long rollno) {
		List<StudentRegistration> slist = srepo.findByStd(std);
		for (int i = 0; i < slist.size(); i++) {
			if (slist.get(i).getRollNo() == rollno) {
				slist.get(i).setStatus("blocked");
				srepo.saveAll(slist);
			}
		}

	}

	// unblock student
	@Override
	public void getUnBlockStudent(Long std, long rollno) {
		List<StudentRegistration> slist = srepo.findByStd(std);
		for (int i = 0; i < slist.size(); i++) {
			if (slist.get(i).getRollNo() == rollno) {
				slist.get(i).setStatus("register");
				srepo.saveAll(slist);
			}
		}

	}

}