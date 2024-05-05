package com.enoca.challenge.atakanaksoy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Enoca Java Challenge API", version = "v1", description = "Atakan Aksoy Enoca Java Challenge API"))
public class AtakanaksoyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtakanaksoyApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

}
