package com.syon.isrms.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.syon.isrms.Activity.Admin_Leave_Approval;
import com.syon.isrms.Beans.Userbean_Admin_Leave_Approval_Put;
import com.syon.isrms.Beans.Userbean_Admin_Leave_Get_2;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Leave_Approval_Adapter extends RecyclerView.Adapter<Admin_Leave_Approval_Adapter.ViewHolder> {

    Context ctx;
    int adminid;
    String lleaveid,remark;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    List<Userbean_Admin_Leave_Get_2> datal;

    public Admin_Leave_Approval_Adapter(Context ctx, List<Userbean_Admin_Leave_Get_2> datal) {
        this.ctx = ctx;
        this.datal = datal;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_leave_approval_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        holder.appno.setText(datal.get(position).getLeaveAppNo().toString());
        holder.appdate.setText(datal.get(position).getLeaveAppDate().toString());
        holder.empname.setText(datal.get(position).getSEmpName().toString());
        holder.leavedate.setText(datal.get(position).getLeaveFromToDate().toString());
        holder.days.setText(datal.get(position).getDTotalLeave().toString());
        holder.daysedit.setText(datal.get(position).getSLeaveDetails().toString());
        holder.reasondata.setText(datal.get(position).getSReason().toString());
        holder.spintwo.setText(datal.get(position).getSLeaveAprvStatus().toString());
        if(datal.get(position).getSLeaveAprvStatus().toLowerCase().toString().equals("approved")){
            holder.spintwo.setTextColor(Color.parseColor("#006400"));
        }else if(datal.get(position).getSLeaveAprvStatus().toString().toLowerCase().equals("not approved")){
            holder.spintwo.setTextColor(Color.parseColor("#8B0000"));
        }else if(datal.get(position).getSLeaveAprvStatus().toString().toLowerCase().equals("pending")){
            holder.spintwo.setTextColor(Color.parseColor("#FFD700"));
        }
        remark = String.valueOf(holder.remarks.getText());
        lleaveid = datal.get(position).getLLeaveAppId().toString();

        holder.addstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ctx);
                dialog.setContentView(R.layout.dailog_admin_leave_approval);
                final RadioGroup[] lv = {dialog.findViewById(R.id.radiogroupleave)};
                final RadioButton[] rd = new RadioButton[1];
                Button done = (Button) dialog.findViewById(R.id.donedailog);
                ImageView im = (ImageView) dialog.findViewById(R.id.cancel);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = lv[0].getCheckedRadioButtonId();
                        rd[0] = (RadioButton) dialog.findViewById(selectedId);
                        if(rd[0].getText().toString().toLowerCase().equals("approved")){
                            adminid = 1;
                            holder.spintwo.setTextColor(Color.parseColor("#006400"));
                        }else if(rd[0].getText().toString().toLowerCase().equals("not approved")){
                            adminid = 2;
                            holder.spintwo.setTextColor(Color.parseColor("#8B0000"));
                        }else if(rd[0].getText().toString().toLowerCase().equals("pending")){
                            adminid= 3;
                            holder.spintwo.setTextColor(Color.parseColor("#FFD700"));
                        }
                      //  Toast.makeText(ctx, ""+adminid, Toast.LENGTH_SHORT).show();
                     //   Toast.makeText(ctx,rd[0].getText(), Toast.LENGTH_LONG).show();
                        holder.spintwo.setText(rd[0].getText().toString());

                        dialog.dismiss();

                    }
                });
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setTitle("Inbox");
                dialog.show();

            }
        });
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doApiOperation();
            }
        });

       /* String[] arraySpinner = new String[] { "Approved", "Not Approved", "Pending"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        holder.spintwo.setAdapter(adapter);
      */ /* holder.spintwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    adminid = 1;
                }else if(position==1){
                    adminid = 2;
                }else if(position==2){
                    adminid= 3;
                }


    }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    */
    }

    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Userbean_Admin_Leave_Approval_Put> call = apiInterfce.putleaveadmin(lleaveid,adminid,remark,preferences.getString(ctx.getString(R.string.adminEmpIdNo),""),preferences.getString(ctx.getString(R.string.adminSessionIdNo), ""), preferences.getString(ctx.getString(R.string.adminSchCode), ""));
        call.enqueue(new Callback<Userbean_Admin_Leave_Approval_Put>() {
            @Override
            public void onResponse(Call<Userbean_Admin_Leave_Approval_Put> call, Response<Userbean_Admin_Leave_Approval_Put> response) {
                Userbean_Admin_Leave_Approval_Put status = response.body();
                Toast.makeText(ctx,""+status.getData(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Userbean_Admin_Leave_Approval_Put> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return datal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView appno,appdate,empname,leavedate,days,daysedit,reasondata;
        ImageButton addstatus;
        EditText remarks,spintwo;
        Button submit;
        public ViewHolder(View itemView) {
            super(itemView);
            appno = itemView.findViewById(R.id.appno);
            appdate = itemView.findViewById(R.id.appdate);
            empname = itemView.findViewById(R.id.empname);
            spintwo = itemView.findViewById(R.id.spinnertwo);
            addstatus = itemView.findViewById(R.id.addstatus);
            leavedate = itemView.findViewById(R.id.ldate);
            days = itemView.findViewById(R.id.daysedit);
            daysedit = itemView.findViewById(R.id.daysedit2);
            reasondata = itemView.findViewById(R.id.resaondata);
            remarks = itemView.findViewById(R.id.remak1);
            submit = itemView.findViewById(R.id.submitleave);

        }
    }
}
