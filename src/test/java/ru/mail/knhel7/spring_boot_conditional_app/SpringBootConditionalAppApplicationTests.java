package ru.mail.knhel7.spring_boot_conditional_app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalAppApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;
	public static GenericContainer<?> devapp = new GenericContainer<>("devapp")
			.withExposedPorts(8080);
	public static GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
			.withExposedPorts(8081);


	@BeforeAll
	public static void setUp() {
		devapp.start();
		prodapp.start();
	}

	@Test
	void contextLoadsDevApp() {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080), String.class);
		System.out.println(forEntity.getBody());
	}
	@Test
	void contextLoadsProdApp() {
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8080), String.class);
		System.out.println(forEntity.getBody());
	}

}
