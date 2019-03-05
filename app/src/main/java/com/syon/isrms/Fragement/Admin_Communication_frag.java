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

import com.syon.isrms.Adapter.AdminAdapter;
import com.syon.isrms.Beans.Userbean_Admin;
import com.syon.isrms.Beans.Userbean_AdminData;
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
public class Admin_Communication_frag extends Fragment {

    RecyclerView recyclerViewAdmin;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Admin_Communication_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin__communication_frag, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerViewAdmin = view.findViewById(R.id.admincommunicationRecycle);
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Userbean_Admin> call = apiInterfce.adminlist(preferences.getString(getString(R.string.SchoolCode), ""));
        call.enqueue(new Callback<Userbean_Admin>() {
            @Override
            public void onResponse(Call<Userbean_Admin> call, Response<Userbean_Admin> response) {
                Userbean_Admin userbean_admin = response.body();
                List<Userbean_AdminData> userbean_adminData = userbean_admin.getData();
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerViewAdmin.setLayoutManager(layoutManager);
                recyclerViewAdmin.setAdapter(new AdminAdapter(getContext(), userbean_adminData));
            }

            @Override
            public void onFailure(Call<Userbean_Admin> call, Throwable t) {

            }
        });

    }
}
