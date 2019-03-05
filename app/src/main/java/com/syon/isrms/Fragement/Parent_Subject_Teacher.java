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

import com.syon.isrms.Adapter.Parent_subjectteacher_adapter;
import com.syon.isrms.Beans.Userbean_stParent_data;
import com.syon.isrms.Beans.Userbean_stParent_main;
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
public class Parent_Subject_Teacher extends Fragment {

    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    public Parent_Subject_Teacher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_parent__subject__teacher, container, false);
        recyclerView = view.findViewById(R.id.subjectteachercommunicationRecycle);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        doApiOperation();
        return view;



    }
    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_stParent_main> call = apiInterfce.st(preferences.getString(getString(R.string.lClass_IdNo),""),preferences.getString(getString(R.string.lSec_IdNo),""),preferences.getString(getString(R.string.lFac_IdNo),""),preferences.getString(getString(R.string.lSessionIdNo),""),preferences.getString(getString(R.string.sSchCode),""));
            call.enqueue(new Callback<Userbean_stParent_main>() {
                @Override
                public void onResponse(Call<Userbean_stParent_main> call, Response<Userbean_stParent_main> response) {
                    if(response.code() == 200){
                        Userbean_stParent_main st  = response.body();
                        List<Userbean_stParent_data> data1 = st.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Parent_subjectteacher_adapter(getContext(),data1));

                    }else {
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Userbean_stParent_main> call, Throwable t) {
                    Toast.makeText(getContext(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        }catch(Exception e)
        {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();

        }
    }
}
