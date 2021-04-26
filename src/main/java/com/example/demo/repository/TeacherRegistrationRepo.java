package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TeacherRegistration;

public interface TeacherRegistrationRepo extends JpaRepository<TeacherRegistration, Long> {

}
