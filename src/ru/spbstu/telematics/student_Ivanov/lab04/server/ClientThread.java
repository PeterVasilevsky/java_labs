package ru.spbstu.telematics.student_Ivanov.lab04.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ru.spbstu.telematics.student_Ivanov.lab04.Message;

public class ClientThread implements Runnable {
	
	private Socket socket;
	private Client client;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Message aMessage;
	
	

	public ClientThread(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			aMessage = (Message) in.readObject();		
			
			
			//создаем и добавляем клиента в список
			client = new Client(aMessage.getFrom(), socket);
			if (ClientsList.addClient(client) == false) {
				client.sendMessage(new Message("server", "%refused%"));
//				in.close();
////				out.close();
//				socket.close();
				return;
			} 
			
			//оповещаем о новом юзере
			System.out.println(client.getNickname() + " connected");	
			aMessage = new Message("server", client.getNickname() + " connected");
			ClientsList.sendToEveryone(aMessage);
//			out.writeObject(aMessage);
//			out.flush();
			
			//рассылаем всем сообщения
			while (aMessage.getText().equals("%exit%") == false) {
				aMessage = (Message) in.readObject();
				System.out.println(aMessage.getFrom() + ": " + aMessage.getText());
				if (aMessage.getText().equals("%exit%") == false) 
					ClientsList.sendToEveryone(aMessage);
			}
			
			//если юзер дисконнектнулся
			aMessage = new Message("server", client.getNickname() + " disconnected");
			ClientsList.removeClient(client);
			ClientsList.sendToEveryone(aMessage);
			in.close();
			socket.close();
			System.out.println(client.getNickname() + " disconnected");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			aMessage = new Message(client.getNickname(), "%exit%");
		}
	}
	
}
