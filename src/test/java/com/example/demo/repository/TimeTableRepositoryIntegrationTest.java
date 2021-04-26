package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;



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
import com.example.demo.entity.Standard;
import com.example.demo.entity.TimeTable;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { OnlineEducationSystemMineApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
class TimeTableRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private TimeTableRepository timeRepository;

	@Test
	public void whenFindById_thenReturnTimeTable() {
		TimeTable table = new TimeTable(5L,"10/04/2021");
		entityManager.persistAndFlush(table);

		TimeTable fromDb = timeRepository.findById(table.getTimetable_id()).orElse(null);
		assertThat(fromDb.getLectures()).isEqualTo(table.getLectures());
	}
    
	 @Test
	    public void whenInvalidId_thenReturnNull() {
	    	TimeTable fromDb = timeRepository.findById((long) -11).orElse(null);
	        assertThat(fromDb).isNull();
	    }

	 @Test
	  public void should_find_no_timetable_if_repository_is_empty() {
	    Iterable<TimeTable> t= timeRepository.findAll();

	    assertThat(t).isEmpty();
	  }
   
}