package jcc00078.TFG.seguridad;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Configuración de Swagger
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(List.class, LocalDate.class), typeResolver.resolve(List.class, java.sql.Date.class)),//Para tipos List<LocalDate>
                        AlternateTypeRules.newRule(typeResolver.resolve(List.class, LocalDateTime.class), typeResolver.resolve(List.class, java.util.Date.class)))//Para tipos List<LocalDateTime>
                .directModelSubstitute(LocalDate.class, java.sql.Date.class) //Para tipos LocalDate
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class) //Para tipos LocalDateTime
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(securitySchemeApiKey())); //Para aplicar la autenticacion en swagger

    }

    private ApiKey securitySchemeApiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }

}

