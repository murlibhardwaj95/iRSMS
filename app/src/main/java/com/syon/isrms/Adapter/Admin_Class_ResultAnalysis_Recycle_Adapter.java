package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.syon.isrms.Activity.Admin_Class_Result_Detail;
import com.syon.isrms.Beans.Admin_Class_Result_Selected_Exam_Data;
import com.syon.isrms.Beans.Result_Analasis_Data;
import com.syon.isrms.R;

import java.util.List;

public class Admin_Class_ResultAnalysis_Recycle_Adapter extends RecyclerView.Adapter<Admin_Class_ResultAnalysis_Recycle_Adapter.ViewHolder> {
    List<Admin_Class_Result_Selected_Exam_Data> data;
    String exam_id;
    Context context;

    public Admin_Class_ResultAnalysis_Recycle_Adapter(Context context, List<Admin_Class_Result_Selected_Exam_Data> data, String exam_id) {
        this.context = context;
        this.data = data;
        this.exam_id=exam_id;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_class_result_analysis_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.setClass.setText(data.get(position).getSClass().toString());
    /*    holder.subject.setText(data.get(position).getSSubject().toString());
        holder.setNoOfStudent.setText(data.get(position).getTblId().toString());*/
        holder.setAverage.setText(data.get(position).getDOAPercent().toString());
        holder.setAverageGrade.setText(data.get(position).getSOAGrade().toString());
        holder.btnViewDetailes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(context, Admin_Class_Result_Detail.class).putExtra("exam_id",exam_id).putExtra("tbl_id",data.get(position).getLTblId().toString()));
            }
        });




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView setClass,subject, btnViewDetailes;
        EditText setNoOfStudent, setAverage, setAverageGrade;
        Button download;


        public ViewHolder(View itemView) {
            super(itemView);

            setClass = itemView.findViewById(R.id.setClass);
            subject = itemView.findViewById(R.id.setSubject);
            setNoOfStudent = itemView.findViewById(R.id.setNoOfStudent);
            setAverage = itemView.findViewById(R.id.setAverage);
            setAverageGrade = itemView.findViewById(R.id.setAverageGrade);
            btnViewDetailes = itemView.findViewById(R.id.btnViewDetailes);

        }
    }
}
