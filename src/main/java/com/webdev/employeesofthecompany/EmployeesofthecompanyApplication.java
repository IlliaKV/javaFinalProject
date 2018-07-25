package com.webdev.employeesofthecompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmployeesofthecompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesofthecompanyApplication.class, args);
	}
}
