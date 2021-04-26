package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.OnlineEducationSystemMineApplication;
import com.example.demo.entity.OnlineTest;
import com.example.demo.entity.QuestionDesc;
import com.example.demo.entity.TimeTable;
import com.example.demo.repository.OnlineTestRepo;
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { OnlineEducationSystemMineApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class TestOnlineTestRepo {
	
		@Autowired
	    private TestEntityManager entityManager;
		
		@Autowired
	    private OnlineTestRepo testRepository;

		@Test
		public void whenFindById_thenReturnOnlineTest() {
			OnlineTest test = new OnlineTest();
			entityManager.persistAndFlush(test);

			OnlineTest fromDb = testRepository.findById(test.getTestId()).orElse(null);
			assertThat(fromDb.getTotalQuestions()).isEqualTo(test.getTotalQuestions());
		}
		
		
		@Test
		public void saveOnlineTest() {
			
			List<QuestionDesc> question = new ArrayList<QuestionDesc>();
			QuestionDesc q = new QuestionDesc("what is oop",1,"A","B","C","D","A",5);
			question.add(q);
			
			 OnlineTest test= new OnlineTest(12L, 22, 11, 4.4f, "11-2-2019",  "11-2-2020",
						"ateemp", question, 11,"MATH",new Long(1));
			 
		        testRepository.save(test);
		        Assert.assertNotNull(test.getTestId()); 
		}
		
		 @Test
		  public void should_find_no_test_if_repository_is_empty() {
		    Iterable<OnlineTest> test = testRepository.findAll();

		    assertThat(test).isEmpty();
		  }

		 @Test
		  public void should_find_all_test() {
			 List<QuestionDesc> question = new ArrayList<QuestionDesc>();
				QuestionDesc q = new QuestionDesc("what is oop",1,"A","B","C","D","A",5);
				question.add(q);
				 OnlineTest test= new OnlineTest(12L, 22, 11, 4.4f, "11-2-2019",  "11-2-2020",
							"ateemp", question, 11,"MATH",new Long(1));
		    entityManager.persist(test);

		   

		    Iterable<OnlineTest> tests = testRepository.findAll();

		    assertThat(tests).hasSize(1).contains(test);
		  }

	}