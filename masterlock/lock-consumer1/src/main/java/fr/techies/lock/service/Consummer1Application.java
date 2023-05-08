package fr.techies.lock.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Attention: spring-boot ne scanne que les classes du même package et en
 * dessous.
 */
@SpringBootApplication
public class Consummer1Application {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {

		try {
			SpringApplication.run(Consummer1Application.class, args);
		} catch (Throwable e) {
			if (e.getClass().getName().contains("SilentExitException")) {
				// Spring is restarting the main thread - See spring-boot-devtools
			} else {
				e.printStackTrace();
			}
		}
	}
}
