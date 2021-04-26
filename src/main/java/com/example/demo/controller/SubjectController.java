package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Standard;
import com.example.demo.entity.Subject;
import com.example.demo.exception.StandardException;
import com.example.demo.exception.SubjectException;
import com.example.demo.service.SubjectService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/higherAuthority/subject")

public class SubjectController {
	
	@Autowired
	SubjectService subjectService;
	
	@PostMapping
	public ResponseEntity<Subject> addSubject(@RequestBody Subject sub) {
		Subject newSubject = null;
		try {
			newSubject = subjectService.addSubject(sub);
			return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
		} catch (SubjectException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
		Subject sub = null;
		try {
			sub = subjectService.getSubjectById(id);
			return new ResponseEntity<>(sub, HttpStatus.OK);
		} catch (SubjectException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Subject>> getAllSubjects() {
		List<Subject> subList = null;
		try {
			subList = subjectService.getAllSubjects();
			return new ResponseEntity<>(subList, HttpStatus.OK);
		} catch (SubjectException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject subject){
		try {
			Subject e= subjectService.getSubjectById(id);
			e.setSubName(subject.getSubName());
			return new ResponseEntity<>(subjectService.updateSubject(e),HttpStatus.OK);
		}catch(SubjectException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}
