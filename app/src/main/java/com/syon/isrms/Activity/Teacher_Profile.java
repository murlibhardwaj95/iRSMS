package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.syon.isrms.Adapter.Pager_Adapter;
import com.syon.isrms.Beans.Example;
import com.syon.isrms.Beans.Userbean_Personal;
import com.syon.isrms.Fragement.Profile_second_Fragment;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Profile extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ApiInterfce apiInterfce;
    TextView setTeacher_Name, setTeacher_designation;
    RoundedImageView setTeacherPic;
    ImageView back_button;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        setData();
        doOperation();
        doApiOperation();


    }

    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Example> call = apiInterfce.Login(preferences.getString(getString(R.string.EmpId),""), preferences.getString(getString(R.string.SchoolCode),""));
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code()==200)
                {
                    Example example = response.body();
                    List<Userbean_Personal> data = example.getData();
                    preferences=PreferenceManager.getDefaultSharedPreferences(Teacher_Profile.this);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString(getString(R.string.T_Gender),data.get(0).getSGender());
                    editor.putString(getString(R.string.T_DOB),data.get(0).getSDOB());
                    editor.putString(getString(R.string.T_Aadhar),data.get(0).getSAadharNo());
                    editor.putString(getString(R.string.T_PAN),data.get(0).getSPANNo());
                    editor.putString(getString(R.string.T_Mobile),data.get(0).getMobileNo());
                    editor.putString(getString(R.string.T_Email),data.get(0).getSEmail());
                    editor.putString(getString(R.string.T_Address),data.get(0).getSAddress());
                    editor.putString(getString(R.string.T_Joining),data.get(0).getSJoinDate());
                    editor.putString(getString(R.string.T_Designation),data.get(0).getSDesignation());
                    editor.putString(getString(R.string.T_Department),data.get(0).getSDepartment());
                    editor.putString(getString(R.string.T_Payscale),data.get(0).getSPayScale());
                    editor.putString(getString(R.string.T_Basic),data.get(0).getMBasic().toString());
                    editor.putString(getString(R.string.T_PF),data.get(0).getSPFNo());
                    editor.putString(getString(R.string.T_ESI),data.get(0).getSESINo());
                    editor.commit();

                }
                else {
                    Toast.makeText(Teacher_Profile.this, "Data not Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Profile.super.onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void setData() {
        setTeacher_Name.setText(preferences.getString(getString(R.string.EmpName),""));
        setTeacher_designation.setText(preferences.getString(getString(R.string.DeptName),""));
        Picasso.with(Teacher_Profile.this).load(preferences.getString(getString(R.string.TeacherPhoto),"")).placeholder(R.drawable.avtar)
                .error(R.drawable.avtar).resize(60, 60).centerCrop().into(setTeacherPic);
    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Profile.this);
        setTeacherPic = findViewById(R.id.dpHeader);
        setTeacher_Name = findViewById(R.id.userNameHeader);
        setTeacher_designation = findViewById(R.id.teacher_Designation);
        back_button=findViewById(R.id.back_button);
        viewPager = (ViewPager) findViewById(R.id.viewpagerProfile);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        Pager_Adapter adapter = new Pager_Adapter(getSupportFragmentManager());
        adapter.addFragment(new com.syon.isrms.Fragement.Profile_First_Fragment(), "Personal Detail");
        adapter.addFragment(new Profile_second_Fragment(), "Other Detail");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

}
