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

import com.syon.isrms.Adapter.Inbox_Recycle_Adapter;
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
public class Teacher_Inbox_Fragment extends Fragment {
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    RecyclerView recyclerView;

    public Teacher_Inbox_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher__inbox_, container, false);
        recyclerView = view.findViewById(R.id.inbox_Recycler);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<com.syon.isrms.Beans.InboxBean> call = apiInterfce.getInbox(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.nType), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<com.syon.isrms.Beans.InboxBean>() {
                @Override
                public void onResponse(Call<com.syon.isrms.Beans.InboxBean> call, Response<com.syon.isrms.Beans.InboxBean> response) {
                    if(response.code()==200) {
                        com.syon.isrms.Beans.InboxBean inboxBean = response.body();
                        List<Inbox_Bean_Data> inbox_bean_data = inboxBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Inbox_Recycle_Adapter(getContext(), inbox_bean_data));
                    }
                    else
                    {
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.teacherinbox,new DataNotFoundFragment(),"teachersent").commit();
                    }
                }

                @Override
                public void onFailure(Call<com.syon.isrms.Beans.InboxBean> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

}
