package com.kalyan0510.root.iiticonnect;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class OthersProfileActivity extends AppCompatActivity{
    ImageView iv;
    TextView un , fn ,Ml,St;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_profile);
        ((ImageView)findViewById(R.id.header_imageview)).setScaleType(ImageView.ScaleType.FIT_XY);
        iv = ((ImageView)findViewById(R.id.imgdp));
        un= (TextView)findViewById(R.id.usrnameTv);
        fn = (TextView)findViewById(R.id.fnameTv);
        Ml =(TextView)findViewById(R.id.mailTv);
        St = (TextView)findViewById(R.id.statusTv);
        if(!Utilities.isOncampusWifi(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = getIntent();
        getdp(intent.getIntExtra("reg_id",0));
        new getusertask().execute(intent.getIntExtra("reg_id",0));
    }


    public  void getdp(int reg_id) {

        new getdptask().execute(reg_id);
    }

    ////////////////////////DOWNLOAD PROFILE PICTURE//////////////////////////
    class getdptask extends AsyncTask<Integer,String ,String> {
        String result;
        @Override
        protected String doInBackground(Integer... params) {

            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, Utilities.connection.method_names.getdp);
                request.addProperty("reg_id", params[0]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + Utilities.connection.method_names.getdp, envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
                result=envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    result=resultSOAP.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s==null){
                Toast.makeText(getApplicationContext(), "NULL s", Toast.LENGTH_SHORT).show();
                return;
            }
            byte[] array = Base64.decode(s.getBytes(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
            if(bitmap!=null)
                iv.setImageBitmap(bitmap);
            super.onPostExecute(s);
        }
    }
    /////////////////////////////GET USER OBJECT///////////////////////////////////

    class getusertask extends AsyncTask<Integer, String,String>{
        String result="*";
        protected String doInBackground(Integer... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE,"getuser");
                request.addProperty("Reg_id", params[0]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX +"getuser", envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "*";
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return "*";
                }
                result=envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    result=resultSOAP.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "*";
            }
            return result;
        }


        @Override
        protected void onPostExecute(String  s) {
            if(s.equals("*"))
                return;
            User u = new Gson().fromJson(s,User.class);
            un.setText("username: "+u.getUsername());
            fn.setText(u.getFirst_name()+' '+u.getLast_name());
            //ln.setText(u.getLast_name());
            St.setText(u.getStatus());
            Ml.setText(u.getMail());
            //Utilities.currentUser= u;
        }
    }
}
