package com.kalyan0510.root.iiticonnect;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SignupActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText username;
    EditText mail;
    CheckBox tnc;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firstName= (EditText)findViewById(R.id.fn);
        lastName= (EditText)findViewById(R.id.ln);
        username= (EditText)findViewById(R.id.un);
        mail= (EditText)findViewById(R.id.mailTv);
        tnc = (CheckBox)findViewById(R.id.tc);
        submit = (Button)findViewById(R.id.submit);
        TextView nc = (TextView)findViewById(R.id.tnc);
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*File pdfFile = new File("res/tnc.pdf" );
                Uri path = Uri.fromFile(pdfFile);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
                CopyAssets();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Utilities.isOncampusWifi(getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "not connected to CAMPUS WIFI", Toast.LENGTH_SHORT).show();
                    return;
                }
                String fn = firstName.getText().toString().trim();
                String ln = lastName.getText().toString().trim();
                String un = username.getText().toString().trim().toLowerCase();
                String m = mail.getText().toString().trim().toLowerCase();
                if(fn.equals("")||ln.equals("")||un.equals("")||m.equals("")){
                    Toast.makeText(SignupActivity.this, "Dont leave boxes empty", Toast.LENGTH_SHORT).show();
                }
                else if(!tnc.isChecked()){
                    Toast.makeText(SignupActivity.this, "Agree to the terms and conditions ", Toast.LENGTH_SHORT).show();
                }else {

                    Utilities.signup(getApplicationContext(),fn,ln,un,m);
                }
            }
        });
    }
    private void CopyAssets() {

        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "tnc.pdf");
        try {
            in = assetManager.open("tnc.pdf");
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
                Uri.parse("file://" + getFilesDir() + "/tnc.pdf"),
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

}
