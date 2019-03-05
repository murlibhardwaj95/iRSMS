package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Admin_Class_Result_Selected_Deatil_Exam_Bean;
import com.syon.isrms.Beans.Result_Analysis_Detail_Data;
import com.syon.isrms.R;

import java.util.List;

public class Admin_Class_Result_Analysis_Detail_Recycle_Adapter extends RecyclerView.Adapter<Admin_Class_Result_Analysis_Detail_Recycle_Adapter.ViewHolder> {
    List<Admin_Class_Result_Selected_Deatil_Exam_Bean> data;
    Context context;

    public Admin_Class_Result_Analysis_Detail_Recycle_Adapter(Context context, List<Admin_Class_Result_Selected_Deatil_Exam_Bean> data) {
        this.context = context;
        this.data = data;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.result_analysis_detail_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.setRange.setText(data.get(position).getSPercentRange().toString());
        holder.setLblAverageGrade.setText(data.get(position).getSAverageGrade().toString());
        holder.setLbLNoOfStudent.setText(data.get(position).getSStudCntPercent().toString());





    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView setRange,setLblAverageGrade, setLbLNoOfStudent;


        public ViewHolder(View itemView) {
            super(itemView);

            setRange = itemView.findViewById(R.id.setRange);
            setLblAverageGrade = itemView.findViewById(R.id.setLblAverageGrade);
            setLbLNoOfStudent = itemView.findViewById(R.id.setLbLNoOfStudent);

        }
    }
}
