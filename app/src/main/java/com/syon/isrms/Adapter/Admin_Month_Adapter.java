package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Admin_Month_Salary_Slip;
import com.syon.isrms.R;

import java.util.List;

public class Admin_Month_Adapter extends RecyclerView.Adapter<Admin_Month_Adapter.ViewHolder> {

    Context ctx;
    List<Admin_Month_Salary_Slip> list;

    public Admin_Month_Adapter(Context ctx,List<Admin_Month_Salary_Slip> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_month_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.months.setText(list.get(position).getSMonthYear().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView months;
        public ViewHolder(View itemView) {
            super(itemView);
            months = itemView.findViewById(R.id.salarymonth);
        }
    }
}
