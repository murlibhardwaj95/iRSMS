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

import com.syon.isrms.Adapter.AdminInbox_Adapter;
import com.syon.isrms.Adapter.Inbox_Recycle_Adapter;
import com.syon.isrms.Beans.InboxBean;
import com.syon.isrms.Beans.Inbox_Bean_Data;
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
public class Admin_Comm_Inbox_Frag extends Fragment {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;


    public Admin_Comm_Inbox_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_admin__comm__parent_, container, false);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerView = view.findViewById(R.id.admininbox_recycler);
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<InboxBean> call = apiInterfce.getInbox(preferences.getString(getString(R.string.adminEmpIdNo), ""),"2", preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<InboxBean>() {
                @Override
                public void onResponse(Call<InboxBean> call, Response<InboxBean> response) {
                    if(response.code()==200) {
                        InboxBean inboxBean = response.body();
                        List<Inbox_Bean_Data> inbox_bean_data = inboxBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new AdminInbox_Adapter(getContext(), inbox_bean_data));
                    }
                    else
                    {

                    }
                }

                @Override
                public void onFailure(Call<InboxBean> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }


    }

}
