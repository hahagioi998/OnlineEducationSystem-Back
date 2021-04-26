package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.StudentRegistration;

public interface StudentRegistrationRepo extends JpaRepository<StudentRegistration, Integer> {
	List<StudentRegistration> findByStd(Long std);
	List<StudentRegistration> findByStatus(String status);
	@Query("select cm from StudentRegistration cm where cm.userName=?1")
	//Optional<StudentRegistration> findByEmailId(String email);
	Optional<StudentRegistration> findByUserName(String userName);

	@Query("select e from StudentRegistration e where e.studentEmail=?1")
	Optional<StudentRegistration> findByEmailId(String studentEmail);

	StudentRegistration findByUserNameAndStudentPassword(String username, String password);
	
	
}