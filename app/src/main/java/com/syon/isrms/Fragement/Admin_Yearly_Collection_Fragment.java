package com.syon.isrms.Fragement;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.syon.isrms.Beans.AdminDailySchoolCollectionBean;
import com.syon.isrms.Beans.AdminYearlySchoolCollectionBean;
import com.syon.isrms.HelperClasses.ColorClass;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
*
 * A simple {@link Fragment} subclass.
*/


public class Admin_Yearly_Collection_Fragment extends Fragment {

    PieChart piccollection;
    EditText proectussalerate,fee,specialfee,libraryfee;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Admin_Yearly_Collection_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_admin__yearly__collection_, container, false);
        piccollection = view.findViewById(R.id.piecollection);
         proectussalerate=view.findViewById(R.id.proectussalerate);
         fee=view.findViewById(R.id.fee);
         specialfee=view.findViewById(R.id.specialfee);
         libraryfee=view.findViewById(R.id.libraryfee);
         preferences=PreferenceManager.getDefaultSharedPreferences(getContext());
         doApiOperation();

        return view;
    }

    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<AdminYearlySchoolCollectionBean> call = apiInterfce.getAdminYearlySchoolCollection(preferences.getString(getString(R.string.adminSessionIdNo),""));
        call.enqueue(new Callback<AdminYearlySchoolCollectionBean>() {
            @Override
            public void onResponse(Call<AdminYearlySchoolCollectionBean> call, Response<AdminYearlySchoolCollectionBean> response) {
                if(response.code()==200)
                {

                    AdminYearlySchoolCollectionBean data=response.body();


                    proectussalerate.setText(data.getSProsSaleAmt().toString());
                    fee.setText(data.getSFeeCollAmt().toString());
                    specialfee.setText(data.getSSpFeeCollAmt().toString());
                    libraryfee.setText(data.getSLibFineAmt().toString());

                    //pie chart Data
                    ArrayList<Entry> yvalues = new ArrayList<Entry>();
                    yvalues.add(new Entry(Float.parseFloat(data.getSProsSaleAmt().toString()), 0));
                    yvalues.add(new Entry(Float.parseFloat(data.getSFeeCollAmt().toString()), 1));
                    yvalues.add(new Entry(Float.parseFloat(data.getSSpFeeCollAmt().toString()), 2));
                    yvalues.add(new Entry(Float.parseFloat(data.getSLibFineAmt().toString()), 3));

                    PieDataSet dataSet = new PieDataSet(yvalues, "");

                    ArrayList<String> xVals = new ArrayList<String>();

                    xVals.add("Prospectus Sale");
                    xVals.add("Fee");
                    xVals.add("Special Fee");
                    xVals.add("Library");


                    PieData pdata = new PieData(xVals, dataSet);
                    piccollection.setData(pdata);
                    piccollection.setDescription("");
                    dataSet.setColors(ColorClass.COLORFUL_COLORS);
                    pdata.setValueTextColor(Color.WHITE);
                    // data.setValueTextSize(20f);
                    piccollection.setDrawHoleEnabled(false);
                    piccollection.animateXY(700, 700);



                }
                else
                {
                    Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AdminYearlySchoolCollectionBean> call, Throwable t) {
                Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
