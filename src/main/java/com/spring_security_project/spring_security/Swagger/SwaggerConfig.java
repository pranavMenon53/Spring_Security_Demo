package com.spring_security_project.spring_security.Swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public static final Contact DEFAULT_CONTACT = new Contact(
    "Sherlock Holmes",
    "ElementaryMyDearWtson.com",
    "221BBakerStreet@Detective.com"
  );
  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
    "My Awesome Api Documentation",
    "Api Documentation",
    "1.0",
    "urn:tos",
    DEFAULT_CONTACT,
    "Apache 2.0",
    "http://www.apache.org/licenses/LICENSE-2.0",
    new ArrayList<>()
  );

  Set<String> Produces_and_Comsumes = new HashSet<>(
    Arrays.asList("application/json", "application/xml")
  );

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(DEFAULT_API_INFO)
      .produces(Produces_and_Comsumes)
      .consumes(Produces_and_Comsumes);
  }
}
