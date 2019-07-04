package com.tele2.subscription.configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	// @formatter:off
    @Bean
    public Docket apiDocket(@Value("${api.title:}") String title,
    		@Value("${api.paths:}") String paths,
			@Value("${api.description:}") String description,
			@Value("${api.version:}") String version,
			@Value("${api.license.url:}") String licenseUrl,
			@Value("${api.contact.email:}") String contactEmail) {
    	 return new Docket(DocumentationType.SWAGGER_2)
                 .select()
                 .apis(RequestHandlerSelectors.any())
                 .paths(paths(paths))
                 .build().apiInfo(new ApiInfoBuilder()
 						.title( title )
 						.description( description )
 						.version( version )
 						.licenseUrl( licenseUrl )
 						.build());
    	 
    	// @formatter:on
	}

	private com.google.common.base.Predicate<String> paths(String paths) {
		return regex(paths);
	}

}