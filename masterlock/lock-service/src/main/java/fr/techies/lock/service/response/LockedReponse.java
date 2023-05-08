package fr.techies.lock.service.response;

import java.util.UUID;

public class LockedReponse {

	private String resource;

	private boolean locked;

	public LockedReponse(UUID UUID, String resource, boolean locked) {
		this.resource = resource;
		this.locked = locked;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
