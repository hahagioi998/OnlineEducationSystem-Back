package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Standard;
import com.example.demo.entity.Subject;
import com.example.demo.exception.StandardException;
import com.example.demo.exception.SubjectException;
import com.example.demo.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	SubjectRepository subjectRepository;
	
	@Override
	public Subject addSubject(Subject std) throws SubjectException {
		Subject subject = null;
		try {
			subject = subjectRepository.save(std);
			return subject;
		}catch(DataAccessException e) {
			throw new SubjectException(e.getMessage(),e);
		}
		
	}

	@Override
	public Subject getSubjectById(Long id) throws SubjectException {
		Subject subject = null;
		try {
			Optional<Subject> optional = subjectRepository.findById(id);
			if (optional.isPresent()) {
				subject = optional.get();
				return subject;
			} else {
				throw new SubjectException("Invalid Subject Id");
			}
		} catch (DataAccessException e) {
			throw new SubjectException(e.getMessage(), e);
		}
	}

	@Override
	public List<Subject> getAllSubjects() throws SubjectException {
		List<Subject> subList = null;
		try {
			subList = subjectRepository.findAll();
			if (subList.size() != 0) {
				return subList;
			} else {
				throw new SubjectException("No Subjects in the database");
			}
		} catch (DataAccessException e) {
			throw new SubjectException(e.getMessage(), e);
		}
	}

	@Override
	public Subject updateSubject(Subject subject) throws SubjectException {
		try {
			Subject e=subjectRepository.save(subject);
			return e;
		}catch(DataAccessException e) {
			throw new SubjectException(e.getMessage(),e);
		}
	}

}
