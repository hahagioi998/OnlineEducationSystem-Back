package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.StandardSubjects;

public interface StandardSubjectsRepository extends JpaRepository<StandardSubjects, Long>{
	 
	
	@Query(value = "SELECT * FROM standard_subjects WHERE teacher_id = :teacher_id" , nativeQuery = true)
	List<StandardSubjects> findAllByTeacherId(@Param("teacher_id") Long teacher_id);
	

	@Query(value = "SELECT * FROM standard_subjects WHERE std_id = :std_id" , nativeQuery = true)
	List<StandardSubjects> findAllByStandardId(@Param("std_id") Long std_id);

}
