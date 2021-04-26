package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Standard;
import com.example.demo.exception.StandardException;

public interface StandardService {
	public Standard addStandard(Standard std) throws StandardException;
	public Standard getStandardById(Long id)throws StandardException;
	public List<Standard> getAllStandards()throws StandardException;
	public Standard updateStandard(Standard standard) throws StandardException;
}
