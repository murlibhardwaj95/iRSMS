package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_topperlist_adapter;
import com.syon.isrms.Adapter.ExpendableList_Recycle_Adapter;
import com.syon.isrms.Adapter.School_Chooser_Adapter;
import com.syon.isrms.Beans.AdminToppersListBean;
import com.syon.isrms.Beans.Admin_Toppers_List_Data;
import com.syon.isrms.Beans.ISRMSSelectSchoolBean;
import com.syon.isrms.Beans.Isrms_School_Select_Bean_Data;
import com.syon.isrms.Home;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class iSRMS_Choose_School extends AppCompatActivity {
    SharedPreferences preferences;
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_srms__choose__school);

        SharedPreferences log = PreferenceManager.getDefaultSharedPreferences(iSRMS_Choose_School.this);

        if(log.getString("isrms_screen","").equals("show"))
        {
           finish();

        }


       /* switch (log.getInt("log", 0)) {
            case 1:
                startActivity(new Intent(iSRMS_Choose_School.this, Login.class));
                finish();
                break;
            case 2:
                startActivity(new Intent(iSRMS_Choose_School.this, Home.class));
                finish();
                break;
            case 3:
                startActivity(new Intent(iSRMS_Choose_School.this, Teacher_Dashboard.class));
                finish();
                break;
            case 4:
                startActivity(new Intent(iSRMS_Choose_School.this, Admin_Dashboard.class));
                finish();
                break;
           *//* default:
                startActivity(new Intent(iSRMS_Choose_School.this, iSRMS_Choose_School.class));
                finish();*//*
        }*/
        init();
        doApiOperation();
    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<ISRMSSelectSchoolBean> call = apiInterfce.getSchoolsNeme();
            call.enqueue(new Callback<ISRMSSelectSchoolBean>() {
                @Override
                public void onResponse(Call<ISRMSSelectSchoolBean> call, Response<ISRMSSelectSchoolBean> response) {

                    if (response.code()==200) {
                        List<Isrms_School_Select_Bean_Data> data=response.body().getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(iSRMS_Choose_School.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new School_Chooser_Adapter(iSRMS_Choose_School.this, data));

                    } else {
                        Toast.makeText(iSRMS_Choose_School.this, "Data Not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ISRMSSelectSchoolBean> call, Throwable t) {
                    Toast.makeText(iSRMS_Choose_School.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }



    }

    private void init() {
        recyclerView = findViewById(R.id.schoolChooser_Recycler);
        preferences = PreferenceManager.getDefaultSharedPreferences(iSRMS_Choose_School.this);
    }


    public static class ApiClient {

        public static String BASE_URL="http://www.webschoolapi.syontechnologies.com/api/";
        public  static Retrofit retrofit = null;
        public static Retrofit getApiClient(OkHttpClient.Builder httpClient)
        {
            if(retrofit == null)
            {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build()).build();
            }
            return retrofit;
        }
    }
}
