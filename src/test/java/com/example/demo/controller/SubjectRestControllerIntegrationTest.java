package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.OnlineEducationSystemMineApplication;
import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OnlineEducationSystemMineApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SubjectRestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private SubjectRepository repository;
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateSubject() throws IOException, Exception {
    	Subject sub1 = new Subject("MATH");
        mvc.perform(post("/api/higherAuthority/subject/")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(sub1)));

        List<Subject> found = repository.findAll();
        assertThat(found).extracting(Subject::getSubName).containsOnly("MATH");
    }
 
    @Test
    public void givenSubjects_whenGetSubjects_thenStatus200() throws Exception {
        createTestSubject("MATH");
        createTestSubject("HINDI");

        
        mvc.perform(get("/api/higherAuthority/subject/").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
          .andExpect(jsonPath("$[0].subName", is("MATH")))
          .andExpect(jsonPath("$[1].subName", is("HINDI")));
        
    }
    
	private void createTestSubject(String name) {
		Subject std = new Subject(name);
		repository.saveAndFlush(std);
	}
}
