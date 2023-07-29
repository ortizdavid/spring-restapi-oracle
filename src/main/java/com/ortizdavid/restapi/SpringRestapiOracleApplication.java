package com.ortizdavid.restapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
			title = "Task Management API",
			version = "1.0.0",
			description = "Example using Spring Boot and Oracle 19c"
        ),
        servers = {
			@Server(url = "http://localhost:8081", description = "Local server"),
			@Server(url = "https://api.example.com", description = "Production server")
        }
)
@SpringBootApplication
public class SpringRestapiOracleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestapiOracleApplication.class, args);
	}
		
}
