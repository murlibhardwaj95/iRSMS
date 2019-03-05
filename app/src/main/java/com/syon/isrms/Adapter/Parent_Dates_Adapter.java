package com.syon.isrms.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.syon.isrms.Activity.Homework;
import com.syon.isrms.Beans.Parent_Homework_Date_Data_Bean;
import com.syon.isrms.R;

import java.util.List;

public class Parent_Dates_Adapter extends RecyclerView.Adapter<Parent_Dates_Adapter.ViewHolder> {

    Context context;
    List<Parent_Homework_Date_Data_Bean> data;
    int row_index = -1;


    public Parent_Dates_Adapter(Context context, List<Parent_Homework_Date_Data_Bean> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_button_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        holder.date.setText(data.get(position).getNDay().toString());
        if (data.get(position).getSHomeWork().isEmpty()) {
            holder.homework.setVisibility(View.GONE);
        } else {
            holder.homework.setVisibility(View.VISIBLE);
        }
        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                ((Homework)context).dodata(position,data.get(position).getNDay().toString());



            }
        });

        if (row_index == position) {
            holder.date.setBackgroundColor(Color.parseColor("#D03E24"));

        } else {
            holder.date.setBackgroundColor(Color.parseColor("#6E1B42"));

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView homework;
        Button date;



        public ViewHolder(View itemView) {
            super(itemView);
            homework = itemView.findViewById(R.id.lableH);
            date = itemView.findViewById(R.id.dateText);

        }



    }

}
