package fr.techies.lock.service.model;

/**
 * {@link Resource#equals(Object)} and {@link Resource#hashCode()} are present
 * to remind subclasses to implement these.
 */
public interface Resource {

	boolean equals(Object obj);

	int hashCode();
}
