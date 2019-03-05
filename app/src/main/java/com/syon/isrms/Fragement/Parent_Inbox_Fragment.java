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

import com.syon.isrms.Beans.Userbean_ParentInbox_Main;
import com.syon.isrms.Beans.Userbean_ParentInbox_data;
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
public class Parent_Inbox_Fragment extends Fragment {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Parent_Inbox_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        View view=inflater.inflate(R.layout.fragment_parent__inbox_, container, false);
        recyclerView = view.findViewById(R.id.inbox_Recyclerparent);
        doApiOperation();
        return view;
    }
    private void doApiOperation() {

        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_ParentInbox_Main> call = apiInterfce.getParentInbox(preferences.getString(getString(R.string.lStudIdNo),""),"1",preferences.getString(getString(R.string.sSchCode),""));
            call.enqueue(new Callback<Userbean_ParentInbox_Main>() {
                @Override
                public void onResponse(Call<Userbean_ParentInbox_Main> call, Response<Userbean_ParentInbox_Main> response) {
                    /*Userbean_ParentInbox_Main inboxmain = response.body();
                    Toast.makeText(getContext(), "response "+ response.body(), Toast.LENGTH_SHORT).show();*/
                    if(response.code()==200) {
                        Userbean_ParentInbox_Main inboxmain = response.body();
                        List<Userbean_ParentInbox_data> inbox = inboxmain.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new com.syon.isrms.Adapter.InboxAdapter_parent(getContext(), inbox));
                    }
                    else
                    {
                        LinearLayout datanotfound=getActivity().findViewById(R.id.datanotfound);
                        datanotfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Userbean_ParentInbox_Main> call, Throwable t) {
                    Toast.makeText(getContext(), "Check internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }
    }

}
