package com.syon.isrms.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_Adapter_Circualar_View;
import com.syon.isrms.Adapter.Admin_Adapter_News_View;
import com.syon.isrms.Beans.AdminCircularViewBean;
import com.syon.isrms.Beans.AdminNewsViewBean;
import com.syon.isrms.Beans.Admin_Circualar_View_Bean_Data;
import com.syon.isrms.Beans.Admin_News_View_Bean_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_View_Circulars extends AppCompatActivity {
    ImageView back_button;
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    RecyclerView recyclerView;
    private static final int PERMISSION_REQUEST_CODE = 1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__view__circulars);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        requestForWritePermission();
        doApiOperation();
    }

    private void requestForWritePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {

            } else {
                requestPermission();
            }
        } else {

            // Code for Below 23 API Oriented Device
            // Do next code
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Admin_View_Circulars.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Admin_View_Circulars.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Admin_View_Circulars.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Admin_View_Circulars.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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


    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<AdminCircularViewBean> call = apiInterfce.getAdminCircualrView(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
        call.enqueue(new Callback<AdminCircularViewBean>() {
            @Override
            public void onResponse(Call<AdminCircularViewBean> call, Response<AdminCircularViewBean> response) {
                AdminCircularViewBean circularViewBean = response.body();
                if (response.code() == 200) {
                    List<Admin_Circualar_View_Bean_Data> data = new ArrayList<>();
                    data = circularViewBean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new Admin_Adapter_Circualar_View(Admin_View_Circulars.this, data));
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Admin_View_Circulars.this, "Data not found", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<AdminCircularViewBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_View_Circulars.this);
        recyclerView = findViewById(R.id.viewCircularRecycler);
        back_button = findViewById(R.id.back_button);
        progressDialog = new ProgressDialog(Admin_View_Circulars.this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        doOperation();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_View_Circulars.super.onBackPressed();
            }
        });
    }


}
