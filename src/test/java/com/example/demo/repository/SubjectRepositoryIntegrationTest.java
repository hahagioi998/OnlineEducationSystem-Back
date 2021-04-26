package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import com.example.demo.entity.Subject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { OnlineEducationSystemMineApplication.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class SubjectRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SubjectRepository subRepository;

	@Test
	public void whenFindById_thenReturnSubject() {
		Subject sub = new Subject("MATH");
		entityManager.persistAndFlush(sub);

		Subject fromDb = subRepository.findById(sub.getSubId()).orElse(null);
		assertThat(fromDb.getSubName()).isEqualTo(sub.getSubName());
	}

	@Test
	public void whenInvalidId_thenReturnNull() {
		Subject fromDb = subRepository.findById((long) -11).orElse(null);
		assertThat(fromDb).isNull();
	}

	@Test
	public void deleteSubject_test() {

		Subject sub = new Subject("MATH");

		entityManager.persistAndFlush(sub);

		assertNotNull(entityManager.find(Subject.class, sub.getSubId()));

		entityManager.remove(sub);

		Subject deleted = entityManager.find(Subject.class, sub.getSubId());
		assertNull(deleted);
	}
}
