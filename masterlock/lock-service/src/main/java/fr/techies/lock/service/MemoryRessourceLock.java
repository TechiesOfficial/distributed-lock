package fr.techies.lock.service;

import java.util.HashMap;
import java.util.Map;

import fr.techies.lock.service.lock.CustomReentrantLock;
import fr.techies.lock.service.register.Client;

public class MemoryRessourceLock<Resource> {

	private Map<Resource, CustomReentrantLock> customLockByRessource = new HashMap<>();

	public boolean tryLock(Client client, Resource resource) {

		CustomReentrantLock customLock = null;
		boolean result = false;

		if (!customLockByRessource.containsKey(resource))
			customLockByRessource.put(resource, new CustomReentrantLock());

		customLock = this.customLockByRessource.get(resource);

		// No one currently hold the lock.
		if (customLock.getHolder() == null) {
			// Lock the lock.
			result = customLock.getLock().tryLock();
			// Set the holder.
			customLock.setHolder(client);
		} 
		//Lock is currently held by the current client
		else if (customLock.getHolder().equals(client)) {
			customLock.setHolder(client);
			result = true;
		}
		//Lock is currently held by another uuid.
		else {
			result = false;
		}

		return result;
	}

	public Boolean unlock(Client client, Resource resource) {

		CustomReentrantLock uuidLock = null;

		// If an unlock has been asked before lock.
		// Should not happen, it is to prevent bad behaviour.
		if (!customLockByRessource.containsKey(resource)) {
			customLockByRessource.put(resource, new CustomReentrantLock());
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
}
