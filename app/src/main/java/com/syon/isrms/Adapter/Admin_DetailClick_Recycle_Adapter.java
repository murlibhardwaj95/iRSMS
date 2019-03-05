package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Admin_DetailClik_Data;
import com.syon.isrms.R;

import java.util.List;

public class Admin_DetailClick_Recycle_Adapter extends RecyclerView.Adapter<Admin_DetailClick_Recycle_Adapter.Viewholder> {
    Context ctx;
    List<Admin_DetailClik_Data> list;

    public Admin_DetailClick_Recycle_Adapter(Context ctx,List<Admin_DetailClik_Data> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.result_analysis_detail_layout , parent, false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.setRange.setText(list.get(position).getSPercentRange().toString());
        holder.setLblAverageGrade.setText(list.get(position).getAverageGrade().toString());
        holder.setLbLNoOfStudent.setText(list.get(position).getDStudCntPercent().toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView setRange,setLblAverageGrade, setLbLNoOfStudent;
        public Viewholder(View itemView) {
            super(itemView);

            setRange = itemView.findViewById(R.id.setRange);
            setLblAverageGrade = itemView.findViewById(R.id.setLblAverageGrade);
            setLbLNoOfStudent = itemView.findViewById(R.id.setLbLNoOfStudent);

        }
    }
}
