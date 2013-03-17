package ru.spbstu.telematics.student_Ivanov.lab03;

import java.util.Random;

public class CarBlueSN implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				//ждем до 2 секунд прежде чем респауниться
				long sleepTime = new Random().nextInt(2000);
//				long sleepTime = 20;
				Thread.sleep(sleepTime);
				
				CrossRoad.getLockCardirS().lock();
				System.out.println("Синяя машина (S) подъезжает к перекрестку");
				CrossRoad.setBusyCardirS(true);
				
				while (CrossRoad.isBusyCardirE() == true)
					continue;
				
//				CrossRoad.getLockCardirW().lock();
				System.out.println("Синяя машина (S) выезжает на перекресток");
//				CrossRoad.setBusyCardirW(true);
				
				CrossRoad.getLockSectionSE().lock();
				System.out.println("Синяя машина (S) занимает секцию SE");
				CrossRoad.setBusySectionSE(true);
				
				CrossRoad.getLockSectionNE().lock();
				System.out.println("Синяя машина (S) занимает секцию NE");
				CrossRoad.setBusySectionNE(true);
				
				try {					
					//захватили, радуемся и ждем какое-то время
					Thread.sleep(1);
					
					//отпускаем всех, кого захватили
					CrossRoad.setBusyCardirS(false);	//захватываем свою сторону света
					CrossRoad.setBusyCardirW(false);	//захватываем сторону света, которая слева
					CrossRoad.setBusySectionSE(false);		
					CrossRoad.setBusySectionNE(false);
					
				} finally {
					CrossRoad.getLockCardirS().unlock();
//					CrossRoad.getLockCardirW().unlock();
					CrossRoad.getLockSectionSE().unlock();
					CrossRoad.getLockSectionNE().unlock();
				}
				
				System.out.println("Синяя машина (S) покинула перекресток");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
