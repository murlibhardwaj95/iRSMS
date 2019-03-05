package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syon.isrms.Beans.Parent_Attendance_Daywise_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Calendar_Recycle_Adapter extends RecyclerView.Adapter<Calendar_Recycle_Adapter.ViewHolder> {

    Context context;
    String[] date;
    List<Parent_Attendance_Daywise_Bean_Data> data;

    public Calendar_Recycle_Adapter(Context context, String[] date,List<Parent_Attendance_Daywise_Bean_Data> data) {
        this.context = context;
        this.date = date;
        this.data=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_custom_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.date.setText(date[position].toString());

        if (data.get(position).getAttndStatus().equals("H")) {
            holder.circle.setImageResource(R.drawable.blue_circle);
        } else if(data.get(position).getAttndStatus().equals("A"))  {
            holder.circle.setImageResource(R.drawable.red_circle);
        }
        else if(data.get(position).getAttndStatus().equals("P"))  {
            holder.circle.setImageResource(R.drawable.green_circle);
        }
        else if(data.get(position).getAttndStatus().equals("S"))  {
            holder.circle.setImageResource(R.drawable.blue_circle);
        }
        else if(data.get(position).getAttndStatus().equals("L"))  {
            holder.circle.setImageResource(R.drawable.yellow_circle);
        }
        else if(data.get(position).getAttndStatus().equals("M"))
        {
            holder.circle.setImageResource(R.drawable.yellow_circle);
        }
        else if(data.get(position).getAttndStatus().equals("D"))
        {
            holder.circle.setImageResource(R.drawable.red_circle);
        }
        else
        {

        }

        /* holder.date.setText(date.get(position));*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView circle;

        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.cal_date);
            circle = itemView.findViewById(R.id.cus_cal_circle);

        }


    }
}