package org.labseq.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LabseqApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabseqApiApplication.class, args);
	}

}