package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.ScheduleLecture;
import com.example.demo.entity.TimeTable;
import com.example.demo.repository.TimeTableRepository;
import com.example.demo.util.GlobalResources;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	private Logger logger = GlobalResources.getLogger(TimeTableServiceImpl.class);
	@Autowired
	private TimeTableRepository trepo;

	// save timetable entries
	@Override

	public void saveMoreProduct(TimeTable t, Long std, String date) {
		String methodName = "saveMoreProduct() methode in TimeTableServiceImpl class";
		logger.info(methodName + "called");

		List<TimeTable> tlist = trepo.findByStd(std);
		List<TimeTable> t2list = trepo.findByDate(date);

		int k = 0;
		if (tlist.size() == 0) {
			trepo.save(t);
		} else if (t2list.size() == 0) {
			trepo.save(t);
		} else {
			for (int i = 0; i < tlist.size(); i++) {
				k++;
				if (tlist.get(i).getDate().equals(date)) {
					List<ScheduleLecture> slist = tlist.get(i).getLectures();
					List<ScheduleLecture> flist = new ArrayList<>();
					flist.addAll(slist);
					flist.add(t.getLectures().get(0));
					tlist.get(i).setLectures(flist);
					trepo.saveAll(tlist);
					break;
				}
			}
		}

	}

	// get timetable with particular date and std

	@Override
	public List<ScheduleLecture> getSchedule(Long std, String date) {
		String methodName = "getSchedule() methode in TimeTableServiceImpl class";
		logger.info(methodName + "called");
		// return trepo.findByStd(std);
		List<TimeTable> tlist = trepo.findByStd(std);
		List<ScheduleLecture> sc = new ArrayList<>();
		for (int i = 0; i < tlist.size(); i++) {

			if (tlist.get(i).getDate().equals(date)) {
				return tlist.get(i).getLectures();
			}
		}
		return sc;
	}

	// getting lectures for each teacher

	@Override
	public List<ScheduleLecture> getLectures(Long id, String date) {
		String methodName = "getLectures() methode in TimeTableServiceImpl class";
		logger.info(methodName + "called");
		// return trepo.findByStd(std);
		List<ScheduleLecture> finalLectures = new ArrayList<>();
		List<TimeTable> tlist = trepo.findByDate(date);

		for (int i = 0; i < tlist.size(); i++) {
			List<ScheduleLecture> lectures = tlist.get(i).getLectures();
			for (int j = 0; j < lectures.size(); j++) {
				if (lectures.get(j).getId() == id) {
					finalLectures.add(lectures.get(j));
				}
			}
		}
		return finalLectures;
	}

}
