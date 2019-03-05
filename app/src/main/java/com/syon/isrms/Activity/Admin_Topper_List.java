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

import com.syon.isrms.Adapter.Admin_topperlist_adapter;
import com.syon.isrms.Beans.AdminToppersListBean;
import com.syon.isrms.Beans.Admin_Toppers_List_Data;
import com.syon.isrms.Beans.CoSchBean;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Topper_List extends AppCompatActivity {
    RecyclerView topperslist;
    ImageView back_button;
    ApiInterfce  apiInterfce;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__topper__list);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doApioperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminToppersListBean> call = apiInterfce.getAdminToppersList(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminToppersListBean>() {
                @Override
                public void onResponse(Call<AdminToppersListBean> call, Response<AdminToppersListBean> response) {

                    if (response.code()==200) {
                        AdminToppersListBean toppersListBean = response.body();
                        List<Admin_Toppers_List_Data> data=toppersListBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        topperslist.setLayoutManager(layoutManager);
                        topperslist.setAdapter(new Admin_topperlist_adapter(Admin_Topper_List.this, data));
                    } else {
                        Toast.makeText(Admin_Topper_List.this, "Data not found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminToppersListBean> call, Throwable t) {
                    Toast.makeText(Admin_Topper_List.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }



    }

    private void init() {
        topperslist = findViewById(R.id.recycler_toppers_list);
        back_button = findViewById(R.id.back_button);
        preferences= PreferenceManager.getDefaultSharedPreferences(Admin_Topper_List.this);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Topper_List.super.onBackPressed();
            }
        });
    }

}
