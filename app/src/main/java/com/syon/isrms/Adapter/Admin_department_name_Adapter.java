package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Admin_Department_Salary_Slip_Data;
import com.syon.isrms.R;

import java.util.List;

public class Admin_department_name_Adapter extends RecyclerView.Adapter<Admin_department_name_Adapter.ViewHolder> {
    Context ctx;
    List<Admin_Department_Salary_Slip_Data> list;

    public Admin_department_name_Adapter(Context ctx,List<Admin_Department_Salary_Slip_Data> list) {
        this.ctx=ctx;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_department_name_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Toast.makeText(ctx, list.get(position).getSDeptName().toString(), Toast.LENGTH_SHORT).show();

        holder.salarydepartment.setText(list.get(position).getSDeptName()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView salarydepartment;
        public ViewHolder(View itemView) {
            super(itemView);
            salarydepartment = itemView.findViewById(R.id.salarydepartment);
        }
    }
}
