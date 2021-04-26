package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AttemptedTest;

public interface AttemptedTestRepo extends JpaRepository<AttemptedTest,Integer>{

	List<AttemptedTest> findByRollNo(Long rollNo);
	List<AttemptedTest> findByStd(Long std);

	AttemptedTest findByRollNoAndTestId(Long rollNo, Long testId);
	
	List<AttemptedTest> findByStdAndRollNo(Long std, Long rollNo);
}
