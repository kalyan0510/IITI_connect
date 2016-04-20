package com.kalyan0510.root.iiticonnect;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ChatActivity extends ListActivity {
    Context contextx;
    ChatAdapter adapter;
    String Name;
    EditText mesgbox;
    static int id ;
    TextView nameview;
    TextView statView;
    RoundedImageView rv;
    BroadcastReceiver receiver;

    @Override
    protected void onNewIntent(Intent it) {
        super.onNewIntent(it);
        Name = it.getStringExtra("Name");
        id = it.getIntExtra("reg_id", 0);
        adapter = new ChatAdapter();
        Toast.makeText(ChatActivity.this, Name+" "+id, Toast.LENGTH_SHORT).show();
        setListAdapter(adapter);

        /*ArrayList<Message>  al= new ArrayList<Message>();
        al.add(new Message(1, "m", "mess", "89:90", null, 0));
        adapter.addItem(new MessageData(new Gson().toJson(new Messagelist(al)), "", 0));
        adapter.addItem(new MessageData(getmes(id),"",0));
        */String str = getmes(id);
        str= "{\"messagelist\":["+str;
        System.out.println(str);
        int len=str.length()-1;
        if(str.charAt(len)==',')
            str = str.substring(0,len);
        System.out.println(str);
        str = str+"]}";
        System.out.println(str);
        Messagelist ml = new Gson().fromJson(str,Messagelist.class);
        for(Message mg:ml.messagelist){
            adapter.addItem(new MessageData(mg.message,mg.time,mg.status));
        }
        nameview.setText(Name);
        try{
            User u = new Gson().fromJson(HomeActivity.RecentChat.getusercontent(id), User.class);
            byte[] array = Base64.decode(u.getPic().getBytes(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
            rv.setImageBitmap(bitmap);

            statView.setText(u.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*if (!isTaskRoot()) {
            final Intent intent = getIntent();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
                ///Log.w(LOG_TAG, "Main Activity is not the root.  Finishing Main Activity instead of launching.");
                finish();
                return;
            }
        }*/
        setContentView(R.layout.activity_chat);
        rv=(RoundedImageView)findViewById(R.id.usericon);
        rv.setScaleType(ImageView.ScaleType.FIT_XY);
        (findViewById(R.id.linear2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contextx, OthersProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Toast.makeText(context, intlist[Arrays.asList(strlist).indexOf(name)]+"" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("reg_id", id);
                intent.putExtras(bundle);

                contextx.startActivity(intent);
            }
        });
        nameview = (TextView)findViewById(R.id.oppname);
        statView = (TextView)findViewById(R.id.opp_status);
        contextx = getApplicationContext();
        mesgbox = (EditText) findViewById(R.id.enter_message);
        Intent it = getIntent();
        Name = it.getStringExtra("Name");
        id = it.getIntExtra("reg_id", 0);
        adapter = new ChatAdapter();
        Toast.makeText(ChatActivity.this, Name+" "+id, Toast.LENGTH_SHORT).show();
        setListAdapter(adapter);

        /*ArrayList<Message>  al= new ArrayList<Message>();
        al.add(new Message(1, "m", "mess", "89:90", null, 0));
        adapter.addItem(new MessageData(new Gson().toJson(new Messagelist(al)), "", 0));
        adapter.addItem(new MessageData(getmes(id),"",0));
        */String str = getmes(id);
        str= "{\"messagelist\":["+str;
        System.out.println(str);
        int len=str.length()-1;
        if(str.charAt(len)==',')
        str = str.substring(0,len);
        System.out.println(str);
        str = str+"]}";
        System.out.println(str);
        Messagelist ml = new Gson().fromJson(str,Messagelist.class);
        for(Message mg:ml.messagelist){

            adapter.addItem(new MessageData(mg.message,mg.time,mg.status));
        }
        nameview.setText(Name);
        try{
            User u = new Gson().fromJson(HomeActivity.RecentChat.getusercontent(id), User.class);
            byte[] array = Base64.decode(u.getPic().getBytes(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
            rv.setImageBitmap(bitmap);

            statView.setText(u.getStatus());
        }catch (Exception e){
            e.printStackTrace();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kalyan.messagereceived");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, final Intent intent) {
                if(id!=intent.getIntExtra("from_id",0)||intent.getIntExtra("from_id",0)==0)
                    return;

                new Runnable(){

                    @Override
                    public void run() {
                        MessageData md = new MessageData(intent.getStringExtra("messg"),intent.getStringExtra("time"),MessageData.HE);
                        adapter.addItem(md);
                    }
                }.run();
            }
        };
        registerReceiver(receiver, filter);
    }
    public void goback(View view){
        finish();
    }
    String getmes(int x){
        File f=new File(getApplicationContext().getFilesDir()+"/", x+".gk");
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
    @Override
    protected void onDestroy() {
        if(receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    public void sendMessage(View view) {
        EditText message = (EditText) findViewById(R.id.enter_message);
        String mText = message.getText().toString();
        if(mText==null||mText.equals(""))
            return;
        new putMessagetask().execute();

        adapter.addItem(new MessageData(mText,timenow(),MessageData.ME));
        putMessagetofile(new Message(id,Name,mText,timenow(),null,0));
        message.setText("");
    }
    public static String timenow() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        System.out.println( sdf.format(cal.getTime()) );
        return sdf.format(cal.getTime());
    }

    private class ChatAdapter extends BaseAdapter {
        private static final int TYPE_ME = 0;
        private static final int TYPE_HE= 1;
        private ArrayList<MessageData> mData = new ArrayList();
        public LayoutInflater mInflater;
        public ChatAdapter() {
            mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
        @Override
        public int getItemViewType(int position) {
            return mData.get(position).who==MessageData.ME ? TYPE_ME: TYPE_HE;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            int type = getItemViewType(position);
            // System.out.println("getView " + position + " " + convertView + " type = " + type);
            MessageData currentMsg =  (MessageData)mData.get(position);
            if (convertView == null) {
                holder = new ViewHolder();
                switch (currentMsg.who) {
                    case TYPE_ME:
                        convertView = mInflater.inflate(R.layout.right_list, null);
                        holder.message = (TextView)convertView.findViewById(R.id.message_text);
                        holder.time = (TextView)convertView.findViewById(R.id.txtTimeFrom);
                        break;
                    case TYPE_HE:
                        convertView = mInflater.inflate(R.layout.message_list, null);
                        holder.message = (TextView)convertView.findViewById(R.id.message_text);
                        holder.time = (TextView)convertView.findViewById(R.id.txtTimeFrom);

                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.message.setText(mData.get(position).getMessage());
            holder.time.setText(mData.get(position).getTime());
            return convertView;
        }
        public void addItem(final MessageData item) {
            mData.add(item);
            notifyDataSetChanged();
        }


    }
    public static class ViewHolder{
        TextView message;
        TextView time;
    }


    public class putMessagetask extends AsyncTask<Void,String,String> {
        String result;
        String messagestr;
        int idto;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            messagestr= mesgbox.getText().toString();
            idto=id;


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
                request.addProperty("regto", idto);

                request.addProperty("str",URLEncoder.encode(messagestr+"", "UTF-8"));
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    int putMessagetofile(Message mg){


        File filey = new File(contextx.getFilesDir()+"/", mg.from+"details.gk");
        if(!filey.exists()){
            Toast.makeText(contextx, "User details storing called", Toast.LENGTH_SHORT).show();
            new putuserdetailsinfile().execute(mg.from);
        }

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
            f.write(new Gson().toJson(mg)+",");
            f.flush();

            f.close();
            putLastMessagetofile(mg);
            //Toast.makeText(MainActivity.this, getApplicationContext().getFilesDir()+"", Toast.LENGTH_SHORT).show();

            return 1;
        }
        catch (Exception e){
            e.printStackTrace();


            return 0;
        }

    }
    int putLastMessagetofile(Message mg){
        //putnotify(mg);
        //putrecent(mg);
        mg.message = "me: "+mg.message;
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

            putrecent(mg);

            return 1;
        }
        catch (Exception e){
            e.printStackTrace();


            return 0;
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
    public class putuserdetailsinfile extends AsyncTask<Integer,String ,String>{

        int id;
        @Override
        protected String doInBackground(Integer... params) {
            String result;
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
            //Toast.makeText(contextx, ""+s, Toast.LENGTH_SHORT).show();
            Boolean t = Utilities.addofflineuser(contextx,id);
            Toast.makeText(contextx, "addofflinee "+t, Toast.LENGTH_SHORT).show();
            Log.w("addolf", t + "");
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
                f = new FileWriter(contextx.getFilesDir()+"/"+id+"details.gk",false);
                Toast.makeText(contextx, "main open", Toast.LENGTH_SHORT).show();
                f.write(s);
                f.flush();

                f.close();
                Toast.makeText(contextx, "user saved success", Toast.LENGTH_SHORT).show();




            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(contextx, "user saved FAILED", Toast.LENGTH_SHORT).show();



            }


        }
    }
}