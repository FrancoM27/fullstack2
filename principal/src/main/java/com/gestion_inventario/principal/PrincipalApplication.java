package com.gestion_inventario.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan
@ComponentScan 
public class PrincipalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrincipalApplication.class, args);
	}

}
