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
import android.widget.Toast;

import com.syon.isrms.Adapter.Sent_Recycle_Adapter;
import com.syon.isrms.Beans.SentBoxBean;
import com.syon.isrms.Beans.SentBox_Bean_Data;
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
public class Teacher_Sent_Fragment extends Fragment {
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    RecyclerView recyclerView;


    public Teacher_Sent_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_teacher__sent_, container, false);
        recyclerView = view.findViewById(R.id.sent_Recycler);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        doApiOperation();

        return view;
    }

    private void doApiOperation() {
        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<SentBoxBean> call = apiInterfce.getSentbox(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.nType),""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<SentBoxBean>() {
                @Override
                public void onResponse(Call<SentBoxBean> call, Response<SentBoxBean> response) {
                    if (response.code()==200)
                    {
                        SentBoxBean sentBoxBean=response.body();
                        List<SentBox_Bean_Data> sentBox_bean_data=sentBoxBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Sent_Recycle_Adapter(getContext(), sentBox_bean_data));

                    }
                    else
                    {
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.teachersent,new DataNotFoundFragment(),"teachersent").commit();
                    }

                }

                @Override
                public void onFailure(Call<SentBoxBean> call, Throwable t) {

                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }
            }

}
