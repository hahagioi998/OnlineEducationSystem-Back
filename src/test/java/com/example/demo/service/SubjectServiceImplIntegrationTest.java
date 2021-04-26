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

import com.example.demo.entity.Subject;
import com.example.demo.exception.StandardException;
import com.example.demo.exception.SubjectException;
import com.example.demo.repository.StandardRepository;
import com.example.demo.repository.SubjectRepository;

@ExtendWith(SpringExtension.class)
public class SubjectServiceImplIntegrationTest {

	@TestConfiguration
	static class subjectServiceImplTestContextConfiguration {

		@Bean
		public SubjectService subjectService() {
			return new SubjectServiceImpl();
		}
	}

	@Autowired
	private SubjectService subjectService;

	@MockBean
	private SubjectRepository subjectRepository;

	@BeforeEach
	public void setUp() {
		Subject sub1 = new Subject("MATH");
		sub1.setSubId(11L);
		Subject sub2 = new Subject("HINDI");
		Subject sub3 = new Subject("ENGLISH");

		List<Subject> stds = Arrays.asList(sub1,sub2,sub3);

		Mockito.when(subjectRepository.findById(sub1.getSubId())).thenReturn(Optional.of(sub1));
		Mockito.when(subjectRepository.findAll()).thenReturn(stds);
		Mockito.when(subjectRepository.findById((long) -99)).thenReturn(Optional.empty());
	}

	
	@Test
	public void whenValidId_thenSubjectShouldBeFound() throws SubjectException {
		Subject fromDb = subjectService.getSubjectById(11L);
		assertThat(fromDb.getSubName()).isEqualTo("MATH");

		verifyFindByIdIsCalledOnce();
	}

//	@Test
	public void whenInValidId_thenSubjectShouldNotBeFound() throws SubjectException {
		Subject fromDb = subjectService.getSubjectById(99L);
		verifyFindByIdIsCalledOnce();
		assertThat(fromDb).isNull();
	}

	
	@Test
	public void given3Subjects_whenGetAll_thenReturn3Records() throws SubjectException {
		Subject sub1 = new Subject("MATH");
		Subject sub2 = new Subject("HINDI");
		Subject sub3 = new Subject("ENGLISH");

		List<Subject> allSubjects = subjectService.getAllSubjects();
		verifyFindAllSubjectsIsCalledOnce();
		assertThat(allSubjects).hasSize(3).extracting(Subject::getSubName)
				.contains(sub1.getSubName(), sub2.getSubName(), sub3.getSubName());
	}

	
	private void verifyFindByIdIsCalledOnce() {
		Mockito.verify(subjectRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
		Mockito.reset(subjectRepository);
	}

	
	private void verifyFindAllSubjectsIsCalledOnce() {
		Mockito.verify(subjectRepository, VerificationModeFactory.times(1)).findAll();
		Mockito.reset(subjectRepository);
	}

}
