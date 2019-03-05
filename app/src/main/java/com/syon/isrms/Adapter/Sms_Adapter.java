package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.syon.isrms.R;

import java.util.List;

public class Sms_Adapter extends RecyclerView.Adapter<Sms_Adapter.ViewHolder> {

    Context ctx;
    List<com.syon.isrms.Beans.Userbean_SMS_Data> sms;

    public Sms_Adapter( Context ctx,List<com.syon.isrms.Beans.Userbean_SMS_Data> sms) {

        this.ctx=ctx;
        this.sms=sms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mobileno.setText(sms.get(position).getSMobile().toString());
        holder.date.setText(sms.get(position).getSSMSDate().toString());
        holder.time.setText(sms.get(position).getSSMSTime().toString());
        holder.message.setText(sms.get(position).getSSMS());

    }

    @Override
    public int getItemCount() {
        return sms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText mobileno;
        TextView message,date,time;
        public ViewHolder(View itemView) {
            super(itemView);
            mobileno = itemView.findViewById(R.id.mobno);
            date = itemView.findViewById(R.id.datesms);
            time = itemView.findViewById(R.id.timesms);
            message = itemView.findViewById(R.id.readsms);
        }
    }
}
