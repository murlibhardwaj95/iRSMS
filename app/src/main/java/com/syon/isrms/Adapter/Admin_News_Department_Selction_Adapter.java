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
import android.widget.Toast;

import com.syon.isrms.Beans.Admin_Department_Fill_Bean_Data;
import com.syon.isrms.R;

import java.util.List;

public class Admin_News_Department_Selction_Adapter extends RecyclerView.Adapter<Admin_News_Department_Selction_Adapter.ViewHolder> {
    List<Admin_Department_Fill_Bean_Data> data;
    Context context;

    public Admin_News_Department_Selction_Adapter(Context context, List<Admin_Department_Fill_Bean_Data> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_select_department_layout_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.selection_checks.setText(data.get(position).getSDeptName().toString());
        holder.selection_checks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.selection_checks.isChecked()) {
                    String s = ",";
                    String s1 = ",";
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String a_ids = preferences.getString("get_department_id", "");
                    String a_names = preferences.getString("get_department_name", "");
                    s = a_ids + "," + data.get(position).getLDeptId().toString();
                    s1 = a_names + "," + data.get(position).getSDeptName().toString();
                    holder.add(s, s1);
                } else {
                    String s = ",";
                    String s1 = ",";
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String a_ids = preferences.getString("get_department_id", "");
                    String a_names = preferences.getString("get_department_name", "");
                    s = a_ids.toString();
                    s1 = a_names.toString();
                    holder.remove(s, s1, data.get(position).getSDeptName().toString(), data.get(position).getLDeptId().toString());

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox selection_checks;


        public ViewHolder(View itemView) {
            super(itemView);
            selection_checks = itemView.findViewById(R.id.txt_class);


        }
        public void add(String s, String s1) {

           /* teacher_ids.add(data.get(position).getEmpId().toString());
            //   teacher_ids.add(data.get(position).getEmpId().toString());
            teacher_names.add(data.get(position).getEmpName().toString());*/

            StringBuilder admin_id = new StringBuilder();
            StringBuilder admin_name = new StringBuilder();

            admin_id.append(s);
            admin_id.append(",");

            admin_name.append(s1);
            admin_name.append(",");


            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("department_id", admin_id.toString());
            editor.putString("department_name", admin_name.toString());
            editor.putString("get_department_id", s);
            editor.putString("get_department_name", s1);
            editor.commit();
        }

        public void remove(String s, String s1, String removeword, String removeword2) {

            StringBuilder admin_id = new StringBuilder();
            StringBuilder admin_name = new StringBuilder();

            admin_id.append(s);
            admin_id.append(",");

            admin_name.append(s1);
            admin_name.append(",");

            StringBuffer sentence = new StringBuffer(s.toString());
            StringBuffer sentence1 = new StringBuffer(s1.toString());
            String result1 = sentence.toString().replace(removeword, "");
            String result2 = sentence1.toString().replace(removeword2, "");


            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("department_id", result1.toString());
            editor.putString("department_name", result2.toString());
            editor.putString("get_department_id", result1);
            editor.putString("get_department_name", result2);
            editor.commit();
        }

    }


}
