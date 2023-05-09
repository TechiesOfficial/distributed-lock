package fr.techies.lock.service;

import java.util.concurrent.Callable;

import fr.techies.lock.service.model.Resource;
import fr.techies.lock.service.register.Client;
import fr.techies.lock.service.register.MemoryLockRegister;

public class LockTask<R extends Resource> implements Callable<Boolean>{

	private MemoryLockRegister<R> memoryRessourceLock;

	private R resource;
	
	private Client client;
	
	public LockTask(MemoryLockRegister<R> memoryRessourceLock, Client client, R resource) {
		this.resource = resource;
		this.client = client;
		this.memoryRessourceLock = memoryRessourceLock;
	}

	@Override
	public Boolean call() throws Exception {

		return this.memoryRessourceLock.tryLock(this.client, this.resource);
	}
}
