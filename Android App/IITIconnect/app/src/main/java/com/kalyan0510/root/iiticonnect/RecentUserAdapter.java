package com.kalyan0510.root.iiticonnect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by root on 3/4/16.
 */

public class RecentUserAdapter extends BaseAdapter {
    private ArrayList<RecentUserItem> listData;
    private LayoutInflater layoutInflater;
    Context context;
    public RecentUserAdapter(Context aContext, ArrayList<RecentUserItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        context=aContext;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.chat_user_item, null);
            holder = new ViewHolder();
            holder.Name  = (TextView) convertView.findViewById(R.id.recent_item_name);
            holder.img  = (ImageView) convertView.findViewById(R.id.recent_item_image);
            holder.time = (TextView) convertView.findViewById(R.id.recent_item_time);
            holder.str=(TextView)convertView.findViewById(R.id.recent_item_str);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position<0)
            return convertView;
        final RecentUserItem li = listData.get(position);
        holder.time.setText(li.time);
        if(li.image!=null)
        holder.img.setImageBitmap
                (li.image);
        else
        holder.img.setImageResource(R.mipmap.userdefault);
        holder.Name.setText(li.Name);
        holder.str.setText(li.str);
        convertView.findViewById(R.id.linear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Toast.makeText(context, intlist[Arrays.asList(strlist).indexOf(name)]+"" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("Name", li.Name);
                bundle.putInt("reg_id", li.id);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
        convertView.findViewById(R.id.recent_item_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OthersProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Toast.makeText(context, intlist[Arrays.asList(strlist).indexOf(name)]+"" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("Name", li.Name);
                bundle.putInt("reg_id", li.id);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });


        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView Name;
        TextView str;
        TextView time;

    }
}