package com.syon.isrms.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.syon.isrms.Adapter.Admin_Result_Analysis_Page_Adapter;
import com.syon.isrms.R;

public class Admin_Result_Analysis extends AppCompatActivity {
    ImageView back_button;
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__result__analysis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        back_button=findViewById(R.id.back_button);
        viewPager = findViewById(R.id.viewpagerAdminResult);
        tabLayout = findViewById(R.id.admin_result_analysis_tablayout);
        doOperation();
        callPageAdapter();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Admin_Result_Analysis.super.onBackPressed();
            }
        });
    }
    private void callPageAdapter() {
       Admin_Result_Analysis_Page_Adapter admin_result_analysis_page_adapter = new Admin_Result_Analysis_Page_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(admin_result_analysis_page_adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
