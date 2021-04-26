package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TeacherRegistrationKomal;
@Repository
public interface TeacherRepository extends JpaRepository<TeacherRegistrationKomal, Long>{

	
}
