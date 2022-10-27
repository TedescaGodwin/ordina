package nl.ordina.wordfrequency.config;

import nl.ordina.wordfrequency.WordFrequencyApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("nl.ordina.wordfrequency.controller"))
                .paths(regex("/api/*"))
                .build()
                .apiInfo(getInfo());
    }

    private ApiInfo getInfo(){
        return new ApiInfoBuilder()
                .title("WordFrequency")
                .description("Word Frequency Assessment")
                .license("WordFrequency")
                .build();
    }
}