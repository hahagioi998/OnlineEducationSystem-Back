package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.OnlineEducationSystemMineApplication;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.MarkAttendance;
import com.example.demo.entity.Standard;
import com.example.demo.entity.TimeTable;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { OnlineEducationSystemMineApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
class AttendanceRepositoryIntegrationTest {
	
	 @Autowired
	    private TestEntityManager entityManager;

	    @Autowired
	    private AttendanceRepository attendanceRepository;

	   
	    
	    
	    @Test
		public void whenFindById_thenReturnAttendance() {
			Attendance att = new Attendance(6L,"10/04/2021","Math");
			entityManager.persistAndFlush(att);

			Attendance fromDb = attendanceRepository.findById(att.getAttId()).orElse(null);
			assertThat(fromDb.getAttendancesheet()).isEqualTo(att.getAttendancesheet());
		}
	    
	    @Test
	    public void whenInvalidId_thenReturnNull() {
	    	Attendance fromDb = attendanceRepository.findById((long) -10).orElse(null);
	        assertThat(fromDb).isNull();
	    }

	    
	    @Test
		  public void should_find_no_attendance_if_repository_is_empty() {
		    Iterable<Attendance> a= attendanceRepository.findAll();

		    assertThat(a).isEmpty();
		  }
}