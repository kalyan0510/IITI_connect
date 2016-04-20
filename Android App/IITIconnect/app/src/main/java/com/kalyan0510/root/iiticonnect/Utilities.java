package com.kalyan0510.root.iiticonnect;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Objects;

public class Utilities {
    public static Context context;
    public static final String Wifiname="\"D-Link\"";

    public class connection{
        public static final String NAMESPACE = "http://webService";
        public static final String exs="/IITIserver/services/FeedService?wsdl";
        public static final String SOAP_PREFIX = "/";
        public static  final String url= "http://";
        public static final String x = "10.42.0.1";
        public class method_names{
            public static final String signup = "signup";
            public static final String login = "login";
            public static final String changedp ="changedp";
            public static final String getdp = "getdp";
            public static final String getu = "getuser";
            public static final String fp="forgotpassword";
            public static final String cp="changepassword";
        }

    }
    public static boolean isOncampusWifi(Context c){
        return getwifiname(c).equals(Wifiname)||true;
    }
    //public static User currentUser = new User();
   // public static ArrayList<RecentUserItem> recentusers= new ArrayList<>();
    public static ArrayList<Integer> recentUserIds= new ArrayList<>();
    public static User getUser(Context ctx){
        SharedPreferences sp = ctx.getSharedPreferences(SharesPresfKeys.key, Context.MODE_PRIVATE);
        User u = new Gson().fromJson(sp.getString(SharesPresfKeys.user, ""),User.class);
        return u;

    }
    public class SharesPresfKeys{
        public static final String key = "xmotiv0510";
        public static final String regid = "reg_id";
        public  static final String name = "full_name";
        public  static final String recents = "recents";
        public  static final String user = "jsonUser";
        public static final String auth = "auth";

