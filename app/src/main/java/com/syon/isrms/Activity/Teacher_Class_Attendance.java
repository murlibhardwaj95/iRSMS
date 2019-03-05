package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Beans.ClassAttendanceBean;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Class_Attendance extends AppCompatActivity {
    WebView webView;
    ApiInterfce apiInterfce;
    ImageView back_button;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__class__attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doApiOperation();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<ClassAttendanceBean> call = apiInterfce.getClassAttendance(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<ClassAttendanceBean>() {
                @Override
                public void onResponse(Call<ClassAttendanceBean> call, Response<ClassAttendanceBean> response) {
                    ClassAttendanceBean markBean = response.body();
                    if (response.code()==200) {
                        String url = response.body().getData().toString();
                        loadWebView(url);
                    } else {
                        Toast.makeText(Teacher_Class_Attendance.this, "Data Not Found ", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ClassAttendanceBean> call, Throwable t) {
                    Toast.makeText(Teacher_Class_Attendance.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void loadWebView(String url) {
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Class_Attendance.this);
        back_button = findViewById(R.id.back_button);
        webView = findViewById(R.id.classAttendanceWeb);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Class_Attendance.super.onBackPressed();
            }
        });
    }

}
