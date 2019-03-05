package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.syon.isrms.Beans.View_Homework_Data;
import com.syon.isrms.R;

import java.util.List;

public class View_Homework_Recycle_Adapter extends RecyclerView.Adapter<View_Homework_Recycle_Adapter.ViewHolder> {
    List<View_Homework_Data> data;
    String exam_id;
    Context context;

    public View_Homework_Recycle_Adapter(Context context, List<View_Homework_Data> data) {
        this.context = context;
        this.data = data;
        this.exam_id=exam_id;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_homework_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.setHomeworkDate.setText("HW Date:"+data.get(position).getDtHWDate().toString());

        holder.setCompletionDate.setText("Comp Date:"+data.get(position).getDtComplDate().toString());

        holder.setClass.setText(data.get(position).getSClass().toString());

        holder.setSubject.setText(data.get(position).getSSubject().toString());

        holder.setTitle.setText(data.get(position).getSTitle().toString());





    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView setHomeworkDate,setCompletionDate;
        EditText setClass, setSubject, setTitle;


        public ViewHolder(View itemView) {
            super(itemView);

            setHomeworkDate = itemView.findViewById(R.id.setHomeworkDate);
            setCompletionDate = itemView.findViewById(R.id.setCompletionDate);
            setClass = itemView.findViewById(R.id.setClass);
            setSubject = itemView.findViewById(R.id.setSubject);
            setTitle = itemView.findViewById(R.id.setTitle);

        }
    }
}
