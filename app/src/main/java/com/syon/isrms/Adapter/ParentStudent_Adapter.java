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

import com.syon.isrms.Beans.Userbean_parent_studentList;
import com.syon.isrms.R;

import java.util.ArrayList;

public class ParentStudent_Adapter extends RecyclerView.Adapter<ParentStudent_Adapter.ViewHolder> {
    Context context;
    ArrayList<Userbean_parent_studentList> studentlistdata;

    public ParentStudent_Adapter(Context context, ArrayList<Userbean_parent_studentList> studentlistdata) {
        this.context = context;
        this.studentlistdata = studentlistdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.adminno.setText(studentlistdata.get(position).getAdmNo());
        holder.parentusername.setText(studentlistdata.get(position).getFatherName());
        holder.studentusername.setText(studentlistdata.get(position).getStudName());
        holder.studentcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.studentcheck.isChecked()) {
                    String s=",";
                    String s1=",";
                    SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
                    String s_ids=preferences.getString("get_student_id","");
                    String s_names=preferences.getString("get_student_name","");
                    s=s_ids+","+studentlistdata.get(position).getStudId().toString();
                    s1=s_names+","+studentlistdata.get(position).getStudName().toString();
                    holder.add(s,s1);
                }
                else {
                    String s = ",";
                    String s1 = ",";
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String s_ids=preferences.getString("get_student_id","");
                    String s_names=preferences.getString("get_student_name","");
                    s=s_ids.toString();
                    s1=s_names.toString();
                    holder.remove(s, s1,studentlistdata.get(position).getStudId().toString(),studentlistdata.get(position).getStudName().toString());

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentlistdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView parentusername, studentusername, adminno;
  /*      ArrayList<String> student_ids = new ArrayList<>();
        ArrayList<String> student_names = new ArrayList<>();*/
        CheckBox studentcheck;

        public ViewHolder(View itemView) {
            super(itemView);
            adminno = itemView.findViewById(R.id.studentadminno);
            parentusername = itemView.findViewById(R.id.parentusernametext);
            studentusername = itemView.findViewById(R.id.studentnametext);
            studentcheck = itemView.findViewById(R.id.studentcheck);

        }

        public void add(String s,String s1) {

           /* teacher_ids.add(data.get(position).getEmpId().toString());
            //   teacher_ids.add(data.get(position).getEmpId().toString());
            teacher_names.add(data.get(position).getEmpName().toString());*/

            StringBuilder student_id = new StringBuilder();
            StringBuilder student_name = new StringBuilder();

            student_id.append(s);
            student_id.append(",");

            student_name.append(s1);
            student_name.append(",");



            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("student_id",student_id.toString());
            editor.putString("student_name",student_name.toString());
            editor.putString("get_student_id",s);
            editor.putString("get_student_name",s1);
            editor.commit();
        }
        public void remove(String s, String s1,String removeword ,String removeword2) {

           /* teacher_ids.add(data.get(position).getEmpId().toString());
            //   teacher_ids.add(data.get(position).getEmpId().toString());
            teacher_names.add(data.get(position).getEmpName().toString());*/

            StringBuilder admin_id = new StringBuilder();
            StringBuilder admin_name = new StringBuilder();

            admin_id.append(s);
            admin_id.append(",");

            admin_name.append(s1);
            admin_name.append(",");

            StringBuffer sentence = new StringBuffer(s.toString());
            StringBuffer sentence1 = new StringBuffer(s1.toString());
            String result1 = sentence.toString().replace(removeword, "");
            String result2=sentence1.toString().replace(removeword2, "");


            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("student_id",result1.toString());
            editor.putString("student_name",result2.toString());
            editor.putString("get_student_id",result1);
            editor.putString("get_student_name",result2);
            editor.commit();


            /*editor.putString("teacher_id", result1.toString());
            editor.putString("teacher_name", result2.toString());
            editor.putString("get_teacher_id", result1);
            editor.putString("get_teacher_name", result2);
            editor.commit();*/
        }

    }
}
