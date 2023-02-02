package com.skillzamp.blogApp.config;

import java.util.Collections; 

import javax.print.attribute.DocAttributeSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
		
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
						
	}

	private ApiInfo getInfo() {
		
		return new ApiInfo ("Blogging Application: Backend Application",
				"This Project Is developed by ****","1.0","Terms of Service",
				new springfox.documentation.service.Contact("Omer","https://omersabir.netlify.app/","omer.sabir03@gmail.com"),
				"Licence of API","Licence of URl",Collections.emptyList());
	}
}
