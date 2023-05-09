package fr.techies.lock.service.model;

public class StringRessource implements Resource{

	private String name = null;

	public StringRessource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		StringRessource stringRessource = (StringRessource) obj;
		
		return this.name.equals(stringRessource.name);
	}
	
	@Override
	public int hashCode() {

		return this.name.hashCode();
	}
}
