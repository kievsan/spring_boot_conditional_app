package ru.mail.knhel7.spring_boot_conditional_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalAppApplicationTests {

	String result(Integer port) {
		String targetURL = "http://localhost:" + port + "/profile";
		ResponseEntity<String> respEntity = testTemplate.getForEntity(targetURL, String.class);
		final String result = respEntity.getBody();
		System.out.println(targetURL + ": " + result);
		return result;
	}

	@Autowired
	TestRestTemplate testTemplate;

//	@Test
//	void contextLoads() {}

	@Container
	public final static GenericContainer<?> devApp = new GenericContainer<>("dev_app")
			.withExposedPorts(8080);

	@Container
	public final static GenericContainer<?> prodApp = new GenericContainer<>("prod_app")
			.withExposedPorts(8081);

	@Test
	void contextLoadsDevApp() {
		final Integer port = devApp.getMappedPort(8080);
		assertEquals("Current profile is dev", result(port));
	}

	@Test
	void contextLoadsProdApp() {
		final Integer port = prodApp.getMappedPort(8081);
		assertEquals("Current profile is production", result(port));
	}

}
