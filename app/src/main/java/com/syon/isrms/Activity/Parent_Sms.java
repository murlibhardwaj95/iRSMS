package com.syon.isrms.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.syon.isrms.Adapter.Sms_Adapter;
import com.syon.isrms.Beans.Userbean_SMS_Main;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Parent_Sms extends AppCompatActivity {
    RecyclerView sms_recycler;
    ImageView smsback;
    ApiInterfce apiInterfce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doOperation();
        doApiOperation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }

    }
    private void doOperation() {
        smsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parent_Sms.super.onBackPressed();
            }
        });
    }

    private void init() {
        sms_recycler = findViewById(R.id.sms_recycler);
        smsback = findViewById(R.id.back_button);
    }

    private void doApiOperation() {
        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_SMS_Main> call = apiInterfce.sms("3375","112","MGS001");
            call.enqueue(new Callback<Userbean_SMS_Main>() {
                @Override
                public void onResponse(Call<Userbean_SMS_Main> call, Response<Userbean_SMS_Main> response) {
                    if(response.code() == 200){
                        Userbean_SMS_Main mainsms = response.body();
                        List<com.syon.isrms.Beans.Userbean_SMS_Data> data = mainsms.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        sms_recycler.setLayoutManager(layoutManager);
                        sms_recycler.setAdapter(new Sms_Adapter(getApplicationContext(),data));
                    }else{
                        LinearLayout linearLayout=findViewById(R.id.datanotfound);
                        linearLayout.setVisibility(View.VISIBLE);
                        sms_recycler.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Userbean_SMS_Main> call, Throwable t) {

                    Toast.makeText(Parent_Sms.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e ){

        }
    }

}
