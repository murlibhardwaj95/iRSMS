package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.LeaveAdapter;
import com.syon.isrms.Beans.Userbean_LeaveData;
import com.syon.isrms.Beans.Userbean_LeaveMain;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_MyLeaves extends AppCompatActivity {

    RecyclerView recyclerleave;
    ImageView back_button_leave;
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__my_leaves);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doOperation();
        doApiOperation();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doOperation() {
        fab = (FloatingActionButton) findViewById(R.id.fableave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Teacher_MyLeaves.this, Teacher_Take_Leave.class));
            }
        });
        back_button_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_MyLeaves.super.onBackPressed();
            }
        });

    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_MyLeaves.this);
        recyclerleave = findViewById(R.id.recyclerleave);

        back_button_leave = findViewById(R.id.back_button);
    }

    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ApiInterfce apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Userbean_LeaveMain> call = apiInterfce.tleave(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
        call.enqueue(new Callback<Userbean_LeaveMain>() {
            @Override
            public void onResponse(Call<Userbean_LeaveMain> call, Response<Userbean_LeaveMain> response) {
                Userbean_LeaveMain userbean_leaveMain = response.body();
                if (response.code() == 200) {
                    List<Userbean_LeaveData> userbean_leaveData = userbean_leaveMain.getData();

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerleave.setLayoutManager(layoutManager);
                    recyclerleave.setAdapter(new LeaveAdapter(getApplicationContext(), userbean_leaveData));
                } else {
                    ImageView imageView = findViewById(R.id.teacher_data_not_found);
                    imageView.setVisibility(View.VISIBLE);
                    recyclerleave.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<Userbean_LeaveMain> call, Throwable t) {
                Toast.makeText(Teacher_MyLeaves.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
