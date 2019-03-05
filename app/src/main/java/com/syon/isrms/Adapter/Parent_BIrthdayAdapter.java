package com.syon.isrms.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Beans.Parent_Birthday_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Parent_BIrthdayAdapter extends RecyclerView.Adapter<Parent_BIrthdayAdapter.ViewHolder> {
    List<Parent_Birthday_Bean_Data> data;
    Context context;
    ProgressDialog progressDialog;

    public Parent_BIrthdayAdapter(Context context, List<Parent_Birthday_Bean_Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_birthday_header_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.birthday_Date.setText(data.get(position).getSDate().toString());
        holder.birthday_name.setText(data.get(position).getSStudName().toString());
        Picasso.with(context).load( data.get(position).getSPhoto().toString()).placeholder(R.drawable.avtar).resize(60,60).into(holder.birthdayphoto);
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
        RelativeLayout header;

       RoundedImageView birthdayphoto;

        public ViewHolder(View itemView) {
            super(itemView);
            birthday_Date = itemView.findViewById(R.id.birthdaydate);
            birthday_name = itemView.findViewById(R.id.birthday_name);

            birthday_department = itemView.findViewById(R.id.birthday_department);
            birthday_designation = itemView.findViewById(R.id.birthday_designation);
            birthdayphoto = itemView.findViewById(R.id.birthdayphoto);
            header = itemView.findViewById(R.id.header_layout);


        }
    }

}
