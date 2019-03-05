package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Activity.Teacher_Discusiion_Board;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_Two;
import com.syon.isrms.R;

import java.util.List;

public class Teacher_Notice_Board_Adapter extends RecyclerView.Adapter<Teacher_Notice_Board_Adapter.ViewHolder> {

    Context ctx;
    List<Bean_Admin_Discussion_Board_Two> discus;

    public Teacher_Notice_Board_Adapter(Context ctx,List<Bean_Admin_Discussion_Board_Two> discus) {
        this.ctx = ctx;
        this.discus = discus;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_meassage_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.namesendr.setText(discus.get(position).getEmpName().toString());
        holder.messagetxt.setText(discus.get(position).getSMessage().toString());
        holder.datetime.setText(discus.get(position).getMsgTime().toString());
        holder.discuss_date.setText(discus.get(position).getMsgDate().toString());
        Picasso.with(ctx).load(discus.get(position).getEmpPhoto().toString()).placeholder(R.drawable.avtar).resize(40,40).into(holder.photo);

        if(discus.get(position).getMsgDate().equals("")){
            holder.header.setVisibility(View.GONE);
        }
        else{
            holder.header.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return discus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namesendr,messagetxt,discuss_date,datetime;
        RoundedImageView photo;
        LinearLayout header;
        public ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.t_circle);
            namesendr = itemView.findViewById(R.id.t_name);
            discuss_date = itemView.findViewById(R.id.t_discuss_date);
            datetime = itemView.findViewById(R.id.t_datetimea);
            messagetxt = itemView.findViewById(R.id.t_message_body);
            header = itemView.findViewById(R.id.t_header_layout);
        }
    }
}
