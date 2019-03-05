package com.syon.isrms.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.syon.isrms.R;

public class Parent_Compose_DataList extends AppCompatActivity {
    ViewPager viewPagerparent;
    TabLayout tabLayoutparent;
    SharedPreferences preferences;
    Button donebutton;
    ImageView back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__compose__data_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));}
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
        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*startActivity(new Intent(Parent_Compose_DataList.this,Parent_Compose_Mail.class));*/

                SharedPreferences.Editor editor = preferences.edit();

                editor.remove("get_parent_admin_id");
                editor.remove("get_parent_admin_name");
                editor.remove("get_parent_class_teacher_id");
                editor.remove("get_parent_class_teacher_name");
                editor.remove("get_parent_subject_teacher_id");
                editor.remove("get_parent_subject_teacher_name");


                editor.commit();

                //teacher prefrence

                String admin_id = preferences.getString("parent_admin_id", "");
                String admin_name = preferences.getString("parent_admin_name", "");

                //admin prefrence

                String class_teacher_id = preferences.getString("parent_class_teacher_id", "");
                String class_teacher_name = preferences.getString("parent_class_teacher_name", "");

                //student prefrence

                String subject_teacher_id = preferences.getString("parent_subject_teacher_id", "");
                String subject_teacher_name = preferences.getString("parent_subject_teacher_name", "");
                if(admin_id.length()<=1 && class_teacher_id.length()<=1 && subject_teacher_id.length()<=1) //condition for all null
                {
                    Toast.makeText(Parent_Compose_DataList.this, "select at list 1 contect", Toast.LENGTH_SHORT).show();
                }
                if((admin_id.length()<=1 && class_teacher_id.length()<=1 && subject_teacher_id.length()>1)) //condition for subject teacher only
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_parent_admin_id", "").putExtra("get_parent_admin_name", "").putExtra("data", "1").putExtra("get_parent_class_teacher_id", "").putExtra("get_parent_class_teacher_name", "").putExtra("get_parent_subject_teacher_id", subject_teacher_id).putExtra("get_parent_subject_teacher_name", subject_teacher_name).putExtra("n_Type","1"));
                    finish();
                }
                if((admin_id.length()>1 && class_teacher_id.length()<=1 && subject_teacher_id.length()<=1)) //condition for admin only
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_parent_admin_id", admin_id).putExtra("get_parent_admin_name", admin_name).putExtra("data", "1").putExtra("get_parent_class_teacher_id", "").putExtra("get_parent_class_teacher_name", "").putExtra("get_parent_subject_teacher_id", "").putExtra("get_parent_subject_teacher_name", "").putExtra("n_Type","1"));
                    finish();
                }
                if((admin_id.length()<=1 && class_teacher_id.length()>1 && subject_teacher_id.length()<=1)) //condition for class teacher only
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_parent_admin_id", "").putExtra("get_parent_admin_name", "").putExtra("data", "1").putExtra("get_parent_class_teacher_id", class_teacher_id).putExtra("get_parent_class_teacher_name", class_teacher_name).putExtra("get_parent_subject_teacher_id", "").putExtra("get_parent_subject_teacher_name", "").putExtra("n_Type","1"));
                    finish();
                }
                if((admin_id.length()>1 && class_teacher_id.length()>1 && subject_teacher_id.length()>1)) //condition for all
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_parent_admin_id", admin_id).putExtra("get_parent_admin_name", admin_name).putExtra("data", "1").putExtra("get_parent_class_teacher_id", class_teacher_id).putExtra("get_parent_class_teacher_name", class_teacher_name).putExtra("get_parent_subject_teacher_id", subject_teacher_id).putExtra("get_parent_subject_teacher_name", subject_teacher_name).putExtra("n_Type","1"));
                    finish();
                }
                if((admin_id.length()>1 && class_teacher_id.length()>1 && subject_teacher_id.length()<=1)) //condition for admin and class teacher
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_parent_admin_id", admin_id).putExtra("get_parent_admin_name", admin_name).putExtra("data", "1").putExtra("get_parent_class_teacher_id", class_teacher_id).putExtra("get_parent_class_teacher_name", class_teacher_name).putExtra("get_parent_subject_teacher_id", "").putExtra("get_parent_subject_teacher_name", "").putExtra("n_Type","1"));
                    finish();
                }
                if((admin_id.length()<=1 && class_teacher_id.length()>1 && subject_teacher_id.length()>1)) //condition for  class teacher and subject teacher
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_parent_admin_id", "").putExtra("get_parent_admin_name", "").putExtra("data", "1").putExtra("get_parent_class_teacher_id", class_teacher_id).putExtra("get_parent_class_teacher_name", class_teacher_name).putExtra("get_parent_subject_teacher_id", subject_teacher_id).putExtra("get_parent_subject_teacher_name", subject_teacher_name).putExtra("n_Type","1"));
                    finish();
                }
                if((admin_id.length()>1 && class_teacher_id.length()<=1 && subject_teacher_id.length()>1)) //condition for  admin and subject teacher
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_parent_admin_id", admin_id).putExtra("get_parent_admin_name", admin_name).putExtra("data", "1").putExtra("get_parent_class_teacher_id", "").putExtra("get_parent_class_teacher_name", "").putExtra("get_parent_subject_teacher_id", subject_teacher_id).putExtra("get_parent_subject_teacher_name", subject_teacher_name).putExtra("n_Type","1"));
                    finish();
                }

               /* if(admin_id.length()<=1 && class_teacher_id.length()<=1 && subject_teacher_id.length()<=1) //condition for all null
                {
                    Toast.makeText(Parent_Compose_DataList.this, "select at list 1 contect", Toast.LENGTH_SHORT).show();
                }
                if((admin_id.length()>1 && class_teacher_id.length()>1 && subject_teacher_id.length()<=1)) //condition for admin and teacher
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_teacher_id", teacherp_id).putExtra("get_teacher_name", teacherp_name).putExtra("data", "1").putExtra("get_admin_id", adminp_id).putExtra("get_admin_name", adminp_name).putExtra("get_student_id", "").putExtra("get_student_name", "").putExtra("n_Type","2"));
                    finish();
                }
                if((admin_id.length()<=1 && adminp_id.length()<=1 && studentp_id.length()>1)) //condition for parent only
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_teacher_id", "").putExtra("get_teacher_name", "").putExtra("data", "1").putExtra("get_admin_id", "").putExtra("get_admin_name", "").putExtra("get_student_id", studentp_id).putExtra("get_student_name", studentp_name).putExtra("n_Type","1"));
                    finish();
                }
                if((teacherp_id.length()>1 && adminp_id.length()<=1 && studentp_id.length()>1)) //condition for teacher and parent
                {
                    Toast.makeText(Parent_Compose_DataList.this, "You cant Select Parent and Teacher at a time", Toast.LENGTH_SHORT).show();
                }
                if((teacherp_id.length()<=1 && adminp_id.length()>1 && studentp_id.length()>1)) //condition for admin and parent
                {
                    Toast.makeText(Parent_Compose_DataList.this, "You cant Select admin and parent at a time", Toast.LENGTH_SHORT).show();
                }
                if((teacherp_id.length()>1 && adminp_id.length()<=1 && studentp_id.length()<=1)) //condition for teacher only
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_teacher_id", teacherp_id).putExtra("get_teacher_name", teacherp_name).putExtra("data", "1").putExtra("get_admin_id", "").putExtra("get_admin_name", "").putExtra("get_student_id", "").putExtra("get_student_name", "").putExtra("n_Type","2"));
                    finish();
                }
                if((teacherp_id.length()<=1 && adminp_id.length()>1 && studentp_id.length()<=1)) //condition for admin and parent
                {
                    startActivity(new Intent(Parent_Compose_DataList.this, Parent_Compose_Mail.class).putExtra("get_teacher_id", "").putExtra("get_teacher_name", "").putExtra("data", "1").putExtra("get_admin_id", adminp_id).putExtra("get_admin_name", adminp_name).putExtra("get_student_id", "").putExtra("get_student_name", "").putExtra("n_Type","2"));
                    finish();
                }
*/

            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parent_Compose_DataList.super.onBackPressed();
                finish();

            }
        });

    }
    private void adaptercommunication() {
        com.syon.isrms.Adapter.ParentCommunicationAdapter adapt = new com.syon.isrms.Adapter.ParentCommunicationAdapter(getSupportFragmentManager());
        tabLayoutparent.setupWithViewPager(viewPagerparent);
        viewPagerparent.setAdapter(adapt);

    }

    private void init() {

        viewPagerparent = findViewById(R.id.communication_view_pagerparent);
        tabLayoutparent = findViewById(R.id.composetabparent);
        donebutton = findViewById(R.id.donebuttonparent);
        preferences = PreferenceManager.getDefaultSharedPreferences(Parent_Compose_DataList.this);
        back_button=findViewById(R.id.back_button);


    }



}
