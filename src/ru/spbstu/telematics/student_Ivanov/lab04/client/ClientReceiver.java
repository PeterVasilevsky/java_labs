package ru.spbstu.telematics.student_Ivanov.lab04.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JTextArea;

import ru.spbstu.telematics.student_Ivanov.lab04.Message;

public class ClientReceiver implements Runnable {
	private Socket socket;
	private ObjectInputStream in;
	private javax.swing.JTextArea txtrMainArea;
	
	

	public ClientReceiver(Socket socket, ObjectInputStream in) {
		super();
		this.socket = socket;
		this.in = in;
	}


	public ClientReceiver(Socket socket, ObjectInputStream in,
			JTextArea txtrMainArea) {
		super();
		this.socket = socket;
		this.in = in;
		this.txtrMainArea = txtrMainArea;
	}


	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			Message aMessage;
			while (socket.isClosed() == false) {
				aMessage = (Message) in.readObject(); 
				if (aMessage.getText().equals("%refused")) {
					txtrMainArea.setText("Connection refused");
				}
				txtrMainArea.append(aMessage.getFrom() + ": " + aMessage.getText() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
