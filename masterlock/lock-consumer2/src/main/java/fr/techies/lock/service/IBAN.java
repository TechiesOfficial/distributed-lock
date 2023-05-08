package fr.techies.lock.service;

public class IBAN {

	private String numeroDeCompte;
	
	public IBAN(String numeroDeCompte) {
		this.numeroDeCompte = numeroDeCompte;
	}

	public String getNumeroDeCompte() {
		return numeroDeCompte;
	}
	
	@Override
	public boolean equals(Object obj) {

		IBAN iban = (IBAN) obj;
		
		return iban.numeroDeCompte.equals(this.numeroDeCompte);
	}
	
	@Override
	public int hashCode() {
		return numeroDeCompte.hashCode();
	}
}
