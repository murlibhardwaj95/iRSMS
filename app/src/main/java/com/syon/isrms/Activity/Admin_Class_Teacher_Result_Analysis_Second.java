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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.syon.isrms.Beans.Admin_TeacherResultDetails_Data;
import com.syon.isrms.Beans.Admin_TeacherResultDetails_Main;
import com.syon.isrms.Beans.FillExamBean;
import com.syon.isrms.Beans.Fill_Exam_Data;
import com.syon.isrms.Beans.ResultAnalysisBean;
import com.syon.isrms.Beans.Result_Analasis_Data;
import com.syon.isrms.R;
import com.syon.isrms.Adapter.Admin_Class_Teacher_ResultAnalysis_Recycle_Second_Adapter;
import com.syon.isrms.HelperClasses.ColorClass;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;
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

public class Admin_Class_Teacher_Result_Analysis_Second extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    Spinner class_exams_spinner;
    ArrayList<String> exams_id = new ArrayList<>();
    ArrayList<String> exams = new ArrayList<>();
    String exam_id;
    BarChart mChart;
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__class__teacher__result__analysis__second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }


        init();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Class_Teacher_Result_Analysis_Second.this);
        mChart=findViewById(R.id.graph_admin_class_teacher_Result_detail);
        recyclerView = findViewById(R.id.admin_class_Teacher_resultDeatilRecycler_second);
        class_exams_spinner = findViewById(R.id.spn_admin_class_teacher_result_spinner);
        back_button=findViewById(R.id.back_button);
        doApiOperation();
        //doGraphApiOperation();
        doOperaton();
    }

    private void doOperaton() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Class_Teacher_Result_Analysis_Second.super.onBackPressed();
            }
        });
    }
/*
    private void doGraphApiOperation() {
        ArrayList<BarEntry> barchartdata = new ArrayList<>();
        barchartdata.add(new BarEntry(11.0f, 0));
        barchartdata.add(new BarEntry(4.0f, 1));
        barchartdata.add(new BarEntry(8.0f, 2));
        barchartdata.add(new BarEntry(12.0f, 3));
        barchartdata.add(new BarEntry(16.0f, 4));

        BarDataSet dataset = new BarDataSet(barchartdata, "");


        ArrayList<String> labels = new ArrayList<>();

        labels.add(0, "V");
        labels.add(1, "VI");
        labels.add(2, "VII");
        labels.add(3, "VIII");
        labels.add(4, "IX");


        BarData data = new BarData(labels, dataset); // initialize Piedata<br />//

        mChart.setData(data);
        mChart.setDescription("");


        dataset.setColors(ColorClass.COLORFUL_COLORS);

        mChart.animateY(3000);
    }*/


    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<FillExamBean> call = apiInterfce.getFillExam("33", "112","MGS001");
            call.enqueue(new Callback<FillExamBean>() {
                @Override
                public void onResponse(Call<FillExamBean> call, Response<FillExamBean> response) {
                    if (response.code() == 200) {
                        FillExamBean FillExamBean = response.body();
                        List<Fill_Exam_Data> data = FillExamBean.getData();

                        for (int i = 0; i < data.size(); i++) {
                            exams_id.add(data.get(i).getExamId().toString());
                            exams.add(data.get(i).getExamName().toString());
                        }

                        ArrayAdapter<String> adaptersubject = new ArrayAdapter<String>(Admin_Class_Teacher_Result_Analysis_Second.this, android.R.layout.simple_spinner_item, exams);
                        adaptersubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        class_exams_spinner.setAdapter(adaptersubject);
                        class_exams_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                doResultAnalysis(exams_id.get(position).toString());
                                exam_id = exams_id.get(position).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        Toast.makeText(Admin_Class_Teacher_Result_Analysis_Second.this, "data not found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<FillExamBean> call, Throwable t) {
                    Toast.makeText(Admin_Class_Teacher_Result_Analysis_Second.this, "Check internet connection", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(Admin_Class_Teacher_Result_Analysis_Second.this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }


    }

    private void doResultAnalysis(String lExamId) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Admin_TeacherResultDetails_Main> call = apiInterfce.getteacherresultdetails("33",lExamId ,"112","MGS001");
        call.enqueue(new Callback<Admin_TeacherResultDetails_Main>() {
            @Override
            public void onResponse(Call<Admin_TeacherResultDetails_Main> call, Response<Admin_TeacherResultDetails_Main> response) {
                if (response.code() == 200) {
                    Admin_TeacherResultDetails_Main resultAnalysisBean = response.body();
                    List<Admin_TeacherResultDetails_Data> data = resultAnalysisBean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Admin_Class_Teacher_Result_Analysis_Second.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new Admin_Class_Teacher_ResultAnalysis_Recycle_Second_Adapter(Admin_Class_Teacher_Result_Analysis_Second.this, data, exam_id));

                    ArrayList<BarEntry> barchartdata = new ArrayList<>();

                    for (int i = 0; i <= data.size() - 1; i++)
                    {
                        String strcount = data.get(i).getDOAPercent().toString();
                        barchartdata.add(new BarEntry(Float.parseFloat(strcount), i));


                    }
                    BarDataSet dataset = new BarDataSet(barchartdata, "");


                    ArrayList<String> labels = new ArrayList<>();

                    for (int i = 0; i <= data.size() - 1; i++){

                        String stringcount = data.get(i).getSSubject().toString();
                        labels.add(i, stringcount);
                    }


                    BarData data1 = new BarData(labels, dataset); // initialize Piedata<br />//

                    mChart.setData(data1);
                    mChart.setDescription("");


                    dataset.setColors(ColorClass.COLORFUL_COLORS);

                    mChart.animateY(3000);
                } else {
                    Toast.makeText(Admin_Class_Teacher_Result_Analysis_Second.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Admin_TeacherResultDetails_Main> call, Throwable t) {
                Toast.makeText(Admin_Class_Teacher_Result_Analysis_Second.this, "Check internet connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
