package com.kalyan0510.root.iiticonnect;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.parse.Parse;
import com.parse.ParseObject;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends Activity {
    TextView tv;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context  = getApplicationContext();
        setContentView(R.layout.activity_main);
        //new HomeActivity.RecentChat.getuserforsearch().execute(this);
        WebView wv = (WebView)findViewById(R.id.logo);
        wv.loadUrl("file:///android_asset/htmll.html");


        //Toast.makeText(MainActivity.this, "Main", Toast.LENGTH_SHORT).show();
                (findViewById(R.id.rl)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
                int reg_id = sp.getInt(Utilities.SharesPresfKeys.regid, 0);
                if (reg_id == 0) {
                    //open signup or login page activity
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else {
                    //open Home
                    //Toast.makeText(MainActivity.this, "HOME HOME HOME " + reg_id, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
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
                        Toast.makeText(context,
                                "Broadcast Started",
                                Toast.LENGTH_LONG).show();
                    } else {
                   //     Toast.makeText(MainActivity.this, "initialising mgbr", Toast.LENGTH_SHORT).show();
                        alarmManagerm.setExact(AlarmManager.RTC_WAKEUP,
                                cal.getTimeInMillis(), pendingIntentm);
                    }

                    startActivity(intent);
                }
                finish();


            }
        });
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
//        Toast.makeText(MainActivity.this, "Services initialising", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, RecursiveReceiver.class);
        PendingIntent pendingIntent =
        PendingIntent.getBroadcast(context,
        1, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager =
        (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        alarmManager.set(AlarmManager.RTC_WAKEUP,
        cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(context,
        "Broadcast Started",
        Toast.LENGTH_LONG).show();
        } else {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
        cal.getTimeInMillis(), pendingIntent);
        }



        if(Utilities.isOncampusWifi(context)){
        Log.w("x","Calling check mac execute");
        new checkmacregistered().execute();
        }else {
        Log.w("x","Skipped");
        }
        // Toast.makeText(MainActivity.this, "skipped", Toast.LENGTH_SHORT).show();
        //else


        }

class checkmacregistered extends AsyncTask<String,String,String>{
    String result;

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Log.w("x","In mac check start");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            SoapObject request = new SoapObject(Utilities.connection.NAMESPACE,"wasMacSet");
            request.addProperty("mac",Utilities.getwifimac(context));
            envelope.bodyOut = request;
            HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
            Log.w("x","In mac check BEFORE TRANS.CALL");
            try {
                transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX +"wasMacSet", envelope);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                return "error";
            }
            Log.w("x","In mac check After TRANS.CALL");
            result=envelope.getResponse().toString();
            if (envelope.bodyIn != null) {
                SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                result=resultSOAP.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
        }
        Log.w("x", "In mac check returning res " + result);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

        if(s.equals("error")){
            Toast.makeText(getApplicationContext(), "Server is currently down", Toast.LENGTH_SHORT).show();
            return;
        }

        //Toast.makeText(getApplicationContext(), "Server ", Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
        if(s.equals("false")){
            // Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
            // Toast.makeText(context, ""+Utilities.getwifimac(context), Toast.LENGTH_SHORT).show();
            ////////CALL for set mac//////////
            createpopup();
        }else {
            // finish();
            SharedPreferences sp = getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
            int reg_id = sp.getInt(Utilities.SharesPresfKeys.regid, 0);
            if (reg_id == 0) {
                //open signup or login page activity

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            } else {
                //open Home
                //Toast.makeText(MainActivity.this, "HOME HOME HOME " + reg_id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
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
                    Toast.makeText(context,
                            "Broadcast Started",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "initialising mgbr", Toast.LENGTH_SHORT).show();
                    alarmManagerm.setExact(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), pendingIntentm);
                }

                startActivity(intent);
            }
            finish();
            //Toast.makeText(context, "FINISH called", Toast.LENGTH_SHORT).show();
        }
    }
}
class setAddresstask extends AsyncTask<String,String,String>{
    String result;
    @Override
    protected String doInBackground(String... params) {
        try {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            SoapObject request = new SoapObject(Utilities.connection.NAMESPACE,"setAddress");
            request.addProperty("address",params[0]);
            request.addProperty("mac",Utilities.getwifimac(context));
            request.addProperty("reg_id",getSharedPreferences(Utilities.SharesPresfKeys.key,Context.MODE_PRIVATE).getInt("reg_id",0));

            envelope.bodyOut = request;
            HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
            try {
                transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX +"setAddress", envelope);
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

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("error")){
            Toast.makeText(getApplicationContext(), "Server is currently down", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(), "Server running", Toast.LENGTH_SHORT).show();
        super.onPostExecute(s);
        if(s.equals("false")){
            Toast.makeText(context, "Thanks for helping", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "failed while setting address", Toast.LENGTH_SHORT).show();
        }
        pw.dismiss();

        SharedPreferences sp = getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
        int reg_id = sp.getInt(Utilities.SharesPresfKeys.regid, 0);
        if (reg_id == 0) {
            //open signup or login page activity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else {
            //open Home
            //Toast.makeText(MainActivity.this, "HOME HOME HOME " + reg_id, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
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
                Toast.makeText(context,
                        "Broadcast Started",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "initialising mgbr", Toast.LENGTH_SHORT).show();
                alarmManagerm.setExact(AlarmManager.RTC_WAKEUP,
                        cal.getTimeInMillis(), pendingIntentm);
            }


            startActivity(intent);
        }
        finish();
        //Toast.makeText(context, "FINISHxxxx called", Toast.LENGTH_SHORT).show();
    }
}
PopupWindow pw;
    public void createpopup(){
        // Toast.makeText(MainActivity.this, "popup creating", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater  = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_askaddress,(ViewGroup)findViewById(R.id.popup));
        final EditText addressEdt = (EditText)layout.findViewById(R.id.setAddressEdt);
        Button addressBut = (Button)layout.findViewById(R.id.setAddressBut);
        addressBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = addressEdt.getText().toString();
                if(str.equals("")){
                    Toast.makeText(MainActivity.this, "please enter valid Address", Toast.LENGTH_SHORT).show();
                }else{
                    new setAddresstask().execute(str);
                }
            }
        });
        pw = new PopupWindow(layout,300,370,true);
        pw.setHeight(800);
        pw.setWidth(500);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);



    }






}



