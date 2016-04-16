package com.kalyan0510.root.iiticonnect;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.kobjects.util.Util;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PutMessage extends AppCompatActivity {
    EditText toid,mesg;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_message);
        toid = (EditText)findViewById(R.id.toUser);
        mesg = (EditText)findViewById(R.id.Message);
        send = (Button)findViewById(R.id.sendMessage);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new putMessagetask().execute();

            }
        });
    }
    public class putMessagetask extends AsyncTask<Void,String,String>{
        String result;
        String messagestr;
        int idto;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            messagestr= mesg.getText().toString();
            try{
                idto = Integer.parseInt(toid.getText().toString()+"");
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "put integer    ", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, "putmessage");
                request.addProperty("regfrom", Utilities.getuid(getApplicationContext()));
                User u=Utilities.getUser(getApplicationContext());

                request.addProperty("name", getApplicationContext().getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getString("full_name", "Anonymous :D"));
                request.addProperty("au","");
                request.addProperty("regto",idto);
                request.addProperty("str",messagestr+"");
                request.addProperty("img","");

                        envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url + Utilities.connection.x + Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + "putmessage", envelope);
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
            Toast.makeText(getApplicationContext(), ""+s, Toast.LENGTH_SHORT).show();
        }
    }
}
