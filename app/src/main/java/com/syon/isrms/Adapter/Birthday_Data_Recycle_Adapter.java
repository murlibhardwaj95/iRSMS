package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Birthday_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Birthday_Data_Recycle_Adapter extends RecyclerView.Adapter<Birthday_Data_Recycle_Adapter.ViewHolder> {
    List<Birthday_Bean_Data> data;
    Context context;


    public Birthday_Data_Recycle_Adapter(Context context, List<Birthday_Bean_Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.birthday_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.birthday_name.setText(data.get(position).getSEmpName().toString());
        holder.birthday_department.setText(data.get(position).getSDepartment().toString());
        holder.birthday_designation.setText(data.get(position).getSDesignation().toString());
       /* RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.birthday_data.setLayoutManager(layoutManager);
        holder.birthday_data.setAdapter(new Birthday_Data_Recycle_Adapter(context, data));
*/



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView birthday_name,birthday_department,birthday_designation;
       

        public ViewHolder(View itemView) {
            super(itemView);
            birthday_name = itemView.findViewById(R.id.birthday_name);
            birthday_department = itemView.findViewById(R.id.birthday_department);
            birthday_designation = itemView.findViewById(R.id.birthday_designation);

        }
    }

   }
