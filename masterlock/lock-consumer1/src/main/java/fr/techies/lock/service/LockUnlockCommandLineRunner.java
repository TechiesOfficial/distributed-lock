package fr.techies.lock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.techies.lock.service.response.LockedReponse;
import fr.techies.lock.service.response.UnlockedReponse;

@Component
public class LockUnlockCommandLineRunner implements CommandLineRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void run(String... args) throws Exception {
		
		UnlockedReponse unlockedReponse = null;
		LockedReponse lockedReponse = null;
		
		while(true) {
			lockedReponse = this.restTemplate.getForObject("http://localhost:8080/lock/compte1234", LockedReponse.class);

			System.out.println("Account " + lockedReponse.getIban().getNumeroDeCompte() + "has been locked? " + lockedReponse.isLocked());
			
			//Simulate a 10s task
			Thread.sleep(10000);
			
			unlockedReponse = this.restTemplate.getForObject("http://localhost:8080/unlock/compte1234", UnlockedReponse.class);
			
			System.out.println("Account " + lockedReponse.getIban().getNumeroDeCompte() + "has been released? " + lockedReponse.isLocked());
			
		}
	}
}
