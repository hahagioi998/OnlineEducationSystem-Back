package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.ScheduleLecture;
import com.example.demo.entity.TimeTable;
import com.example.demo.repository.TimeTableRepository;


public interface TimeTableService {
	public void saveMoreProduct(TimeTable t,Long std,String date);
	public List<ScheduleLecture> getSchedule(Long std, String date);
	public List<ScheduleLecture> getLectures(Long id,String date);
		
}
