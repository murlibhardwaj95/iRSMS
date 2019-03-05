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
import com.syon.isrms.Beans.AdminClassResultSelectedDetailExamBean;
import com.syon.isrms.Beans.Admin_Class_Result_Selected_Deatil_Exam_Bean;
import com.syon.isrms.R;
import com.syon.isrms.Adapter.ResultAnalysis_Detail_Recycle_Adapter;
import com.syon.isrms.Beans.ResultAnalysisDetailBean;
import com.syon.isrms.Beans.Result_Analysis_Detail_Data;
import com.syon.isrms.HelperClasses.ColorClass;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;

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


public class Admin_Class_Result_Detail extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    ImageView back_button, grafics_details;
    String exam_id, tbl_id;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__class__result__detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();
        exam_id = getIntent().getStringExtra("exam_id");
        tbl_id = getIntent().getStringExtra("tbl_id");
        doApiOperation();


    }


    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminClassResultSelectedDetailExamBean> call = apiInterfce.getAdminSelectedDeatilExam(getIntent().getStringExtra("exam_id"), getIntent().getStringExtra("tbl_id"), "112", "MGS001");
            call.enqueue(new Callback<AdminClassResultSelectedDetailExamBean>() {
                @Override
                public void onResponse(Call<AdminClassResultSelectedDetailExamBean> call, Response<AdminClassResultSelectedDetailExamBean> response) {
                    if (response.code() == 200) {
                        AdminClassResultSelectedDetailExamBean classResultSelectedDetailExamBean = response.body();
                        List<Admin_Class_Result_Selected_Deatil_Exam_Bean> data = classResultSelectedDetailExamBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Admin_Class_Result_Detail.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Admin_Class_Result_Analysis_Detail_Recycle_Adapter(Admin_Class_Result_Detail.this, data));

                        //Start Graph Data
                        ArrayList<BarEntry> barchartdata = new ArrayList<>();
                        for(int j=0;j<data.size();j++) {
                            String strOut = data.get(j).getSStudCntPercent().toString();
                            String result;
                            int nPos = strOut.indexOf("[");

                            if (nPos <= 0) {
                                result = "0.00";
                            } else {
                                result = strOut.substring(0, nPos - 1) + "";
                            }
                            barchartdata.add(new BarEntry(Float.parseFloat(result), j));
                            }
                        BarDataSet dataset = new BarDataSet(barchartdata, "");


                        ArrayList<String> labels = new ArrayList<>();
                        for (int i = 0; i < data.size(); i++) {

                            labels.add(i, data.get(i).getSAverageGrade());
                        }


                        BarData bdata = new BarData(labels, dataset); // initialize Piedata<br />//

                        barChart.setData(bdata);
                        barChart.setDescription("");


                        dataset.setColors(ColorClass.COLORFUL_COLORS);

                        barChart.animateY(3000);

                        //End Graph Data


                    } else {
                        Toast.makeText(Admin_Class_Result_Detail.this, "Data not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminClassResultSelectedDetailExamBean> call, Throwable t) {
                    Toast.makeText(Admin_Class_Result_Detail.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(Admin_Class_Result_Detail.this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Class_Result_Detail.this);
        recyclerView = findViewById(R.id.admin_class_resultDeatilRecycler);
        back_button = findViewById(R.id.back_button);
        barChart = findViewById(R.id.graph_admin_class_Result_detail);
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Class_Result_Detail.super.onBackPressed();
            }
        });
       /* grafics_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Class_Result_Detail.this, Teacher_Result_Grafical_Details.class).putExtra("e_id",exam_id).putExtra("tbl__id",tbl_id));
            }
        });
*/
    }


}
