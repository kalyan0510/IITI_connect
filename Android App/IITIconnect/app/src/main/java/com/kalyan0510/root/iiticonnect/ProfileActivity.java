package com.kalyan0510.root.iiticonnect;

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

public class ProfileActivity extends AppCompatActivity {
    Uri selectedImageUri;
    String selectedImagePath,status;
    ImageView iv;
    TextView un , fn , ln ,Ml,St;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        iv = ((ImageView)findViewById(R.id.imgdp));
        un= (TextView)findViewById(R.id.usrnameTv);
        fn = (TextView)findViewById(R.id.fnameTv);
        ln = (TextView)findViewById(R.id.lnameTv);
        Ml =(TextView)findViewById(R.id.mailTv);
        St = (TextView)findViewById(R.id.statusTv);
        Ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
        findViewById(R.id.changestatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Utilities.isOncampusWifi(getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
                    return;
                }
               Intent i =new Intent(getApplicationContext(),changeStatusActivity.class);
                i.putExtra("status",St.getText().toString());
                startActivity(i);

            }
        });
        findViewById(R.id.editbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Utilities.isOncampusWifi(getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 456);
            }
        });
        findViewById(R.id.changepass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Utilities.isOncampusWifi(getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(getApplicationContext(),ChangePwdActivity.class);
                startActivity(i);
            }
        });
        if(!Utilities.isOncampusWifi(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
            return;
        }
        getdp(getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getInt("reg_id", 0));
        new getusertask().execute(getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getInt("reg_id", 0));
        findViewById(R.id.alarmset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 1);

                Intent intent = new Intent(getBaseContext(), RecursiveReceiver.class);
                PendingIntent pendingIntent =
                        PendingIntent.getBroadcast(getBaseContext(),
                                1, intent, PendingIntent.FLAG_ONE_SHOT);
                AlarmManager alarmManager =
                        (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), pendingIntent);
                    Toast.makeText(getBaseContext(),
                            "Broadcast Started",
                            Toast.LENGTH_LONG).show();
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), pendingIntent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getdp(getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getInt("reg_id", 0));
        if(!Utilities.isOncampusWifi(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
            return;
        }
        new getusertask().execute(getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getInt("reg_id", 0));
    }

    public  void getdp(int reg_id) {

        new getdptask().execute(reg_id);
    }
    public void setprofilepic( Uri selectedimgURI){
        new setprofilepictask().execute(selectedimgURI);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == 456) {
                selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                if (selectedImagePath == null)
                    return;
                iv.setImageURI(selectedImageUri);
                setprofilepic( selectedImageUri);
            }
        }
    }
    public String getPath(Uri uri){
        if(uri==null)
            return null;
        return uri.getPath();
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
            iv.setImageBitmap(bitmap);
            super.onPostExecute(s);
        }
    }
    /////////////////////////////GET USER OBJECT///////////////////////////////////

     class getusertask extends AsyncTask<Integer, String,String>{
        String result;
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
        protected void onPostExecute(String  s) {
            User u = new Gson().fromJson(s,User.class);
            un.setText(u.getUsername());
            fn.setText(u.getFirst_name());
            ln.setText(u.getLast_name());
            St.setText(u.getStatus());
            Ml.setText(u.getMail());
            Utilities.currentUser= u;
            SharedPreferences sp =getSharedPreferences(Utilities.SharesPresfKeys.key,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("full_name",u.getFirst_name()+" "+u.getLast_name());
            editor.commit();
        }
    }

    //////////////////////////SEND PROFILE PIC TO DATABASE/////////////////////////////////////////////////////
    class setprofilepictask extends AsyncTask<Uri,String,String> {
         String result;

         @Override
         protected String doInBackground(Uri... params) {

             Bitmap bitmap = null;Bitmap icon = null;
             try {
                 bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), params[0]);
                 icon=bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
                 ByteArrayOutputStream stream = new ByteArrayOutputStream();
                 ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                 bitmap.compress(Bitmap.CompressFormat.PNG, 45, stream);
                 icon.compress(Bitmap.CompressFormat.PNG, 10, stream2);
                 byte[] array = stream.toByteArray();
                 byte[] icon_array = stream2.toByteArray();
                 String Imagestr = Base64.encodeToString(array, Base64.DEFAULT);
                 String icon_str = Base64.encodeToString(icon_array, Base64.DEFAULT);
                 array=null;
                 icon_array=null;
                 //converted to byte array and have to send it
                 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                 SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, Utilities.connection.method_names.changedp);

                 int x = getApplicationContext().getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getInt("reg_id", 0);
                 request.addProperty("Reg_id", x);
                 request.addProperty("x", Imagestr);
                 request.addProperty("y", icon_str);
                 envelope.bodyOut = request;
                 envelope.setOutputSoapObject(request);
                 HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url + Utilities.connection.x + Utilities.connection.exs);
                 try {

                     transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + Utilities.connection.method_names.changedp, envelope);
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
                 return result;
             } catch (IOException e) {
                 e.printStackTrace();
                 return e.getMessage() ;
             } catch (Exception e) {
                 e.printStackTrace();
                 result = e.getMessage() ;
             }
             return "no result sorry";

         }

         @Override
         protected void onPostExecute(String s) {
             super.onPostExecute(s);
             getdp(getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getInt("reg_id",0));
         }
     }
}
