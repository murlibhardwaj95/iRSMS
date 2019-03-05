package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syon.isrms.R;

public class Month_Picker_Recycle_Adapter extends RecyclerView.Adapter<Month_Picker_Recycle_Adapter.ViewHolder> {
   String[] data;
    Context context;

    public Month_Picker_Recycle_Adapter(Context context,  String[] data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.monthpicker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.month.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView month;
        ImageView download, circle_color;

        public ViewHolder(View itemView) {
            super(itemView);

            month = itemView.findViewById(R.id.monthpicked);




        }
    }
}
