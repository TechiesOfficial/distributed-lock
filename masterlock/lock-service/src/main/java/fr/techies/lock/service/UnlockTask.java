package fr.techies.lock.service;

import java.util.UUID;
import java.util.concurrent.Callable;

public class UnlockTask<Resource> implements Callable<Boolean> {

	private MemoryRessourceLock<Resource> memoryRessourceLock;

	private Resource resource;
	
	private UUID uuid;

	public UnlockTask(MemoryRessourceLock<Resource> memoryRessourceLock, UUID uuid, Resource resource) {

		this.memoryRessourceLock = memoryRessourceLock;
		this.resource = resource;
		this.uuid = uuid;
	}

	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		return this.memoryRessourceLock.unlock(this.uuid, this.resource);
	}
}
