package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Activity.Admin_Toppers_List_Web;
import com.syon.isrms.Beans.Admin_Toppers_List_Data;
import com.syon.isrms.R;

import java.util.List;

public class Admin_topperlist_adapter extends RecyclerView.Adapter<Admin_topperlist_adapter.Viewholder> {
    Context ctx;
    List<Admin_Toppers_List_Data> data;
    public Admin_topperlist_adapter(Context ctx, List<Admin_Toppers_List_Data> data) {
    this.ctx= ctx;
    this.data = data;

    }
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.topperlist_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.adminclass.setText(data.get(position).getSClass().toString());
        holder.adminexam.setText(data.get(position).getSExamName().toString().toString());

        holder.adminexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ctx.startActivity(new Intent(v.getContext(), Admin_Toppers_List_Web.class).putExtra("topperExamId",data.get(position).getLExamId()+"").putExtra("classId",data.get(position).getLClassId()+"").putExtra("streamId",data.get(position).getLStreamId()+""));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView adminclass,adminexam;
        public Viewholder(View itemView) {
            super(itemView);
            adminclass = itemView.findViewById(R.id.adminclass);
            adminexam = itemView.findViewById(R.id.adminexam);
        }
    }
}