        public static final String offlineusers = "loadedusers";
    }
    public static String getauth(Context ctx){
        SharedPreferences sp = ctx.getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
        String s= sp.getString(SharesPresfKeys.auth, "");
        return s;
    }
    public static int getuid(Context ctx){
        SharedPreferences sp = ctx.getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
        int s= sp.getInt(SharesPresfKeys.regid, -1);
        return s;
    }
    public static boolean addofflineuser(Context ctx,int u){
        SharedPreferences sp = ctx.getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();

        Gson gson = new Gson();
        Recents r= gson.fromJson(sp.getString(SharesPresfKeys.offlineusers, gson.toJson(new Recents())), Recents.class);
        r.recentids.add(u);
        e.putString(SharesPresfKeys.offlineusers,gson.toJson(r));
        e.commit();
        return isonofflineusers(ctx,u);
    }
    public static boolean isonofflineusers(Context c,int u){
        SharedPreferences sp = c.getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Log.w("ofllineusers",sp.getString(SharesPresfKeys.offlineusers, gson.toJson(new Recents())));
        return gson.fromJson(sp.getString(SharesPresfKeys.offlineusers, gson.toJson(new Recents())), Recents.class).recentids.contains(u);
    }
    public static Recents getrecents(Context xcontext){
        SharedPreferences sp = xcontext.getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE);
        String s= sp.getString(Utilities.SharesPresfKeys.recents, "");
        Recents rc;
        if(!s.equals("")){
            rc = new Gson().fromJson(s,Recents.class);

        }else {
            rc = new Recents();
        }
        return rc;
    }
    public static boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager ctvMngr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo aNetInfo = ctvMngr.getActiveNetworkInfo();
        return aNetInfo != null && aNetInfo.isAvailable();
    }

    public static void signup(Context c,String fn,String ln,String un, String mail){
        context  = c;
        new Signuptask().execute(fn,ln,un,mail);

    }

    public static void setprofilepic(Context c, int regid, Uri selectedimgURI){
        context  = c;
         new setprofilepictask().execute(selectedimgURI);
    }
    public static void getdp(Context c,int reg_id) {
        context  = c;
        new getdptask().execute(reg_id);
    }
    public static String getwifimac(Context contexttemp){
        WifiManager wm = (WifiManager)contexttemp.getSystemService(Context.WIFI_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return wm.getConnectionInfo().getBSSID();//+" "+wm.getConnectionInfo().getFrequency()+" "+wm.getConnectionInfo().getLinkSpeed();
        }
        else return wm.getConnectionInfo().getBSSID();//+"  "+wm.getConnectionInfo().getLinkSpeed();
    }
    public static String getwifiname(Context contexttemp){
        WifiManager wm = (WifiManager)contexttemp.getSystemService(Context.WIFI_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return wm.getConnectionInfo().getSSID();//+" "+wm.getConnectionInfo().getFrequency()+" "+wm.getConnectionInfo().getLinkSpeed();
        }
        else return wm.getConnectionInfo().getSSID();//+"  "+wm.getConnectionInfo().getLinkSpeed();
    }

    static class getdptask extends AsyncTask<Integer,String ,String>{
        String result;
        @Override
        protected String doInBackground(Integer... params) {

            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, connection.method_names.login);
                request.addProperty("reg_id", params[0]);
                //request.addProperty("password", params[1]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + connection.method_names.login, envelope);
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
            byte[] array = Base64.decode(s.getBytes(),Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array,0,array.length);

            super.onPostExecute(s);
        }
    }

    static class setprofilepictask extends AsyncTask<Uri,String,String>{
        String result;

        @Override
        protected String doInBackground(Uri... params) {

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), params[0]);
                bitmap = Bitmap.createScaledBitmap(bitmap,200,200,false);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 45, stream);
                final byte[] array=stream.toByteArray();
                String Imagestr = Base64.encodeToString(array,Base64.DEFAULT);
                //converted to byte array and have to send it
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, connection.method_names.changedp);

                int x=context.getSharedPreferences(SharesPresfKeys.key,Context.MODE_PRIVATE).getInt("reg_id",0);
                request.addProperty("Reg_id", x);
                request.addProperty("x",Imagestr);

                envelope.bodyOut = request;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + connection.method_names.changedp, envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage()+"xxxxxx";
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return e.getMessage()+"yyyyyy";
                }
                result=envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    result=resultSOAP.toString();
                }
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage()+"zzzzzzz";
            }
            catch (Exception e) {
                e.printStackTrace();
                result = e.getMessage()+"wwwwwww";
            }
            return "no result sorry";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context,  context.getSharedPreferences(SharesPresfKeys.key,Context.MODE_PRIVATE).getInt("reg_id",0)
            +"<-regid  res->"+result, Toast.LENGTH_LONG).show();
        }
    }
    static class logintask extends AsyncTask<String, String,String>{
        String result;
        protected String doInBackground(String... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, connection.method_names.login);
                request.addProperty("username", params[0]);
                request.addProperty("password", params[1]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + connection.method_names.login, envelope);
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
        protected void onPreExecute() {
            Toast.makeText(context, "Entered async", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if((s+"").trim().equals("Invalid/Wrong login credentials"))
                Toast.makeText(context,s, Toast.LENGTH_SHORT).show();
            else {
                try{
                    SharedPreferences sp = context.getSharedPreferences(SharesPresfKeys.key,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt(SharesPresfKeys.regid,Integer.parseInt(s));
                    editor.commit();
                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                }

                catch(Exception e){
                    Toast.makeText(context,s+" -- "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private static  class Signuptask extends AsyncTask<String, String, String> {

        private String resp,res;
        //int a =0,b=0;
        @Override
        protected String doInBackground(String... params) {
           // publishProgress("Loading contents...");
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE, connection.method_names.signup);
                request.addProperty("username", params[2]);
                request.addProperty("first_name", params[0]);
                request.addProperty("last_name", params[1]);
                request.addProperty("mail", params[3]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX + connection.method_names.signup, envelope);
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
                res=envelope.getResponse().toString();
                if (envelope.bodyIn != null) {
                    SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                    resp=resultSOAP.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context, result+"-res", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onPreExecute() {

        }


    }
}
