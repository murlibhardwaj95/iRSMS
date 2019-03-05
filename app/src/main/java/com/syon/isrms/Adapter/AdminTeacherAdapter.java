package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.syon.isrms.Beans.Userbean_TeacherData;
import com.syon.isrms.R;

import java.util.List;

public class AdminTeacherAdapter extends RecyclerView.Adapter<AdminTeacherAdapter.ViewHolder> {
    Context context;
    List<Userbean_TeacherData> data;

    public AdminTeacherAdapter(Context context, List<Userbean_TeacherData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.teacherusername.setText(data.get(position).getEmpName().toString());
        holder.teacherdesignation.setText(data.get(position).getDesignation().toString());

        holder.teachercheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.teachercheck.isChecked()) {
                    String s = ",";
                    String s1 = ",";
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String t_ids = preferences.getString("get_teacher_id", "");
                    String t_names = preferences.getString("get_teacher_name", "");
                    s = t_ids + "," + data.get(position).getEmpId().toString();
                    s1 = t_names + "," + data.get(position).getEmpName().toString();
                    holder.add(s, s1);
                }
                else {
                    String s = ",";
                    String s1 = ",";
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String t_ids = preferences.getString("get_teacher_id", "");
                    String t_names = preferences.getString("get_teacher_name", "");
                    s = t_ids .toString();
                    s1 = t_names.toString();
                    holder.remove(s, s1,data.get(position).getEmpId().toString(),data.get(position).getEmpName().toString());

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView teacherusername, teacherdesignation;
        CheckBox teachercheck;

        public ViewHolder(View itemView) {
            super(itemView);
            teacherusername = itemView.findViewById(R.id.teacherusernametext);
            teacherdesignation = itemView.findViewById(R.id.teacherdesignationtext);
            teachercheck = itemView.findViewById(R.id.teachercheck);
        }

        public void add(String s, String s1) {


            StringBuilder teacher_id = new StringBuilder();
            StringBuilder teacher_name = new StringBuilder();

            teacher_id.append(s);
            teacher_id.append(",");

            teacher_name.append(s1);
            teacher_name.append(",");


            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("teacher_id", teacher_id.toString());
            editor.putString("teacher_name", teacher_name.toString());
            editor.putString("get_teacher_id", s);
            editor.putString("get_teacher_name", s1);
            editor.commit();
        }
        public void remove(String s, String s1,String removeword ,String removeword2) {


            StringBuilder teacher_id = new StringBuilder();
            StringBuilder teacher_name = new StringBuilder();

            teacher_id.append(s);
            teacher_id.append(",");

            teacher_name.append(s1);
            teacher_name.append(",");

            StringBuffer sentence = new StringBuffer(s.toString());
            StringBuffer sentence1 = new StringBuffer(s1.toString());
            String result1 = sentence.toString().replace(removeword, "");
            String result2=sentence1.toString().replace(removeword2, "");


            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("teacher_id", result1.toString());
            editor.putString("teacher_name", result2.toString());
            editor.putString("get_teacher_id", result1);
            editor.putString("get_teacher_name", result2);
            editor.commit();
        }


    }
}
