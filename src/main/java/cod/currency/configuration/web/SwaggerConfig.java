package cod.currency.configuration.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig /*extends WebMvcConfigurationSupport*/ {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //.paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex("/admin.*").or(PathSelectors.regex("/.*")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .pathMapping("/");
    }

    /*@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/

    private ApiInfo metaData() {

        Contact contact = new Contact("Joao Marques", "https://cod<not completed>.com/",
                "jm.a.marques.93@gmail.com");

        return new ApiInfo(
                "Cryptocurrency",
                "A cryptocurrency (or crypto currency or crypto for short) is a digital asset designed to work" +
                        " as a medium of exchange wherein individual coin ownership records are stored in a ledger existing" +
                        " in a form of computerized database using strong cryptography to secure transaction records," +
                        " to control the creation of additional coins, and to verify the transfer of coin ownership",
                "1.0",
                "Terms of Service: Have Fun and Respect the others",
                contact,
                "MIT License",
                "https://opensource.org/licenses/MIT",
                new ArrayList<>());
    }
}
