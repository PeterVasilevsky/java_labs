package ru.spbstu.telematics.student_Vasilevsky.lab04.server;

import java.net.Socket;
import java.util.ArrayList;

public class ClientsList {
	private static ArrayList<ConnectedClient>list = new ArrayList<ConnectedClient>();

	public static synchronized ArrayList<ConnectedClient> getList() {
		return list;
	}
	
	public static synchronized void addClient(Socket socket, String nickname) {
		list.add(new ConnectedClient(socket, nickname));
	}
	
	public static synchronized void addClient(ConnectedClient client) {
		list.add(client);
	}
	
	public static synchronized void removeClient(Socket socket) {
		for (ConnectedClient client : list) {
			if (client.socket.equals(socket)) {
				list.remove(client);
				return;
			}
		}
	}
	
	public static synchronized boolean containsNickname(String nickname) {
		for (ConnectedClient client: list) {
			if (client.nickname.equals(nickname)) {
				return true;
			}
		}
		return false;
	}
	
}
