package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.OnlineEducationSystemMineApplication;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Subject;
import com.example.demo.repository.AttendanceRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OnlineEducationSystemMineApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)

class AttendanceRestControllerIntegrationTest {

	
	    @Autowired
	    private MockMvc mvc;

	    @Autowired
	    private AttendanceRepository repository;
	    
	    @AfterEach
	    public void resetDb() {
	        repository.deleteAll();
	    }
	   
	    @Test
	    public void whenValidInput_thenCreateAttendance() throws IOException, Exception {
	    	Attendance att = new Attendance(6L,"03/04/2020","Math");
	    	
	        mvc.perform(post("/api/attendance/savep")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(JsonUtil.toJson(att)));

	        List<Attendance> found = repository.findAll();
	        assertThat(found).extracting(Attendance::getStd).containsOnly(6L);
	        assertThat(found).extracting(Attendance::getDate).containsOnly("03/04/2020");
	        assertThat(found).extracting(Attendance::getSubjectName).containsOnly("Math");
	    }
	    
	    
//	   
//	    @Test
//	    public void givenAttendance_whenGetAttendance_thenStatus200() throws Exception {
//	        createAttendance(6,"03/04/2020","Math");
//	        createAttendance(5,"01/03/2021","English");
//
//	        
//	        mvc.perform(get("/api/attendance/getall").contentType(MediaType.APPLICATION_JSON))
//	          .andDo(print())
//	          .andExpect(status().isOk())
//	          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
//	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
//	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
//	          .andExpect(jsonPath("$[0].std", is(6)))
//	          .andExpect(jsonPath("$[0].date", is("03/04/2020")))
//	          .andExpect((ResultMatcher) jsonPath("$[0].subjectName", ("Math")))
//	          .andExpect(jsonPath("$[1].std", is(5)))
//	          .andExpect(jsonPath("$[1].date", is("01/03/2021")))
//	          .andExpect((ResultMatcher) jsonPath("$[1].subjectName", ("English")));
//	        
//	    }
//
//		private void createAttendance(long std, String date, String sub) {
//			Attendance att = new Attendance(std,date,sub);
//			repository.saveAndFlush(att);
//			
//		}
/*
		
	    
	    @Test
	    public void givenAttendance_whenGetAttendance_thenStatus200() throws Exception {
	        createAttendance(6L);
	     //   createAttendance(5L);

	        
	        mvc.perform(get("/api/attendance/getreport/{std}").contentType(MediaType.APPLICATION_JSON))
	          .andDo(print())
	          .andExpect(status().isOk())
	          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
	          .andExpect(jsonPath("$[0].Std", is(6L)));
	          
	    }

		private void createAttendance(long std) {
			Attendance att = new Attendance(std);
			repository.saveAndFlush(att);
			
		}   */
		
}