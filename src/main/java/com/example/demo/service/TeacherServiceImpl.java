package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Subject;
import com.example.demo.entity.TeacherRegistrationKomal;
import com.example.demo.exception.SubjectException;
import com.example.demo.exception.TeacherException;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.util.GlobalResources;

import org.slf4j.Logger;

@Service
public class TeacherServiceImpl implements TeacherService {

	private Logger logger = GlobalResources.getLogger(TeacherServiceImpl.class);
	@Autowired
	TeacherRepository teacherRepository;
	@Autowired
	private TeacherRepository trepo;

	// Teacher Registation
	@Override
	public TeacherRegistrationKomal addTeacher(TeacherRegistrationKomal teach) throws TeacherException {
		String methodName = "addTeacher() from TeacherServiceImpl class";
		logger.info(methodName + "called");
		TeacherRegistrationKomal teacher = null;
		try {
			teacher = teacherRepository.save(teach);

			return teacher;
		} catch (DataAccessException e) {
			throw new TeacherException(e.getMessage(), e);
		}
	}

	// Teacher login
	@Override
	public Boolean login(String username, String password) {
		List<TeacherRegistrationKomal> tlist = trepo.findAll();
		for (int i = 0; i < tlist.size(); i++) {
			if (tlist.get(i).getUsername().equals(username) && tlist.get(i).getPassword().equals(password)) {
				return true;
			}
		}
		return false;

	}

//get teacher by id
	@Override
	public TeacherRegistrationKomal getTeacherById(Long id) throws TeacherException {
		String methodName = "getTeacherById() from TeacherServiceImpl class";
		logger.info(methodName + "called");
		TeacherRegistrationKomal teacher = null;
		try {
			Optional<TeacherRegistrationKomal> optional = teacherRepository.findById(id);
			if (optional.isPresent()) {
				teacher = optional.get();
				return teacher;
			} else {
				throw new TeacherException("Invalid Teacher Id");
			}
		} catch (DataAccessException e) {
			throw new TeacherException(e.getMessage(), e);
		}
	}

	@Override
	public List<TeacherRegistrationKomal> getAllTeachers() throws TeacherException {
		String methodName = "getAllTeachers() from TeacherServiceImpl class";
		logger.info(methodName + "called");
		List<TeacherRegistrationKomal> teacherList = null;
		try {
			teacherList = teacherRepository.findAll();
			if (teacherList.size() != 0) {
				return teacherList;
			} else {
				throw new TeacherException("No Teachers in the database");
			}
		} catch (DataAccessException e) {
			throw new TeacherException(e.getMessage(), e);
		}
	}

	
	// Update teacher profile

	@Override
	public TeacherRegistrationKomal updateTeacher(TeacherRegistrationKomal teacher) throws TeacherException {
		String methodName = "updateTeacher() from TeacherServiceImpl class";
		logger.info(methodName + "called");
		try {
			TeacherRegistrationKomal trepo = teacherRepository.save(teacher);
			return trepo;
		} catch (DataAccessException e) {
			throw new TeacherException(e.getMessage(), e);
		}
	}

}
