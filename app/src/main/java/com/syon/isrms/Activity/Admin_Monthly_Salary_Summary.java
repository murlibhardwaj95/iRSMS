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

import com.syon.isrms.Adapter.Admin_Month_Adapter;
import com.syon.isrms.Adapter.Admin_Monthly_Salary_Adapter;
import com.syon.isrms.Beans.Admin_Month_Salary_Slip;
import com.syon.isrms.Beans.Admin_Monthly_Salary;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Monthly_Salary_Summary extends AppCompatActivity {
    ImageView back_button;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    RecyclerView salaryrecycler,month_recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__monthly__salary__summary);
        Toolbar toolbar =  findViewById(R.id.toolbar);
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
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Admin_Monthly_Salary> call = apiInterfce.monthsalary(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<Admin_Monthly_Salary>() {
                @Override
                public void onResponse(Call<Admin_Monthly_Salary> call, Response<Admin_Monthly_Salary> response) {
                    if (response.code() == 200) {
                        Admin_Monthly_Salary mainlist = response.body();
                        List<Admin_Month_Salary_Slip> data = mainlist.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Admin_Monthly_Salary_Summary.this);
                        salaryrecycler.setLayoutManager(layoutManager);
                        salaryrecycler.setAdapter(new Admin_Monthly_Salary_Adapter(Admin_Monthly_Salary_Summary.this, data));

                        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(Admin_Monthly_Salary_Summary.this);
                        month_recycler.setLayoutManager(layoutManager1);
                        month_recycler.setAdapter(new Admin_Month_Adapter(Admin_Monthly_Salary_Summary.this, data));


                    } else {
                        Toast.makeText(Admin_Monthly_Salary_Summary.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Admin_Monthly_Salary> call, Throwable t) {
                    Toast.makeText(Admin_Monthly_Salary_Summary.this, "Check Internet Connection Fail", Toast.LENGTH_SHORT).show();

                }
            });

        }catch(Exception e ){
            Toast.makeText(this, "Check Internet Connection try", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        back_button = findViewById(R.id.back_button);
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Monthly_Salary_Summary.this);
        salaryrecycler = findViewById(R.id.monthly_salary_recycler);
        month_recycler = findViewById(R.id.month_recyclerView);
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Monthly_Salary_Summary.super.onBackPressed();
            }
        });
    }

}
