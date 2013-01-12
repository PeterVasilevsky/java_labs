package ru.spbstu.telematics.student_Vasilevsky.lab04.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;

public class MessageReceiver implements Runnable{
	private Socket s = null;
	private Scanner in = null;
    private String inMessage = null;
    private JTextArea txtrChatarea;
    
	public MessageReceiver(Socket s, JTextArea txtrChatarea) {
		this.s = s;
		this.txtrChatarea = txtrChatarea;
	}
	
	public void run() {
        try {
            in = new Scanner(s.getInputStream());
            while(true){
                if(in.hasNext()) {
                	inMessage = in.nextLine();
                	txtrChatarea.append(inMessage + "\n");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
