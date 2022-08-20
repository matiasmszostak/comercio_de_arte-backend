package com.arte.comercio.comerciobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.arte.comercio.comerciobackend.models")
public class ComercioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComercioBackendApplication.class, args);
	}

}
