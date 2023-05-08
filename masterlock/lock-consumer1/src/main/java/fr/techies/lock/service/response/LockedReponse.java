package fr.techies.lock.service.response;

import fr.techies.lock.service.IBAN;

public class LockedReponse {

	private IBAN iban;
	
	private boolean locked;
	
	public LockedReponse() {
		// TODO Auto-generated constructor stub
	}
	
	public LockedReponse(IBAN iban, boolean locked) {
		this.iban = iban;
		this.locked = locked;
	}

	public IBAN getIban() {
		return iban;
	}

	public void setIban(IBAN iban) {
		this.iban = iban;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
