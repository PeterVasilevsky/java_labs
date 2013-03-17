package ru.spbstu.telematics.student_Ivanov.lab03;

import java.util.Random;

public class CarGreenNS implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				//ждем до 2 секунд прежде чем респауниться
				long sleepTime = new Random().nextInt(2000);
//				long sleepTime = 20;
				Thread.sleep(sleepTime);
				
				CrossRoad.getLockCardirN().lock();
				System.out.println("Зеленая машина (N) подъезжает к перекрестку");
				CrossRoad.setBusyCardirN(true);
				
				while (CrossRoad.isBusyCardirW() == true) {
//					System.out.println("green has to give road to red");
					continue;
				}
				
//				CrossRoad.getLockCardirE().lock();
				System.out.println("Зеленая машина (N) выезжает на перекресток");
//				CrossRoad.setBusyCardirE(true);
				
				CrossRoad.getLockSectionWN().lock();
				System.out.println("Зеленая машина (N) занимает секцию WN");
				CrossRoad.setBusySectionWN(true);
				
				CrossRoad.getLockSectionWS().lock();
				System.out.println("Зеленая машина (N) занимает секцию WS");
				CrossRoad.setBusySectionWS(true);
				
				try {					
					//захватили, радуемся и ждем какое-то время
					Thread.sleep(1);
					
					//отпускаем всех, кого захватили
					CrossRoad.setBusyCardirN(false);	//захватываем свою сторону света
					CrossRoad.setBusyCardirE(false);	//захватываем сторону света, которая слева
					CrossRoad.setBusySectionWN(false);		
					CrossRoad.setBusySectionWS(false);
					
				} finally {
					CrossRoad.getLockCardirN().unlock();
//					CrossRoad.getLockCardirE().unlock();
					CrossRoad.getLockSectionWN().unlock();
					CrossRoad.getLockSectionWS().unlock();
				}
				
				System.out.println("Зеленая машина (N) покинула перекресток");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
