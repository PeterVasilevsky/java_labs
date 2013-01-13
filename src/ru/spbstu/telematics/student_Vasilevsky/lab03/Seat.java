package ru.spbstu.telematics.student_Vasilevsky.lab03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum seatState {
	SELECTED,
	RESERVED,
	FREE;
}

public class Seat {
	private seatState state;
	private Lock seatLock;
	public final int MAX_SLEEP_TIME = 3000;
	private int number;
	
	public Seat(int number) {
		super();
		this.number = number;
		this.state = seatState.FREE;
		this.seatLock = new ReentrantLock();
	}
	
	public void select() throws InterruptedException {
		seatLock.lock();
		try {
			if (state != seatState.RESERVED) {
				state = seatState.SELECTED;
				System.out.printf("seat %d changed state to SELECTED\n", this.getNumber());
				Thread.sleep((int) (MAX_SLEEP_TIME * Math.random()));
				state = Math.random() * 100 > 50 ? seatState.RESERVED : seatState.FREE;
				System.out.println("seat " + number + " changed state to " + state);
			}
		}
		finally {
			seatLock.unlock();
		}
	}
	
	public int getNumber() {
		seatLock.lock();
		try {
			return number;
		} finally {
			seatLock.unlock();
		}
	}
	
	public seatState getState() {
		seatLock.lock();
		try {
			return state;
		} finally {
			seatLock.unlock();
		}
	}

	public void setState(seatState state) {
		seatLock.lock();
		try {
			this.state = state;
		} finally {
			seatLock.unlock();
		}
	}

}
