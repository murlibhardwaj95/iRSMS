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

import com.syon.isrms.Adapter.AdminYearlyReportPageAdapter;
import com.syon.isrms.R;

public class Admin_Yearly_Report extends AppCompatActivity {
    ImageView back_button;
    TabLayout admintab;
    ViewPager adminpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__yearly__report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        admintab = findViewById(R.id.admintabyearly);
        adminpager = findViewById(R.id.adminViewPageryearly);
        AdminYearlyReportPageAdapter adapter = new AdminYearlyReportPageAdapter(getSupportFragmentManager());
        admintab.setupWithViewPager(adminpager);
        adminpager.setAdapter(adapter);
        back_button = findViewById(R.id.back_button);
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Yearly_Report.super.onBackPressed();
            }
        });
    }

}
