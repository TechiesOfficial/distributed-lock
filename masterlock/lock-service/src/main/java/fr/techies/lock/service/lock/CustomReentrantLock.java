package fr.techies.lock.service.lock;

import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import fr.techies.lock.service.register.Client;

/**
 * <p>
 * Used to encapsulate a lock.
 * </p>
 * <p>
 * Also store some information :
 * <ul>
 * <li>Who is the current {@link Client} holder</li>
 * <li>How many time the lock has been attempted to be held by a {@link Client}
 * </li>
 * <li>Since when the fisrt attempted lock has been hold</li>
 * <li>Since when the last attempted lock has been hold</li>
 * </ul>
 * </p>
 * 
 */
public class CustomReentrantLock {

	private Lock lock = new ReentrantLock();

	private Client client;

	private long fisrtHold = -1;

	private int howManyTimesHold = -1;

	private long lastHold = -1;

	public CustomReentrantLock() {

	}

	public Client getHolder() {
		return client;
	}

	public void setHolder(Client client) {

		long time = Calendar.getInstance().getTimeInMillis();

		// A new holder
		if (this.client == null && client != null && this.fisrtHold == -1) {
			this.client = client;
			this.fisrtHold = time;
			this.lastHold = time;
			this.howManyTimesHold = 1;
		}
		// Same holder lock again. Simulate reentrant lock.
		else if (this.client != null && client != null && this.fisrtHold != -1) {
			this.lastHold = time;
			this.howManyTimesHold = this.howManyTimesHold + 1;
		}
		// Provided holder is null, reset timer.
		else if (client == null) {
			this.client = null;
			this.fisrtHold = -1;
			this.lastHold = -1;
			this.howManyTimesHold = -1;
		}
	}

	public Lock getLock() {
		return lock;
	}

	public long getFisrtHold() {
		return fisrtHold;
	}

	public int getHowManyTimesHold() {
		return howManyTimesHold;
	}

	public long getLastHold() {
		return lastHold;
	}
}
