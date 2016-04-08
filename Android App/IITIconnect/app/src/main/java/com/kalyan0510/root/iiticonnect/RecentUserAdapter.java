package com.kalyan0510.root.iiticonnect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
        holder.img.setImageBitmap(li.image);
        holder.Name.setText(li.Name);
        holder.str.setText(li.str);
       /* if(li.getCol()==1){
            holder.from.setBackgroundColor(Color.rgb(74,68,86));
            holder.to.setBackgroundColor(Color.rgb(74,68,86));
            holder.time.setBackgroundColor(Color.rgb(74,68,86));
            holder.bus.setBackgroundColor(Color.rgb(74,68,86));
        }
        else {
        }*/
        /*int x=li.getCol();
        if(x!=0)
            ((LinearLayout)convertView.findViewById(R.id.busitem)).setBackgroundColor(Color.rgb(200-5*x,200-5*x,200-5*x));
        else
            ((LinearLayout)convertView.findViewById(R.id.busitem)).setBackgroundColor(Color.rgb(240,240,240));
        //convertView.notifyAll();
        ((LinearLayout)convertView.findViewById(R.id.busitem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(li.getUsers())
                        .setTitle("Users")
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        */
        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView Name;
        TextView str;
        TextView time;

    }
}