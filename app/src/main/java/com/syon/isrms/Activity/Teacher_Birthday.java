package com.syon.isrms.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Birthday_Recycle_Adapter;
import com.syon.isrms.Beans.BirthdayBean;
import com.syon.isrms.Beans.Birthday_Bean_Data;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Birthday extends AppCompatActivity {
RecyclerView recyclerView;
SharedPreferences preferences;
ApiInterfce apiInterfce;
ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__birthday);
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        final ProgressDialog dialog= new ProgressDialog(Teacher_Birthday.this);
        dialog.setMessage("Loading..");
        dialog.show();
        Call<BirthdayBean> call = apiInterfce.getBirthday(preferences.getString(getString(R.string.SchoolCode), ""));
        call.enqueue(new Callback<BirthdayBean>() {
            @Override
            public void onResponse(Call<BirthdayBean> call, Response<BirthdayBean> response) {
                if(response.code()==200)
                {
                    dialog.dismiss();
                    BirthdayBean birthdayBean=response.body();
                    List<Birthday_Bean_Data> data=birthdayBean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_Birthday.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new Birthday_Recycle_Adapter(Teacher_Birthday.this, data));
                }
                else {
                    dialog.dismiss();
                    Toast.makeText(Teacher_Birthday.this, "Data not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BirthdayBean> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(Teacher_Birthday.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init() {
        recyclerView=findViewById(R.id.birthday_recycler);
        back_button=findViewById(R.id.back_button);
        preferences= PreferenceManager.getDefaultSharedPreferences(Teacher_Birthday.this);
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Birthday.super.onBackPressed();
            }
        });


    }

}
