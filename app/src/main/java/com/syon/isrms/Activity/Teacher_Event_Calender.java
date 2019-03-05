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

import com.syon.isrms.Adapter.ExpendableList_Recycle_Adapter;
import com.syon.isrms.Beans.EventCalenderYearlyBean;
import com.syon.isrms.Beans.Event_calender_Yearly_Data;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Event_Calender extends AppCompatActivity {

    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    RecyclerView recyclerView;
    ImageView back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__event__calender);
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
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Event_Calender.this);
        recyclerView = findViewById(R.id.Expendable_list);
        back_button = findViewById(R.id.back_button);
        doOperation();
    }
    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Event_Calender.super.onBackPressed();
            }
        });
    }

    private void doApiOperation() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<EventCalenderYearlyBean> call = apiInterfce.getEventYearly(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
        call.enqueue(new Callback<EventCalenderYearlyBean>() {
            @Override
            public void onResponse(Call<EventCalenderYearlyBean> call, Response<EventCalenderYearlyBean> response) {
                if(response.code()==200)
                {
                    EventCalenderYearlyBean groupBean = response.body();
                    List<Event_calender_Yearly_Data> data = groupBean.getData();
                    String[] parameter = {preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), "")};

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_Event_Calender.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new ExpendableList_Recycle_Adapter(Teacher_Event_Calender.this, data, parameter));


                }
                else
                {
                    ImageView imageView = findViewById(R.id.teacher_data_not_found);
                    imageView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<EventCalenderYearlyBean> call, Throwable t) {
                Toast.makeText(Teacher_Event_Calender.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
