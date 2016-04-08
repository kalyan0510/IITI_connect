package com.kalyan0510.root.iiticonnect;

/**
 * Created by root on 13/2/16.
 */

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by root on 13/2/16.
 */
public class RecursiveReceiver extends WakefulBroadcastReceiver {

    Context contextx;
    public static boolean canNotifySOS=true;
    public static boolean canNotifyWarning=true;
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "recursive started", Toast.LENGTH_SHORT).show();
        contextx = context;
        //WakeLocker.acquire(context);
        if(!Utilities.isOncampusWifi(context)){
            //Toast.makeText(context, "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
            return;
        }
        new checkWarning().execute();
        new checkSOS().execute();
        recursiveBroadcast(context);
        //WakeLocker.release();
    }

    void recursiveBroadcast(Context context){
        Context bc = context;
        Context c = context;
        int interval=5;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, interval);
        Intent xtent = new Intent(bc, RecursiveReceiver.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(bc,
                        1, xtent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager =
                (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);


        alarmManager.set(AlarmManager.RTC_WAKEUP,
                cal.getTimeInMillis(), pendingIntent);
    }

    class checkWarning extends AsyncTask<String,String,String> {
        String result;
        protected String doInBackground(String... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, "isWarnignGenerated");

                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + "isWarnignGenerated", envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return "error";
                }
                result=envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    result=resultSOAP.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "error";
            }
            if(result.equals("no"))
                return result;
            else{
                //get the Alert details
                try{
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, "getWarning");
                    envelope.bodyOut = request;
                    HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                    try {
                        transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + "getWarning", envelope);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "error";
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                        return "error";
                    }
                    result=envelope.getResponse().toString();
                    if (envelope.bodyIn != null) {
                        SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                        result=resultSOAP.toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result = "error";
                }
                return result;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("error")){
                return;
            }
            System.out.println(s + "\n");
            if(!s.equals("no")){
                WakeLocker.acquire(contextx);
                //Toast.makeText(contextx, "Move your ass out of this building", Toast.LENGTH_SHORT).show();
                String[] x = s.split("`");
                if(x.length>=3)
                setcall(contextx,x[1],x[2]+" ",x[0],4);
                else{
                    setcall(contextx,"No description","no Message ","Idiot",4);
                }

                WakeLocker.release();

            }
        }
    }
    class checkSOS extends AsyncTask<String,String,String> {
        String result;
        protected String doInBackground(String... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, "isSOSGenerated");

                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + "isSOSGenerated", envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return "error";
                }
                result=envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    result=resultSOAP.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "error";
            }
            if(result.equals("no"))
                return result;
            else{
                //get the Alert details
                try{
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, "getSOS");
                    envelope.bodyOut = request;
                    HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                    try {
                        transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + "getSOS", envelope);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "error";
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                        return "error";
                    }
                    result=envelope.getResponse().toString();
                    if (envelope.bodyIn != null) {
                        SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                        result=resultSOAP.toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result = "error";
                }
                return result;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("error"))
                return;
            System.out.println(s + "\n");
            if(!s.equals("no")){
                WakeLocker.acquire(contextx);
                //Toast.makeText(contextx, "Help him", Toast.LENGTH_SHORT).show();
                String[] x = s.split("`");
                if(x.length>=3)
                    setSOScall(contextx, x[1], x[2] + " ", x[0], 4);
                else{
                    setSOScall(contextx, "No description", "no Message ", "Idiot", 4);
                }

                WakeLocker.release();

            }
        }
    }
    public void setcall(Context c,String descr,String type,String user,int id) {
        // Toast.makeText(this,"call", Toast.LENGTH_SHORT).show();
        if(!canNotifyWarning){
            return;
        }
        if(!Utilities.isOncampusWifi(c)){
            //Toast.makeText(context, "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(c)
                .setSmallIcon(R.mipmap.editw)
                .setContentTitle("Warning from "+user)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        notificationBuilder.setStyle(inboxStyle);
        inboxStyle.setBigContentTitle("from "+user);
        inboxStyle.addLine("type - "+type);
        inboxStyle.setSummaryText(descr);
        notificationBuilder.setStyle(inboxStyle);

        NotificationManager notificationManager =
                (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        int NOTIFICATION_ID = 100+id;
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        new Thread(new Runnable() {
            @Override
            public void run() {
                canNotifyWarning = false;
                try {
                    Thread.sleep(1000*50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canNotifyWarning = true;
            }
        }).start();
    }
    public void setSOScall(Context c,String loc,String abt,String user,int id) {
        // Toast.makeText(this,"call", Toast.LENGTH_SHORT).show();
        if(!canNotifySOS){
            return;
        }
        if(!Utilities.isOncampusWifi(c)){
            //Toast.makeText(context, "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
            return;
        }
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(c)
                .setSmallIcon(R.mipmap.editw)
                .setContentTitle(""+user+":help me")
                .setAutoCancel(true)
                .setSound(defaultSoundUri);


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        notificationBuilder.setStyle(inboxStyle);
        inboxStyle.setBigContentTitle("from " + user);
        inboxStyle.addLine("Location - " + loc);
        inboxStyle.setSummaryText("message:" + abt);
        notificationBuilder.setStyle(inboxStyle);

        NotificationManager notificationManager =
                (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        int NOTIFICATION_ID = 103+id;
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        new Thread(new Runnable() {
            @Override
            public void run() {
                canNotifySOS = false;
                try {
                    Thread.sleep(1000*50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                canNotifySOS = true;
            }
        }).start();
    }


}
