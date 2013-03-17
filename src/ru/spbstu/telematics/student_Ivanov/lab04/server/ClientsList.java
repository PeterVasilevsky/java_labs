package ru.spbstu.telematics.student_Ivanov.lab04.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import ru.spbstu.telematics.student_Ivanov.lab04.Message;

public class ClientsList {
	private static ArrayList<Client>list = new ArrayList<Client>();
	private static ReentrantLock aLock = new ReentrantLock();
	
	public static boolean addClient(Client aClient) {
		boolean result = false;
		aLock.lock();
		try {
			if (containsNickname(aClient.getNickname()) == false) {
				list.add(aClient);
				result = true;
			}
		} finally {
			aLock.unlock();
		}
		return result;
	}
	
	public static void removeClient(Client aClient) {
		aLock.lock();
		try {
			list.remove(aClient);
		} finally {
			aLock.unlock();
		}
	}
	
	public static boolean containsNickname (String aNickname) {
		aLock.lock();
		try {
			for (Client aClient : list) {
				if (aClient.nickname.equals(aNickname)) {
					return true;
				}
			}
		} finally {
			aLock.unlock();
		}
		return false;
	}
	
	public static void sendToEveryone (Message aMessage) {
		aLock.lock();
		try {
			for (Client aClient : list) {
				aClient.sendMessage(aMessage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			aLock.unlock();
		}
	}
}
