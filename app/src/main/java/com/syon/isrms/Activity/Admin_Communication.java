package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_CommunicationPageAdapter;
import com.syon.isrms.R;

public class Admin_Communication extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    ImageView back_button;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__communication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_Communication.this,Admin_Communication_Mail.class));
            }
        });
        init();
        doOperation();
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {

            // Code for Below 23 API Oriented Device
            // Do next code
        }
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Admin_Communication.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Admin_Communication.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Admin_Communication.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Admin_Communication.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
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
                Admin_Communication.super.onBackPressed();
            }
        });
    }

    private void init() {
        viewPager = findViewById(R.id.viewpagerCommunicationadmin);
        tabLayout = findViewById(R.id.admincommunicationtab);
        back_button = findViewById(R.id.back_button);
        callPageAdapter();
    }

    private void callPageAdapter() {
        Admin_CommunicationPageAdapter communicationPageAdepter = new Admin_CommunicationPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(communicationPageAdepter);
        tabLayout.setupWithViewPager(viewPager);
    }


}