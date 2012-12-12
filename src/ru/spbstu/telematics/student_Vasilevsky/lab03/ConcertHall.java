package ru.spbstu.telematics.student_Vasilevsky.lab03;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class ConcertHall {
	
	public static final int TERMINALS_COUNT = 5;
	public static final int SEATS_COUNT = 10;	
	public static final int CONCERT_PERIOD = 15000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Vector<Seat> seatsVector = new Vector<Seat>();
		SelectRunnable.seatsArray = seatsVector;
		
		//создаем стулья
		for (int i = 0; i < SEATS_COUNT; i++) {
			seatsVector.add(new Seat(i));
		}
		
		//создаем потоки терминалов
		for (int i = 0; i < TERMINALS_COUNT; i++) {
			SelectRunnable terminal = new SelectRunnable();
			Thread t = new Thread(terminal, "terminal " + i);
			t.start();
		}
		
		
		//концерты случаются c периодом в CONCERT_PERIOD / 1000 секунд
		Timer timer  = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("It's show time!___________________________");
				for (Seat seat : seatsVector) {
					System.out.println("seat " + seat.getNumber() + "is " + seat.getState());
					seat.setState(seatState.FREE);
				}
				System.out.println("_________________________________________");
			}
		}, CONCERT_PERIOD, CONCERT_PERIOD);
	}

}
