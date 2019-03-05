package com.syon.isrms.Fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.syon.isrms.Beans.AdminDailyReportBean;
import com.syon.isrms.HelperClasses.ColorClass;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_Acadmic_Fragment extends Fragment {
    BarChart barChart;
    TextView enquirynumber,admissionnumber,writeoffnumber,tcnumber,registrationnumber,prospectussalenumber;
    ApiInterfce apiInterfce;
    public Admin_Acadmic_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_admin__acadmic_, container, false);
        barChart = view.findViewById(R.id.acadmicbar);
        enquirynumber=view.findViewById(R.id.enquirynumber);
        prospectussalenumber=view.findViewById(R.id.prospectussalenumber);
        registrationnumber=view.findViewById(R.id.registrationnumber);
        admissionnumber=view.findViewById(R.id.admissionnumber);
        tcnumber=view.findViewById(R.id.tcnumber);
        writeoffnumber=view.findViewById(R.id.writeoffnumber);
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminDailyReportBean> call = apiInterfce.getDailyReport();
            call.enqueue(new Callback<AdminDailyReportBean>() {
                @Override
                public void onResponse(Call<AdminDailyReportBean> call, Response<AdminDailyReportBean> response) {
                    if(response.code()==200)
                    {
//                        Toast.makeText(getContext(), ""+response.body(), Toast.LENGTH_SHORT).show();
                        AdminDailyReportBean data=response.body();
                        enquirynumber.setText(data.getNEnqCnt().toString());
                        prospectussalenumber.setText(data.getNProsSaleCnt().toString());
                        registrationnumber.setText(data.getNRegCnt().toString());
                        admissionnumber.setText(data.getNAdmCnt().toString());
                        tcnumber.setText(data.getNTCCnt().toString());
                        writeoffnumber.setText(data.getNWOCnt().toString());
                        //bar chart data
                       ArrayList<BarEntry> barchartdata = new ArrayList<>();
                        /*for (int j = 0; j < data.getNEnqCnt(); j++) {
                            barchartdata.add(new BarEntry(data.get(j).getNEnqCnt().floatValue(), j));
                        }*/
                        barchartdata.add(new BarEntry(Float.parseFloat(data.getNEnqCnt().toString()), 0));
                        barchartdata.add(new BarEntry(Float.parseFloat(data.getNEnqCnt().toString()), 1));
                        barchartdata.add(new BarEntry(Float.parseFloat(data.getNRegCnt().toString()), 2));
                        barchartdata.add(new BarEntry(Float.parseFloat(data.getNAdmCnt().toString()), 3));
                        barchartdata.add(new BarEntry(Float.parseFloat(data.getNTCCnt().toString()), 4));
                        barchartdata.add(new BarEntry(Float.parseFloat(data.getNWOCnt().toString()), 5));
                        BarDataSet dataset = new BarDataSet(barchartdata, "");

                        ArrayList<String> labels = new ArrayList<>();
                       /* for (int i = 0; i < data.size(); i++) {
                            labels.add(i, ""+i); }*/
                        labels.add(0, "");
                        labels.add(1, "");
                        labels.add(2, "");
                        labels.add(3, "");
                        labels.add(4, "");
                        labels.add(5, "");

                        BarData bdata = new BarData(labels, dataset); // initialize Piedata<br />//
                        barChart.setData(bdata);
                        barChart.setDescription("");
                        dataset.setColors(ColorClass.COLORFUL_COLORS);
                        dataset.getBarSpace();
                        barChart.animateY(3000);

                        //close bar chart data
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<AdminDailyReportBean> call, Throwable t) {
                    //Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }

    }


}
