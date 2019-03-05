package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.syon.isrms.Beans.Userbean_TeacherData;
import com.syon.isrms.Beans.Userbean_parent_studentList;
import com.syon.isrms.R;

import java.util.List;

public class Admin_Comm_Parent_Adapter extends RecyclerView.Adapter<Admin_Comm_Parent_Adapter.ViewHolder> {
    Context context;
    List<Userbean_parent_studentList> data;

    public Admin_Comm_Parent_Adapter(Context context,List<Userbean_parent_studentList> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_parent_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.adminno.setText(data.get(position).getAdmNo());
        holder.parentusername.setText(data.get(position).getFatherName());
        holder.studentusername.setText(data.get(position).getStudName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView parentusername, studentusername, adminno;
        CheckBox studentcheck;
        public ViewHolder(View itemView) {
            super(itemView);
            adminno = itemView.findViewById(R.id.adstudentadminno);
            parentusername = itemView.findViewById(R.id.adparentusernametext);
            studentusername = itemView.findViewById(R.id.adstudentnametext);
            studentcheck = itemView.findViewById(R.id.adstudentcheck);
        }
    }
}
