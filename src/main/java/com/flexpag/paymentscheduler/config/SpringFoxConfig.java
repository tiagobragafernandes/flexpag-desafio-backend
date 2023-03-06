package com.flexpag.paymentscheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.flexpag.paymentscheduler"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Serviço de agendamento de pagamentos - Desafio Flexpag")
                .description("Serviço criado para agendar pagamentos para serem concretizados de forma automatizada.")
                .version("1.0.0")
                .contact(new Contact("Tiago Braga Fernandes", "https://www.linkedin.com/in/tiago-fernandes-219b12182/", "tibrafe@gmail.com"))
                .build();
    }
}