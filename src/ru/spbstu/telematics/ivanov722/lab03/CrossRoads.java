package ru.spbstu.telematics.ivanov722.lab03;

import java.util.ArrayList;
import java.util.Random;

public class CrossRoads {

	private static int numOfThread = 50;

	public static void main(String[] args) throws InterruptedException {

		Random random = new Random();
		int typeNextCar;

		CriticalSection collision = new CriticalSection();

		TrafficLight tLSN = new TrafficLight("SN", 0, collision);
		TrafficLight tLNS = new TrafficLight("NS", 0, collision);
		TrafficLight tLWE = new TrafficLight("WE", 0, collision);
		TrafficLight tLES = new TrafficLight("ES", 0, collision);

		//переделать в bool
		int SN = 0;
		int NS = 0;
		int WE = 0;
		int ES = 0;
		
//		boolean SN = false;
//		boolean NS = false;
//		boolean WE = false;
//		boolean ES = false;

		ArrayList<Thread> threads = new ArrayList<Thread>();

		Thread tempThread;

		while (threads.size() < numOfThread) {
			Thread.sleep(500);
			typeNextCar = random.nextInt(4) + 1;
			switch (typeNextCar) {
			case 1:
				tempThread = new Thread(new Car(tLSN, SN++));
				tempThread.start();
				threads.add(tempThread);
				break;
			case 2:
				tempThread = new Thread(new Car(tLNS, NS++));
				tempThread.start();
				threads.add(tempThread);
				break;
			case 3:
				tempThread = new Thread(new Car(tLWE, WE++));
				tempThread.start();
				threads.add(tempThread);
				break;

			case 4:
				tempThread = new Thread(new Car(tLES, ES++));
				tempThread.start();
				threads.add(tempThread);
				break;

			}

		}
		for (Thread th : threads) {
			th.join();
		}
		System.out.println("Finish");

	}

}