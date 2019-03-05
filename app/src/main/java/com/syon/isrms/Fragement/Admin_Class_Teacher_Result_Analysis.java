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
import android.widget.SearchView;
import android.widget.Toast;

import com.syon.isrms.Activity.Teacher_Co_Sch_Remark_Entry;
import com.syon.isrms.Adapter.Admin_Class_Teacher_First_Result_Analysis_Recycle_Adapter;
import com.syon.isrms.Beans.AdminClassTeacherResultFillTeacherBean;
import com.syon.isrms.Beans.Admin_Class_Teacher_Result_Fill_Teacher_Data;
import com.syon.isrms.Beans.CoSchBean;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_Class_Teacher_Result_Analysis extends Fragment {

    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    Admin_Class_Teacher_First_Result_Analysis_Recycle_Adapter adapter;


    public Admin_Class_Teacher_Result_Analysis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_admin__class__teacher__result__analysis, container, false);
        SearchView searchView = view.findViewById(R.id.admin_Class_Teacher_Search);
        search(searchView);
        preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminClassTeacherResultFillTeacherBean> call = apiInterfce.getAdminClassTeacherFill(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminClassTeacherResultFillTeacherBean>() {
                @Override
                public void onResponse(Call<AdminClassTeacherResultFillTeacherBean> call, Response<AdminClassTeacherResultFillTeacherBean> response) {
                    AdminClassTeacherResultFillTeacherBean fillTeacherBean = response.body();
                    if (response.isSuccessful()) {
                        List<Admin_Class_Teacher_Result_Fill_Teacher_Data> data=fillTeacherBean.getData();
                        recyclerView = view.findViewById(R.id.admin_class_teacher_Result_recycler);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter=new Admin_Class_Teacher_First_Result_Analysis_Recycle_Adapter(getContext(), data);
                        recyclerView.setAdapter(adapter);

                    } else {
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminClassTeacherResultFillTeacherBean> call, Throwable t) {
                    Toast.makeText(getContext(), "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }



        return view;
    }
    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
