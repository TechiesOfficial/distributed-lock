package fr.techies.lock.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MemoryRessourceLock<Resource> {

	private Map<Resource, UUIDLock> lockByRessource = new HashMap<>();

	public boolean tryLock(UUID uuid, Resource resource) {

		UUIDLock uuidLock = null;
		boolean result = false;

		if (!lockByRessource.containsKey(resource))
			lockByRessource.put(resource, new UUIDLock());

		uuidLock = this.lockByRessource.get(resource);

		// No one currently hold the lock.
		if (uuidLock.getHolder() == null) {
			// Lock the lock.
			result = uuidLock.getLock().tryLock();
			// Set the holder.
			uuidLock.setHolder(uuid);
		} 
		//Lock is currently held by the current uuid
		else if (uuidLock.getHolder().equals(uuid)) {
			uuidLock.setHolder(uuid);
			result = true;
		}
		//Lock is currently held by another uuid.
		else {
			result = false;
		}

		return result;
	}

	public Boolean unlock(UUID uuid, Resource resource) {

		UUIDLock uuidLock = null;

		// If an unlock has been asked before lock.
		// Should not happen, it is to prevent bad behaviour.
		if (!lockByRessource.containsKey(resource)) {
			lockByRessource.put(resource, new UUIDLock());
			return false;
		}

		try {
			uuidLock = this.lockByRessource.get(resource);

			//Lock is not held by the current uuid. Can not unlock.
			if (!uuid.equals(uuidLock.getHolder()))
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
