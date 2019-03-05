package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Beans.Admin_Userbean_Emplyoee;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Employee_Attendance extends AppCompatActivity {
    ImageView back_button;
    WebView webView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__employee__attendance);
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
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Admin_Userbean_Emplyoee> call = apiInterfce.employeweb(preferences.getString(getString(R.string.adminUserName), ""),preferences.getString(getString(R.string.adminSessionIdNo),""),preferences.getString(getString(R.string.adminSchCode),""));
            call.enqueue(new Callback<Admin_Userbean_Emplyoee>() {
                @Override
                public void onResponse(Call<Admin_Userbean_Emplyoee> call, Response<Admin_Userbean_Emplyoee> response) {
                    if(response.code() == 200){
                        Admin_Userbean_Emplyoee list = response.body();
                        String url = list.getData();
                        loadUrl(url);
                    }else {
                        Toast.makeText(Admin_Employee_Attendance.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Admin_Userbean_Emplyoee> call, Throwable t) {
                    Toast.makeText(Admin_Employee_Attendance.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e ){
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
    private void loadUrl(String url) {
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void init() {
        back_button = findViewById(R.id.back_button);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        webView = findViewById(R.id.employee_data);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Employee_Attendance.super.onBackPressed();
            }
        });


    }


}
