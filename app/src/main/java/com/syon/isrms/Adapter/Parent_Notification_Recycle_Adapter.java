package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Parent_Notification_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Parent_Notification_Recycle_Adapter extends RecyclerView.Adapter<Parent_Notification_Recycle_Adapter.ViewHolder> {
    List<Parent_Notification_Bean_Data> data;
    Context context;

    public Parent_Notification_Recycle_Adapter(Context context, List<Parent_Notification_Bean_Data> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.notification.setText(data.get(position).getSNotifyBody().toString());
        holder.date.setText(data.get(position).getSNotifyDate().toString());
        holder.notificationtype.setText(data.get(position).getSNotifyType().toString());
        holder.time.setText(data.get(position).getSNotifyTime());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notification,date,notificationtype,time;


        public ViewHolder(View itemView) {
            super(itemView);
            notification = itemView.findViewById(R.id.notification_Data);
            date = itemView.findViewById(R.id.notification_date);
            notificationtype = itemView.findViewById(R.id.notificationtype);
            time = itemView.findViewById(R.id.notification_time);

        }
    }


}
