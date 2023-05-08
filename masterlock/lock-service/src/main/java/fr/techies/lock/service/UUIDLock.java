package fr.techies.lock.service;

import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UUIDLock {

	private Lock lock = new ReentrantLock();

	private UUID holder;

	private long fisrtHold = -1;
	
	private int howManyTimesHold = -1;
	
	private long lastHold = -1;
	
	public UUIDLock() {
	
	}
	
	public UUID getHolder() {
		return holder;
	}
	
	public void setHolder(UUID holder) {
		
		long time = Calendar.getInstance().getTimeInMillis();
		
		//A new holder
		if(this.holder == null && holder != null && this.fisrtHold == -1){
			this.holder = holder;
			this.fisrtHold = time; 
			this.lastHold = time;
			this.howManyTimesHold = 1;
		}
		//Same holder lock again. Simulate reentrant lock.
		else if (this.holder != null && holder != null && this.fisrtHold != -1)
		{
			this.lastHold = time;
			this.howManyTimesHold = this.howManyTimesHold + 1;
		}
		//Provided holder is null, reset timer.
		else if (holder == null){
			this.holder = null;
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
