package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Standard;
import com.example.demo.exception.StandardException;
import com.example.demo.repository.StandardRepository;


@Service
public class StandardServiceImpl implements StandardService{

	@Autowired
	StandardRepository standardRepository;
	
	@Override
	public Standard addStandard(Standard std) throws StandardException {
		Standard standard = null;
		try {
			standard = standardRepository.save(std);
			return standard;
		}catch(DataAccessException e) {
			throw new StandardException(e.getMessage(),e);
		}
		
	}

	@Override
	public Standard getStandardById(Long id) throws StandardException{
		Standard standard = null;
		try {
			Optional<Standard> optional = standardRepository.findById(id);
			if (optional.isPresent()) {
				standard = optional.get();
				return standard;
			} else {
				throw new StandardException("Invalid standard Id");
			}
		} catch (DataAccessException e) {
			throw new StandardException(e.getMessage(), e);
		}
	}

	@Override
	public List<Standard> getAllStandards()throws StandardException {
		List<Standard> stdList = null;
		try {
			stdList = standardRepository.findAll();
			if (stdList.size() != 0) {
				return stdList;
			} else {
				throw new StandardException("No standards in the database");
			}
		} catch (DataAccessException e) {
			throw new StandardException(e.getMessage(), e);
		}
		
	}

	@Override
	public Standard updateStandard(Standard standard) throws StandardException {
		try {
			Standard e=standardRepository.save(standard);
			return e;
		}catch(DataAccessException e) {
			throw new StandardException(e.getMessage(),e);
		}
		
	}

}
