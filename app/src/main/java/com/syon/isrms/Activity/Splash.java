package com.syon.isrms.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Beans.AdminHeaderImageBean;
import com.syon.isrms.Beans.UserbeanShareRate;
import com.syon.isrms.Home;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;
import com.syon.isrms.interfaces.custom.ApiIHandler;
import com.syon.isrms.urlsApimanage.ApiUrls;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Splash extends AppCompatActivity {

    static ConnectivityManager cm;
    AlertDialog dailog;
    AlertDialog.Builder build;
    TextView tv;
    Context ctx = this;
    Intent myintent;
    ImageView img;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        tv = (TextView) findViewById(R.id.head);
        img = (ImageView) findViewById(R.id.imag);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
     /*   ApiClient apiClient=new ApiClient(mPrefs.getString("BASE_URL",""));
        ApiUrls apiUrls=new ApiUrls(mPrefs.getString("BASE_URL",""));*/


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#8E2C53"));
        }


     /*   getSchoolName();*/

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        build = new AlertDialog.Builder(this);

        if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting()
                || cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting()// if connection is

                ) {
            Log.e("cm value", "" + cm.getAllNetworkInfo().toString());
            // MyToast.show(Splash.this, "Internet is active", false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences log = PreferenceManager.getDefaultSharedPreferences(Splash.this);
                    switch (log.getInt("log", 0)) {
                        case 1:
                            startActivity(new Intent(Splash.this, Login.class));
                            finish();
                            break;
                        case 2:
                            startActivity(new Intent(Splash.this, Home.class));
                            finish();
                            break;
                        case 3:
                            startActivity(new Intent(Splash.this, Teacher_Dashboard.class));
                            finish();
                            break;
                        case 4:
                            startActivity(new Intent(Splash.this, Admin_Dashboard.class));
                            finish();
                            break;
                        default:
                            startActivity(new Intent(Splash.this, Slide_Activity.class));
                            finish();
                    }

                }
            }, 5000);
        } else {
            build.setMessage("Oops, check your internet connection");
            build.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    finish();

                }
            });
            dailog = build.create();
            dailog.show();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        ApiClient apiClient=new ApiClient(mPrefs.getString("BASE_URL",""));
        ApiUrls apiUrls=new ApiUrls(mPrefs.getString("BASE_URL",""));
        getSchoolName();
    }

    private void getSchoolName() {
       // Toast.makeText(ctx, "aaya", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mPrefs.getString("BASE_URL",""))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiIHandler service = retrofit.create(ApiIHandler.class);
        Call<SchoolNameResponse> call = service.getNewsDataFROMSERVER();
        call.enqueue(new Callback<SchoolNameResponse>() {
            @Override
            public void onResponse(Call<SchoolNameResponse> call, retrofit2.Response<SchoolNameResponse> response) {
                // pDialog.dismiss();
                if (response.code() == 200) {

                    SchoolNameResponse data = response.body();
                    tv.setText(data.getsSch_Name());
                    Picasso.with(ctx).load(data.getSchoolLogo()).into(img);
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    prefsEditor.remove("MyObject");
                    prefsEditor.remove(getString(R.string.School_Name));
                    prefsEditor.putString("MyObject", json);
                    prefsEditor.putString(getString(R.string.School_Name),data.getsSch_Name());
                    prefsEditor.commit();

                    // pDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<SchoolNameResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
/*
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
*/
                // Toast.makeText(Splash.this, getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });


        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            ApiInterfce apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminHeaderImageBean> call1 = apiInterfce.getAdminHeaderImage();
            call1.enqueue(new Callback<AdminHeaderImageBean>() {
                @Override
                public void onResponse(Call<AdminHeaderImageBean> call, retrofit2.Response<AdminHeaderImageBean> response) {
                    if (response.code() == 200) {
                        AdminHeaderImageBean data = response.body();
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        prefsEditor.putString("SCHOOL_PHOTO",data.getData().toString());
                        prefsEditor.commit();



                    } else {

                    }
                }

                @Override
                public void onFailure(Call<AdminHeaderImageBean> call, Throwable t) {


                }
            });
        } catch (Exception e) {


        }







    }

}
