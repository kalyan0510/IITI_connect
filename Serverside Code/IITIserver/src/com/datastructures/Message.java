package com.datastructures;

public class Message {
	int from;
	String message;
	String from_name;
	String time;
	String img;
	int status; //0-failed   1- received(tick)  2-read(double/blue)
	public Message(int from_reg,String name,String mess,String time_sent,String pic,int stat){
		from= from_reg;
		from_name = name;
		message = mess;
		time=time_sent;
		img = pic;
		status = stat;
	}
}
