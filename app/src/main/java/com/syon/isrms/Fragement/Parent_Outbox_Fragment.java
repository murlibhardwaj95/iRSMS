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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.syon.isrms.Adapter.Parent_Outbox_Adapter;
import com.syon.isrms.Beans.Userbean_ParentOutbox_Main;
import com.syon.isrms.Beans.Userbean_ParentOutbox_data;
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
public class Parent_Outbox_Fragment extends Fragment {

    ApiInterfce apiInterfce;
    RecyclerView recyclerView;
    SharedPreferences preferences;

    public Parent_Outbox_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        final View view = inflater.inflate(R.layout.fragment_parent__outbox_, container, false);

        recyclerView = view.findViewById(R.id.outbox_Recyclerparent);
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_ParentOutbox_Main> call = apiInterfce.getParentOutbox(preferences.getString(getString(R.string.lStudIdNo),""),"1",preferences.getString(getString(R.string.sSchCode),""));
            call.enqueue(new Callback<Userbean_ParentOutbox_Main>() {
                @Override
                public void onResponse(Call<Userbean_ParentOutbox_Main> call, Response<Userbean_ParentOutbox_Main> response) {
                    if(response.code() == 200){
                        Userbean_ParentOutbox_Main userbean_parentOutbox_main  = response.body();
                        List<Userbean_ParentOutbox_data> data = userbean_parentOutbox_main.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Parent_Outbox_Adapter(getContext(), data));

                    }else {
                        LinearLayout datanotfound=view.findViewById(R.id.datanotfound);
                        datanotfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Userbean_ParentOutbox_Main> call, Throwable t) {
                    Toast.makeText(getContext(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        }catch(Exception e)
        {
            Toast.makeText(getContext(), e+"", Toast.LENGTH_SHORT).show();

        }

        return view;
    }
   /* private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_ParentOutbox_Main> call = apiInterfce.getParentOutbox(preferences.getString(getString(R.string.lStudIdNo),""),"1",preferences.getString(getString(R.string.sSchCode),""));
            call.enqueue(new Callback<Userbean_ParentOutbox_Main>() {
                @Override
                public void onResponse(Call<Userbean_ParentOutbox_Main> call, Response<Userbean_ParentOutbox_Main> response) {
                    if(response.code() == 200){
                        Userbean_ParentOutbox_Main userbean_parentOutbox_main  = response.body();
                        List<Userbean_ParentOutbox_data> data = userbean_parentOutbox_main.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Parent_Outbox_Adapter(getContext(), data));

                    }else {
                        LinearLayout datanotfound=getActivity().findViewById(R.id.datanotfound);
                        datanotfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Userbean_ParentOutbox_Main> call, Throwable t) {
                    Toast.makeText(getContext(), "Check Your Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        }catch(Exception e)
        {
            Toast.makeText(getContext(), e+"", Toast.LENGTH_SHORT).show();

        }
    }*/

}
