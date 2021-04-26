package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>
{
	@Query("select cm from Admin cm where cm.adminuserName=?1")
	//Optional<StudentRegistration> findByEmailId(String email);
	Optional<Admin> findByUserName(String adminuserName);

	@Query("select e from Admin e where e.adminEmail=?1")
	Optional<Admin> findByEmailId(String adminEmail);

}