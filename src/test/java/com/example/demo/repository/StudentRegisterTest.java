package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.example.demo.entity.StudentRegistration;
import com.example.demo.repository.OnlineTestRepo;
import com.example.demo.repository.StudentRegistrationRepo;
@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { OnlineEducationSystemMineApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class StudentRegisterTest {
	

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    StudentRegistrationRepo repository;
     
    @Test
    public void testRepository() 
    {
        StudentRegistration s = new StudentRegistration();
        
        s.setFullName("pooja sarode");
        s.setParentEmail("aparna@gmail.com");
        s.setParentMobile("9767705189");
        s.setRollNo(12);
        s.setStatus("register");
        s.setStd((long) 7);
        s.setStudentEmail("pooja@gmail.com");
        s.setStudentPassword("Pooja@123");
        s.setUserName("PoojaSarodem");
         
        repository.save(s);
        Assert.assertNotNull(s.getId()); 
       
    }
   
    
    @Test
	  public void should_find_no_student_if_repository_is_empty() {
	    Iterable<StudentRegistration> s= repository.findAll();

	    assertThat(s).isEmpty();
	  }
    
	
//    @Test
//    public void should_find_block_students() {
//      StudentRegistration s1 = new StudentRegistration(1,"Pooja Sarode","PoojaSarode","pooja@gmail.com","aparna@gmail.com","9767705189","PoojaSarode","blocked",7,(long) 19);
//      entityManager.persist(s1);
//      
//      StudentRegistration s2 = new StudentRegistration(2,"Richa Aware","RichaAware","richa@gmail.com","pooja@gmail.com","9818953326","RichaAware","blocked",8,(long) 21);
//      entityManager.persist(s2);
//
//     
//
//      Iterable<StudentRegistration> students = repository.findByStatus("blocked");
//
//      assertThat(students).hasSize(2).contains(s1, s2);
//    }
//    @Test
//	  public void should_find_all_students() {
//    	StudentRegistration s1 = new StudentRegistration(1,"Pooja Sarode","PoojaSarode","pooja@gmail.com","aparna@gmail.com","9767705189","PoojaSarode","blocked",7,(long) 19);
//	    entityManager.persist(s1);
//
//	   
//
//	    Iterable<StudentRegistration> students = repository.findAll();
//
//	    assertThat(students).hasSize(1);
//	  }



}