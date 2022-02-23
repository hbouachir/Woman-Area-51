package tn.esprit.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("tn.esprit.spring.controllers"))//Package+Classes
                .paths(PathSelectors.any()) //méthodes
                .build().apiInfo(apiInfo());
    }
    private ApiInfo apiInfo () {
  	    return new ApiInfoBuilder()
  	            .title("Swagger Configuration for Woman Area 5Project")
  	            .description("\"Spring Boot Swagger configuration\"")
  	            .version("1.0").build();
  	}
}
