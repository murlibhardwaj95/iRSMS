package com.syon.isrms.Activity;

import android.app.DownloadManager;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.syon.isrms.Beans.Userbean_MySubjectTeacher_Data;
import com.syon.isrms.Beans.Userbean_MySubjectTeacher_Main;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class MyTeacher extends AppCompatActivity {

    ListView listView;
    String lUPIdNo, lClass_IdNo, lSec_IdNo;
    String lSessionId;
    String sSchCode;
    static String dataaa;
    Context ctx = this;
    private static final String TAG = "Something";
    RecyclerView ctrecycle, strecycle;
    ApiInterfce apiInterfce;
    ArrayList<String> data;


    ArrayList<HashMap<String, String>> CatlistDes = new ArrayList<>();
    ArrayList<com.syon.isrms.Model_Class.GetterSetterMyTeacher> array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterMyTeacher>();

    private SharedPreferences mPrefs;
    private static final int PERMISSION_REQUEST_CODE = 1;
    LinearLayout datanotfound;
    DownloadManager downloadManager;
    ImageView iv_offers;
    TextView heading, sWeight, sBMI, sLeftEyeVision, sRightEyeVision, sDentalHygiene, sBloodGroup, sAllergy, sHealthProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_teacher);
        listView = (ListView) findViewById(R.id.list);
        heading = (TextView) findViewById(R.id.heading);
        iv_offers = (ImageView) findViewById(R.id.iv_offers);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyTeacher.this);


        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId = obj.getWebSessionId();
        sSchCode = obj.getsSch_Code();
        lUPIdNo = mPrefs.getString("lUPIdNo", "");
        lClass_IdNo = mPrefs.getString("lClass_IdNo", "");
        lSec_IdNo = mPrefs.getString("lSec_IdNo", "");

        FessData(lClass_IdNo, lSec_IdNo, lSessionId, sSchCode);
        FessDataaa(lClass_IdNo, lSec_IdNo, lUPIdNo, lSessionId, sSchCode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTeacher.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("My Teacher");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.myte, 0, 0, 0);*/
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTeacher.super.onBackPressed();
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }
        toolbar.setTitleMargin(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            toolbar.setPaddingRelative(0, 0, 0, 0);
        }
        toolbar.setPadding(0, 0, 0, 0);
        init();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }



    private void init() {

        strecycle = findViewById(R.id.subjectteacherrecycler);
    }

    private void FessDataaa(String lClass_IdNo, String lSec_IdNo, String lUPIdNo, String lSessionId, String sSchCode) {

        try {
            Call<Userbean_MySubjectTeacher_Main> call1 = apiInterfce.subjectteacher(lClass_IdNo, lSec_IdNo, mPrefs.getString(getString(R.string.lFac_IdNo),""), lSessionId, sSchCode);
            call1.enqueue(new Callback<Userbean_MySubjectTeacher_Main>() {
                @Override
                public void onResponse(Call<Userbean_MySubjectTeacher_Main> call, retrofit2.Response<Userbean_MySubjectTeacher_Main> response) {
                    if (response.code() == 200) {
                        Userbean_MySubjectTeacher_Main stm = response.body();
                        List<Userbean_MySubjectTeacher_Data> std = stm.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        strecycle.setLayoutManager(layoutManager);
                        strecycle.setAdapter(new com.syon.isrms.Adapter.MyTeacher_SubjextAdapter(getApplicationContext(), std));

                    } else {
                        Toast.makeText(MyTeacher.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Userbean_MySubjectTeacher_Main> call, Throwable t) {
                    Toast.makeText(MyTeacher.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {

        }





    }


    private void FessData(String lClass_IdNo, String lSec_IdNo, String lSessionId, String sSchCode) {

        try {


            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<com.syon.isrms.Beans.Userbean_MyClassTeacher_Main> call = apiInterfce.classteacher(lClass_IdNo, lSec_IdNo, lSessionId, sSchCode);
            call.enqueue(new Callback<com.syon.isrms.Beans.Userbean_MyClassTeacher_Main>() {
                @Override
                public void onResponse(Call<com.syon.isrms.Beans.Userbean_MyClassTeacher_Main> call, retrofit2.Response<com.syon.isrms.Beans.Userbean_MyClassTeacher_Main> response) {
                    if (response.code() == 200) {

                        CircleImageView roundedImageView=findViewById(R.id.classteacher);
                        TextView textView=findViewById(R.id.ctname);
                        Picasso.with(MyTeacher.this).load(response.body().getData().getClassTeacherPhoto().toString()).resize(65,65).placeholder(R.drawable.avtar).into(roundedImageView);
                        textView.setText(response.body().getData().getClassTeacherName().toString());

                    } else {
                        Toast.makeText(MyTeacher.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<com.syon.isrms.Beans.Userbean_MyClassTeacher_Main> call, Throwable t) {
                    Toast.makeText(MyTeacher.this, "Check internet Connection", Toast.LENGTH_SHORT).show();

                }
            });


        } catch (Exception e) {

        }



    }


}

