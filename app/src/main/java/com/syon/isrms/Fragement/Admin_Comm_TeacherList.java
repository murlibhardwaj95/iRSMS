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

import com.syon.isrms.Adapter.TeacherAdapter;
import com.syon.isrms.Beans.Teacher_Userbean;
import com.syon.isrms.Beans.Userbean_TeacherData;
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
public class Admin_Comm_TeacherList extends Fragment {


    RecyclerView teacherrecycler;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Admin_Comm_TeacherList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin__comm__teacher_list, container, false);
        teacherrecycler = view.findViewById(R.id.admin_teacher_list);
        preferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Teacher_Userbean> call = apiInterfce.teacherlist(preferences.getString(getString(R.string.adminSchCode),""));
            call.enqueue(new Callback<Teacher_Userbean>() {
                @Override
                public void onResponse(Call<Teacher_Userbean> call, Response<Teacher_Userbean> response) {

                    if(response.code()==200){
                        Teacher_Userbean teacher_userbean = response.body();
                        List<Userbean_TeacherData> teacher_userbeanData = teacher_userbean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        teacherrecycler.setLayoutManager(layoutManager);
                        teacherrecycler.setAdapter(new TeacherAdapter(getContext(),teacher_userbeanData));
                    }else{
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Teacher_Userbean> call, Throwable t) {
                    Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

}
