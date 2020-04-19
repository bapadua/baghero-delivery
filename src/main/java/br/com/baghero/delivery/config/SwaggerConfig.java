package br.com.baghero.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo metaData() {	
		return new ApiInfoBuilder()
				.title("Baghero Delivery - UMC")
				.description("Trabalho conclusão de curso UMC, Sistemas de Informação")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaData())
				.tags(
						new Tag("delivery", "Controller  responsável pelos serviços para o usuario final")
				)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.baghero.delivery"))
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/");
	}
}
