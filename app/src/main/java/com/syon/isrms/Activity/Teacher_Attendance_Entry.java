package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Beans.AttendanceBEan;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Attendance_Entry extends AppCompatActivity {
    ApiInterfce apiInterfce;
    WebView webview;
    ImageView back_button;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__attendance__entry);
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

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Attendance_Entry.this);
        back_button=findViewById(R.id.back_button);
        webview = findViewById(R.id.Attendance_web);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Attendance_Entry.super.onBackPressed();
            }
        });
    }

    private void doApiOperation() {
        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AttendanceBEan> call = apiInterfce.getAttendanceData(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<AttendanceBEan>() {
                @Override
                public void onResponse(Call<AttendanceBEan> call, Response<AttendanceBEan> response) {
                    AttendanceBEan attendanceBEan = response.body();
                    if (response.code()==200) {
                        String url = response.body().getData().toString();
                      /*  loadWebView(url);*/
                        webview.setWebViewClient(new MyBrowser());
                        webview.getSettings().setJavaScriptEnabled(true);
                        webview.loadUrl(url);


                    } else {
                        Toast.makeText(Teacher_Attendance_Entry.this, "Data not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AttendanceBEan> call, Throwable t) {
                    Toast.makeText(Teacher_Attendance_Entry.this, "internet Connection maybe show or off", Toast.LENGTH_SHORT).show();
                }


            });

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

  /*  private void loadWebView(String url) {
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.loadUrl(url);
    }*/


 /*   private void loadwebview(String url) {
        Toast.makeText(this, url+"", Toast.LENGTH_SHORT).show();

    }*/

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
