package ru.spbstu.telematics.ivanov722.lab03;

import java.util.Random;

public class Car implements Runnable {

	private TrafficLight tL;
	private int id;

	Car(TrafficLight tL, int id) {
		this.tL = tL;
		this.id = id;
	}

	public void run() {
		long sleepTime = 1000 + new Random().nextInt(5000);
		System.out.println(tL.getRoute() + " #" + id + " come to a crossroads");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tL.start(id);
		System.out.println(tL.getRoute() + " #" + id
				+ " depart form crossroads");

	}

}