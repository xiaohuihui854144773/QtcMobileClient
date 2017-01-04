package com.example.dh.qtcmobileclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dh.qtcmobileclient.R;
import com.example.dh.qtcmobileclient.model.HomeLvModel;

import java.util.ArrayList;
import java.util.List;

public class HomeLvAdapter extends BaseAdapter {
    List<HomeLvModel> data;
    LayoutInflater inflater;

    public HomeLvAdapter(Context context, List<HomeLvModel> data) {
        inflater=LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<HomeLvModel> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public HomeLvModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView==null) {
            convertView=inflater.inflate(R.layout.homelv_item,parent,false);
            holder=new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }
        holder.title.setText(getItem(position).getTitle());
        holder.time.setText(getItem(position).getTime());

        return convertView;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.homelv_item_title);
            time = (TextView) itemView.findViewById(R.id.homelv_item_time);
        }
    }
}
