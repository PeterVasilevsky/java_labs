package ru.spbstu.telematics.student_Ivanov.lab03;

import java.util.Random;

public class CarBrownES implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				//ждем до 2 секунд прежде чем респауниться
				long sleepTime = new Random().nextInt(2000);
//				long sleepTime = 20;
				Thread.sleep(sleepTime);
				
				CrossRoad.getLockCardirE().lock();
				System.out.println("Коричневая машина (E) подъезжает к перекрестку");
				CrossRoad.setBusyCardirE(true);
				
				while (CrossRoad.isBusyCardirS() == true || CrossRoad.isBusyCardirS() == true)
					continue;
				
//				CrossRoad.getLockCardirS().lock();
				System.out.println("Коричневая машина (Е) выезжает на перекресток");
//				CrossRoad.setBusyCardirS(true);
				
				CrossRoad.getLockSectionNE().lock();
				System.out.println("Коричневая машина (Е) занимает секцию NE");
				CrossRoad.setBusySectionNE(true);
				
//				//пропускаем красную машину, если есть
//				while (CrossRoad.isBusyCardirW() == true)
//					continue;
				
				CrossRoad.getLockSectionWS().lock();
				System.out.println("Коричневая машина (Е) занимает секцию WS");
				CrossRoad.setBusySectionWS(true);
				
				try {					
					//захватили, радуемся и ждем какое-то время
					Thread.sleep(1);
					
					//отпускаем всех, кого захватили
					CrossRoad.setBusyCardirE(false);	//захватываем свою сторону света
					CrossRoad.setBusyCardirS(false);	//захватываем сторону света, которая слева
					CrossRoad.setBusySectionNE(false);		
					CrossRoad.setBusySectionWS(false);
					
				} finally {
					CrossRoad.getLockCardirE().unlock();
//					CrossRoad.getLockCardirS().unlock();
					CrossRoad.getLockSectionNE().unlock();
					CrossRoad.getLockSectionWS().unlock();
				}
				
				System.out.println("Коричневая машина (Е) покинула перекресток");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}	
	}

}
