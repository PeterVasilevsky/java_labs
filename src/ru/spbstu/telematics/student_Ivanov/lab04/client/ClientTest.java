package ru.spbstu.telematics.student_Ivanov.lab04.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream.GetField;
import java.net.Socket;
import java.net.UnknownHostException;

import ru.spbstu.telematics.student_Ivanov.lab04.Message;

public class ClientTest {
	
	static Socket socket;
	static ObjectOutputStream out;
	static ObjectInputStream in;
	
	public static void main(String[] args) {
		try {
			socket = new Socket("127.0.0.1", 6667);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Nickname: ");
			String nickname = bufferRead.readLine();
			Message aMessage = new Message(nickname, "%hello%");
			out.writeObject(aMessage);
			out.flush();
			
			//поток для приемника сообщений от сервера
			ClientReceiver aReceiver = new ClientReceiver(socket, in);
			Thread receiverThread = new Thread(aReceiver);
			receiverThread.start();
	
			while (true) {
				//считываем строку с консоли
				String textMessage = bufferRead.readLine();
				aMessage = new Message(nickname, textMessage);
				out.writeObject(aMessage);
				out.flush();
			}
			
		} catch (UnknownHostException e) {
			System.out.println("Unknown host");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

}
