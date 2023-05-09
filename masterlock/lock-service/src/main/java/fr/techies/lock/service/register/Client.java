package fr.techies.lock.service.register;

import java.util.UUID;

public class Client {

	private UUID uuid;
	
	public Client(UUID uuid) {
		
		this.uuid = uuid;
	}
	
	public UUID getUuid() {
		return uuid;
	}

	/** 
	 * Do not change! Used to identify uniquely a client.
	 */
	@Override
	public boolean equals(Object obj) {

		Client toCompareWith = (Client) obj;
		
		return this.uuid.equals(toCompareWith.uuid);
	}
	
	/** 
	 * Do not change! Used to identify uniquely a client.
	 */
	@Override
	public int hashCode() {

		return uuid.hashCode();
	}
	
}
