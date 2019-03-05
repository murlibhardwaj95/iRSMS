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


import com.syon.isrms.Adapter.Admin_Class_Result_Analysis_Detail_Recycle_Adapter;
import com.syon.isrms.Adapter.Admin_DetailClick_Recycle_Adapter;
import com.syon.isrms.Adapter.ResultAnalysis_Detail_Recycle_Adapter;
import com.syon.isrms.Beans.AdminClassResultSelectedDetailExamBean;
import com.syon.isrms.Beans.Admin_Class_Result_Selected_Deatil_Exam_Bean;
import com.syon.isrms.Beans.Admin_DetailClick_Main;
import com.syon.isrms.Beans.Admin_DetailClik_Data;
import com.syon.isrms.Beans.ResultAnalysisDetailBean;
import com.syon.isrms.Beans.Result_Analysis_Detail_Data;
import com.syon.isrms.HelperClasses.ColorClass;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Class_Teacher_Result_Analysis_Detail extends AppCompatActivity {
    ImageView back_button;
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    String exam_id,tbl_id;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__class__teacher__result__analysis__detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();
        exam_id=getIntent().getStringExtra("exam_id");
        tbl_id= getIntent().getStringExtra("tbl_id");
        doApiOperation();
        //doGraphApiOperation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }



    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
   /* private void doGraphApiOperation() {
        ArrayList<BarEntry> barchartdata = new ArrayList<>();
        barchartdata.add(new BarEntry(11.0f, 0));
        barchartdata.add(new BarEntry(4.0f, 1));
        barchartdata.add(new BarEntry(8.0f, 2));
        barchartdata.add(new BarEntry(12.0f, 3));
        barchartdata.add(new BarEntry(16.0f, 4));

        BarDataSet dataset = new BarDataSet(barchartdata, "");


        ArrayList<String> labels = new ArrayList<>();

        labels.add(0, "New");
        labels.add(1, "Old");
        labels.add(2, "TC");
        labels.add(3, "WO");
        labels.add(4, "CS");


        BarData data = new BarData(labels, dataset); // initialize Piedata<br />//

        barChart.setData(data);
        barChart.setDescription("");


        dataset.setColors(ColorClass.COLORFUL_COLORS);

        barChart.animateY(3000);

    }*/

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Admin_DetailClick_Main> call = apiInterfce.detailsclick("33",getIntent().getStringExtra("exam_id"), getIntent().getStringExtra("tbl_id"), "112", "MGS001");
            call.enqueue(new Callback<Admin_DetailClick_Main>() {
                @Override
                public void onResponse(Call<Admin_DetailClick_Main> call, Response<Admin_DetailClick_Main> response) {
                    if (response.code() == 200) {
                        Admin_DetailClick_Main list = response.body();
                        List<Admin_DetailClik_Data> data = list.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Admin_Class_Teacher_Result_Analysis_Detail.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Admin_DetailClick_Recycle_Adapter(Admin_Class_Teacher_Result_Analysis_Detail.this, data));

                        ArrayList<BarEntry> barchartdata = new ArrayList<>();

                        for (int i = 0; i<data.size();i++){
                            String str = data.get(i).getDStudCntPercent().toString();


                            barchartdata.add(new BarEntry(Float.parseFloat(str), i));

                        }/*
                        barchartdata.add(new BarEntry(11.0f, 0));
                        barchartdata.add(new BarEntry(4.0f, 1));
                        barchartdata.add(new BarEntry(8.0f, 2));
                        barchartdata.add(new BarEntry(12.0f, 3));
                        barchartdata.add(new BarEntry(16.0f, 4));
*/
                        BarDataSet dataset = new BarDataSet(barchartdata, "");


                        ArrayList<String> labels = new ArrayList<>();

                        for (int i = 0; i<data.size(); i++){


                            String st = data.get(i).getAverageGrade().toLowerCase();
                            labels.add(i, st);

                        }

                        BarData data1 = new BarData(labels, dataset); // initialize Piedata<br />//

                        barChart.setData(data1);
                        barChart.setDescription("");


                        dataset.setColors(ColorClass.COLORFUL_COLORS);

                        barChart.animateY(3000);


                    } else {
                        Toast.makeText(Admin_Class_Teacher_Result_Analysis_Detail.this, "Data not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Admin_DetailClick_Main> call, Throwable t) {
                    Toast.makeText(Admin_Class_Teacher_Result_Analysis_Detail.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(Admin_Class_Teacher_Result_Analysis_Detail.this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Class_Teacher_Result_Analysis_Detail.this);
        recyclerView = findViewById(R.id.admin_teacher_resultDeatilRecycler);
        back_button = findViewById(R.id.back_button);
        barChart = findViewById(R.id.graph_admin_teacher_Result_detail);
        doOperation();

    }



}
