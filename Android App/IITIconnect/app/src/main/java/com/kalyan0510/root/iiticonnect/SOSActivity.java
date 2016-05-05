package com.kalyan0510.root.iiticonnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SOSActivity extends AppCompatActivity {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        ((TextView)findViewById(R.id.Sosfrom)).setText(intent.getStringExtra("nam")+"");

        ((TextView)findViewById(R.id.oopsmsgsos)).setText(intent.getStringExtra("message") + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        onNewIntent(getIntent());
        // RoundedImageView im = (RoundedImageView)findViewById(R.id.informations_container);
        //im.setImageResource(R.mipmap.gkalyan);
    }
}
