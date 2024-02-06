package ru.mail.knhel7.spring_boot_conditional_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalAppApplicationTests {

	@Autowired
	TestRestTemplate testTemplate;

	@Container
	public final static GenericContainer<?> devApp = new GenericContainer<>("devapp")
			.withExposedPorts(8080);

	@Container
	public final static GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
			.withExposedPorts(8081);

	@Test
	void contextLoadsDevApp() {
		ResponseEntity<String> respEntity = testTemplate.getForEntity(
				"http://localhost:" + devApp.getMappedPort(8080),
				String.class);
		String answer = respEntity.getBody();
		System.out.println(answer);
	}

	@Test
	void contextLoadsProdApp() {
		ResponseEntity<String> respEntity = testTemplate.getForEntity(
				"http://localhost:" + prodApp.getMappedPort(8080),
				String.class);
		String answer = respEntity.getBody();
		System.out.println(answer);
	}

}
