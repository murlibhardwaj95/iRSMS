package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.syon.isrms.Activity.Admin_Class_Teacher_Result_Analysis_Second;
import com.syon.isrms.Beans.Admin_Class_Teacher_Result_Fill_Teacher_Data;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;


public class Admin_Class_Teacher_First_Result_Analysis_Recycle_Adapter extends RecyclerView.Adapter<Admin_Class_Teacher_First_Result_Analysis_Recycle_Adapter.ViewHolder> implements Filterable {
    Context context;
    List<Admin_Class_Teacher_Result_Fill_Teacher_Data> data;
    List<Admin_Class_Teacher_Result_Fill_Teacher_Data> f_data;


    private List<Admin_Class_Teacher_Result_Fill_Teacher_Data> mArrayList;
    private List<Admin_Class_Teacher_Result_Fill_Teacher_Data> mFilteredList;
    public Admin_Class_Teacher_First_Result_Analysis_Recycle_Adapter(Context context,  List<Admin_Class_Teacher_Result_Fill_Teacher_Data> data) {
        this.context = context;
        this.data=data;
        this.f_data=data;

        mArrayList = data;
        mFilteredList = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_class_teacher_result_first_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.a_sno.setText(mFilteredList.get(position).getNSrNo().toString());
        holder.a_teacher_name.setText(mFilteredList.get(position).getSTeacherName().toString());
        holder.a_teacher_designation.setText(mFilteredList.get(position).getSDesignation().toString());

        holder.a_teacher_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(context, Admin_Class_Teacher_Result_Analysis_Second.class).putExtra("data",f_data.get(position).getLTeacherId()+""));
            }
        });

    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

/*    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    f_data = data;
                } else {

                    List<Admin_Class_Teacher_Result_Fill_Teacher_Data> filteredList = new ArrayList<>();

                    for (Admin_Class_Teacher_Result_Fill_Teacher_Data getdata : data) {

                        if (*//*getdata.getSTeacherName().toLowerCase().charAt(0)==charSequence.charAt(0) && *//*getdata.getSTeacherName().toLowerCase().contains(charString)) {

                            filteredList.add(getdata);
                        }
                    }

                    f_data = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = f_data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                f_data = (List<Admin_Class_Teacher_Result_Fill_Teacher_Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/



    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<Admin_Class_Teacher_Result_Fill_Teacher_Data> filteredList = new ArrayList<>();

                    for (Admin_Class_Teacher_Result_Fill_Teacher_Data androidVersion : mArrayList) {

                        if (androidVersion.getSTeacherName().toLowerCase().contains(charString) && androidVersion.getSTeacherName().toLowerCase().charAt(0)==charSequence.charAt(0)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Admin_Class_Teacher_Result_Fill_Teacher_Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }







    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView a_sno, a_teacher_name, a_teacher_designation;


        public ViewHolder(View itemView) {
            super(itemView);

            a_sno = itemView.findViewById(R.id.admin_teacher_sno);
            a_teacher_name = itemView.findViewById(R.id.admin_teacher_name);
            a_teacher_designation = itemView.findViewById(R.id.admin_teacher_designation);


        }
    }
}
