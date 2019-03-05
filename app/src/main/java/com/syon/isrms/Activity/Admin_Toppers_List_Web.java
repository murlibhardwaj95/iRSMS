package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Beans.Admin_Userbean_ToopersList;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Toppers_List_Web extends AppCompatActivity {
WebView webView;
SharedPreferences preferences;
ApiInterfce apiInterfce;
ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__toppers__list__web);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();
        doApioperation();

    }

    private void doApioperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Admin_Userbean_ToopersList> call = apiInterfce.topperslistweb(preferences.getString(getString(R.string.adminUserName), ""),getIntent().getStringExtra("classId"),getIntent().getStringExtra("streamId"),getIntent().getStringExtra("topperExamId"),preferences.getString(getString(R.string.adminSessionIdNo),""),preferences.getString(getString(R.string.adminSchCode),""));
            call.enqueue(new Callback<Admin_Userbean_ToopersList>() {
                @Override
                public void onResponse(Call<Admin_Userbean_ToopersList> call, Response<Admin_Userbean_ToopersList> response) {
                    if(response.code() == 200){
                        Admin_Userbean_ToopersList list = response.body();
                        String url = response.body().getData().toString();
                        loadWebView(url);

                    }
                    else{
                        /*Toast.makeText(Admin_Toppers_List_Web.this, "Check Internet Connection  1", Toast.LENGTH_SHORT).show();*/
                    }
                }

                @Override
                public void onFailure(Call<Admin_Userbean_ToopersList> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        back_button=findViewById(R.id.back_button);
        webView=findViewById(R.id.adminTopperWebview);
        preferences= PreferenceManager.getDefaultSharedPreferences(Admin_Toppers_List_Web.this);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Toppers_List_Web.super.onBackPressed();
            }
        });
    }
    private void loadWebView(String url) {
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

}
