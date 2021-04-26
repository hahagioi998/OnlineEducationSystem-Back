package com.example.demo;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@SpringBootApplication
public class OnlineEducationSystemMineApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineEducationSystemMineApplication.class, args);
	}
	
	@Bean
    public Docket openApiEmployeeStore() {
      return new Docket(DocumentationType.OAS_30)
          .groupName("open-api-OES-management")
          .select()
          .paths(OESPaths())
          .build();
    }
    
    /**
     * TLTA paths.
     *
     * @return the predicate
     */
    private Predicate<String> OESPaths() {
      return regex(".*/api/.*");
    }
}
