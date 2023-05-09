package fr.techies.lock.service;

import java.util.UUID;
import java.util.concurrent.Callable;

import fr.techies.lock.service.register.Client;

public class UnlockTask<Resource> implements Callable<Boolean> {

	private MemoryRessourceLock<Resource> memoryRessourceLock;

	private Resource resource;
	
	private Client client;

	public UnlockTask(MemoryRessourceLock<Resource> memoryRessourceLock, Client client, Resource resource) {

		this.memoryRessourceLock = memoryRessourceLock;
		this.resource = resource;
		this.client = client;
	}

	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		return this.memoryRessourceLock.unlock(this.client, this.resource);
	}
}
