package com.datastructures;

public class SOS {
	public static String setBy = "";
	public static String Message="";
	public static String type="";
	public static Boolean isGenerated=false;
	public static Boolean isSOS(){
		return isGenerated;
	}
	public static void setSOS(String setby,String message,String Type){
		setBy = setby;
		Message = message;
		type = Type;
		isGenerated=true;
		new Thread(new Runnable(){
			public void run(){
				try {
					Thread.sleep(1000*60);
					isGenerated=false;
					Message="";
					type="";
					setBy = "";
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	public static String getSOS(){
		return setBy+'`'+Message+"`"+type;
	}
	public static void removeSOS(){
		Message=""; type="";setBy = "";
		isGenerated = false;
	}
}
