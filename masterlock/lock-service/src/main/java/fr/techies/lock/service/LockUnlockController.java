package fr.techies.lock.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.techies.lock.service.model.Resource;
import fr.techies.lock.service.model.StringRessource;
import fr.techies.lock.service.register.Client;
import fr.techies.lock.service.register.ClientRegister;
import fr.techies.lock.service.response.LockedReponse;
import fr.techies.lock.service.response.UnlockedReponse;

@Controller
public class LockUnlockController {

	@Autowired
	private ClientRegister clientRegister;

	@Autowired
	private LockUnlockThreadPool<Resource> lockUnlockThreadPool;

	@GetMapping("lock/{serviceUUID}/{resource}")
	public ResponseEntity<?> lock(HttpServletRequest request, @PathVariable String serviceUUID,
			@PathVariable String resource) {

		HttpHeaders httpHeaders = new HttpHeaders();
		Resource r = new StringRessource(resource);
		ResponseEntity<?> responseEntity = null;
		UUID uuid = UUID.fromString(serviceUUID);
		boolean result = false;
		Client client = null;

		// Registering client
		client = clientRegister.register(uuid);

		result = this.lockUnlockThreadPool.tryLock(client, r);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseEntity = new ResponseEntity<LockedReponse>(new LockedReponse(uuid, resource, result), httpHeaders,
				HttpStatus.OK);

		return responseEntity;
	}

	@GetMapping("unlock/{serviceUUID}/{resource}")
	public ResponseEntity<?> unlock(@PathVariable String serviceUUID, @PathVariable String resource) {

		HttpHeaders httpHeaders = new HttpHeaders();
		Resource r = new StringRessource(resource);
		ResponseEntity<?> responseEntity = null;
		UUID uuid = UUID.fromString(serviceUUID);
		boolean result = false;
		Client client = null;

		// Registering client
		client = clientRegister.register(uuid);

		result = this.lockUnlockThreadPool.unlock(client, r);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseEntity = new ResponseEntity<UnlockedReponse>(new UnlockedReponse(uuid, resource, result), httpHeaders,
				HttpStatus.OK);

		return responseEntity;
	}

}
