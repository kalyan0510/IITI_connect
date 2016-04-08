package com.kalyan0510.root.iiticonnect;

public class Message {
	int from;
	String message;
	String time;
	String img;
	int status; //0-failed   1- received(tick)  2-read(double/blue)
	public Message(int from_reg,String mess,String time_sent,String pic,int stat){
		from= from_reg;
		message = mess;
		time=time_sent;
		img = pic;
		status = stat;
	}
}
