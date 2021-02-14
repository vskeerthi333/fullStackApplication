package com.crio.xmeme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class XmemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmemeApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/memes/**")
						.allowedOrigins("*")
						.allowedHeaders("GET", "POST", "PATCH", "DELETE")
						.allowedHeaders("*");
			}
		};
	}

	@Bean
	public Docket api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());

		return docket;
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Xmeme Rest API",
				"A platform to post and visit meme's",
				"API 0.1",
				null,
				new Contact("Venkata Sai keerthi", "", "vskeerthiswarna@gmail.com"), null, null, Collections.emptyList());
	}
}
