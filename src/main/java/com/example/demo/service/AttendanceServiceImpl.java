package com.example.demo.service;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.MarkAttendance;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.util.GlobalResources;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	private Logger logger = GlobalResources.getLogger(AttendanceServiceImpl.class);
	@Autowired
	private AttendanceRepository arepo;

	// save attendance
	@Override

	public void saveMoreProduct(Attendance attendance) {

		String methodName = "saveMoreProduct() from AttendanceServiceImpl";
		logger.info(methodName + "called");
		System.out.println(attendance);
		arepo.save(attendance);
	}

	// get attendance for particular std, date and subject

	@Override
	public List<MarkAttendance> getAttendance(Long std, String date, String sub) {
		String methodName = "getAttendance() from AttendanceServiceImpl";
		logger.info(methodName + "called");
		List<Attendance> alist = arepo.findByStd(std);
		for (int i = 0; i < alist.size(); i++) {
			if (alist.get(i).getSubjectName().equals(sub) && alist.get(i).getDate().equals(date)) {
				return alist.get(i).getAttendancesheet();
			}
		}
		return new ArrayList<>();
	}

	// modify status in attendance
	@Override
	public void changeAttendance(Long std, String date, String sub, int index, String cs) {
		String methodName = "changeAttendance() from AttendanceServiceImpl";
		logger.info(methodName + "called");

		List<Attendance> alist = arepo.findByStd(std);

		for (int i = 0; i < alist.size(); i++) {
			if (alist.get(i).getSubjectName().equals(sub) && alist.get(i).getDate().equals(date)) {
				alist.get(i).getAttendancesheet().get(index).setStatus(cs);
				arepo.saveAll(alist);
				break;
			}
		}

	}

	// get report of attendance
	@Override
	public List<Attendance> getAttendanceReport(Long std) {
		String methodName = "getAttendanceReport() from AttendanceServiceImpl";
		logger.info(methodName + "called");
		return arepo.findByStd(std);

	}

}
