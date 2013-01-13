package ru.spbstu.telematics.student_Vasilevsky.lab04.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class ClientThread implements Runnable {

	private Socket socket;
	private String nickname;
	private ConnectedClient client;
	
	private Scanner in = null;
    private PrintWriter out = null;
    private boolean exit = true;
    private String inMessage = null;
    

    public ClientThread(Socket s) {
    	this.socket = s;
    }

    public void run() { 
        try {
        	System.out.println("User connect...");
        	in = new Scanner(this.socket.getInputStream());
        	nickname = in.nextLine();
        	out = new PrintWriter(this.socket.getOutputStream());
        	
        	if (ClientsList.containsNickname(nickname)) {
        		System.out.println(nickname + " is kicked off");
        		out.println("Error connecting to server: nickname is busy");
				return;
			}
        	this.client = new ConnectedClient(socket, nickname);
        	ClientsList.addClient(this.client);	//добавление клиента в список клиентов сервера
            System.out.println(this.client.nickname + " is conected");
            out.println("Welcome to chat, " + this.client.nickname + "!");
            out.flush();
            
            //оповещение о присоединенном юзере
            ClientsList.sendToEveryone(nickname + " connected", this.client);
            
            while (exit) {
            	if (in.hasNext()) {
            		inMessage = in.nextLine();
            		if (inMessage.trim().equals("chat:exit")) {
            			System.out.println("attempt to disconnect");
            			exit = false;
            			continue;
            		}
                    System.out.println("message: "+inMessage);
                    ClientsList.sendToEveryone(nickname + ": " + inMessage, null);
				}
            }  
            ClientsList.removeClient(this.socket);
            
            //оповещение об ушедшем юзере
            System.out.println(nickname + " disconnected");
            ClientsList.sendToEveryone(nickname + " is disconnected", null);
            
        } catch (IOException ex) {
            try {
            	this.socket.close();
            } catch (IOException ex1) {
            	ex1.printStackTrace();
            }
            ex.printStackTrace();
        }
    }
}
