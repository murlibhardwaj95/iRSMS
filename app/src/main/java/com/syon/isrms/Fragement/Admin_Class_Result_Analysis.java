package com.syon.isrms.Fragement;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.syon.isrms.Adapter.Admin_Class_ResultAnalysis_Recycle_Adapter;
import com.syon.isrms.Beans.AdminClassResultFillExamBean;
import com.syon.isrms.Beans.AdminClassResultSelectedExamBean;
import com.syon.isrms.Beans.Admin_Class_Result_Fill_Exam_Bean_Data;
import com.syon.isrms.Beans.Admin_Class_Result_Selected_Exam_Data;
import com.syon.isrms.Beans.FillExamBean;
import com.syon.isrms.Beans.Fill_Exam_Data;
import com.syon.isrms.Beans.ResultAnalysisBean;
import com.syon.isrms.Beans.Result_Analasis_Data;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_Class_Result_Analysis extends Fragment {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    Spinner class_exams_spinner;
    ArrayList<String> exams_id = new ArrayList<>();
    ArrayList<String> exams = new ArrayList<>();
    String exam_id;
    BarChart mChart;


    public Admin_Class_Result_Analysis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__class__result__analysis, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mChart = view.findViewById(R.id.graphClassResultAnalysis);
        recyclerView = view.findViewById(R.id.admin_class_Result_Recycler);
        class_exams_spinner = view.findViewById(R.id.spn_admin_class_result_spinner);
        doApiOperation();

        return view;
    }


    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminClassResultFillExamBean> call = apiInterfce.getAdminFillExam(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminClassResultFillExamBean>() {
                @Override
                public void onResponse(Call<AdminClassResultFillExamBean> call, Response<AdminClassResultFillExamBean> response) {
                    if (response.code() == 200) {
                        AdminClassResultFillExamBean classResultFillExamBean = response.body();
                        List<Admin_Class_Result_Fill_Exam_Bean_Data> data = classResultFillExamBean.getData();

                        for (int i = 0; i < data.size(); i++) {
                            exams_id.add(data.get(i).getExamId().toString());
                            exams.add(data.get(i).getExamName().toString());
                        }

                        ArrayAdapter<String> adaptersubject = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, exams);
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
                        Toast.makeText(getContext(), "data not found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<AdminClassResultFillExamBean> call, Throwable t) {
                    Toast.makeText(getContext(), "Check internet connection", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }


    }

    private void doResultAnalysis(String lExamId) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<AdminClassResultSelectedExamBean> call = apiInterfce.getAdminSelectedExam(lExamId, "112", "MGS001");
        call.enqueue(new Callback<AdminClassResultSelectedExamBean>() {
            @Override
            public void onResponse(Call<AdminClassResultSelectedExamBean> call, Response<AdminClassResultSelectedExamBean> response) {
                if (response.code() == 200) {
                    //Start Recycleview Data
                    AdminClassResultSelectedExamBean resultAnalysisBean = response.body();
                    List<Admin_Class_Result_Selected_Exam_Data> data = resultAnalysisBean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new Admin_Class_ResultAnalysis_Recycle_Adapter(getContext(), data, exam_id));
                    //end Recycleview Data


                    // Start Graph Data
                    ArrayList<BarEntry> barchartdata = new ArrayList<>();
                    for (int j = 0; j < data.size(); j++) {
                        barchartdata.add(new BarEntry(data.get(j).getDOAPercent().floatValue(), j));
                    }
                    BarDataSet dataset = new BarDataSet(barchartdata, "");
                    ArrayList<String> labels = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        labels.add(i, data.get(i).getSClass().toString());

                    }
                    BarData bdata = new BarData(labels, dataset); // initialize Piedata<br />//
                    mChart.setData(bdata);
                    mChart.setDescription("");
                    dataset.setColors(ColorClass.COLORFUL_COLORS);
                    mChart.animateY(3000);
                } else {
                    Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AdminClassResultSelectedExamBean> call, Throwable t) {
                Toast.makeText(getContext(), "Check internet connection", Toast.LENGTH_SHORT).show();

            }
        });


    }
}