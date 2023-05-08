package fr.techies.lock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LockUnlockCommandLineRunner implements CommandLineRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void run(String... args) throws Exception {
		boolean result = this.restTemplate.getForObject("http://localhost:8080", Boolean.class);
		System.out.println(result);

	}

}
