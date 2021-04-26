package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.MarkAttendance;

public interface AttendanceService {
	public void saveMoreProduct(Attendance attendance);
	public List<MarkAttendance> getAttendance(Long std,String date,String sub);
	public void changeAttendance(Long std,String date, String sub, int index,String cs);
	public List<Attendance> getAttendanceReport( Long std);
	
}
