package com.datastructures;

public class Warning {
	public static String setBy = "";
	public static String Message="";
	public static String type="";
	public static Boolean isGenerated=false;
	public static Boolean isWarning(){
		return isGenerated;
	}
	public static void setWarning(String setby,String message,String Type){
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
	public static String getWarning(){
		return setBy+'`'+Message+"`"+type;
	}
	public static void removeWarning(){
		Message=""; type="";setBy = "";
		isGenerated = false;
	}
}
