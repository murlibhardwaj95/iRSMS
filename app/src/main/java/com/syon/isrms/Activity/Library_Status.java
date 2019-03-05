package com.syon.isrms.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.syon.isrms.Adapter.Pager_Adapter;
import com.syon.isrms.R;

public class Library_Status extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library__status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doOperation();


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Library_Status.super.onBackPressed();
            }
        });
    }

    private void init() {
        back_button = findViewById(R.id.back_button);
        viewPager = (ViewPager) findViewById(R.id.library_view_pager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        Pager_Adapter adapter = new Pager_Adapter(getSupportFragmentManager());
        adapter.addFragment(new com.syon.isrms.Fragement.Issued_book_Fragment(), "Issued Books");
        adapter.addFragment(new com.syon.isrms.Fragement.Library_Status_Fragment(), "Library Status");
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.libray_tab);
        tabLayout.setupWithViewPager(viewPager);
    }



}
