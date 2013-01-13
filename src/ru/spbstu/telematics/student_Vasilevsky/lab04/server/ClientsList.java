package ru.spbstu.telematics.student_Vasilevsky.lab04.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientsList {
	private static Vector<ConnectedClient>list = new Vector<ConnectedClient>();	//в векторе все методы синхронизированы
	private static Lock clientsLock = new ReentrantLock();

	public static Vector<ConnectedClient> getList() {
		return list;
	}
	
	public static void addClient(Socket socket, String nickname) {
		list.add(new ConnectedClient(socket, nickname));
	}
	
	public static void addClient(ConnectedClient client) {
		list.add(client);
	}
	
	public static void removeClient(Socket socket) {
		clientsLock.lock();
		try {
			for (ConnectedClient client : list) {
				if (client.socket.equals(socket)) {
					list.remove(client);
					return;
				}
			}
		} finally {
			clientsLock.unlock();
		}
	}
	
	public static synchronized boolean containsNickname(String nickname) {
		clientsLock.lock();
		try {
			for (ConnectedClient client: list) {
				if (client.nickname.equals(nickname)) {
					return true;
				}
			}
			return false;
		} finally {
			clientsLock.unlock();
		}
	}
	
}
