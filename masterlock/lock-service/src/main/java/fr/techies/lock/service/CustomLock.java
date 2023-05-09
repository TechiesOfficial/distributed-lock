package fr.techies.lock.service;

import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import fr.techies.lock.service.register.Client;

public class CustomLock {

	private Lock lock = new ReentrantLock();

	private Client client;

	private long fisrtHold = -1;
	
	private int howManyTimesHold = -1;
	
	private long lastHold = -1;
	
	public CustomLock() {
	
	}
	
	public Client getHolder() {
		return client;
	}
	
	public void setHolder(Client client) {
		
		long time = Calendar.getInstance().getTimeInMillis();
		
		//A new holder
		if(this.client == null && client != null && this.fisrtHold == -1){
			this.client = client;
			this.fisrtHold = time; 
			this.lastHold = time;
			this.howManyTimesHold = 1;
		}
		//Same holder lock again. Simulate reentrant lock.
		else if (this.client != null && client != null && this.fisrtHold != -1)
		{
			this.lastHold = time;
			this.howManyTimesHold = this.howManyTimesHold + 1;
		}
		//Provided holder is null, reset timer.
		else if (client == null){
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
