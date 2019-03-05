package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.syon.isrms.Beans.MyAttendance_Data;
import com.syon.isrms.R;

import java.util.List;

public class MyAttendance_Recycle_Adapter extends RecyclerView.Adapter<MyAttendance_Recycle_Adapter.ViewHolder> {
    List<MyAttendance_Data> data;
    Context context;

    public MyAttendance_Recycle_Adapter(Context context, List<MyAttendance_Data> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_attendance_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.lblDate.setText(data.get(position).getSMonthYear().toString());
        holder.lblWD.setText("WD: "+data.get(position).getSWD().toString());
        holder.setPresent.setText(data.get(position).getSPresent().toString());
        holder.setAbsent.setText(data.get(position).getSAbsent().toString());
        holder.setDutyLeave.setText(data.get(position).getSDutyLeave().toString());
        holder.setLeave.setText(data.get(position).getSLeave().toString());
        holder.setLeaveDetail.setText(data.get(position).getSLeaveDetail().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText setPresent, setAbsent, setDutyLeave,setLeave,setLeaveDetail;
        TextView lblDate, lblWD;

        public ViewHolder(View itemView) {
            super(itemView);

            lblDate = itemView.findViewById(R.id.lblDate);
            lblWD = itemView.findViewById(R.id.lblWD);
            setPresent = itemView.findViewById(R.id.setPresent);
            setAbsent = itemView.findViewById(R.id.setAbsent);
            setDutyLeave=itemView.findViewById(R.id.setDutyLeave);
            setLeave=itemView.findViewById(R.id.setLeave);
            setLeaveDetail=itemView.findViewById(R.id.setLeaveDetail);


        }
    }
}
