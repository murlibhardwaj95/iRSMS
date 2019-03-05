package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_Communication_Mail_PageAdapter;
import com.syon.isrms.Adapter.CoummunicationPageAdepter;
import com.syon.isrms.R;

public class Admin_Communocation_Mail_List extends AppCompatActivity {

    ImageView backbuttoncomm;
    ViewPager viewPager;
    TabLayout tabLayoutcomm;
    Button donebutton;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__communocation__mail__list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();
        adaptercommunication();
        doOperation();

    }

    private void doOperation() {
        backbuttoncomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Communocation_Mail_List.super.onBackPressed();
            }
        });
        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("get_teacher_id");
                editor.remove("get_teacher_name");
                editor.remove("get_student_id");
                editor.remove("get_student_name");
                editor.commit();

                //get selected checks

                String teacher_id = preferences.getString("teacher_id", "");
                String teacher_name = preferences.getString("teacher_name", "");

                //parent prefrence

                String student_id = preferences.getString("student_id", "");
                String student_name = preferences.getString("student_name", "");



                if(teacher_id.length()<=1 && student_id.length()<=1) //condition for all null
                {
                    Toast.makeText(Admin_Communocation_Mail_List.this, "select at list 1 contect", Toast.LENGTH_SHORT).show();
                }

                if((teacher_id.length()<=1 && student_id.length()>1)) //condition for parent only
                {
                    startActivity(new Intent(Admin_Communocation_Mail_List.this, Admin_Communication_Mail.class).putExtra("get_teacher_id", "").putExtra("get_teacher_name", "").putExtra("data", "1").putExtra("get_admin_id", "").putExtra("get_admin_name", "").putExtra("get_student_id", student_id).putExtra("get_student_name", student_name).putExtra("n_Type","1"));
                    finish();
                }
                if((teacher_id.length()>1  && student_id.length()>1)) //condition for teacher and parent
                {
                    Toast.makeText(Admin_Communocation_Mail_List.this, "You cant Select Parent and Teacher at a time", Toast.LENGTH_SHORT).show();
                }

                if((teacher_id.length()>1 && student_id.length()<=1)) //condition for teacher only
                {
                    startActivity(new Intent(Admin_Communocation_Mail_List.this, Admin_Communication_Mail.class).putExtra("get_teacher_id", teacher_id).putExtra("get_teacher_name", teacher_name).putExtra("data", "1").putExtra("get_admin_id", "").putExtra("get_admin_name", "").putExtra("get_student_id", "").putExtra("get_student_name", "").putExtra("n_Type","2"));
                    finish();
                }


            }
        });
    }


    private void adaptercommunication() {
            Admin_Communication_Mail_PageAdapter adapt = new Admin_Communication_Mail_PageAdapter(getSupportFragmentManager());
            tabLayoutcomm.setupWithViewPager(viewPager);
            viewPager.setAdapter(adapt);
        }

        private void init() {
            backbuttoncomm = findViewById(R.id.back_button);
            viewPager = findViewById(R.id.admin_communication_view_pager);
            tabLayoutcomm = findViewById(R.id.adcomposetab);
            donebutton = findViewById(R.id.addonebutton);
            preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Communocation_Mail_List.this);
        }
}
