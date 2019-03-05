package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.syon.isrms.Adapter.View_Homework_Recycle_Adapter;
import com.syon.isrms.Beans.FillClassBean;
import com.syon.isrms.Beans.FillSectionBean;
import com.syon.isrms.Beans.FillSubjectBean;
import com.syon.isrms.Beans.Fill_Class_Data;
import com.syon.isrms.Beans.Fill_Section_Data;
import com.syon.isrms.Beans.Fill_Subject_Data;
import com.syon.isrms.Beans.ViewHomeworkBean;
import com.syon.isrms.Beans.View_Homework_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_View_Homework extends AppCompatActivity {
    RecyclerView  recyclerView;
    String subject_id, class_id, section_id, sub_name, class_Name, section_Name;
    ArrayList<String> sub = new ArrayList<>();
    ArrayList<String> sub_list_id = new ArrayList<String>();
    ArrayList<String> class_name = new ArrayList<>();
    ArrayList<String> class_list_id = new ArrayList<String>();
    ArrayList<String> fill_section = new ArrayList<>();
    ArrayList<String> fill_section_id = new ArrayList<String>();
    Spinner spinnersubject,spinnerclass,spinnersection;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__view__homework);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doApiOperation();

    }


    private void doApiOperation() {
       try{
           OkHttpClient.Builder builder = new OkHttpClient.Builder();
           apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
           Call<FillSubjectBean> call = apiInterfce.getFillSubject(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
           call.enqueue(new Callback<FillSubjectBean>() {
               @Override
               public void onResponse(Call<FillSubjectBean> call, Response<FillSubjectBean> response) {
                   if (response.code() == 200) {
                       FillSubjectBean fillSubjectBean = response.body();
                       List<Fill_Subject_Data> fill_subject_data = fillSubjectBean.getData();

                       for (int i = 0; i < fill_subject_data.size(); i++) {
                           sub_list_id.add(fill_subject_data.get(i).getLSubjectId().toString());
                           sub.add(fill_subject_data.get(i).getSSubject());

                       }
                       ArrayAdapter<String> adaptersub = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sub);
                       adaptersub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                       spinnersubject.setAdapter(adaptersub);
                       spinnersubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                               spinnerclass.setEnabled(true);
                               class_list_id.clear();
                               class_name.clear();
                               sub_name = sub.get(position).toString();
                               subject_id = sub_list_id.get(position).toString();
                               OkHttpClient.Builder builder = new OkHttpClient.Builder();
                               apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                               Call<FillClassBean> call = apiInterfce.getFillClass(preferences.getString(getString(R.string.EmpId), ""), sub_list_id.get(position), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
                               call.enqueue(new Callback<FillClassBean>() {
                                   @Override
                                   public void onResponse(Call<FillClassBean> call, Response<FillClassBean> response) {
                                       if (response.code() == 200) {
                                           FillClassBean classBean = response.body();
                                           List<Fill_Class_Data> fill_class_data = classBean.getData();
                                           for (int i = 0; i < fill_class_data.size(); i++) {
                                               class_list_id.add(fill_class_data.get(i).getLClassId().toString());
                                               class_name.add(fill_class_data.get(i).getSClassName());
                                           }
                                           ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, class_name);
                                           adapter_class.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                           spinnerclass.setAdapter(adapter_class);
                                           spinnerclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   spinnersection.setEnabled(true);
                                                   class_Name = class_name.get(position).toString();
                                                   class_id = class_list_id.get(position).toString();
                                                   fill_section.clear();
                                                   fill_section_id.clear();
                                                   OkHttpClient.Builder builder = new OkHttpClient.Builder();
                                                   apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                                                   Call<FillSectionBean> call = apiInterfce.getFillSection(preferences.getString(getString(R.string.EmpId), ""), sub_list_id.get(position), class_list_id.get(position), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
                                                   call.enqueue(new Callback<FillSectionBean>() {
                                                       @Override
                                                       public void onResponse(Call<FillSectionBean> call, Response<FillSectionBean> response) {
                                                           if (response.code() == 200) {
                                                               FillSectionBean fillSectionBean = response.body();
                                                               List<Fill_Section_Data> fill_section_data = fillSectionBean.getData();
                                                               for (int i = 0; i < fill_section_data.size(); i++) {
                                                                   fill_section.add(fill_section_data.get(i).getSSection().toString());
                                                                   fill_section_id.add(fill_section_data.get(i).getLSecId().toString());
                                                               }
                                                               ArrayAdapter<String> adapter_section = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, fill_section);
                                                               adapter_section.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                               spinnersection.setAdapter(adapter_section);
                                                               spinnersection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                   @Override
                                                                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                       section_Name = fill_section.get(position).toString();
                                                                       section_id = fill_section_id.get(position).toString();
                                                                       OkHttpClient.Builder builder = new OkHttpClient.Builder();
                                                                       apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);

                                                                       Call<ViewHomeworkBean> call = apiInterfce.getViewHomework(preferences.getString(getString(R.string.EmpId), ""),subject_id,class_id,section_id, preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
                                                                       call.enqueue(new Callback<ViewHomeworkBean>() {
                                                                           @Override
                                                                           public void onResponse(Call<ViewHomeworkBean> call, Response<ViewHomeworkBean> response) {
                                                                               if(response.code()==200)
                                                                               {
                                                                                   ViewHomeworkBean viewHomeworkBean=response.body();
                                                                                   List<View_Homework_Data> data=viewHomeworkBean.getData();
                                                                                   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_View_Homework.this);
                                                                                   recyclerView.setLayoutManager(layoutManager);
                                                                                   recyclerView.setAdapter(new View_Homework_Recycle_Adapter(Teacher_View_Homework.this, data));
                                                                               }
                                                                               else
                                                                               {
                                                                                   Toast.makeText(Teacher_View_Homework.this, "Data not Found", Toast.LENGTH_SHORT).show();
                                                                               }

                                                                           }

                                                                           @Override
                                                                           public void onFailure(Call<ViewHomeworkBean> call, Throwable t) {

                                                                           }
                                                                       });
                                                                   }

                                                                   @Override
                                                                   public void onNothingSelected(AdapterView<?> parent) {

                                                                   }
                                                               });
                                                           } else {
                                                               Toast.makeText(Teacher_View_Homework.this, "Data not found", Toast.LENGTH_SHORT).show();
                                                           }

                                                       }

                                                       @Override
                                                       public void onFailure(Call<FillSectionBean> call, Throwable t) {
                                                           Toast.makeText(Teacher_View_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                                                       }
                                                   });
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           });
                                       } else {
                                           Toast.makeText(Teacher_View_Homework.this, "Data not found", Toast.LENGTH_SHORT).show();
                                       }

                                   }

                                   @Override
                                   public void onFailure(Call<FillClassBean> call, Throwable t) {
                                       Toast.makeText(Teacher_View_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                                   }
                               });
                           }

                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {

                           }
                       });
                   }


               }

               @Override
               public void onFailure(Call<FillSubjectBean> call, Throwable t) {
                   Toast.makeText(Teacher_View_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

               }
           });

           try{

        }catch (Exception e){
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
       }catch(Exception e){

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        recyclerView=findViewById(R.id.ViewHomework_recycle);
        back_button=findViewById(R.id.back_button);
        spinnersubject=findViewById(R.id.subjectName);
        spinnerclass=findViewById(R.id.className);
        spinnersection=findViewById(R.id.sectionName);
        preferences= PreferenceManager.getDefaultSharedPreferences(Teacher_View_Homework.this);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_View_Homework.super.onBackPressed();
            }
        });

    }

}
