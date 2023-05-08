package fr.techies.lock.service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LockUnlockThreadPool<Resource> {

	// We create a TP of 1 thread in order to be sure the thread used to lock/unlock
	// resources
	// is effectively holding the monitor
	private ExecutorService executor = Executors.newFixedThreadPool(1);

	private MemoryRessourceLock<Resource> memoryRessourceLock = new MemoryRessourceLock<>();

	public LockUnlockThreadPool() {
		// TODO Auto-generated constructor stub
	}

	public Boolean tryLock(UUID uuid, Resource resource) {

		Future<Boolean> future = null;

		future = this.executor.submit(new LockTask<Resource>(this.memoryRessourceLock, uuid, resource));

		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Boolean unlock(UUID uuid, Resource resource) {

		Future<Boolean> future = null;

		future = this.executor.submit(new UnlockTask<Resource>(this.memoryRessourceLock, uuid, resource));

		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
