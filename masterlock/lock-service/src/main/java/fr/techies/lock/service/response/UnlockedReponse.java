package fr.techies.lock.service.response;

import java.util.UUID;

public class UnlockedReponse {

	private String resource;

	private boolean unlocked;

	public UnlockedReponse(UUID UUID, String resource, boolean unlocked) {
		this.resource = resource;
		this.unlocked = unlocked;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}
}
