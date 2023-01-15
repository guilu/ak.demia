package com.diegobarrioh.akdemia;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Akdemia DBHSTUDIOS", version = "1.0", description = "Academia de preparación de tests de Oposiciones"))
public class AkdemiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkdemiaApplication.class, args);
	}

}
