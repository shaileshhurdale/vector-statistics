package com.vector.statistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 * SwaggerConfig is a configuration class. it provides Docket bean for swagger
 * implementation base packages, API info that has been configured for Docket
 * bean creation
 * 
 * @author Shailesh Hurdale
 *
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.vector.statistics")) // Root package
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Vector Statistics")
				.description(
						"API to demonstrate different operations on vector like Arithmetic mean and Standard deviation")
				.version("1.0.0").build();
	}

}
