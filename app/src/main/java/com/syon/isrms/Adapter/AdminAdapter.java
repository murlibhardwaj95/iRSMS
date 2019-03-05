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

import com.syon.isrms.Beans.Userbean_AdminData;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {
    Context context;
    List<Userbean_AdminData> data;

    public AdminAdapter(Context context,List<Userbean_AdminData> data ) {
        this.context=context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.usernametext.setText(data.get(position).getUserName().toString());
        holder.designationtext.setText(data.get(position).getDesignation().toString());
        holder.admincheck.setOnCheckedChangeListener(null);
        holder.admincheck.setTag(position);
        holder.admincheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.admincheck.isChecked()) {
                    String s=",";
                    String s1=",";
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                    String a_ids=preferences.getString("get_admin_id","");
                    String a_names=preferences.getString("get_admin_name","");
                    s=a_ids+","+data.get(position).getEmpId().toString();
                    s1=a_names+","+data.get(position).getUserName().toString();
                    holder.add(s, s1);
                }
                else {
                    String s=",";
                    String s1=",";
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                    String a_ids=preferences.getString("get_admin_id","");
                    String a_names=preferences.getString("get_admin_name","");
                    s=a_ids.toString();
                    s1=a_names.toString();
                    holder.remove(s,s1,data.get(position).getEmpId().toString(),data.get(position).getUserName().toString());

                }

            }
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView usernametext,designationtext;
        CheckBox admincheck;
        ArrayList<String> admin_ids=new ArrayList<>();
        ArrayList<String> admin_names=new ArrayList<>();
        public ViewHolder(View itemView) {
            super(itemView);
            usernametext=itemView.findViewById(R.id.adminusernametext);
            designationtext = itemView.findViewById(R.id.admindesignationtext);
            admincheck = itemView.findViewById(R.id.admincheck);


        }
        public void add(String s,String s1) {

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
            editor.putString("admin_id",admin_id.toString());
            editor.putString("admin_name",admin_name.toString());
            editor.putString("get_admin_id",s);
            editor.putString("get_admin_name",s1);
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

            editor.putString("admin_id",result1.toString());
            editor.putString("admin_name",result2.toString());
            editor.putString("get_admin_id",result1);
            editor.putString("get_admin_name",result2);
            editor.commit();


            /*editor.putString("teacher_id", result1.toString());
            editor.putString("teacher_name", result2.toString());
            editor.putString("get_teacher_id", result1);
            editor.putString("get_teacher_name", result2);
            editor.commit();*/
        }



    }
}
