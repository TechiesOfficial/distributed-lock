package fr.techies.lock.service.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import fr.techies.lock.service.model.CustomReentrantLock;
import fr.techies.lock.service.model.Resource;

@Component
public class MemoryLockRegister<R extends Resource> {

	private Map<R, CustomReentrantLock<R>> customLockByRessource = new HashMap<>();

	private Map<Client, List<CustomReentrantLock<R>>> customLockByClient = new HashMap<>();

	public boolean tryLock(Client client, R resource) {

		CustomReentrantLock<R> customLock = null;
		boolean result = false;

		if (!customLockByRessource.containsKey(resource))
			customLockByRessource.put(resource, new CustomReentrantLock<R>(resource));

		customLock = this.customLockByRessource.get(resource);

		//Register
		if(this.customLockByClient.get(client) == null)
			this.customLockByClient.put(client, new ArrayList<>());
		this.customLockByClient.get(client).add(customLock);
		
		// No one currently hold the lock.
		if (customLock.getHolder() == null) {
			// Lock the lock.
			result = customLock.getLock().tryLock();
			// Set the holder.
			customLock.setHolder(client);
		}
		// Lock is currently held by the current client
		else if (customLock.getHolder().equals(client)) {
			customLock.setHolder(client);
			result = true;
		}
		// Lock is currently held by another uuid.
		else {
			result = false;
		}

		return result;
	}

	public Boolean unlock(Client client, R resource) {

		CustomReentrantLock<R> uuidLock = null;

		// If an unlock has been asked before lock.
		// Should not happen, it is to prevent bad behaviour.
		if (!customLockByRessource.containsKey(resource)) {
			customLockByRessource.put(resource, new CustomReentrantLock<>(resource));
			return false;
		}

		try {
			uuidLock = this.customLockByRessource.get(resource);

			//Lock is not held by the current uuid. Can not unlock.
			if (!client.equals(uuidLock.getHolder()))
				return false;

			// Unlock the lock.
			uuidLock.getLock().unlock();
			// Delete holder.
			uuidLock.setHolder(null);

		} catch (IllegalMonitorStateException e) {
			// Same as above, when unlock is called a second time on a lock not held,
			// It throws an exception.
			return false;
		}

		return true;
	}

	public List<CustomReentrantLock<R>> getLocksHeldByClient(Client client) {
		
		return this.customLockByClient.get(client);
	}
}
