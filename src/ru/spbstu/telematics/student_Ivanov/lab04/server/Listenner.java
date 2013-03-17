package ru.spbstu.telematics.student_Ivanov.lab04.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listenner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("server started");
		try {
			ServerSocket ss = new ServerSocket(6667);
			while (true) {
				Socket s = ss.accept();					//ждем нового клиента
				//создаем поток для подключившегося клиента
				ClientThread clientThread = new ClientThread(s);
				Thread t = new Thread(clientThread);
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
