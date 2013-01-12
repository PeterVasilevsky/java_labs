package ru.spbstu.telematics.student_Vasilevsky.lab04.server;

import java.net.Socket;

public class ConnectedClient {
	Socket socket;
	String nickname;
	
	public ConnectedClient(Socket socket, String nickname) {
		super();
		this.socket = socket;
		this.nickname = nickname;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public String getNickname() {
		return nickname;
	}
}
