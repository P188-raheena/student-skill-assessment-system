package com.example.studentskillassessment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI studentSkillAssessmentAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Student Skill Assessment System API")
                        .version("1.0")
                        .description(
                                "REST API for the Student Skill Assessment System. "
                                + "Provides student management, skill management, "
                                + "question and assessment management, authentication, "
                                + "assessment evaluation, results, analytics, "
                                + "recommendations, and admin functionality."
                        )
                );
    }
}