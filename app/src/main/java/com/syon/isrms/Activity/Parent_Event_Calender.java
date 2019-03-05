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

import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Parent_Event_Calender extends AppCompatActivity {
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    RecyclerView recyclerView;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__event__calender);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }
        init();
        doApiOperation();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Parent_Event_Calender.this);
        recyclerView = findViewById(R.id.Expendable_list);
        back_button=findViewById(R.id.back_button);
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parent_Event_Calender.super.onBackPressed();
            }
        });
    }

    private void doApiOperation() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        String lClassId=preferences.getString("lClass_IdNo","");
        Call<com.syon.isrms.Beans.ParentEventYearlyBean> call = apiInterfce.getParentEventYearly(preferences.getString(getString(R.string.lClass_IdNo),""), preferences.getString(getString(R.string.lSessionIdNo),""), preferences.getString(getString(R.string.sSchCode),""));
        call.enqueue(new Callback<com.syon.isrms.Beans.ParentEventYearlyBean>() {
            @Override
            public void onResponse(Call<com.syon.isrms.Beans.ParentEventYearlyBean> call, Response<com.syon.isrms.Beans.ParentEventYearlyBean> response) {
                if(response.code()==200)
                {
                    com.syon.isrms.Beans.ParentEventYearlyBean groupBean = response.body();
                    List<com.syon.isrms.Beans.Parent_Event_Yealy_Bean_Data> data = groupBean.getData();
                    String[] parameter = {preferences.getString(getString(R.string.lClass_IdNo),""), preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), "")};
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Parent_Event_Calender.this);
                    recyclerView.setLayoutManager(layoutManager);
                  /*  recyclerView.smoothScrollToPosition(data.size());*/
                    recyclerView.setAdapter(new com.syon.isrms.Adapter.Parent_ExpendableList_Recycle_Adapter(Parent_Event_Calender.this, data, parameter));


                }
                else
                {
                    Toast.makeText(Parent_Event_Calender.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<com.syon.isrms.Beans.ParentEventYearlyBean> call, Throwable t) {
                Toast.makeText(Parent_Event_Calender.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
