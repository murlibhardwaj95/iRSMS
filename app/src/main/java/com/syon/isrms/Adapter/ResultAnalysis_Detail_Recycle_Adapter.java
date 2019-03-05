package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.R;

import java.util.List;

public class ResultAnalysis_Detail_Recycle_Adapter extends RecyclerView.Adapter<ResultAnalysis_Detail_Recycle_Adapter.ViewHolder> {
    List<com.syon.isrms.Beans.Result_Analysis_Detail_Data> data;
    Context context;

    public ResultAnalysis_Detail_Recycle_Adapter(Context context, List<com.syon.isrms.Beans.Result_Analysis_Detail_Data> data) {
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
        holder.setLblAverageGrade.setText(data.get(position).getAverageGrade().toString());
        holder.setLbLNoOfStudent.setText(data.get(position).getStudCntPercent().toString());





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
