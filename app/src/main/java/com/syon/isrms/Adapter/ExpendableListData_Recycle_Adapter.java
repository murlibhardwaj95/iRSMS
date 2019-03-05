package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Parent_Event_Monthly_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class ExpendableListData_Recycle_Adapter extends RecyclerView.Adapter<ExpendableListData_Recycle_Adapter.ViewHolder> {
    List<Parent_Event_Monthly_Bean_Data> data;


    Context context;


    public ExpendableListData_Recycle_Adapter(Context context ,List<Parent_Event_Monthly_Bean_Data> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.expendandable_child_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(data.get(position).getSDate().equals(null))
        {
            holder.date.setText("");
            holder.child_data.setText("No data for this month");
        }
        else{
            holder.date.setText(data.get(position).getSDate().toString());
            holder.child_data.setText(data.get(position).getSEventName().toString());
        }



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView child_data,date;



        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.head_date);
            child_data = itemView.findViewById(R.id.head_holiday);


        }
    }
}
