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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_Comm_Parent_Adapter;
import com.syon.isrms.Adapter.ParentStudent_Adapter;
import com.syon.isrms.Adapter.TeacherAdapter;
import com.syon.isrms.Beans.Teacher_Userbean;
import com.syon.isrms.Beans.Userbean_Parent_Class;
import com.syon.isrms.Beans.Userbean_Parent_ClassId;
import com.syon.isrms.Beans.Userbean_Parent_Section;
import com.syon.isrms.Beans.Userbean_Parent_SectionId;
import com.syon.isrms.Beans.Userbean_Parent_Student;
import com.syon.isrms.Beans.Userbean_TeacherData;
import com.syon.isrms.Beans.Userbean_parent_studentList;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Admin_Comm_ParentList extends Fragment {

    RecyclerView parentrecycler;
    Spinner spinclass, spinnsection;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Admin_Comm_ParentList() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin__comm__parent_list, container, false);
        parentrecycler = view.findViewById(R.id.admin_parent_list);
        spinclass = view.findViewById(R.id.adclassparent);
        spinnsection = view.findViewById(R.id.adsectionparent);
        preferences=PreferenceManager.getDefaultSharedPreferences(getContext());
        doApiOperation();
        return view;
    }
    private void doApiOperation() {
        try{
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_Parent_Class> userbean_parent_classCall = apiInterfce.classwise(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));
            userbean_parent_classCall.enqueue(new Callback<Userbean_Parent_Class>() {
                @Override
                public void onResponse(Call<Userbean_Parent_Class> call, Response<Userbean_Parent_Class> response) {
                    if (response.code()==200)
                    {
                        Userbean_Parent_Class userbean_parent_class = response.body();
                        List<Userbean_Parent_ClassId> userbean_parent_classIds = userbean_parent_class.getData();

                        int size = userbean_parent_classIds.size();

                        final ArrayList<String> stringsid = new ArrayList<>();
                        final ArrayList<String> string1 = new ArrayList<>();
                        for (int i = 0; i < size; i++) {

                            stringsid.add(userbean_parent_classIds.get(i).getLClassId());
                            string1.add(userbean_parent_classIds.get(i).getSClassName().toString());
                        }

                        ArrayAdapter<String> Adapterclass = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, string1);
                        spinclass.setAdapter(Adapterclass);
                        spinclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                final long classId = spinclass.getSelectedItemId();
                                final String classids = stringsid.get((int) classId);

                                Call<Userbean_Parent_Section> userbean_parent_sectionCall = apiInterfce.sectionwise(preferences.getString(getString(R.string.EmpId), ""), classids, preferences.getString(getString(R.string.lSessionIdNo),""), preferences.getString(getString(R.string.sSchCode),""));
                                userbean_parent_sectionCall.enqueue(new Callback<Userbean_Parent_Section>() {
                                    @Override
                                    public void onResponse(Call<Userbean_Parent_Section> call, Response<Userbean_Parent_Section> response) {

                                        if (response.code() == 200) {
                                            Userbean_Parent_Section userbean_parent_section = response.body();
                                            List<Userbean_Parent_SectionId> userbean_parent_sectionIds = userbean_parent_section.getData();
                                            int size = userbean_parent_sectionIds.size();

                                            final ArrayList<Integer> stringsidsec = new ArrayList<>();
                                            final ArrayList<String> stringsec1 = new ArrayList<>();
                                            for (int i = 0; i < size; i++) {

                                                stringsidsec.add(userbean_parent_sectionIds.get(i).getLSecId());
                                                stringsec1.add(userbean_parent_sectionIds.get(i).getSSecName().toString());
                                            }

                                            ArrayAdapter<String> Adapterclass = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, stringsec1);
                                            spinnsection.setAdapter(Adapterclass);
                                            spinnsection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {

                                                    final long secid = spinnsection.getSelectedItemId();
                                                    final Integer sectionid = stringsidsec.get((int) secid);
                                                    //Toast.makeText(getContext(), "data "+sectionid, Toast.LENGTH_SHORT).show();
                                                    Call<Userbean_Parent_Student> userbean_parent_studentCall = apiInterfce.studentlist(preferences.getString(getString(R.string.EmpId), ""), classids, sectionid,preferences.getString(getString(R.string.lSessionIdNo),""), preferences.getString(getString(R.string.sSchCode),""));
                                                    userbean_parent_studentCall.enqueue(new Callback<Userbean_Parent_Student>() {
                                                        @Override
                                                        public void onResponse(Call<Userbean_Parent_Student> call, Response<Userbean_Parent_Student> response) {

                                                            Userbean_Parent_Student userbean_parent_student = response.body();
                                                            List<Userbean_parent_studentList> userbean_parent_studentLists = userbean_parent_student.getData();

                                                             RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                                                            parentrecycler.setLayoutManager(layoutManager);
                                                            parentrecycler.setAdapter(new Admin_Comm_Parent_Adapter(getContext(), (ArrayList<Userbean_parent_studentList>) userbean_parent_studentLists));


                                                        }

                                                        @Override
                                                        public void onFailure(Call<Userbean_Parent_Student> call, Throwable t) {

                                                        }
                                                    });
                                                }


                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });
                                        }
                                        else
                                        {
                                            Toast.makeText(getContext(), "Data not found", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<Userbean_Parent_Section> call, Throwable t) {
                                        Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    else {
                        Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();

                    }


                }

                @Override
                public void onFailure(Call<Userbean_Parent_Class> call, Throwable t) {
                    Toast.makeText(getContext(), "Check Internet connection", Toast.LENGTH_SHORT).show();
                }
            });
        }catch(Exception e ){
            Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }


}
