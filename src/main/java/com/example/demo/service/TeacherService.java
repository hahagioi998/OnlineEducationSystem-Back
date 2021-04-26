package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TeacherRegistrationKomal;
import com.example.demo.exception.TeacherException;

public interface TeacherService {
	public TeacherRegistrationKomal addTeacher(TeacherRegistrationKomal std) throws TeacherException;
	public TeacherRegistrationKomal getTeacherById(Long id)throws TeacherException;
	public List<TeacherRegistrationKomal> getAllTeachers()throws TeacherException;
	public TeacherRegistrationKomal updateTeacher(TeacherRegistrationKomal teacher) throws TeacherException;
	public Boolean login(String username, String password);
}
