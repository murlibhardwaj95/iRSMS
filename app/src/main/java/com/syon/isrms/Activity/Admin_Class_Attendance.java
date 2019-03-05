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

import com.syon.isrms.Beans.AdminClassAttendanceBean;
import com.syon.isrms.Beans.Admin_Class_Attendance_Bean_Data;
import com.syon.isrms.Beans.CoSchBean;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;
import com.syon.isrms.Adapter.Admin_Class_Attendance_Adapter;
import com.syon.isrms.HelperClasses.ColorClass;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Class_Attendance extends AppCompatActivity {

    RecyclerView class_attendance;
    ImageView back_button;
    BarChart barchart;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__class__attendance);
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminClassAttendanceBean> call = apiInterfce.getAdminClassAttendance(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminClassAttendanceBean>() {
                @Override
                public void onResponse(Call<AdminClassAttendanceBean> call, Response<AdminClassAttendanceBean> response) {

                    if (response.code()==200) {
                        AdminClassAttendanceBean attendanceBean = response.body();
                        List<Admin_Class_Attendance_Bean_Data> data=attendanceBean.getData();
                        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {
                            bargroup1.add(new BarEntry(Float.parseFloat(data.get(i).getDPresentPercent().toString()), i));
                        }
                        // create BarEntry for Bar Group 1
                        ArrayList<BarEntry> bargroup2 = new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {
                            bargroup2.add(new BarEntry(Float.parseFloat(data.get(i).getDAbsentPercent().toString()), i));
                        }

                        BarDataSet barDataSet1 = new BarDataSet(bargroup1, "Present");

                        //barDataSet1.setColor(Color.rgb(0, 155, 0));
                        barDataSet1.setColors(ColorClass.NEW_COLOR1);

                        // creating dataset for Bar Group 2
                        BarDataSet barDataSet2 = new BarDataSet(bargroup2, "Absent");
                        barDataSet2.setColors(ColorClass.NEW_COLOR2);

                        ArrayList<String> labels = new ArrayList<String>();
                        for (int i = 0; i < data.size(); i++) {
                            labels.add(data.get(i).getSClass().toString());
                        }

                        ArrayList<BarDataSet> dataSets = new ArrayList<>();
                        dataSets.add(barDataSet1);
                        dataSets.add(barDataSet2);
                        barchart.setDescription("");
                        BarData bdata = new BarData(labels, dataSets);
                        barchart.setData(bdata);


                    /*    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        class_attendance.setLayoutManager(layoutManager);
                        class_attendance.setAdapter(new Admin_Class_Attendance_Adapter(getApplication(),data));
                    */    // start graph


                    } else {
                        Toast.makeText(Admin_Class_Attendance.this, "Data Not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminClassAttendanceBean> call, Throwable t) {
                    Toast.makeText(Admin_Class_Attendance.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }


    }


    private void init() {
        class_attendance = findViewById(R.id.recycler_class_attendance);
        back_button = findViewById(R.id.back_button);
        barchart = findViewById(R.id.barchartclass);
        preferences= PreferenceManager.getDefaultSharedPreferences(Admin_Class_Attendance.this);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Class_Attendance.super.onBackPressed();
            }
        });

    }


}
