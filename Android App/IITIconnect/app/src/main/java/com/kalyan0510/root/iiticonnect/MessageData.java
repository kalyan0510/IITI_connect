package com.kalyan0510.root.iiticonnect;

/**
 * Created by root on 16/4/16.
 */

public class MessageData {
    private String message;
    private String time;
    public int who;
    public static int ME=0;
    public static int HE=1;
    public MessageData(String message, String time, int who) {
        this.message = message;
        this.time = time;
        this.who = who;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

}