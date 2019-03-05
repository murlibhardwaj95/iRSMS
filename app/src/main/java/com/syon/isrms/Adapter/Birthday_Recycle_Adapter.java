package com.syon.isrms.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Beans.Birthday_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Birthday_Recycle_Adapter extends RecyclerView.Adapter<Birthday_Recycle_Adapter.ViewHolder> {
    List<Birthday_Bean_Data> data;
    Context context;
    ProgressDialog progressDialog;

    public Birthday_Recycle_Adapter(Context context, List<Birthday_Bean_Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.birthday_header_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.birthday_Date.setText(data.get(position).getSDate().toString());
        holder.birthday_name.setText(data.get(position).getSEmpName().toString());
        holder.birthday_department.setText(data.get(position).getSDepartment().toString());
        holder.birthday_designation.setText(data.get(position).getSDesignation().toString());
        Picasso.with(context).load(data.get(position).getSPhoto().toString()).placeholder(R.drawable.avtar).resize(60,60).into(holder.birthday_person_pic);
        if (data.get(position).getSDate().equals(""))
        {
            holder.header.setVisibility(View.GONE);
        }
        else
        {
            holder.header.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView birthday_Date;
        TextView birthday_name, birthday_department, birthday_designation;
        LinearLayout header;
        RoundedImageView birthday_person_pic;

        public ViewHolder(View itemView) {
            super(itemView);
            birthday_Date = itemView.findViewById(R.id.birthday_date);
            birthday_name = itemView.findViewById(R.id.birthday_name);
            birthday_department = itemView.findViewById(R.id.birthday_department);
            birthday_designation = itemView.findViewById(R.id.birthday_designation);
            header = itemView.findViewById(R.id.header_layout);
            birthday_person_pic=itemView.findViewById(R.id.birthday_teacher_pic);


        }
    }

}
