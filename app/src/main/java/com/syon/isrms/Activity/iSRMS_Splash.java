package com.syon.isrms.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Home;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import retrofit2.Call;
import retrofit2.Callback;

public class iSRMS_Splash extends AppCompatActivity {
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
        setContentView(R.layout.activity_i_srms__splash);
        getSupportActionBar().hide();

        tv = (TextView) findViewById(R.id.head);
        img = (ImageView) findViewById(R.id.imag);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        if(mPrefs.getString("isrms_screen","").equals("show"))
        {
            startActivity(new Intent(iSRMS_Splash.this,Splash.class));
            finish();

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#8E2C53"));
        }


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
                    startActivity(new Intent(iSRMS_Splash.this,iSRMS_Choose_School.class));
                    finish();

                }
            }, 3000);
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



    }


