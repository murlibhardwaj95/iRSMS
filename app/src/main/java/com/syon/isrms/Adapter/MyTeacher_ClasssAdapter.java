package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syon.isrms.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyTeacher_ClasssAdapter extends RecyclerView.Adapter<MyTeacher_ClasssAdapter.ViewHolder> {

    Context ctx;
   String[] ctd;

    public MyTeacher_ClasssAdapter(Context ctx,  String[] ctd) {

        this.ctx = ctx;
        this.ctd = ctd;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myteacher_class,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(ctx).load(ctd[position]).resize(65,65).placeholder(R.drawable.avtar).into(holder.ctphoto);
        holder.namect.setText(ctd[position].toString());
    }

    @Override
    public int getItemCount() {
        return ctd.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ctphoto;
        TextView namect;
        public ViewHolder(View itemView) {
            super(itemView);
            ctphoto = itemView.findViewById(R.id.classteacher);
            namect = itemView.findViewById(R.id.ctname);

        }
    }
}
