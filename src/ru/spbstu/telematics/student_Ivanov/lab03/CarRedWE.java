package ru.spbstu.telematics.student_Ivanov.lab03;

import java.util.Random;

public class CarRedWE implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				//ждем до 2 секунд прежде чем респауниться
				long sleepTime = new Random().nextInt(2000);
//				long sleepTime = 2000;
				Thread.sleep(sleepTime);
				
				CrossRoad.getLockCardirW().lock();
				System.out.println("Красная машина (W) подъезжает к перекрестку");
				CrossRoad.setBusyCardirW(true);
				
				//ждем пока помеха справа
				while (CrossRoad.isBusyCardirS())
					continue;
				
//				CrossRoad.getLockCardirN().lock();
				System.out.println("Красная машина (W) выезжает на перекресток");
//				CrossRoad.setBusyCardirN(true);
				
				CrossRoad.getLockSectionWS().lock();
				System.out.println("Красная машина (W) занимает секцию WS");
				CrossRoad.setBusySectionWS(true);
				
				CrossRoad.getLockSectionSE().lock();
				System.out.println("Красная машина (W) занимает секцию SE");
				CrossRoad.setBusySectionSE(true);
				
				try {//					
					//захватили, радуемся и ждем какое-то время
					Thread.sleep(1);
					
					//отпускаем всех, кого захватили
					CrossRoad.setBusyCardirW(false);	//захватываем свою сторону света
					CrossRoad.setBusyCardirN(false);	//захватываем сторону света, которая слева
					CrossRoad.setBusySectionWS(false);		
					CrossRoad.setBusySectionSE(false);
					
				} finally {
					CrossRoad.getLockCardirW().unlock();
//					CrossRoad.getLockCardirN().unlock();
					CrossRoad.getLockSectionWS().unlock();
					CrossRoad.getLockSectionSE().unlock();
				}
				
				System.out.println("Красная машина (W) покинула перекресток");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
