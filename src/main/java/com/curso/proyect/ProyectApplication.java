package com.curso.proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(ProyectApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR" + e.getMessage());
		}
	}
}
