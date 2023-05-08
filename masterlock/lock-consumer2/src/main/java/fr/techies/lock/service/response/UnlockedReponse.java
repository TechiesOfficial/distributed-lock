package fr.techies.lock.service.response;

import fr.techies.lock.service.IBAN;

public class UnlockedReponse {

	private IBAN iban;
	
	private boolean unlocked;
	
	public UnlockedReponse(IBAN iban, boolean unlocked) {
		this.iban = iban;
		this.unlocked = unlocked;
	}

	public IBAN getIban() {
		return iban;
	}

	public void setIban(IBAN iban) {
		this.iban = iban;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}
}

