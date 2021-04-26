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
import com.example.demo.entity.Standard;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { OnlineEducationSystemMineApplication.class })
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
public class StandardRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StandardRepository stdRepository;

	@Test
	public void whenFindById_thenReturnStandard() {
		Standard std = new Standard("Standard_1");
		entityManager.persistAndFlush(std);

		Standard fromDb = stdRepository.findById(std.getStdId()).orElse(null);
		assertThat(fromDb.getStdName()).isEqualTo(std.getStdName());
	}
    
    
    @Test
    public void whenInvalidId_thenReturnNull() {
    	Standard fromDb = stdRepository.findById((long) -11).orElse(null);
        assertThat(fromDb).isNull();
    }
    
    
    @Test
    public void updateStandardName_test() {
    	Standard std = new Standard("Standard_1");
        entityManager.persistAndFlush(std);
        std.setStdName("Standard_2");;
       
        entityManager.persistAndFlush(std);  
        Standard updatedStd = entityManager.find(Standard.class, std.getStdId());
        
        assertThat(updatedStd.getStdName()).isEqualTo(std.getStdName());
    }
    
   
    @Test
    public void deleteStandard_test() {
    	
    	Standard std = new Standard("Standard_1");

    	entityManager.persistAndFlush(std);
    	
    	assertNotNull(entityManager.find(Standard.class, std.getStdId()));
    	
        entityManager.remove(std);
        
        Standard deleted= entityManager.find(Standard.class, std.getStdId());
        assertNull(deleted);
    }
}
