package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Beans.Userbean_Send;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Communication_Mail extends AppCompatActivity {


    ImageView uploaddocs, getContact;
    ImageView back_button;
    EditText totext, subjecttext, descriptiontext;
    String to_tid, to_p_id, to_a_id;
    Button submitcommunication, resetcommunication;
    String totext_send = null;
    String totext_student = null;
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    String final_names = null;
    EditText fromtext;
    String nType;
    ImageView upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__communication__mail);
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
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("teacher_id", "");
        editor.putString("teacher_name", "");
        editor.putString("student_id", "");
        editor.putString("student_name", "");
        editor.commit();
        finish();
    }

    private void init() {
        getContact = findViewById(R.id.adget);
        back_button = findViewById(R.id.back_button);
        subjecttext = findViewById(R.id.adsubjecttext);
        descriptiontext = findViewById(R.id.addescriptiontext);
        submitcommunication = findViewById(R.id.adsubmitcommunication);
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Communication_Mail.this);
        totext = findViewById(R.id.adtotext);
        fromtext = findViewById(R.id.adfromtext);
        fromtext.setText(preferences.getString(getString(R.string.adminEmpName),""));
        resetcommunication = findViewById(R.id.adresetcommunication);
        nType = getIntent().getStringExtra("n_Type");
        if (nType == null) {

        }
        doOperation();
        try {

            totext_send = getIntent().getStringExtra("get_teacher_name");
            totext_student = getIntent().getStringExtra("get_student_name");


            if (totext_send.length() <= 1 && totext_student.length() <= 1) {
                totext.setText("");

            }
            if (totext_send.length() > 1  && totext_student.length() <= 1) {
                totext_send = totext_send.substring(1, totext_send.length() - 1);
                final_names = totext_send;
                totext.setText(final_names);
            }

            if (totext_send.length() <= 1  && totext_student.length() > 1) {
                totext_student = totext_student.substring(1, totext_student.length() - 1);
                final_names = totext_student;
                totext.setText(final_names);

            }
            if (totext_send.length() > 1 && totext_student.length() <= 1) {
                totext_send = totext_send.substring(1, totext_send.length() - 1);
                final_names = totext_send ;
                totext.setText(final_names);

            }


        } catch (Exception e) {

        }


    }

    private void doOperation() {
        getContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Communication_Mail.this, Admin_Communocation_Mail_List.class);
                startActivity(intent);
                finish();
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Admin_Communication_Mail.super.onBackPressed();
                finish();
            }
        });
        submitcommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subjecttext.length() < 0) {
                    Toast.makeText(Admin_Communication_Mail.this, "Don't fill Blank Subject", Toast.LENGTH_SHORT).show();
                } else if (descriptiontext.length() < 0) {
                    Toast.makeText(Admin_Communication_Mail.this, "Don't fill Blank Description", Toast.LENGTH_SHORT).show();
                } else if (nType == null) {
                    Toast.makeText(Admin_Communication_Mail.this, "Please select Contact", Toast.LENGTH_SHORT).show();
                } else {
                    doApiOpertion();
                }

            }
        });
    }

    private void doApiOpertion() {
        String id = "";

        to_tid = getIntent().getStringExtra("get_teacher_id");
        to_p_id = getIntent().getStringExtra("get_student_id");

        if (to_tid.length() <= 1  && to_p_id.length() <= 1) {
            totext.setText("");

        }
        if (to_tid.length() > 1  && to_p_id.length() <= 1) {
            to_tid = to_tid.substring(1, to_tid.length() - 1);
            id = to_tid;

        }

        if (to_tid.length() <= 1 && to_p_id.length() > 1) {
            to_p_id = to_p_id.substring(1, to_p_id.length() - 1);
            id = to_p_id;


        }


        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        try {

           /* String id = to_tid.substring(0, to_tid.length() - 1);
            to_tid = getIntent().getStringExtra("teacher_id");*/
            if (to_tid == null) {

            } else {
                String subject = subjecttext.getText().toString();
                String message = descriptiontext.getText().toString();
                Call<Userbean_Send> compose_beanCall = apiInterfce.sendmail(preferences.getString(getString(R.string.adminEmpIdNo), ""), nType, id, subject, message, "", preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
                compose_beanCall.enqueue(new Callback<Userbean_Send>() {
                    @Override
                    public void onResponse(Call<Userbean_Send> call, Response<Userbean_Send> response) {

                        if (response.code() == 200) {
                            Userbean_Send userbean_send = response.body();
                            totext.setText("");
                            subjecttext.setText("");
                            descriptiontext.setText("");
                            Toast.makeText(Admin_Communication_Mail.this, response.body().getData() + "", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Admin_Communication_Mail.this, "Mail doesn't Sent", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Userbean_Send> call, Throwable t) {
                        Toast.makeText(Admin_Communication_Mail.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        } catch (Exception e) {

        }
    }

}
