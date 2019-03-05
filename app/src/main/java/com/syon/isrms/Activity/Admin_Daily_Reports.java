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

import com.syon.isrms.Adapter.AdminDailyReportAdapter;
import com.syon.isrms.R;

public class Admin_Daily_Reports extends AppCompatActivity {
    ImageView back_button;
    TabLayout admintab;
    ViewPager adminpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__daily__reports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }


    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Daily_Reports.super.onBackPressed();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        adminpager = findViewById(R.id.adminViewPager);
        admintab = findViewById(R.id.admintab);
        admintab.setupWithViewPager(adminpager);
        AdminDailyReportAdapter adapter = new AdminDailyReportAdapter(getSupportFragmentManager());
        adminpager.setAdapter(adapter);
        back_button = findViewById(R.id.back_button);
        doOperation();
    }
}
