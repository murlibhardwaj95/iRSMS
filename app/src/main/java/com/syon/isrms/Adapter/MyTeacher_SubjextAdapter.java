package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syon.isrms.Beans.Userbean_MySubjectTeacher_Data;
import com.syon.isrms.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyTeacher_SubjextAdapter extends RecyclerView.Adapter<MyTeacher_SubjextAdapter.ViewHolder> {

    Context ctx;
    List<Userbean_MySubjectTeacher_Data> std;

    public MyTeacher_SubjextAdapter(Context ctx, List<Userbean_MySubjectTeacher_Data> std) {
        this.ctx = ctx;
        this.std = std;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myteacher_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(ctx).load(std.get(position).getSubTeacherPhoto().toString()).resize(65,65).placeholder(R.drawable.avtar).into(holder.phototeacher);
        holder.namestd.setText(std.get(position).getSubTeacherName().toString());
        holder.stdsubject.setText(std.get(position).getSSubject().toString());
    }

    @Override
    public int getItemCount() {
        return std.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView phototeacher;
        TextView namestd,stdsubject;
        public ViewHolder(View itemView) {
            super(itemView);
            phototeacher = itemView.findViewById(R.id.subjectteacher);
            namestd = itemView.findViewById(R.id.stname);
            stdsubject = itemView.findViewById(R.id.stsubjectstd);
        }
    }
}
