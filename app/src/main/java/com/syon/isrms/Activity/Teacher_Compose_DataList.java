package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.CoummunicationPageAdepter;
import com.syon.isrms.R;

public class Teacher_Compose_DataList extends AppCompatActivity {

    ImageView backbuttoncomm;
    ViewPager viewPager;
    TabLayout tabLayoutcomm;
    Button donebutton;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__compose__data_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        adaptercommunication();
        doOperation();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doOperation() {
        backbuttoncomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Compose_DataList.super.onBackPressed();

            }
        });
        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("get_teacher_id");
                editor.remove("get_teacher_name");
                editor.remove("get_admin_id");
                editor.remove("get_admin_name");
                editor.remove("get_student_id");
                editor.remove("get_student_name");
                editor.commit();

                //teacher prefrence

                String teacher_id = preferences.getString("teacher_id", "");
                String teacher_name = preferences.getString("teacher_name", "");



                //admin prefrence

                String admin_id = preferences.getString("admin_id", "");
                String admin_name = preferences.getString("admin_name", "");



                //student prefrence

                String student_id = preferences.getString("student_id", "");
                String student_name = preferences.getString("student_name", "");

                if (teacher_id.length()>1 && admin_id.length()>1 && student_id.length()>1) //condition for all
                {
                    Toast.makeText(Teacher_Compose_DataList.this, "you cant send msg to admin,teacher,student", Toast.LENGTH_SHORT).show();
                }
                if(teacher_id.length()<=1 && admin_id.length()<=1 && student_id.length()<=1) //condition for all null
                {
                    Toast.makeText(Teacher_Compose_DataList.this, "select at list 1 contect", Toast.LENGTH_SHORT).show();
                }
                if((teacher_id.length()>1 && admin_id.length()>1 && student_id.length()<=1)) //condition for admin and teacher
                {
                    startActivity(new Intent(Teacher_Compose_DataList.this, Teacher_Compose_Mail.class).putExtra("get_teacher_id", teacher_id).putExtra("get_teacher_name", teacher_name).putExtra("data", "1").putExtra("get_admin_id", admin_id).putExtra("get_admin_name", admin_name).putExtra("get_student_id", "").putExtra("get_student_name", "").putExtra("n_Type","2"));
                    finish();
                }
                if((teacher_id.length()<=1 && admin_id.length()<=1 && student_id.length()>1)) //condition for parent only
                {
                    startActivity(new Intent(Teacher_Compose_DataList.this, Teacher_Compose_Mail.class).putExtra("get_teacher_id", "").putExtra("get_teacher_name", "").putExtra("data", "1").putExtra("get_admin_id", "").putExtra("get_admin_name", "").putExtra("get_student_id", student_id).putExtra("get_student_name", student_name).putExtra("n_Type","1"));
                    finish();
                }
                if((teacher_id.length()>1 && admin_id.length()<=1 && student_id.length()>1)) //condition for teacher and parent
                {
                    Toast.makeText(Teacher_Compose_DataList.this, "You cant Select Parent and Teacher at a time", Toast.LENGTH_SHORT).show();
                }
                if((teacher_id.length()<=1 && admin_id.length()>1 && student_id.length()>1)) //condition for admin and parent
                {
                    Toast.makeText(Teacher_Compose_DataList.this, "You cant Select admin and parent at a time", Toast.LENGTH_SHORT).show();
                }
                if((teacher_id.length()>1 && admin_id.length()<=1 && student_id.length()<=1)) //condition for teacher only
                {
                    startActivity(new Intent(Teacher_Compose_DataList.this, Teacher_Compose_Mail.class).putExtra("get_teacher_id", teacher_id).putExtra("get_teacher_name", teacher_name).putExtra("data", "1").putExtra("get_admin_id", "").putExtra("get_admin_name", "").putExtra("get_student_id", "").putExtra("get_student_name", "").putExtra("n_Type","2"));
                    finish();
                }
                if((teacher_id.length()<=1 && admin_id.length()>1 && student_id.length()<=1)) //condition for admin and parent
                {
                    startActivity(new Intent(Teacher_Compose_DataList.this, Teacher_Compose_Mail.class).putExtra("get_teacher_id", "").putExtra("get_teacher_name", "").putExtra("data", "1").putExtra("get_admin_id", admin_id).putExtra("get_admin_name", admin_name).putExtra("get_student_id", "").putExtra("get_student_name", "").putExtra("n_Type","2"));
                    finish();
                }



                /*startActivity(new Intent(Teacher_Compose_DataList.this, Teacher_Compose_Mail.class).putExtra("get_teacher_id", teacher_id).putExtra("get_teacher_name", teacher_name).putExtra("data", "1").putExtra("get_admin_id", admin_id).putExtra("get_admin_name", admin_name).putExtra("get_student_id", student_id).putExtra("get_student_name", student_name));
                finish();*/
            }
        });
    }

    private void adaptercommunication() {
        CoummunicationPageAdepter adapt = new CoummunicationPageAdepter(getSupportFragmentManager());
        tabLayoutcomm.setupWithViewPager(viewPager);
        viewPager.setAdapter(adapt);
    }

    private void init() {
        backbuttoncomm = findViewById(R.id.back_button);
        viewPager = findViewById(R.id.communication_view_pager);
        tabLayoutcomm = findViewById(R.id.composetab);
        donebutton = findViewById(R.id.donebutton);

        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Compose_DataList.this);
    }

}
