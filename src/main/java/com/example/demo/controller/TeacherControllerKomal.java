package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.entity.TeacherRegistrationKomal;
import com.example.demo.exception.TeacherException;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.MailService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.TeacherServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/higherAuthority/teacherRegistration")
public class TeacherControllerKomal {

	@Autowired
	TeacherService teacherService;

	@Autowired
	private MailService notificationService;

	@Autowired
	private TeacherRepository trepo;

	@Autowired
	private TeacherServiceImpl tsi;

	// teacher login
	@GetMapping("/login/{username}/{password}")
	public Boolean login(@PathVariable String username, @PathVariable String password) {
		return tsi.login(username, password);

	}

	// edit teacher profile

	@PutMapping("/editprofile/{id}")
	public ResponseEntity<TeacherRegistrationKomal> updateTeacher(@PathVariable Long id,
			@RequestBody TeacherRegistrationKomal teacher) {
		try {
			TeacherRegistrationKomal trepo = teacherService.getTeacherById(id);
			trepo.setFullname(teacher.getFullname());
			trepo.setUsername(teacher.getUsername());
			trepo.setEmail(teacher.getEmail());
			trepo.setPassword(teacher.getPassword());
			trepo.setQualification(teacher.getQualification());
			return new ResponseEntity<>(teacherService.updateTeacher(trepo), HttpStatus.OK);
		} catch (TeacherException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/getbyup/{username}/{password}")
	public TeacherRegistrationKomal getTeacher(@PathVariable String username, @PathVariable String password) {
		List<TeacherRegistrationKomal> tlist = trepo.findAll();
		for (int i = 0; i < tlist.size(); i++) {
			if (tlist.get(i).getUsername().equals(username) && tlist.get(i).getPassword().equals(password)) {
				return tlist.get(i);
			}
		}
		return new TeacherRegistrationKomal();

	}

	@GetMapping("/getbyid/{id}")
	public TeacherRegistrationKomal getTeacherByid(@PathVariable Long id) {
		return trepo.findById(id).get();

	}

	@PostMapping
	public ResponseEntity<TeacherRegistrationKomal> addTeacher(@RequestBody TeacherRegistrationKomal teacher) {
		TeacherRegistrationKomal newTeacher = null;
		try {
			newTeacher = teacherService.addTeacher(teacher);
			notificationService.sendEmail(teacher);
			return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
		} catch (TeacherException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (MailException mailException) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, mailException.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeacherRegistrationKomal> getTeacherById(@PathVariable Long id) {
		TeacherRegistrationKomal teacher = null;
		try {
			teacher = teacherService.getTeacherById(id);
			return new ResponseEntity<>(teacher, HttpStatus.OK);
		} catch (TeacherException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<TeacherRegistrationKomal>> getAllTeachers() {
		List<TeacherRegistrationKomal> teacherList = null;
		try {
			teacherList = teacherService.getAllTeachers();
			return new ResponseEntity<>(teacherList, HttpStatus.OK);
		} catch (TeacherException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
