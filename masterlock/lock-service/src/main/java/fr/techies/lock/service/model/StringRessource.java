package fr.techies.lock.service.model;

public class StringRessource implements Resource{

	private String name = null;

	public StringRessource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
