package com.kalyan0510.root.iiticonnect;

import android.graphics.Bitmap;

import com.orm.SugarRecord;

/**
 * Created by root on 3/4/16.
 */
public class RecentUserItem extends SugarRecord {
    Bitmap image;
    String Name;
    String str;
    String time;
    RecentUserItem(Bitmap imag,
            String Nam,
            String st,
            String tim){
        image=imag;
        Name=Nam;
        str=st;
        time=tim;
    }
}
