package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OnlineTest;
import com.example.demo.entity.TimeTable;




public interface OnlineTestRepo  extends JpaRepository<OnlineTest,Long>{
	
	List<OnlineTest> findByTeacherId(Long teacherId);
	List<OnlineTest> findByStd(Long std);

	OnlineTest findByTestId(long testId);
}
