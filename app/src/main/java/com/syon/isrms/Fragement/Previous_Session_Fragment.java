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
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.SalarySlip_Recycle_Adapter;
import com.syon.isrms.Beans.SalarySlipBean;
import com.syon.isrms.Beans.SalarySlipData;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Previous_Session_Fragment extends Fragment {

    ApiInterfce apiInterfce;
    RecyclerView recyclerView;
    SharedPreferences preferences;
    String session_id;
    int session;

    public Previous_Session_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous__session_, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerView = view.findViewById(R.id.previous_session_recycle);
        session=Integer.parseInt(preferences.getString(getString(R.string.SessionId),""))-1;
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<SalarySlipBean> call = apiInterfce.getSalarySlip(preferences.getString(getString(R.string.EmpId), ""),String.valueOf(session), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<SalarySlipBean>() {
                @Override
                public void onResponse(Call<SalarySlipBean> call, Response<SalarySlipBean> response) {
                    if (response.code()==200) {
                        SalarySlipBean slipBean = response.body();
                        List<SalarySlipData> salarySlipData = slipBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new SalarySlip_Recycle_Adapter(getContext(), salarySlipData));
                    }
                    else
                    {
                        ImageView imageView = getActivity().findViewById(R.id.teacher_data_not_found);
                        imageView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);


                    }
                    }

                @Override
                public void onFailure(Call<SalarySlipBean> call, Throwable t) {

                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }
}
