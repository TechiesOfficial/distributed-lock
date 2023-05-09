package fr.techies.lock.service;

import java.util.concurrent.Callable;

import fr.techies.lock.service.model.Resource;
import fr.techies.lock.service.register.Client;
import fr.techies.lock.service.register.MemoryLockRegister;

public class UnlockTask<R extends Resource> implements Callable<Boolean> {

	private MemoryLockRegister<R> memoryRessourceLock;

	private R resource;
	
	private Client client;

	public UnlockTask(MemoryLockRegister<R> memoryRessourceLock, Client client, R resource) {

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
