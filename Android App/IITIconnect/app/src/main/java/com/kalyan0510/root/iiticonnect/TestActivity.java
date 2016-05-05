package com.kalyan0510.root.iiticonnect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        ((TextView)findViewById(R.id.Warnfrom)).setText(intent.getStringExtra("nam")+"");

        ((TextView)findViewById(R.id.oopsmsg)).setText(intent.getStringExtra("message") + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
       onNewIntent(getIntent());
       // RoundedImageView im = (RoundedImageView)findViewById(R.id.informations_container);
        //im.setImageResource(R.mipmap.gkalyan);
    }

}
