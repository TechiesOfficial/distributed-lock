package fr.techies.lock.service.register;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ClientRegister {

	Map<UUID, Client> clientsByUUID = new HashMap<>();
	
	public Client register(UUID uuid) {
		
		Client client = new Client(uuid);
		
		this.clientsByUUID.putIfAbsent(uuid, client);
		
		return client;
	}
}
