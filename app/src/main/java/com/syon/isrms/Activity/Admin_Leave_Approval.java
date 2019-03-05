package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_Leave_Approval_Adapter;
import com.syon.isrms.Beans.Userbean_Admin_Leave_Get_1;
import com.syon.isrms.Beans.Userbean_Admin_Leave_Get_2;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Leave_Approval extends AppCompatActivity {
    private static final int PENDING_FOR_APPROVAL = 0;
    ImageView back_button;
    Spinner leavestatus,spinn2;
    ApiInterfce apiInterfce;
    int adminid;
    SharedPreferences preferences;
    RecyclerView leavedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__leave__approval);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();
        doApiOperation();
}

    private void doApiOperation() {
     try{
         OkHttpClient.Builder builder = new OkHttpClient.Builder();
         apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
         Call<Userbean_Admin_Leave_Get_1> call = apiInterfce.leaveaprovedadmin(adminid+"",preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
         call.enqueue(new Callback<Userbean_Admin_Leave_Get_1>() {
             @Override
             public void onResponse(Call<Userbean_Admin_Leave_Get_1> call, Response<Userbean_Admin_Leave_Get_1> response) {

                 if(response.code()==200){
                     // Toast.makeText(Admin_Leave_Approval.this, ""+adminid, Toast.LENGTH_SHORT).show();
                     Userbean_Admin_Leave_Get_1 data = response.body();
                     List<Userbean_Admin_Leave_Get_2> list = data.getData();

                     RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(Admin_Leave_Approval.this);
                     leavedata.setLayoutManager(layoutManager1);
                     leavedata.setAdapter(new Admin_Leave_Approval_Adapter(Admin_Leave_Approval.this,list));

                 }else{

                 }

             }
             @Override
             public void onFailure(Call<Userbean_Admin_Leave_Get_1> call, Throwable t) {

                 Toast.makeText(Admin_Leave_Approval.this, "Data Not Found", Toast.LENGTH_SHORT).show();
             }
         });
     }catch (Exception e){
         Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
     }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        back_button = findViewById(R.id.back_button);
        leavestatus = findViewById(R.id.approvalstatus);
        leavedata = findViewById(R.id.leave_recycler);
        spinn2 = findViewById(R.id.spinnertwo);
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Leave_Approval.this);
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Leave_Approval.super.onBackPressed(); }
        });

        String[] arraySpinner = new String[] {"Pending for Approval", "Approved", "Not Approved", "All"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        leavestatus.setAdapter(adapter);

      /*  id= adapter.getPosition(""+arraySpinner);
        leavestatus.setSelection(id);*/

   leavestatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          // Toast.makeText(Admin_Leave_Approval.this, ""+position, Toast.LENGTH_SHORT).show();
           if(position==0){
               adminid = 0;
               doApiOperation();
           }else if(position==1){
               adminid = 1;
               doApiOperation();
           }else if(position==2){
               adminid = 2;
               doApiOperation();
           }else if(position==3){
               adminid = 3;
               doApiOperation();
           }
       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {

       }
   });
    }

}
