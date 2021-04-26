package com.example.demo.controller;

import org.slf4j.Logger;
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

import com.example.demo.entity.Attendance;
import com.example.demo.entity.MarkAttendance;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.service.AttendanceServiceImpl;
import com.example.demo.util.GlobalResources;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/attendance")
public class AttendanceController {
	private Logger logger = GlobalResources.getLogger(AttendanceController.class);
	@Autowired
	private AttendanceRepository arepo;
	@Autowired
	private AttendanceServiceImpl asi;
	// save attendance

	@PostMapping("/savep")
	public void saveMoreProduct(@RequestBody Attendance attendance) {

		String methodName = "saveMoreProduct() from AttendanceController";
		logger.info(methodName + "called");
		asi.saveMoreProduct(attendance);
	}

	@GetMapping("/getall")
	List<Attendance> getAll() {
		String methodName = "getAll() from AttendanceController";
		logger.info(methodName + "called");
		return arepo.findAll();
	}

	// get attendance for particular std, date and subject

	@GetMapping("/getatten/{std}/{date}/{sub}")
	List<MarkAttendance> getAttendance(@PathVariable Long std, @PathVariable String date, @PathVariable String sub) {
		String methodName = "getAttendance() from AttendanceController";
		logger.info(methodName + "called");
		return asi.getAttendance(std, date, sub);
	}

	// modify status in attendance

	@GetMapping("/modifystatus/{std}/{date}/{sub}/{index}/{cs}")
	void changeAttendance(@PathVariable Long std, @PathVariable String date, @PathVariable String sub,
			@PathVariable int index, @PathVariable String cs) {
		String methodName = "changeAttendance() from AttendanceController";
		logger.info(methodName + "called");
		asi.changeAttendance(std, date, sub, index, cs);

	}

	// get report of attendance

	@GetMapping("/getreport/{std}")
	List<Attendance> getAttendanceReport(@PathVariable Long std) {
		String methodName = "getAttendanceReport() from AttendanceController";
		logger.info(methodName + "called");
		return asi.getAttendanceReport(std);

	}

}
