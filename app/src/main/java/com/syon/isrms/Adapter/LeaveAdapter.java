package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Userbean_LeaveData;
import com.syon.isrms.R;

import java.util.List;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.ViewHolder> {

    Context context;
    List<Userbean_LeaveData> list;
    public LeaveAdapter(Context context, List<Userbean_LeaveData> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_leave,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tl1.setText(list.get(position).getSFromDate().toString());
        holder.tl2.setText(list.get(position).getSToDate().toString());
        holder.tl3.setText(list.get(position).getSReason().toString());
        holder.tl4.setText(list.get(position).getSLeaveStatus().toString());
        holder.tl5.setText(list.get(position).getSRemark().toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tl1,tl2,tl3,tl4,tl5;
        public ViewHolder(View itemView) {
            super(itemView);
            tl1= itemView.findViewById(R.id.tfromDate);
            tl2 = itemView.findViewById(R.id.ttoDate);
            tl3 = itemView.findViewById(R.id.treason);
            tl4 = itemView.findViewById(R.id.tapproved);
            tl5 = itemView.findViewById(R.id.tremark);


        }
    }
}
