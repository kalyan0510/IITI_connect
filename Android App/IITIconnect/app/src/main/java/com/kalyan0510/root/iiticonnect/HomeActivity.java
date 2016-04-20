package com.kalyan0510.root.iiticonnect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.kobjects.util.Util;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {
    static boolean active = false;

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }



    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    static  Context context;
    static int reg_id;
    static TextView curLocat;
    static String Loc="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        setContentView(R.layout.activity_home);
        reg_id = getSharedPreferences(Utilities.SharesPresfKeys.key,Context.MODE_PRIVATE).
                getInt(Utilities.SharesPresfKeys.regid,0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("IITI Connect");
        toolbar.setSubtitle(getSharedPreferences(Utilities.SharesPresfKeys.key, Context.MODE_PRIVATE).getString(Utilities.SharesPresfKeys.name,"subtitle here"));
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(2);




    }
    private void CopyAssets() {

        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "UM.pdf");
        try {
            in = assetManager.open("UM.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/UM.pdf"),
                "application/pdf");

        startActivity(intent);
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            clearApplicationData();
            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
            System.exit(0);
            return true;
        }
        else if(id==R.id.action_profile){
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            return false;
        }
        else  if(id==R.id.um){
            CopyAssets();
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            WebView map = (WebView) rootView.findViewById(R.id.webmap);
            String html = "<html>\n" +
                    "<head><title>Example KML link page using a geo-uri</title></head>\n" +
                    "<body>\n" +
                    "  <a href=\"geo:0,0?q=file:///sdcard/download/iiti.kml\">overlay.kml</a>\n" +
                    "</body>\n" +
                    "</html>";
            map.loadData(html,"text/html", null);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("HEY no ->" +getArguments().getInt(ARG_SECTION_NUMBER));
            return rootView;
        }
    }
    public static class WarningFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public WarningFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static WarningFragment newInstance(int sectionNumber) {

            WarningFragment fragment = new WarningFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_warning, container, false);
            final EditText descrEdt  = (EditText)rootView.findViewById(R.id.descEdt);
            final RadioGroup typeRadioGroup = (RadioGroup)rootView.findViewById(R.id.typegroup);
            typeRadioGroup.check(R.id.campusRad);
            rootView.findViewById(R.id.sendAlertBut).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((rootView.findViewById(typeRadioGroup.getCheckedRadioButtonId())) == null) {
                        Log.w("x", "afakjh");
                        return;
                    }
                    if (!Utilities.isOncampusWifi(context)) {
                        Toast.makeText(context, "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    new sendAlerttask().execute(reg_id + "", descrEdt.getText().toString().trim(),
                            ((RadioButton) rootView.findViewById(typeRadioGroup.getCheckedRadioButtonId())).getText().toString());
                    //Toast.makeText(context, ""+((RadioButton)rootView.findViewById(typeRadioGroup.getCheckedRadioButtonId())).getText(), Toast.LENGTH_SHORT).show();
                }
            });

            return rootView;
        }
    }

    public static class SOSFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public SOSFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SOSFragment newInstance(int sectionNumber) {

            SOSFragment fragment = new SOSFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_sos, container, false);
            final EditText manLoc  = (EditText)rootView.findViewById(R.id.LocManEdt);
            final RadioGroup typeRG = (RadioGroup)rootView.findViewById(R.id.helptype);
            final RadioGroup LocRG = (RadioGroup)rootView.findViewById(R.id.helpLoc);
            final TextView curloc  = curLocat = (TextView)rootView.findViewById(R.id.curLoc);
            typeRG.check(R.id.help1);
            LocRG.check(R.id.rec);
            if(Utilities.isOncampusWifi(context))
            new getLoctask().execute();
            //curloc.setText(Loc+"");
           // Toast.makeText(context, "SOS FRAGMENT LOADED", Toast.LENGTH_SHORT).show();
            rootView.findViewById(R.id.sendSOS).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Utilities.isOncampusWifi(context)){
                        Toast.makeText(context, "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String Location = curloc.getText().toString().trim();
                    if(Location.equals("Not Recognised")){
                        if(manLoc.getText().toString().trim().equals("")){
                            Toast.makeText(context, "Empty Location Please enter Location", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Location = manLoc.getText().toString().trim();
                    }
                    if(LocRG.getCheckedRadioButtonId()==R.id.msa){
                        Location= manLoc.getText().toString().trim();
                    }
                    new sendSOStask().execute(reg_id+"",Location,
                            ((RadioButton)rootView.findViewById(typeRG.getCheckedRadioButtonId())).getText().toString());
                    //Toast.makeText(context, ""+((RadioButton)rootView.findViewById(typeRadioGroup.getCheckedRadioButtonId())).getText(), Toast.LENGTH_SHORT).show();
                }
            });

            return rootView;
        }
    }


    public static class RecentChat extends Fragment {
        AutoCompleteTextView atv;
        String[] strlist=new String[500];
        int[] intlist=new int[500];
/*
        String[] strlist={"","kal","yan"};
        int[] intlist={1,2,3};
*/


        private static final String ARG_SECTION_NUMBER = "section_number";
        RecentUserAdapter adapter;
        public RecentChat() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RecentChat newInstance(int sectionNumber) {

            RecentChat fragment = new RecentChat();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
         void setstrlist(){
            int i=0;
            String s=getuserlist();
            if(s==null)
                return;
            if(s.equals(""))
                return;
            try{
            Userlist ul = new Gson().fromJson(s,Userlist.class);
                for(User u:ul.list){
                    strlist[i]=u.getFirst_name()+" "+u.getLast_name()+": "+u.getMail();
                     intlist[i]=u.getReg_id();
                    //Toast.makeText(context, intlist[i]+" "+strlist[i], Toast.LENGTH_SHORT).show();
                     i++;
                }
                while(i<=499){
                    strlist[i]=" ";
                    i++;
                }
                if(adapt!=null)
                    adapt.notifyDataSetChanged();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        static   String getuserlist() {
            File f = new File(context.getFilesDir() + "/","users.gk");
            if (!f.exists()) {
                Log.w("e", "f not exists");
                return null;
            }

            String line = "";
            try {
                FileInputStream fis = new FileInputStream(f);
                BufferedReader r = new BufferedReader(new InputStreamReader(fis));
                String str = r.readLine();
                while (str != null) {
                    line += str;
                    str = r.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line;
        }
        ArrayAdapter adapt;
        Button toputmAct;
        BroadcastReceiver receiver;
        ListView recentlistview;ArrayList<RecentUserItem> recent_user_list;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.recent_chats, container, false);
            recentlistview = (ListView) rootView.findViewById(R.id.recent_list_view);
            atv = (AutoCompleteTextView)rootView.findViewById(R.id.user_search);
            new getuserforsearch().execute();
            setstrlist();
            //Toast.makeText(context, "Working or nt--"+strlist[0], Toast.LENGTH_SHORT).show();
            adapt = new ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,strlist);
            atv.setAdapter(adapt);
            atv.setTextColor(Color.BLACK);
            atv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, ChatActivity.class);
                    String name = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(context, intlist[Arrays.asList(strlist).indexOf(name)]+"" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", name);
                    bundle.putInt("reg_id", intlist[Arrays.asList(strlist).indexOf(name)]);
                    intent.putExtras(bundle);
                    atv.setText("");
                    startActivity(intent);
                }
            });
            recent_user_list = new ArrayList<RecentUserItem>();
            adapter = new RecentUserAdapter(context,recent_user_list);

            recentlistview.setAdapter(adapter);
            recentlistview.setEnabled(false);
            adapter.notifyDataSetChanged();
            new AddusertoRecentListview().execute();
            adapter.notifyDataSetChanged();
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.kalyan.messagereceived");
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, final Intent intent) {


                    new Runnable(){

                        @Override
                        public void run() {
                            if(active)
                            new AddusertoRecentListview().execute();
                        }
                    }.run();
                }
            };
            context.registerReceiver(receiver, filter);
            return rootView;
        }

        public class getuserforsearch extends AsyncTask<Void,String,String>{
            @Override
            protected String doInBackground(Void... params) {
                String result="*";
                try {
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    SoapObject request = new SoapObject(Utilities.connection.NAMESPACE,"getusersforsearch");
                    envelope.bodyOut = request;
                    HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                    try {
                        transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX +"getusersforsearch", envelope);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "*1";
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                        return "*2";
                    }
                    result=envelope.getResponse().toString();
                    if (envelope.bodyIn != null) {
                        SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                        result=resultSOAP.toString();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    result = "*3";
                }
                return result;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(context, "/////////////////////////"+s, Toast.LENGTH_SHORT).show();
                if(s.equals("*")) {
                    return;
                }

                //Log.w("dreams",s);

                putuserlisttofile(s);
                setstrlist();
                adapt.notifyDataSetChanged();
            }
        }
        static int putuserlisttofile(String mg){
            FileWriter f;
            File file = new File(context.getFilesDir()+"/", "users.gk");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                context.getExternalFilesDir("dir");
                f = new FileWriter(context.getFilesDir()+"/"+"users.gk",false);
                f.write(mg);
                f.flush();
                f.close();
                return 1;
            }
            catch (Exception e){
                e.printStackTrace();
                return 0;
            }


        }
        public class AddusertoRecentListview extends AsyncTask<String,String,String>{
            String res_error;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                User u=null;
                Recents rc = Utilities.getrecents(context);
                int i;
                recent_user_list.clear();
                for(int x=rc.recentids.size()-1;x>=0;x--){
                    i=rc.recentids.get(x);
                    if(Utilities.isonofflineusers(context,i)){

                        String s = getusercontent(i);
                        // if(s.charAt(0)!='{')
                        Log.w("sys",s);
                        u = new Gson().fromJson(s, User.class);

                    }else
                        res_error+="User NOt Available offline";
                    String lmsg = getlastmes(i);
                    //Log.w("msg",lmsg);
                    Message mg = new Gson().fromJson(lmsg,Message.class);
                    if(u!=null&&u.getPic()!=null&&!(u.getPic()+"").equals("")){
                        byte[] array = Base64.decode(u.getPic().getBytes(), Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
                        recent_user_list.add(new RecentUserItem(bitmap, mg.from_name, mg.message, mg.time,mg.from));
                    }else {
                        if(u==null)
                            res_error+="User NULL";
                        else
                            res_error+="User Pic Null";
                        recent_user_list.add(new RecentUserItem(null, "" + mg.from_name, mg.message, mg.time,mg.from));
                        //Log.w("see here",  u.getFirst_name()+" "+u.getLast_name());
                    }
                }
            }

            @Override
            protected String doInBackground(String... params) {
                return "";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                recentlistview.setEnabled(true);
                adapter.notifyDataSetChanged();
            }
        }

     static   String getusercontent(int x){
            File f=new File(context.getFilesDir()+"/", x+"details.gk");
            if(!f.exists()){
                Log.w("e","f not exists");
                return  null;
            }

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
            return line;///////////////////////////////////.replaceAll("\\\"","\"");


          /*  FileInputStream fos = null;
            try {
                fos = context.openFileInput(x+"details.gk");
                fos.read();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return 0;*/
        }
        String getlastmes(int x){
            File f=new File(context.getFilesDir()+"/", x+"last.gk");
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
            line.replaceAll("\\\"","\"");
            return line;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==0)
                return WarningFragment.newInstance(1);
            else if(position==1){
                return SOSFragment.newInstance(2);
            }else
                return RecentChat.newInstance(position+1);

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Warning";
                case 1:
                    return "SOS";
                case 2:
                    return "Chat";
                case 3:
                    return "Schedule";

            }
            return null;
        }
    }









    //////////////////////////////////////////ASYNC TASKS//////////////////////////////////////

    //////////SEND ALERT///////////////////
    static class sendAlerttask extends AsyncTask<String , String,String> {
        String result;
        protected String doInBackground(String... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE,"setWarning");
                request.addProperty("Reg_id", reg_id);
                request.addProperty("message", params[1]);
                request.addProperty("type", params[2]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX +"setWarning", envelope);
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
            Toast.makeText(context, ""+s, Toast.LENGTH_SHORT).show();
        }
    }
    //////////////////SEND  SOS//////////////////////
    static class sendSOStask extends AsyncTask<String , String,String> {
        String result;
        protected String doInBackground(String... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE,"setSOS");
                request.addProperty("reg_id", reg_id);
                request.addProperty("message", params[1]);
                request.addProperty("type", params[2]);
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX +"setSOS", envelope);
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
            Toast.makeText(context, "Send SOS-> "+s, Toast.LENGTH_SHORT).show();
        }
    }

    //////////////////GET LOCATION///////////////////////////////
    static class getLoctask extends AsyncTask<String , String,String> {
        String result;
        protected String doInBackground(String... params) {
            try {
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                SoapObject request = new SoapObject(Utilities.connection.NAMESPACE,"getAddress");
                request.addProperty("mac", Utilities.getwifimac(context));
                envelope.bodyOut = request;
                HttpTransportSE transport = new HttpTransportSE(Utilities.connection.url+Utilities.connection.x+Utilities.connection.exs);
                try {
                    transport.call(Utilities.connection.NAMESPACE + Utilities.connection.SOAP_PREFIX +"getAddress", envelope);
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
            if(s.equals("fail")){
                return;
            }
            Loc = s;
            curLocat.setText(s+"");
        }
    }


}

