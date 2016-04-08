package com.kalyan0510.root.iiticonnect;

/**
 * Created by root on 13/2/16.
 */

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.UiAutomation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
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
public class MessageReceiver extends WakefulBroadcastReceiver {

    Context contextx;
    public static boolean canNotifySOS = true;
    public static boolean canNotifyWarning = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        contextx = context;
        //WakeLocker.acquire(context);
        //Toast.makeText(context, "MSGR running", Toast.LENGTH_SHORT).show();
        if (!Utilities.isOncampusWifi(context)) {
            //Toast.makeText(context, "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
            return;
        }

        new getNewmessages().execute();
        recursiveBroadcast(context);
        //WakeLocker.release();
    }
    class getNewmessages extends AsyncTask<String,String,String>{
        String result;
        @Override
        protected String doInBackground(String... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, "getMessage");
                request.addProperty("reg", Utilities.getuid(contextx));
                request.addProperty("authentic",Utilities.getauth(contextx));
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + "getMessage", envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error1"+e.getMessage();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return "error2"+e.getMessage();
                }
                result=envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    result=resultSOAP.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "error3"+e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("")){
                return;
            }
            Log.w("x", " "+ s+"\n-------------------------------------------");
            if(s.charAt(0)=='{')
            {Messagelist ml = new Gson().fromJson(s, Messagelist.class);
            for(Message mg:ml.messagelist){
                putMessagetofile(mg);
            }}
        }
    }
    String getmes(int x){
        File f=new File(contextx.getFilesDir()+"/", x+".gk");
        String line="";
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader r = new BufferedReader(new InputStreamReader(fis));
            String str= r.readLine();
            while(str!=null){
                line+=str;
                str  = r.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
    int putMessagetofile(Message mg){

        putrecent(mg);
        File filey = new File(contextx.getFilesDir()+"/", mg.from+"details.gk");
        if(!filey.exists()){
            new putuserdetailsinfile().execute(mg.from);
        }
        putLastMessagetofile(mg);
        FileWriter f;
        File file = new File(contextx.getFilesDir()+"/", mg.from+".gk");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            contextx.getExternalFilesDir("dir");
            f = new FileWriter(contextx.getFilesDir()+"/"+mg.from+".gk",true);
            //Toast.makeText(contextx, "puts: "+new Gson().toJson(mg), Toast.LENGTH_SHORT).show();
            f.write(new Gson().toJson(mg));
            f.flush();

            f.close();
            //Toast.makeText(MainActivity.this, getApplicationContext().getFilesDir()+"", Toast.LENGTH_SHORT).show();

            return 1;
        }
        catch (Exception e){
            e.printStackTrace();


            return 0;
        }

    }
    void putnotify(Message mg){
        int d= mg.from;
        String s = HomeActivity.RecentChat.getusercontent(d);
        User u = new Gson().fromJson(s, User.class);
        setcall(contextx,mg.time,mg.message,u.getFirst_name()+" "+u.getLast_name(),(int)Math.random()*100%100);
    }
    int putLastMessagetofile(Message mg){
        putnotify(mg);
        putrecent(mg);
        FileWriter f;
        File file = new File(contextx.getFilesDir()+"/", mg.from+"last.gk");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            contextx.getExternalFilesDir("dir");
            f = new FileWriter(contextx.getFilesDir()+"/"+mg.from+"last.gk",false);
            Toast.makeText(contextx, "main open", Toast.LENGTH_SHORT).show();
            f.write(new Gson().toJson(mg));
            f.flush();

            f.close();
            //Toast.makeText(MainActivity.this, getApplicationContext().getFilesDir()+"", Toast.LENGTH_SHORT).show();



            return 1;
        }
        catch (Exception e){
            e.printStackTrace();


            return 0;
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
                .setContentTitle("Message from "+user)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);


        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        notificationBuilder.setStyle(inboxStyle);
        inboxStyle.setBigContentTitle(" "+user);
        inboxStyle.addLine(""+type);
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


    public class putuserdetailsinfile extends AsyncTask<Integer,String ,String>{
        String result;
        int id;
        @Override
        protected String doInBackground(Integer... params) {
            id = params[0];
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, Utilities.connection.method_names.getu);
                request.addProperty("reg_id", params[0]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url + Utilities.connection.x + Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + Utilities.connection.method_names.getu, envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
                result = envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    result = resultSOAP.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //User u  = new Gson().fromJson(s,User.class);
            FileWriter f;
            File file = new File(contextx.getFilesDir()+"/", id+"details.gk");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            try {
                contextx.getExternalFilesDir("dir");

                FileOutputStream fos = contextx.openFileOutput(id+"details.gk", Context.MODE_PRIVATE);
                fos.write(s.getBytes());

                fos.close();

            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(contextx, "Problem in saving user", Toast.LENGTH_SHORT).show();


            }
            /*try {
                contextx.getExternalFilesDir("dir");
                f = new FileWriter(contextx.getFilesDir()+"/"+id+"details.gk",false);
                //Toast.makeText(contextx, "puts: "+new Gson().toJson(mg), Toast.LENGTH_SHORT).show();
                f.write(new Gson().toJson(s));
                f.flush();

                f.close();
                //Toast.makeText(MainActivity.this, getApplicationContext().getFilesDir()+"", Toast.LENGTH_SHORT).show();

                return 1;
            }
            catch (Exception e){
                e.printStackTrace();


                return 0;
            }*/


        }
    }

    int putrecent(Message mg){

        SharedPreferences sp = contextx.getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
        String s= sp.getString(Utilities.SharesPresfKeys.recents, "");
        Recents rc;
        if(!s.equals("")){
         rc = new Gson().fromJson(s,Recents.class);
            rc.recentids.remove(Integer.valueOf(mg.from));
            rc.recentids.add(mg.from);

        }else {
            rc = new Recents();
            rc.recentids.add(mg.from);
        }
        SharedPreferences.Editor editor = sp.edit();
        Toast.makeText(contextx, ""+new Gson().toJson(rc), Toast.LENGTH_SHORT).show();
        editor.putString(Utilities.SharesPresfKeys.recents, new Gson().toJson(rc));
        editor.commit();
        return 1;
    }

    void recursiveBroadcast(Context context) {
        Context bc = context;
        Context c = context;
        int interval = 5;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        Intent intentm = new Intent(context, MessageReceiver.class);
        PendingIntent pendingIntentm =
                PendingIntent.getBroadcast(context,
                        1, intentm, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManagerm =
                (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            alarmManagerm.set(AlarmManager.RTC_WAKEUP,
                    cal.getTimeInMillis(), pendingIntentm);

        } else {
          //  Toast.makeText(context, "initialising mgbr", Toast.LENGTH_SHORT).show();
            alarmManagerm.setExact(AlarmManager.RTC_WAKEUP,
                    cal.getTimeInMillis(), pendingIntentm);
        }
    }
}