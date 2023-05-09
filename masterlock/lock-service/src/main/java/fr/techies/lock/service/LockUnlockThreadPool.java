package fr.techies.lock.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.techies.lock.service.model.Resource;
import fr.techies.lock.service.register.Client;
import fr.techies.lock.service.register.MemoryLockRegister;

@Component
public class LockUnlockThreadPool<R extends Resource> {

	@Autowired
	private MemoryLockRegister<R> memoryRessourceLock = new MemoryLockRegister<>();

	// We create a TP of 1 thread in order to be sure the thread used to lock/unlock
	// resources
	// is effectively holding the monitor
	private ExecutorService executor = Executors.newFixedThreadPool(1);

	public LockUnlockThreadPool() {
		// TODO Auto-generated constructor stub
	}

	public Boolean tryLock(Client client, R resource) {

		Future<Boolean> future = null;

		future = this.executor.submit(new LockTask<R>(this.memoryRessourceLock, client, resource));

		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Boolean unlock(Client client, R resource) {

		Future<Boolean> future = null;

		future = this.executor.submit(new UnlockTask<R>(this.memoryRessourceLock, client, resource));

		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
