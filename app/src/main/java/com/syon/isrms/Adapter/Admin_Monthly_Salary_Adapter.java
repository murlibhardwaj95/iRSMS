package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Activity.Admin_Department_Salary_Slip;
import com.syon.isrms.Beans.Admin_Month_Salary_Slip;
import com.syon.isrms.R;

import java.util.List;

public class Admin_Monthly_Salary_Adapter extends RecyclerView.Adapter<Admin_Monthly_Salary_Adapter.ViewHolder> {

    Context ctx;
    List<Admin_Month_Salary_Slip> list;

    public Admin_Monthly_Salary_Adapter(Context ctx,List<Admin_Month_Salary_Slip> list) {
        this.ctx = ctx;
        this.list = list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_monthly_salary_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       // holder.month.setText(list.get(position).getSMonthYear().toString());
        holder.basic.setText(list.get(position).getSBasic().toString());
        holder.allowance.setText(list.get(position).getSAllowance().toString());
        holder.gross.setText(list.get(position).getSGrossSalary().toString());
        holder.deduction.setText(list.get(position).getSDeduction().toString());
        holder.netsalary.setText(list.get(position).getSNetSalary().toString());

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(ctx,Admin_Department_Salary_Slip.class).putExtra("lMESDId",list.get(position).getLMESDId().toString()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView month,basic,allowance,gross,deduction,netsalary,details;
        public ViewHolder(View itemView) {
            super(itemView);
           // month = itemView.findViewById(R.id.salarymonth);
            basic = itemView.findViewById(R.id.salarybasic);
            allowance = itemView.findViewById(R.id.salaryallowance);
            gross = itemView.findViewById(R.id.salarygross);
            deduction = itemView.findViewById(R.id.salarydeduction);
            netsalary = itemView.findViewById(R.id.salarynet);
            details = itemView.findViewById(R.id.salarydetails);
        }
    }
}
