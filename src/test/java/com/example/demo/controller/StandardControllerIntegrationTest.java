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
import com.example.demo.entity.Standard;
import com.example.demo.service.StandardService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OnlineEducationSystemMineApplication.class)
@AutoConfigureMockMvc
public class StandardControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private StandardService service;

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void whenPostStd_thenCreateStd() throws Exception {
		Standard std1 = new Standard("Standard_1");
		given(service.addStandard(Mockito.any())).willReturn(std1);

		mvc.perform(post("/api/higherAuthority/standard/").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(std1))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.stdName", is("Standard_1")));

		verify(service, VerificationModeFactory.times(1)).addStandard(Mockito.any());
		reset(service);
	}

	@Test
	public void givenStandards_whenGetStandards_thenReturnJsonArray() throws Exception {
		Standard std1 = new Standard("Standard_1");
		Standard std2 = new Standard("Standard_2");
		Standard std3 = new Standard("Standard_3");

		List<Standard> allStandards = Arrays.asList(std1, std2, std3);

		given(service.getAllStandards()).willReturn(allStandards);

		mvc.perform(get("/api/higherAuthority/standard/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].stdName", is(std1.getStdName())))
				.andExpect(jsonPath("$[1].stdName", is(std2.getStdName())))
				.andExpect(jsonPath("$[2].stdName", is(std3.getStdName())));
		verify(service, VerificationModeFactory.times(1)).getAllStandards();
		reset(service);
	}
}
