package ru.mail.knhel7.spring_boot_conditional_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootConditionalAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootConditionalAppApplication.class, args);
	}

	@GetMapping("/")
	private String hello() {
		return "Hello from app!";
	}

}
