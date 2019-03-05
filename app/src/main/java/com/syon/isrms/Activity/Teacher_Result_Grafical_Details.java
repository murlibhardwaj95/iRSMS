package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.syon.isrms.Beans.ResultAnalysisDetailBean;
import com.syon.isrms.Beans.Result_Analysis_Detail_Data;
import com.syon.isrms.HelperClasses.ColorClass;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Result_Grafical_Details extends AppCompatActivity {
    BarChart barChart;
    ApiInterfce apiInterfce;
    ImageView back_button;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__result__grafical__details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        barChart = (BarChart) findViewById(R.id.barchart);
        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Result_Grafical_Details.super.onBackPressed();
            }
        });
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Result_Grafical_Details.this);

      /*  ArrayList<BarEntry> barchartdata = new ArrayList<>();
        barchartdata.add(new BarEntry(0.0f, 0));
        barchartdata.add(new BarEntry(4.0f, 1));
        barchartdata.add(new BarEntry(8.0f, 2));
        barchartdata.add(new BarEntry(12.0f, 3));
        barchartdata.add(new BarEntry(16.0f, 4));
        barchartdata.add(new BarEntry(20.0f, 5));
        barchartdata.add(new BarEntry(24.0f, 6));
        barchartdata.add(new BarEntry(48.0f, 7));

        BarDataSet dataset = new BarDataSet(barchartdata, "");


        ArrayList<String> labels = new ArrayList<>();

        labels.add(0, "A1");
        labels.add(1, "A2");
        labels.add(2, "B1");
        labels.add(3, "B2");
        labels.add(4, "C1");
        labels.add(5, "C2");
        labels.add(6, "D");
        labels.add(7, "E");


        BarData data = new BarData(labels, dataset); // initialize Piedata<br />//

        barChart.setData(data);
        barChart.setDescription("");


        dataset.setColors(ColorClass.COLORFUL_COLORS);

        barChart.animateY(3000);
*/
doApiOperation();
    }

    private void doApiOperation() {
        try {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<ResultAnalysisDetailBean> call = apiInterfce.getFillExamDeatil(preferences.getString(getString(R.string.EmpId),""), getIntent().getStringExtra("e_id"), getIntent().getStringExtra("tbl__id"), preferences.getString(getString(R.string.SessionId),""), preferences.getString(getString(R.string.SchoolCode),""));
            call.enqueue(new Callback<ResultAnalysisDetailBean>() {
                @Override
                public void onResponse(Call<ResultAnalysisDetailBean> call, Response<ResultAnalysisDetailBean> response) {
                    //  Toast.makeText(Teacher_Result_Grafical_Details.this, ""+response.body().getStatus(), Toast.LENGTH_SHORT).show();


                    if (response.code() == 200) {
                        ResultAnalysisDetailBean resultAnalysisDetailBean = response.body();
                        List<Result_Analysis_Detail_Data> datagraph = resultAnalysisDetailBean.getData();

                        ArrayList<BarEntry> barchartdata = new ArrayList<>();

                        for (int i = 0; i <= datagraph.size() - 1; i++) {
                            //  Toast.makeText(ChartActivity.this, ""+datagraph.size(), Toast.LENGTH_SHORT).show();
                            String strOut = datagraph.get(i).getStudCntPercent().toString();
                            String result;

                            int nPos = strOut.indexOf("[");

                            if (nPos <= 0) {
                                result = "0.00";
                            } else {
                                result = strOut.substring(0, nPos - 1) + "";
                            }

                            barchartdata.add(new BarEntry(Float.parseFloat(result), i));


                        }
                        BarDataSet dataset = new BarDataSet(barchartdata, "Result as per Category");



                        ArrayList<String> labels = new ArrayList<>();

                        for (int j = 0; j <= datagraph.size() - 1; j++) {

                            labels.add(j, datagraph.get(j).getAverageGrade());

                        }

                        // initialize Piedata<br />//
                        BarData data = new BarData(labels, dataset);
                        barChart.setData(data);
                        barChart.setDescription("");


                        dataset.setColors(ColorClass.COLORFUL_COLORS);

                        barChart.animateY(3000);


                    } else {


                    }

                }

                @Override
                public void onFailure(Call<ResultAnalysisDetailBean> call, Throwable t) {
                    Toast.makeText(Teacher_Result_Grafical_Details.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(Teacher_Result_Grafical_Details.this, "Something went Wrong try after sometime ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
