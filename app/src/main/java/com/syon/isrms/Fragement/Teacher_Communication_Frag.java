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

import com.syon.isrms.Adapter.TeacherAdapter;
import com.syon.isrms.Beans.Teacher_Userbean;
import com.syon.isrms.Beans.Userbean_TeacherData;
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
public class Teacher_Communication_Frag extends Fragment {
    RecyclerView recyclerViewteacher;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;


    public Teacher_Communication_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_teacher__communication_, container, false);
        preferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerViewteacher = view.findViewById(R.id.teachercommunicationRecycle);
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Teacher_Userbean> call = apiInterfce.teacherlist(preferences.getString(getString(R.string.SchoolCode),""));
        call.enqueue(new Callback<Teacher_Userbean>() {
            @Override
            public void onResponse(Call<Teacher_Userbean> call, Response<Teacher_Userbean> response) {
                if (response.code() == 200) {
                    Teacher_Userbean teacher_userbean = response.body();
                    List<Userbean_TeacherData> teacher_userbeanData = teacher_userbean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerViewteacher.setLayoutManager(layoutManager);
                    recyclerViewteacher.setAdapter(new TeacherAdapter(getContext(), teacher_userbeanData));
                }
                else
                {
                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.teachercommunicationframe,new DataNotFoundFragment(),"notfound").commit();
                }
            }


            @Override
            public void onFailure(Call<Teacher_Userbean> call, Throwable t) {

            }
        });
    }

}
