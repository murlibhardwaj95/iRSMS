package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Activity.Admin_Class_Attendance;
import com.syon.isrms.Beans.Admin_Class_Attendance_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Admin_Class_Attendance_Adapter extends RecyclerView.Adapter<Admin_Class_Attendance_Adapter.ViewHolder> {
    Context ctx;
  List<Admin_Class_Attendance_Bean_Data> data;

    public Admin_Class_Attendance_Adapter(Context ctx, List<Admin_Class_Attendance_Bean_Data> data) {
    this.ctx=ctx;
    this.data = data;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.class_attendance_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.adminclass.setText(data.get(position).getSClass().toString());
        holder.adminpresent.setText(data.get(position).getDPresentPercent().toString());
        holder.adminabsent.setText(data.get(position).getDAbsentPercent().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView adminclass,adminpresent,adminabsent;
        public ViewHolder(View itemView) {
            super(itemView);
            adminclass = itemView.findViewById(R.id.adminclass_attendence);
            adminpresent = itemView.findViewById(R.id.adminpresent);
            adminabsent = itemView.findViewById(R.id.adminabsent);

        }
    }
}
