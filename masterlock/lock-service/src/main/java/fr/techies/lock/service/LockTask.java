package fr.techies.lock.service;

import java.util.UUID;
import java.util.concurrent.Callable;

public class LockTask<Resource> implements Callable<Boolean>{

	private MemoryRessourceLock<Resource> memoryRessourceLock;

	private Resource resource;
	
	private UUID uuid;
	
	public LockTask(MemoryRessourceLock<Resource> memoryRessourceLock, UUID uuid, Resource resource) {
		this.resource = resource;
		this.uuid = uuid;
		this.memoryRessourceLock = memoryRessourceLock;
	}

	@Override
	public Boolean call() throws Exception {

		return this.memoryRessourceLock.tryLock(this.uuid, this.resource);
	}
}
