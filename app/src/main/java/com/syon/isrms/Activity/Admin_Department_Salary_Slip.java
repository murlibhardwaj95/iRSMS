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
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_Department_Salary_Adapter;
import com.syon.isrms.Adapter.Admin_department_name_Adapter;
import com.syon.isrms.Beans.Admin_Department_Salary;
import com.syon.isrms.Beans.Admin_Department_Salary_Slip_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Department_Salary_Slip extends AppCompatActivity {

    RecyclerView departmentsalary,departmentname;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__department__salary__slip);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doApiOperation() {
        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce =ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Admin_Department_Salary> call = apiInterfce.departmentsalary(getIntent().getStringExtra("lMESDId"),preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<Admin_Department_Salary>() {
                @Override
                public void onResponse(Call<Admin_Department_Salary> call, Response<Admin_Department_Salary> response) {
                //    Toast.makeText(Admin_Department_Salary_Slip.this, ""+getIntent().getStringExtra("lMESDId"), Toast.LENGTH_SHORT).show();
                    if(response.code()==200){
                        Admin_Department_Salary ads = response.body();
                        List<Admin_Department_Salary_Slip_Data> list = ads.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Admin_Department_Salary_Slip.this);
                        departmentsalary.setLayoutManager(layoutManager);
                        departmentsalary.setAdapter(new Admin_Department_Salary_Adapter(Admin_Department_Salary_Slip.this,list));

                        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(Admin_Department_Salary_Slip.this);
                        departmentname.setLayoutManager(layoutManager1);
                        departmentname.setAdapter(new Admin_department_name_Adapter(Admin_Department_Salary_Slip.this,list));


                    }else {
                        Toast.makeText(Admin_Department_Salary_Slip.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Admin_Department_Salary> call, Throwable t) {
                    Toast.makeText(Admin_Department_Salary_Slip.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e)
        {
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


    private void init() {
        backbutton = findViewById(R.id.back_button);
        departmentname = findViewById(R.id.department_name_recycler);
        departmentsalary = findViewById(R.id.department_salary_recycler);
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Department_Salary_Slip.this);
        doOperation();
    }

    private void doOperation() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Department_Salary_Slip.super.onBackPressed();
            }
        });
    }

}
