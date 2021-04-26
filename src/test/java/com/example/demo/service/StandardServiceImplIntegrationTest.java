package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Standard;
import com.example.demo.exception.StandardException;
import com.example.demo.repository.StandardRepository;

@ExtendWith(SpringExtension.class)
public class StandardServiceImplIntegrationTest {

	@TestConfiguration
	static class standardServiceImplTestContextConfiguration {

		@Bean
		public StandardService standardService() {
			return new StandardServiceImpl();
		}
	}

	@Autowired
	private StandardService standardService;

	@MockBean
	private StandardRepository standardRepository;

	@BeforeEach
	public void setUp() {
		Standard std1 = new Standard("Standard_1");
		std1.setStdId(11L);

		Standard std2 = new Standard("Standard_2");
		Standard std3 = new Standard("Standard_3");

		List<Standard> stds = Arrays.asList(std1,std2,std3);

		Mockito.when(standardRepository.findById(std1.getStdId())).thenReturn(Optional.of(std1));
		Mockito.when(standardRepository.findAll()).thenReturn(stds);
		Mockito.when(standardRepository.findById((long) -99)).thenReturn(Optional.empty());
	}

	
	@Test
	public void whenValidId_thenStandardShouldBeFound() throws StandardException {
		Standard fromDb = standardService.getStandardById(11L);
		assertThat(fromDb.getStdName()).isEqualTo("Standard_1");

		verifyFindByIdIsCalledOnce();
	}

//	@Test
	public void whenInValidId_thenStandardShouldNotBeFound() throws StandardException {
		Standard fromDb = standardService.getStandardById(99L);
		verifyFindByIdIsCalledOnce();
		assertThat(fromDb).isNull();
	}

	
	@Test
	public void given3Standards_whenGetAll_thenReturn3Records() throws StandardException {
		Standard std1 = new Standard("Standard_1");
		Standard std2 = new Standard("Standard_2");
		Standard std3 = new Standard("Standard_3");

		List<Standard> allStandards = standardService.getAllStandards();
		verifyFindAllStandardsIsCalledOnce();
		assertThat(allStandards).hasSize(3).extracting(Standard::getStdName)
				.contains(std1.getStdName(), std2.getStdName(), std3.getStdName());
	}

	
	private void verifyFindByIdIsCalledOnce() {
		Mockito.verify(standardRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
		Mockito.reset(standardRepository);
	}

	
	private void verifyFindAllStandardsIsCalledOnce() {
		Mockito.verify(standardRepository, VerificationModeFactory.times(1)).findAll();
		Mockito.reset(standardRepository);
	}

}
