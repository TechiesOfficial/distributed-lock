package fr.techies.lock.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Attention: spring-boot ne scanne que les classes du même package et en
 * dessous.
 */
@SpringBootApplication
public class LockServiceApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(LockServiceApplication.class, args);
		} catch (Throwable e) {
			if (e.getClass().getName().contains("SilentExitException")) {
				// Spring is restarting the main thread - See spring-boot-devtools
			} else {
				e.printStackTrace();
			}
		}
	}
}
