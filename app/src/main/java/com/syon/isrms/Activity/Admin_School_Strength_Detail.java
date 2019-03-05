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

import com.syon.isrms.Beans.AdminSchoolStrengthWebBean;
import com.syon.isrms.Beans.AttendanceBEan;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_School_Strength_Detail extends AppCompatActivity {
    ImageView back_button;
    WebView webView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__school__strength__detail);
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
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminSchoolStrengthWebBean> call = apiInterfce.getAdminSchoolStrengthWeb(preferences.getString(getString(R.string.adminUserName), ""), preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminSchoolStrengthWebBean>() {
                @Override
                public void onResponse(Call<AdminSchoolStrengthWebBean> call, Response<AdminSchoolStrengthWebBean> response) {

                    if (response.isSuccessful()) {
                        String url = response.body().getData().toString();
                        loadUrl(url);

                    } else {
                        Toast.makeText(Admin_School_Strength_Detail.this, "Data not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AdminSchoolStrengthWebBean> call, Throwable t) {
                    Toast.makeText(Admin_School_Strength_Detail.this, "internet Connection maybe show or off", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        back_button = findViewById(R.id.back_button);
        webView = findViewById(R.id.webViewSchoolStrength);
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_School_Strength_Detail.this);
        doOperation();

    }

      private void loadUrl(String url) {
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_School_Strength_Detail.super.onBackPressed();
            }
        });


    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
