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

import com.syon.isrms.Beans.CoRemarkBean;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Taacher_Gen_Remark_Entry extends AppCompatActivity {
    WebView webView;
    ApiInterfce apiInterfce;
    ImageView back_button;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taacher__gen__remark__entry);
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
        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<CoRemarkBean> call = apiInterfce.getRemarkSch(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<CoRemarkBean>() {
                @Override
                public void onResponse(Call<CoRemarkBean> call, Response<CoRemarkBean> response) {
                    CoRemarkBean remarkBean = response.body();
                    if (response.code()==200) {
                        String url = response.body().getData().toString();
                        loadWebView(url);
                    } else {
                        Toast.makeText(Taacher_Gen_Remark_Entry.this, "Data not Found", Toast.LENGTH_SHORT).show();
                        ImageView imageView = findViewById(R.id.teacher_data_not_found);
                        imageView.setVisibility(View.VISIBLE);
                        webView.setVisibility(View.GONE);


                    }
                }

                @Override
                public void onFailure(Call<CoRemarkBean> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
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
        preferences= PreferenceManager.getDefaultSharedPreferences(Taacher_Gen_Remark_Entry.this);
        back_button=findViewById(R.id.back_button);
        webView=findViewById(R.id.co_remark_web);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Taacher_Gen_Remark_Entry.super.onBackPressed();
            }
        });
    }


}
