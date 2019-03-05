package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_topperlist_adapter;
import com.syon.isrms.Beans.AdminSchoolStrengthBean;
import com.syon.isrms.Beans.AdminToppersListBean;
import com.syon.isrms.Beans.Admin_Toppers_List_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_School_Strength extends AppCompatActivity {
ImageView back_button;
Button btn_detail;
TextView edt_new_student,edt_old_student,edt_tc,edt_write_off,edt_current_strength;
ApiInterfce apiInterfce;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__school__strength);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();
        doApiOperation();

    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminSchoolStrengthBean> call = apiInterfce.getAdminSchoolStrength(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminSchoolStrengthBean>() {
                @Override
                public void onResponse(Call<AdminSchoolStrengthBean> call, Response<AdminSchoolStrengthBean> response) {

                    if (response.code()==200) {
                        AdminSchoolStrengthBean adminSchoolStrengthBean = response.body();
                        edt_new_student.setText(adminSchoolStrengthBean.getNewAdmStud().toString());
                        edt_old_student.setText(adminSchoolStrengthBean.getOldAdmStud().toString());
                        edt_tc.setText(adminSchoolStrengthBean.getTcStud().toString());
                        edt_write_off.setText(adminSchoolStrengthBean.getWoStud().toString());
                        edt_current_strength.setText(adminSchoolStrengthBean.getCurStrength().toString());

                    } else {
                        Toast.makeText(Admin_School_Strength.this, "Data Not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminSchoolStrengthBean> call, Throwable t) {
                    Toast.makeText(Admin_School_Strength.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {

        back_button=findViewById(R.id.back_button);
        btn_detail=findViewById(R.id.btn_school_strength_detail);
        edt_new_student=findViewById(R.id.edt_new_student);
        edt_old_student=findViewById(R.id.edt_old_student);
        edt_tc=findViewById(R.id.edt_tc);
        edt_write_off=findViewById(R.id.edt_write_off);
        edt_current_strength=findViewById(R.id.edt_current_strength);
        preferences= PreferenceManager.getDefaultSharedPreferences(Admin_School_Strength.this);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_School_Strength.super.onBackPressed();
            }
        });
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_School_Strength.this,Admin_School_Strength_Detail.class));

            }
        });
    }


}
