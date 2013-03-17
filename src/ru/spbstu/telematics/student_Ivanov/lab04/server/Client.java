package ru.spbstu.telematics.student_Ivanov.lab04.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ru.spbstu.telematics.student_Ivanov.lab04.Message;

public class Client {
	String nickname;
	Socket socket;
	ObjectOutputStream out;
	
	public Client(String nickname, Socket socket) throws IOException {
		super();
		this.nickname = nickname;
		this.socket = socket;
		out = new ObjectOutputStream(this.socket.getOutputStream());
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void sendMessage(Message aMessage) throws IOException {
		out.writeObject(aMessage);
		out.flush();
	}
}
