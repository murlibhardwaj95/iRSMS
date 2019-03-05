package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syon.isrms.Beans.Admin_Yearly_Outstanding_Bean_Data;
import com.syon.isrms.Beans.Admin_Yearly_Refund_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Refund_RecyclerAdapter extends RecyclerView.Adapter<Refund_RecyclerAdapter.Viewholder> {

    Context ctx;
    List<Admin_Yearly_Refund_Bean_Data> data;
    public Refund_RecyclerAdapter(Context ctx,List<Admin_Yearly_Refund_Bean_Data> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.outstanding_headwise, parent, false));

    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.heading.setText(data.get(position).getSFHeadName().toString());
        holder.subheading.setText(data.get(position).getSFHeadAmt().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView heading, subheading;
        View line;
        public Viewholder(View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.headingtext);
            subheading = itemView.findViewById(R.id.subheadtext);
            line = itemView.findViewById(R.id.line);
        }
    }
}
