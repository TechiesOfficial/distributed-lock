package fr.techies.lock.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.techies.lock.service.response.LockedReponse;
import fr.techies.lock.service.response.UnlockedReponse;

@Controller
public class LockController {

	private LockUnlockThreadPool<String> lockUnlockThreadPool = new LockUnlockThreadPool<>();
	
	@GetMapping("lock/{serviceUUID}/{resource}")
	public ResponseEntity<?> lock(HttpServletRequest request, @PathVariable String serviceUUID, @PathVariable String resource) {

		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<?> responseEntity = null;
		UUID uuid = UUID.fromString(serviceUUID);
		boolean result = false;
		
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemotePort());
		
		result = this.lockUnlockThreadPool.tryLock(uuid, resource);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseEntity = new ResponseEntity<LockedReponse>(new LockedReponse(uuid, resource, result), httpHeaders,
				HttpStatus.OK);

		return responseEntity;
	}
	
	@GetMapping("unlock/{serviceUUID}/{resource}")
	public ResponseEntity<?> unlock(@PathVariable String serviceUUID, @PathVariable String resource) {

		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<?> responseEntity = null;
		UUID uuid = UUID.fromString(serviceUUID);
		boolean result = false;
		
		result = this.lockUnlockThreadPool.unlock(uuid, resource);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		responseEntity = new ResponseEntity<UnlockedReponse>(new UnlockedReponse(uuid, resource, result), httpHeaders,
				HttpStatus.OK);

		return responseEntity;
	}
	
}
