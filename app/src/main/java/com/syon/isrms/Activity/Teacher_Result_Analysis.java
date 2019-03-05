package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.syon.isrms.Adapter.ResultAnalysis_Recycle_Adapter;
import com.syon.isrms.Beans.FillExamBean;
import com.syon.isrms.Beans.Fill_Exam_Data;
import com.syon.isrms.Beans.ResultAnalysisBean;
import com.syon.isrms.Beans.Result_Analasis_Data;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Result_Analysis extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    ImageView back_button;
    SharedPreferences preferences;
    Spinner exams_spinner;
    ArrayList<String> exams_id = new ArrayList<>();
    ArrayList<String> exams = new ArrayList<>();
    String exam_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__result__analysis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Result_Analysis.this);
        back_button = findViewById(R.id.back_button);
        recyclerView = findViewById(R.id.resultRecycler);
        exams_spinner = findViewById(R.id.exam_spinner);
        doOperation();
        doApiOperation();

    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<FillExamBean> call = apiInterfce.getFillExam(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
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

                        ArrayAdapter<String> adaptersubject = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, exams);
                        adaptersubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        exams_spinner.setAdapter(adaptersubject);
                        exams_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                        ImageView imageView = findViewById(R.id.teacher_data_not_found);
                        imageView.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout=findViewById(R.id.resultData);
                        linearLayout.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<FillExamBean> call, Throwable t) {
                    Toast.makeText(Teacher_Result_Analysis.this, "Check internet connection", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            ImageView imageView = findViewById(R.id.teacher_data_not_found);
            imageView.setVisibility(View.VISIBLE);
            LinearLayout linearLayout=findViewById(R.id.resultData);
            linearLayout.setVisibility(View.GONE);

        }

    }

    private void doResultAnalysis(String lExamId) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<ResultAnalysisBean> call = apiInterfce.getResultAnalysis(preferences.getString(getString(R.string.EmpId), ""), lExamId, preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
        call.enqueue(new Callback<ResultAnalysisBean>() {
            @Override
            public void onResponse(Call<ResultAnalysisBean> call, Response<ResultAnalysisBean> response) {
                if (response.code() == 200) {
                    ResultAnalysisBean resultAnalysisBean = response.body();
                    List<Result_Analasis_Data> data = resultAnalysisBean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_Result_Analysis.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new ResultAnalysis_Recycle_Adapter(Teacher_Result_Analysis.this, data, exam_id));
                } else {
                    ImageView imageView = findViewById(R.id.teacher_data_not_found);
                    imageView.setVisibility(View.VISIBLE);
                    LinearLayout linearLayout=findViewById(R.id.resultData);
                    linearLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ResultAnalysisBean> call, Throwable t) {
                Toast.makeText(Teacher_Result_Analysis.this, "Check internet connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Result_Analysis.super.onBackPressed();
            }
        });
    }


}
