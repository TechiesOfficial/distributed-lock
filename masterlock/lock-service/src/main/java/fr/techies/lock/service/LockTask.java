package fr.techies.lock.service;

import java.util.concurrent.Callable;

import fr.techies.lock.service.register.Client;

public class LockTask<Resource> implements Callable<Boolean>{

	private MemoryRessourceLock<Resource> memoryRessourceLock;

	private Resource resource;
	
	private Client client;
	
	public LockTask(MemoryRessourceLock<Resource> memoryRessourceLock, Client client, Resource resource) {
		this.resource = resource;
		this.client = client;
		this.memoryRessourceLock = memoryRessourceLock;
	}

	@Override
	public Boolean call() throws Exception {

		return this.memoryRessourceLock.tryLock(this.client, this.resource);
	}
}
