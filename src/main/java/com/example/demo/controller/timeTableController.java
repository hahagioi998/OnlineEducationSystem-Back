package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ScheduleLecture;
import com.example.demo.entity.TimeTable;
import com.example.demo.repository.TimeTableRepository;
import com.example.demo.service.TimeTableServiceImpl;
import com.example.demo.util.GlobalResources;

import org.slf4j.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/schedule")
public class timeTableController {

	private Logger logger = GlobalResources.getLogger(timeTableController.class);

	@Autowired
	private TimeTableRepository trepo;
	@Autowired
	private TimeTableServiceImpl ts;

//save timetable entries

	@PostMapping("/savep/{std}/{date}")
	public void saveMoreProduct(@RequestBody TimeTable t, @PathVariable Long std, @PathVariable String date) {
		String methodName = "saveMoreProduct() inside TimeTableController";
		logger.info(methodName + "called");
		ts.saveMoreProduct(t, std, date);
	}

	// get timetable with particular date and std

	@GetMapping("/get/{std}/{date}")
	public List<ScheduleLecture> getSchedule(@PathVariable Long std, @PathVariable String date) {
		String methodName = "getSchedule() inside TimeTableController";
		logger.info(methodName + "called");
		return ts.getSchedule(std, date);
	}

	// getting lectures for each teacher

	@GetMapping("/getbyteacherid/{id}/{date}")
	public List<ScheduleLecture> getLectures(@PathVariable Long id, @PathVariable String date) {
		String methodName = "getLectures() inside TimeTableController";
		logger.info(methodName + "called");
		return ts.getLectures(id, date);

	}
}