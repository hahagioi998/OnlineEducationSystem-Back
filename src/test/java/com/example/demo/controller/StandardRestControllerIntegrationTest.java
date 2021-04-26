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
import com.example.demo.entity.Standard;
import com.example.demo.repository.StandardRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OnlineEducationSystemMineApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class StandardRestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private StandardRepository repository;
    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateStandard() throws IOException, Exception {
    	Standard std1 = new Standard("Standard_1");
        mvc.perform(post("/api/higherAuthority/standard/")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(JsonUtil.toJson(std1)));

        List<Standard> found = repository.findAll();
        assertThat(found).extracting(Standard::getStdName).containsOnly("Standard_1");
    }
 
    @Test
    public void givenStandards_whenGetStandards_thenStatus200() throws Exception {
        createTestStandard("Standard_1");
        createTestStandard("Standard_2");

        
        mvc.perform(get("/api/higherAuthority/standard/").contentType(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
          .andExpect(jsonPath("$[0].stdName", is("Standard_1")))
          .andExpect(jsonPath("$[1].stdName", is("Standard_2")));
        
    }
    
	private void createTestStandard(String name) {
		Standard std = new Standard(name);
		repository.saveAndFlush(std);
	}
}
