package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//https://springfox.github.io/springfox/docs/snapshot/

/**
 * The Class SwaggerUiWebMvcConfigurer.
 */
@Component
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {
	private final String baseUrl;

	/**
	 * Instantiates a new swagger ui web mvc configurer.
	 *
	 * @param baseUrl the base url
	 */
	public SwaggerUiWebMvcConfigurer(@Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * Adds the resource handlers.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
		registry.addResourceHandler(baseUrl + "/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
				.resourceChain(false);
	}

	/**
	 * Adds the view controllers.
	 *
	 * @param registry the registry
	 */
	// http://localhost:9090/springfox/swagger-ui/index.html
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(baseUrl + "/swagger-ui/")
				.setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
	}

	/**
	 * Adds the cors mappings.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/admin").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/api/admin").allowedOrigins("*");

		registry.addMapping("/api/higherAuthority/standard").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/api/higherAuthority/standard").allowedOrigins("*");

		registry.addMapping("/api/higherAuthority/standardSubjects").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/api/higherAuthority/standardSubjects").allowedOrigins("*");

		registry.addMapping("/api/students").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
		registry.addMapping("/api/students").allowedOrigins("*");

		registry.addMapping("/api/higherAuthority/subject").allowedOrigins("http://editor.swagger.io");
	    registry .addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
	    registry.addMapping("/api/higherAuthority/subject").allowedOrigins("*");

		registry.addMapping("/api/higherAuthority/teacherRegistration").allowedOrigins("http://editor.swagger.io");
	    registry .addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
	    registry.addMapping("/api/higherAuthority/teacherRegistration").allowedOrigins("*");
	    
	    registry.addMapping("/api/attendance").allowedOrigins("http://editor.swagger.io");
	    registry .addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
	    registry.addMapping("/api/attendance").allowedOrigins("*");
	    
	    registry.addMapping("/api/teacher").allowedOrigins("http://editor.swagger.io");
	    registry .addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
	    registry.addMapping("/api/teacher").allowedOrigins("*");

	    registry.addMapping("/api/schedule").allowedOrigins("http://editor.swagger.io");
	    registry .addMapping("/v2/api-docs.*").allowedOrigins("http://editor.swagger.io");
	    registry.addMapping("/api/schedule").allowedOrigins("*");

	    
	}
}