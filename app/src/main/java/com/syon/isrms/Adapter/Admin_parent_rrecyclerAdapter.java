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

import com.syon.isrms.Beans.Userbean_adminparent_data;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

public class Admin_parent_rrecyclerAdapter extends RecyclerView.Adapter<Admin_parent_rrecyclerAdapter.ViewHolder> {

    Context context;
    List<Userbean_adminparent_data> dataadmin;

    public Admin_parent_rrecyclerAdapter(Context context,List<Userbean_adminparent_data> dataadmin) {
    this.context = context;
    this.dataadmin = dataadmin;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_admin_layout, parent,false));    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.username.setText(dataadmin.get(position).getUserName());
        holder.subject.setText(dataadmin.get(position).getDesignation());
        holder.chekc.setOnCheckedChangeListener(null);
        holder.chekc.setTag(position);
        holder.chekc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.chekc.isChecked()) {
                    String s=",";
                    String s1=",";
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                    String ap_ids=preferences.getString("get_parent_admin_id","");
                    String ap_names=preferences.getString("get_parent_admin_name","");
                    s=ap_ids+","+dataadmin.get(position).getEmpId().toString();
                    s1=ap_names+","+dataadmin.get(position).getUserName().toString();
                    holder.add(s,s1);
                }
                else
                {
                    String s=",";
                    String s1=",";
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                    String ap_ids=preferences.getString("get_parent_admin_id","");
                    String ap_names=preferences.getString("get_parent_admin_name","");
                    s=ap_ids.toString();
                    s1=ap_names.toString();
                    holder.remove(s,s1,dataadmin.get(position).getEmpId().toString(),dataadmin.get(position).getUserName().toString());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataadmin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,subject;
        CheckBox chekc;
        ArrayList<String> admin_ids=new ArrayList<>();
        ArrayList<String> admin_names=new ArrayList<>();
        public ViewHolder(View itemView)
        { super(itemView);
          username = itemView.findViewById(R.id.adminusernametextparent);
          subject = itemView.findViewById(R.id.admindesignationtextparent);
          chekc = itemView.findViewById(R.id.admincheckparent);
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
            editor.putString("parent_admin_id",admin_id.toString());
            editor.putString("parent_admin_name",admin_name.toString());
            editor.putString("get_parent_admin_id",s);
            editor.putString("get_parent_admin_name",s1);
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

            editor.putString("parent_admin_id",result1.toString());
            editor.putString("parent_admin_name",result2.toString());
            editor.putString("get_parent_admin_id",result1);
            editor.putString("get_parent_admin_name",result2);
            editor.commit();


            /*editor.putString("teacher_id", result1.toString());
            editor.putString("teacher_name", result2.toString());
            editor.putString("get_teacher_id", result1);
            editor.putString("get_teacher_name", result2);
            editor.commit();*/
        }



    }
}