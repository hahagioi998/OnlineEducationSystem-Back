package com.example.demo.service;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.AttemptedTest;
import com.example.demo.entity.OnlineTest;
import com.example.demo.entity.QuestionDesc;
import com.example.demo.entity.TeacherRegistration;
import com.example.demo.repository.AttemptedTestRepo;
import com.example.demo.repository.OnlineTestRepo;
import com.example.demo.repository.TeacherRegistrationRepo;
import com.example.demo.util.GlobalResources;

@Service
public class OnlineTestserviceImpl implements OnlineTestService {
	private Logger logger = GlobalResources.getLogger(OnlineTestserviceImpl.class);
	@Autowired
	private OnlineTestRepo orepo;
	@Autowired
	private TeacherRegistrationRepo trepo;

	@Autowired
	private AttemptedTestRepo attemptedTestRepo;

	@Override
	// save test with particular test id
	public void saveTest(OnlineTest test) {
		String methodName = "saveTest() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		orepo.save(test);
	}

	@Override
	// teacher login

	public Boolean login(String username, String password) {
		String methodName = "login() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		List<TeacherRegistration> tlist = trepo.findAll();
		for (int i = 0; i < tlist.size(); i++) {
			if (tlist.get(i).getUsername().equals(username) && tlist.get(i).getPassword().equals(password)) {
				return true;
			}
		}
		return false;

	}

	@Override

	// get teacher info by username and pass

	public TeacherRegistration getTeacher(String username, String password) {
		String methodName = "getTeacher() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		List<TeacherRegistration> tlist = trepo.findAll();
		for (int i = 0; i < tlist.size(); i++) {
			if (tlist.get(i).getUsername().equals(username) && tlist.get(i).getPassword().equals(password)) {
				return tlist.get(i);
			}
		}
		return new TeacherRegistration();

	}

	// get test with attempted status
	@Override
	public int countOfUnAttemptedTest(Long std, String subject) {
		String methodName = "countOfUnAttemptedTest() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		List<OnlineTest> o = orepo.findByStd(std);
		int j = 0;

		for (int i = 0; i < o.size(); i++) {
			if (o.get(i).getSubject().equals(subject)) {
				if (o.get(i).getStatus().equals("attempted")) {
					j++;
				}
			}
		}
		return j;

	}

	// get test with non-attempted status
	@Override
	public int countOfAttemptedTest(Long std, String subject) {
		List<OnlineTest> o = orepo.findByStd(std);
		int j = 0;

		for (int i = 0; i < o.size(); i++) {
			if (o.get(i).getSubject().equals(subject)) {
				if (o.get(i).getStatus().equals("not attempted")) {
					j++;
				}
			}
		}
		return j;

	}

	// after deleting particular question setting question no
	@Override
	public void deletequestion(long tid) {
		String methodName = "deletequestion() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		OnlineTest o = orepo.findById(tid).get();
		int j = 1;
		for (int i = 0; i < o.getQuestions().size(); i++) {
			o.getQuestions().get(i).setQuestionNo(j);
			j++;
			orepo.save(o);
		}

	}

	// after deleting question updation in mark
	@Override
	public void deletequestion(long tid, int questionNo, int marks) {
		OnlineTest o = orepo.findById(tid).get();
		o.setTotalMarks(o.getTotalMarks() - marks);
		o.setTotalQuestions(o.getTotalQuestions() - 1);
		List<QuestionDesc> flist = o.getQuestions();
		for (int i = 0; i < flist.size(); i++) {
			if (flist.get(i).getQuestionNo() == questionNo) {
				flist.remove(i);
				orepo.save(o);

			}
		}

	}

	// edit questions inside test

	@Override
	public QuestionDesc updatetest(long tid, int questionNo, QuestionDesc desc) {
		String methodName = "updatetest() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		OnlineTest o = orepo.findById(tid).get();

		List<QuestionDesc> flist = o.getQuestions();
		for (int i = 0; i < flist.size(); i++) {
			if (flist.get(i).getQuestionNo() == questionNo) {
				int t = o.getTotalMarks() - flist.get(i).getMarks();
				o.setTotalMarks(t + desc.getMarks());
				flist.get(i).setAnswer(desc.getAnswer());
				flist.get(i).setOptionA(desc.getOptionA());
				flist.get(i).setOptionB(desc.getOptionB());
				flist.get(i).setOptionC(desc.getOptionC());
				flist.get(i).setOptionD(desc.getOptionD());
				flist.get(i).setQuestion(desc.getQuestion());
				flist.get(i).setQuestionNo(desc.getQuestionNo());
				flist.get(i).setMarks(desc.getMarks());
				orepo.save(o);
				return flist.get(i);

			}
		}
		return new QuestionDesc();
	}

	// get particular question from test
	@Override
	public QuestionDesc getquestion(long tid, int questionNo) {
		String methodName = "getquestion() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		OnlineTest o = orepo.findById(tid).get();
		List<QuestionDesc> flist = o.getQuestions();
		for (int i = 0; i < flist.size(); i++) {
			if (flist.get(i).getQuestionNo() == questionNo) {
				return flist.get(i);
			}
		}
		return new QuestionDesc();
	}

	// get all tests created by particular teacher

	@Override
	public List<OnlineTest> getall(long teacherId) {
		String methodName = "getall() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		return orepo.findByTeacherId(teacherId);

	}

	// all questions inside particular test

	@Override
	public List<QuestionDesc> getTest(long tid) {
		String methodName = "getTest() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		OnlineTest test = orepo.findById(tid).get();
		return test.getQuestions();
	}

	// to get test report at teacher side

	@Override
	public List<AttemptedTest> getAllTestReport(Long std, long rollNo) {
		String methodName = "getAllTestReport() inside OnlineTestServiceImpl class";
		logger.info(methodName + "called");
		List<AttemptedTest> test = attemptedTestRepo.findByStd(std);
		List<AttemptedTest> ftest = new ArrayList<>();
		for (int i = 0; i < test.size(); i++) {
			if (test.get(i).getRollNo() == rollNo) {
				ftest.add(test.get(i));
			}
		}

		return ftest;
	}

}
