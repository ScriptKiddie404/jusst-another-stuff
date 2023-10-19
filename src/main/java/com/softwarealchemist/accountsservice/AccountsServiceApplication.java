package com.softwarealchemist.accountsservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Account microservice REST API Documentation",
                description = "Software-Alchemist Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Software Alchemist",
                        email = "software@alchemist.com",
                        url = "https://www.softare-alhcemist.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Software-Alchemist Accounts microservice REST API Documentation",
                url = "https://www.software-alchemist.com"
        )
)
public class AccountsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsServiceApplication.class, args);
    }

}
