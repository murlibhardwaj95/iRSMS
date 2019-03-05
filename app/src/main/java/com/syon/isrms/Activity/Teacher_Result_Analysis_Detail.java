package com.syon.isrms.Activity;

import android.content.Intent;
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

import com.syon.isrms.Adapter.ResultAnalysis_Detail_Recycle_Adapter;
import com.syon.isrms.Beans.ResultAnalysisDetailBean;
import com.syon.isrms.Beans.Result_Analysis_Detail_Data;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Result_Analysis_Detail extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    ImageView back_button, grafics_details;
String exam_id,tbl_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__result__analysis__detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        exam_id=getIntent().getStringExtra("exam_id");
        tbl_id= getIntent().getStringExtra("tbl_id");
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
            Call<ResultAnalysisDetailBean> call = apiInterfce.getFillExamDeatil(preferences.getString(getString(R.string.EmpId), ""), getIntent().getStringExtra("exam_id"), getIntent().getStringExtra("tbl_id"), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<ResultAnalysisDetailBean>() {
                @Override
                public void onResponse(Call<ResultAnalysisDetailBean> call, Response<ResultAnalysisDetailBean> response) {
                    if (response.code() == 200) {
                        ResultAnalysisDetailBean resultAnalysisDetailBean = response.body();
                        List<Result_Analysis_Detail_Data> data = resultAnalysisDetailBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_Result_Analysis_Detail.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new ResultAnalysis_Detail_Recycle_Adapter(Teacher_Result_Analysis_Detail.this, data));
                    } else {
                        Toast.makeText(Teacher_Result_Analysis_Detail.this, "Data not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ResultAnalysisDetailBean> call, Throwable t) {
                    Toast.makeText(Teacher_Result_Analysis_Detail.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(Teacher_Result_Analysis_Detail.this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Result_Analysis_Detail.this);
        recyclerView = findViewById(R.id.resultDeatilRecycler);
        back_button = findViewById(R.id.back_button);
        grafics_details = findViewById(R.id.graficalDetail);
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Result_Analysis_Detail.super.onBackPressed();
            }
        });
        grafics_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Result_Analysis_Detail.this, Teacher_Result_Grafical_Details.class).putExtra("e_id",exam_id).putExtra("tbl__id",tbl_id));
            }
        });
    }

}
