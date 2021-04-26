package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Standard;
import com.example.demo.entity.StandardSubjectDO;
import com.example.demo.entity.StandardSubjects;
import com.example.demo.entity.Subject;
import com.example.demo.entity.TeacherRegistrationKomal;
import com.example.demo.exception.StandardSubjectException;
import com.example.demo.repository.StandardRepository;
import com.example.demo.repository.StandardSubjectsRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class StandardSubjectsServiceImpl implements StandardSubjectsService{

	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	StandardRepository standardRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	StandardSubjectsRepository standardSubjectsRepository; 
	
	@Override
	public StandardSubjects assignSubjectsToStandard(StandardSubjectDO standardSubjectDo) throws StandardSubjectException{
		Standard standard = null;
		Subject subject = null;
		TeacherRegistrationKomal teacher = null;
		
		StandardSubjects stdSub = new StandardSubjects(standardSubjectDo);
		
		try {
		standard = standardRepository.getOne(standardSubjectDo.getStd_id());
		subject = subjectRepository.getOne(standardSubjectDo.getSub_id());
		teacher = teacherRepository.getOne(standardSubjectDo.getTeacher_id());
		
		stdSub.setStandard(standard);
		stdSub.setSubject(subject);
		stdSub.setTeacher(teacher);
		
		stdSub = standardSubjectsRepository.save(stdSub);
		return stdSub;
		}catch (DataAccessException e) {
			throw new StandardSubjectException(e.getMessage(), e);
		} catch (Exception e) {
			throw new StandardSubjectException(e.getMessage(), e);
		}
	}
	@Override
	public List<StandardSubjects> getAllRows() throws StandardSubjectException {
		List<StandardSubjects> stdList = null;
		try {
			stdList = standardSubjectsRepository.findAll();
			if (stdList.size() != 0) {
				return stdList;
			} else {
				throw new StandardSubjectException("No rows in the database");
			}
		} catch (DataAccessException e) {
			throw new StandardSubjectException(e.getMessage(), e);
		}
	}
	@Override
	public List<StandardSubjects> findAllByTeacherId(Long id) throws StandardSubjectException {
		List<StandardSubjects> stdList = null;
		try {
			stdList = standardSubjectsRepository.findAllByTeacherId(id);
			if (stdList.size() != 0) {
				return stdList;
			} else {
				throw new StandardSubjectException("No rows in the database with given teacher id");
			}
		}catch (DataAccessException e) {
			throw new StandardSubjectException(e.getMessage(), e);
		}
		
	}
	@Override
	public List<StandardSubjects> findAllByStandardId(Long id) throws StandardSubjectException {
		List<StandardSubjects> stdList = null;
		try {
			stdList = standardSubjectsRepository.findAllByStandardId(id);
			
				return stdList;
			
		}catch (DataAccessException e) {
			throw new StandardSubjectException(e.getMessage(), e);
		}
		
	}
	@Override
	public Map<String, Boolean> deleteById(Long id) throws StandardSubjectException {
		try {
			StandardSubjects stdSub=standardSubjectsRepository.findById(id).get();								
			standardSubjectsRepository.delete(stdSub);
			Map<String,Boolean> statusMap= new HashMap<>();
			statusMap.put("Row deleted", Boolean.TRUE);
			return statusMap;
		}catch(DataAccessException e) {
			throw new StandardSubjectException(e.getMessage(),e);
		}
	}

}
