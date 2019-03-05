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

import com.syon.isrms.Activity.Admin_Topper_List;
import com.syon.isrms.Adapter.Admin_topperlist_adapter;
import com.syon.isrms.Adapter.Outstanding_RecyclerAdapter;
import com.syon.isrms.Adapter.Refund_RecyclerAdapter;
import com.syon.isrms.Beans.AdminToppersListBean;
import com.syon.isrms.Beans.AdminYearlyOutstandingBean;
import com.syon.isrms.Beans.AdminYearlyRefundBean;
import com.syon.isrms.Beans.Admin_Toppers_List_Data;
import com.syon.isrms.Beans.Admin_Yearly_Outstanding_Bean_Data;
import com.syon.isrms.Beans.Admin_Yearly_Refund_Bean_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
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
public class Admin_Yearly_Outstanding_Fragment extends Fragment {


    RecyclerView outstanding, refund;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Admin_Yearly_Outstanding_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin__yearly__outstanding_, container, false);
        outstanding = view.findViewById(R.id.outstandingrecycler);
        refund = view.findViewById(R.id.refundrecycler);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        doApiOpertion();
        return view;
    }

    private void doApiOpertion() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminYearlyOutstandingBean> call = apiInterfce.getYearlyOutstandingReport(preferences.getString(getString(R.string.adminSessionIdNo), ""));
            call.enqueue(new Callback<AdminYearlyOutstandingBean>() {
                @Override
                public void onResponse(Call<AdminYearlyOutstandingBean> call, Response<AdminYearlyOutstandingBean> response) {

                    if (response.code()==200) {
                        AdminYearlyOutstandingBean outstandingBean = response.body();
                        List<Admin_Yearly_Outstanding_Bean_Data> data=outstandingBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        outstanding.setLayoutManager(layoutManager);
                        outstanding.setAdapter(new Outstanding_RecyclerAdapter(getContext(), data));
                    } else {
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminYearlyOutstandingBean> call, Throwable t) {
                    Toast.makeText(getContext(), "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });

            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminYearlyRefundBean> call1 = apiInterfce.getAdminYearlyRefund(preferences.getString(getString(R.string.adminSessionIdNo), ""));
            call1.enqueue(new Callback<AdminYearlyRefundBean>() {
                @Override
                public void onResponse(Call<AdminYearlyRefundBean> call, Response<AdminYearlyRefundBean> response) {

                    if (response.code()==200) {
                        AdminYearlyRefundBean refundBean = response.body();
                        List<Admin_Yearly_Refund_Bean_Data> data=refundBean.getData();
                        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
                        refund.setLayoutManager(layoutManager1);
                        refund.setAdapter(new Refund_RecyclerAdapter(getContext(), data));
                    } else {
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminYearlyRefundBean> call, Throwable t) {
                    Toast.makeText(getContext(), "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }

}
