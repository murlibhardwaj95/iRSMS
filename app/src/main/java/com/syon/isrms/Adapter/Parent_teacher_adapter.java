package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.syon.isrms.Beans.Userbean_ctParent_Data;
import com.syon.isrms.R;

import java.util.List;

public class Parent_teacher_adapter extends RecyclerView.Adapter<Parent_teacher_adapter.ViewHolder> {

    Context context;
    List<Userbean_ctParent_Data> ct;

    public Parent_teacher_adapter(Context context,List<Userbean_ctParent_Data> ct) {
    this.context = context;
    this.ct = ct;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_teacher_layout, parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.username.setText(ct.get(position).getUserName());
        holder.subject.setText(ct.get(position).getSubject());
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.check.isChecked()) {
                    String s=",";
                    String s1=",";
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                    String ap_ids=preferences.getString("get_parent_class_teacher_id","");
                    String ap_names=preferences.getString("get_parent_class_teacher_name","");
                    s=ap_ids+","+ct.get(position).getEmpId().toString();
                    s1=ap_names+","+ct.get(position).getUserName().toString();
                    holder.add(s,s1);
                }
                else
                {
                    String s=",";
                    String s1=",";
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                    String ap_ids=preferences.getString("get_parent_class_teacher_id","");
                    String ap_names=preferences.getString("get_parent_class_teacher_name","");
                    s=ap_ids.toString();
                    s1=ap_names.toString();
                    holder.remove(s,s1,ct.get(position).getEmpId().toString(),ct.get(position).getUserName().toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,subject;
        CheckBox check;
        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.teacherusernametextparent);
            subject = itemView.findViewById(R.id.teacherdesignationtextparent);
            check = itemView.findViewById(R.id.teachercheckparent);
        }
        public void add(String s, String s1) {
            StringBuilder admin_id = new StringBuilder();
            StringBuilder admin_name = new StringBuilder();

            admin_id.append(s);
            admin_id.append(",");

            admin_name.append(s1);
            admin_name.append(",");



            SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putString("parent_class_teacher_id",admin_id.toString());
            editor.putString("parent_class_teacher_name",admin_name.toString());
            editor.putString("get_parent_class_teacher_id",s);
            editor.putString("get_parent_class_teacher_name",s1);
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

            editor.putString("parent_class_teacher_id",result1.toString());
            editor.putString("parent_class_teacher_name",result2.toString());
            editor.putString("get_parent_class_teacher_id",result1);
            editor.putString("get_parent_class_teacher_name",result2);
            editor.commit();


            /*editor.putString("teacher_id", result1.toString());
            editor.putString("teacher_name", result2.toString());
            editor.putString("get_teacher_id", result1);
            editor.putString("get_teacher_name", result2);
            editor.commit();*/
        }

    }
}
