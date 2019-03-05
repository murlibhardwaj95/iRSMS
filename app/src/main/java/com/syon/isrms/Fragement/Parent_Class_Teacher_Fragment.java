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

import com.syon.isrms.Beans.Userbean_ctParent_Data;
import com.syon.isrms.Beans.Userbean_ctParent_main;
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
public class Parent_Class_Teacher_Fragment extends Fragment {
    ApiInterfce apiInterfce;
    RecyclerView classteacher;
    SharedPreferences preferences;

    public Parent_Class_Teacher_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent__class__teacher_, container, false);
        classteacher = view.findViewById(R.id.parent_class_teacher_recycle);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        doApiOperation();

        return view;

    }
    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_ctParent_main> call = apiInterfce.ct(preferences.getString(getString(R.string.lClass_IdNo),""),preferences.getString(getString(R.string.lSec_IdNo),""),preferences.getString(getString(R.string.lSessionIdNo),""), preferences.getString(getString(R.string.sSchCode),""));
            call.enqueue(new Callback<Userbean_ctParent_main>() {
                @Override
                public void onResponse(Call<Userbean_ctParent_main> call, Response<Userbean_ctParent_main> response) {
                    if(response.code() == 200){
                        Userbean_ctParent_main ct  = response.body();
                        List<Userbean_ctParent_Data> data = ct.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        classteacher.setLayoutManager(layoutManager);
                        classteacher.setAdapter(new com.syon.isrms.Adapter.Parent_teacher_adapter(getContext(), data));

                    }else {
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Userbean_ctParent_main> call, Throwable t) {

                }
            });
        }catch(Exception e)
        {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();

        }
    }

}
