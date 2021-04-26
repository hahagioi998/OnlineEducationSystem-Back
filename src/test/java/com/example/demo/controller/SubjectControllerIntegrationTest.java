package com.example.demo.controller;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.OnlineEducationSystemMineApplication;
import com.example.demo.entity.Subject;
import com.example.demo.service.SubjectService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OnlineEducationSystemMineApplication.class)
@AutoConfigureMockMvc
public class SubjectControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private SubjectService service;

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void whenPostSub_thenCreateSub() throws Exception {
		Subject sub = new Subject("HINDI");
		given(service.addSubject(Mockito.any())).willReturn(sub);

		mvc.perform(post("/api/higherAuthority/subject/").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(sub))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.subName", is("HINDI")));

		verify(service, VerificationModeFactory.times(1)).addSubject(Mockito.any());
		reset(service);
	}

	@Test
	public void givenSubjects_whenGetSubjects_thenReturnJsonArray() throws Exception {
		Subject sub1 = new Subject("HINDI");
		Subject sub2 = new Subject("ENGLISH");
		Subject sub3 = new Subject("MARATHI");

		List<Subject> allSubjects = Arrays.asList(sub1, sub2, sub3);

		given(service.getAllSubjects()).willReturn(allSubjects);

		mvc.perform(get("/api/higherAuthority/subject/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].subName", is(sub1.getSubName())))
				.andExpect(jsonPath("$[1].subName", is(sub2.getSubName())))
				.andExpect(jsonPath("$[2].subName", is(sub3.getSubName())));
		verify(service, VerificationModeFactory.times(1)).getAllSubjects();
		reset(service);
	}
}
