package com.syon.isrms.Fragement;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.syon.isrms.Adapter.AdminInbox_Adapter;
import com.syon.isrms.Adapter.Admin_Sent_Recycle_Adapter;
import com.syon.isrms.Adapter.Admin_sent_Adapter;
import com.syon.isrms.Adapter.InboxAdapter_parent;
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


public class Admin_Comm_Outbox_Frag extends Fragment {

    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Admin_Comm_Outbox_Frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__comm__teacher_, container, false);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerView = view.findViewById(R.id.adminoutbox_recycler);
        doApiOperation();
        return view;
    }

    private void doApiOperation() {

        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<SentBoxBean> call = apiInterfce.getSentbox(preferences.getString(getString(R.string.adminEmpIdNo), ""), "2", preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<SentBoxBean>() {
                @Override
                public void onResponse(Call<SentBoxBean> call, Response<SentBoxBean> response) {
                    if (response.code()==200)
                    {
                        SentBoxBean sentBoxBean=response.body();
                        List<SentBox_Bean_Data> sentBox_bean_data=sentBoxBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Admin_sent_Adapter(getContext(), sentBox_bean_data));

                    }
                    else
                    {

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
