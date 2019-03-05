package com.syon.isrms.Activity;

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

import com.syon.isrms.Adapter.MyAttendance_Recycle_Adapter;
import com.syon.isrms.Beans.MyAttendanceBean;
import com.syon.isrms.Beans.MyAttendance_Data;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_My_Attendance extends AppCompatActivity {
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    RecyclerView recyclerView;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__my__attendance);
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
            Call<MyAttendanceBean> call = apiInterfce.getMyAttendance(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<MyAttendanceBean>() {
                @Override
                public void onResponse(Call<MyAttendanceBean> call, Response<MyAttendanceBean> response) {
                    MyAttendanceBean myAttendanceBean = response.body();
                    if (response.code()==200) {
                        List<MyAttendance_Data> data=  myAttendanceBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_My_Attendance.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new MyAttendance_Recycle_Adapter(Teacher_My_Attendance.this, data));
                    }
                    else {
                        Toast.makeText(Teacher_My_Attendance.this, "Data not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MyAttendanceBean> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        preferences= PreferenceManager.getDefaultSharedPreferences(Teacher_My_Attendance.this);
        back_button=findViewById(R.id.back_button);
        recyclerView=findViewById(R.id.MyAttedanceRecycler);
        doOperation();
    }
    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_My_Attendance.super.onBackPressed();
            }
        });
    }

}
