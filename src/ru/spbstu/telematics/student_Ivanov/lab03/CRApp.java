package ru.spbstu.telematics.student_Ivanov.lab03;

public class CRApp {
	public static void main(String[] args) {
		 CarBlueSN carBlueSN = new CarBlueSN();
		 CarBrownES carBrownES = new CarBrownES();
		 CarGreenNS carGreenNS = new CarGreenNS();
		 CarRedWE carRedWE = new CarRedWE();
		 
		 Thread threadCarBlueSN = new Thread(carBlueSN);
		 Thread threadCarBrownES = new Thread(carBrownES);
		 Thread threadCarGreenNS = new Thread(carGreenNS);
		 Thread threadCarRedWE = new Thread(carRedWE);
		 
		 threadCarBlueSN.start();
		 threadCarBrownES.start();
		 threadCarGreenNS.start();
		 threadCarRedWE.start();
	}
}
