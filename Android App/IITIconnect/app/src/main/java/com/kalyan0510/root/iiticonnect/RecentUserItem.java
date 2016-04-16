package com.kalyan0510.root.iiticonnect;

import android.graphics.Bitmap;


/**
 * Created by root on 3/4/16.
 */
public class RecentUserItem   {
    Bitmap image;
    String Name;
    String str;
    String time;
    int id;
    RecentUserItem(Bitmap imag,
            String Nam,
            String st,
            String tim,int id){
        image=imag;
        Name=Nam;
        str=st;
        time=tim;
        this.id = id;
    }
}
