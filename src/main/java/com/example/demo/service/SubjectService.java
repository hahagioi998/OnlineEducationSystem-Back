package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Subject;
import com.example.demo.exception.SubjectException;

public interface SubjectService {
	public Subject addSubject(Subject std) throws SubjectException;
	public Subject getSubjectById(Long id)throws SubjectException;
	public List<Subject> getAllSubjects()throws SubjectException;
	public Subject updateSubject(Subject subject) throws SubjectException;
}
