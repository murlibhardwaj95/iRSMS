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

;


public class Admin_Department_Salary_Adapter extends RecyclerView.Adapter<Admin_Department_Salary_Adapter.ViewHolder> {

    Context ctx;
    List<Admin_Department_Salary_Slip_Data> list;

    public Admin_Department_Salary_Adapter( Context ctx,List<Admin_Department_Salary_Slip_Data> list) {
        this.ctx=ctx;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_department_salary_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // holder.department.setText(list.get(position).getSDeptName());
        holder.basic.setText(list.get(position).getSBasic());
        holder.allowance.setText(list.get(position).getSAllowance());
        holder.gross.setText(list.get(position).getSGrossSalary());
        holder.deduction.setText(list.get(position).getSDeduction());
        holder.net.setText(list.get(position).getSNetSalary());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView department,basic,allowance,gross,deduction,net;
        public ViewHolder(View itemView) {
            super(itemView);
         //   department = itemView.findViewById(R.id.salarydepartment);
            basic = itemView.findViewById(R.id.basicdepartment);
            allowance = itemView.findViewById(R.id.departmentallowance);
            gross = itemView.findViewById(R.id.departmentgross);
            deduction = itemView.findViewById(R.id.departmentdeduction);
            net = itemView.findViewById(R.id.departmentnet);
        }
    }
}
