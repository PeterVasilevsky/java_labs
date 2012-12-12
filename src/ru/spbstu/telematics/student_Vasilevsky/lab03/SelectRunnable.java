package ru.spbstu.telematics.student_Vasilevsky.lab03;

import java.util.Vector;

public class SelectRunnable implements Runnable {
	
	public static Vector<Seat> seatsArray;
	private Seat seat;
	private final int DELAY = 10;
	
	public void run() {
		try {
			while (true) {
				seat = seatsArray.get((int) (Math.random() * seatsArray.size()));
				seat.select();
				Thread.sleep((long) (Math.random() * DELAY));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
