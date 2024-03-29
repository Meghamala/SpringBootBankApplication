package com.springbank.accounts;

import com.springbank.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients // to allow accounts to connect to other MS
// telling JPA to activate auditing and levarage bean with name auditAwareImpl to understand current auditor
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
@OpenAPIDefinition(  // to add more user friendly wording to spring doc
		info = @Info(
				title= "Accounts Microservice REST API Documentation",
				description= "SpringBank Accounts microservice REST API Documentation",
				version="v1",
				contact=@Contact(
						name = "Meghamala",
						email= "meghamalans1996@gmail.com"
				),
				license=@License(
				name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description ="SpringBank Accounts microservice REST API Documentation",
				url= "http://localhost:8080/swagger-ui/index.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);
	}

}
