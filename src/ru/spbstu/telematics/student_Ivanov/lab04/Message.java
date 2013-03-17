package ru.spbstu.telematics.student_Ivanov.lab04;

import java.io.Serializable;

public class Message implements Serializable {
	String from;
	String text;
	
	public String getFrom() {
		return from;
	}
	public String getText() {
		return text;
	}
	public Message(String from, String text) {
		super();
		this.from = from;
		this.text = text;
	}
}
