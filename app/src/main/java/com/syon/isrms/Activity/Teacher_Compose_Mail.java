package com.syon.isrms.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;
import com.nononsenseapps.filepicker.Utils;
import com.syon.isrms.Beans.Userbean_Communication_Notification;
import com.syon.isrms.Beans.Userbean_Notification_Communication;
import com.syon.isrms.Beans.Userbean_Send;
import com.syon.isrms.HelperClasses.PostMultipart;
import com.syon.isrms.HelperClasses.PostMultipartNews;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.io.File;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Compose_Mail extends AppCompatActivity {

    ImageView uploaddocs, getContact;
    ImageView back_button;
    EditText totext, subjecttext, descriptiontext;
    String to_tid, to_p_id, to_a_id;
    Button submitcommunication, resetcommunication;
    String totext_send = null;
    String totext_admin = null;
    String totext_student = null;
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    EditText filenames;
    String final_names = null;
    EditText fromtext;
    String nType;
    ImageButton upload;
    Uri mediaPath;
    File file;
    String filename;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__compose__mail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doOperation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("teacher_id", "");
        editor.putString("teacher_name", "");
        editor.putString("admin_id", "");
        editor.putString("admin_name", "");
        editor.putString("student_id", "");
        editor.putString("student_name", "");
        editor.commit();
        finish();
    }

    private void init() {
        getContact = findViewById(R.id.get);
        back_button = findViewById(R.id.back_button);
        subjecttext = findViewById(R.id.subjecttext);
        descriptiontext = findViewById(R.id.descriptiontext);
        submitcommunication = findViewById(R.id.submitcommunication);
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Compose_Mail.this);
        totext = findViewById(R.id.totext);
        fromtext = findViewById(R.id.fromtext);
        resetcommunication = findViewById(R.id.resetcommunication);
        upload = findViewById(R.id.upload_btn);
        filenames=findViewById(R.id.filename);
        fromtext.setText(preferences.getString(getString(R.string.EmpName), ""));
        nType = getIntent().getStringExtra("n_Type");
        if (nType == null) {

        }





        try {

            totext_send = getIntent().getStringExtra("get_teacher_name");
            totext_admin = getIntent().getStringExtra("get_admin_name");
            totext_student = getIntent().getStringExtra("get_student_name");


            if (totext_send.length() <= 1 && totext_admin.length() <= 1 && totext_student.length() <= 1) {
                totext.setText("");

            }
            if (totext_send.length() > 1 && totext_admin.length() <= 1 && totext_student.length() <= 1) {
                totext_send = totext_send.substring(1, totext_send.length() - 1);
                final_names = totext_send;
                totext.setText(final_names);
            }
            if (totext_send.length() <= 1 && totext_admin.length() > 1 && totext_student.length() <= 1) {
                totext_admin = totext_admin.substring(1, totext_admin.length() - 1);
                final_names = totext_admin;
                totext.setText(final_names);

            }
            if (totext_send.length() <= 1 && totext_admin.length() <= 1 && totext_student.length() > 1) {
                totext_student = totext_student.substring(1, totext_student.length() - 1);
                final_names = totext_student;
                totext.setText(final_names);

            }
            if (totext_send.length() > 1 && totext_admin.length() > 1 && totext_student.length() <= 1) {
                totext_send = totext_send.substring(1, totext_send.length() - 1);
                totext_admin = totext_admin.substring(0, totext_admin.length() - 1);
                final_names = totext_send + totext_admin;
                totext.setText(final_names);

            }





        } catch (Exception e) {

        }


    }

    private void doOperation() {
        getContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_Compose_Mail.this, Teacher_Compose_DataList.class);
                startActivity(intent);
                finish();
            }
        });



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Teacher_Compose_Mail.super.onBackPressed();
                finish();
            }
        });
        submitcommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (subjecttext.length() < 0) {
                    Toast.makeText(Teacher_Compose_Mail.this, "Don't fill Blank Subject", Toast.LENGTH_SHORT).show();
                } else if (descriptiontext.length() < 0) {
                    Toast.makeText(Teacher_Compose_Mail.this, "Don't fill Blank Description", Toast.LENGTH_SHORT).show();
                } else if (nType == null) {
                    Toast.makeText(Teacher_Compose_Mail.this, "Please select Contact", Toast.LENGTH_SHORT).show();
                } else {
                    doApiOpertion();

                   uploadFile();
                }


            }
        });
        resetcommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totext.setText("");
                subjecttext.setText("");
                descriptiontext.setText("");

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Teacher_Compose_Mail.this, FilePickerActivity.class);
                // This works if you defined the intent filter
                // Intent i = new Intent(Intent.ACTION_GET_CONTENT);

                // Set these depending on your use case. These are the defaults.
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);

                // Configure initial directory by specifying a String.
                // You could specify a String like "/storage/emulated/0/", but that can
                // dangerous. Always use Android's API calls to get paths to the SD-card or
                // internal memory.
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());

                startActivityForResult(i, 1010);
            }
        });
    }

    private void uploadFile() {

        PostMultipartNews postMultipart=new PostMultipartNews();
        String url=preferences.getString("BASE_URL","");

        try {
            postMultipart.run(url,"Communication",file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doApiOpertion() {
        String id = "";

        to_tid = getIntent().getStringExtra("get_teacher_id");
        to_a_id = getIntent().getStringExtra("get_admin_id");
        to_p_id = getIntent().getStringExtra("get_student_id");

        if (to_tid.length() <= 1 && to_a_id.length() <= 1 && to_p_id.length() <= 1) {
            totext.setText("");

        }
        if (to_tid.length() > 1 && to_a_id.length() <= 1 && to_p_id.length() <= 1) {
            to_tid = to_tid.substring(1, to_tid.length() - 1);
            id = to_tid;

        }
        if (to_tid.length() <= 1 && to_a_id.length() > 1 && to_p_id.length() <= 1) {
            to_a_id = to_a_id.substring(1, to_a_id.length() - 1);
            id = to_a_id;

        }
        if (to_tid.length() <= 1 && to_a_id.length() <= 1 && to_p_id.length() > 1) {
            to_p_id = to_p_id.substring(1, to_p_id.length() - 1);
            id = to_p_id;


        }
        if (to_tid.length() > 1 && to_a_id.length() > 1 && to_p_id.length() <= 1) {
            to_tid = to_tid.substring(1, to_tid.length() - 1);
            to_a_id = to_a_id.substring(0, to_a_id.length() - 1);
            id = to_tid + to_a_id;
        }


if (file!=null)
{
    filename=file.getName().toString();
}
else {
filename="";
}

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        try {


            if (to_tid == null) {

            } else {
                String subject = subjecttext.getText().toString();
                String message = descriptiontext.getText().toString();
                Call<Userbean_Send> compose_beanCall = apiInterfce.sendmail(preferences.getString(getString(R.string.EmpId), ""), nType, id, subject, message, filename, preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
                compose_beanCall.enqueue(new Callback<Userbean_Send>() {
                    @Override
                    public void onResponse(Call<Userbean_Send> call, Response<Userbean_Send> response) {

                        if (response.code() == 200) {

                            Userbean_Send userbean_send = response.body();
                            totext.setText("");
                            subjecttext.setText("");
                            descriptiontext.setText("");
                            Toast.makeText(Teacher_Compose_Mail.this, response.body().getData() + "", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Teacher_Compose_Mail.this, "Mail doesn't Sent", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Userbean_Send> call, Throwable t) {
                        Toast.makeText(Teacher_Compose_Mail.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        } catch (Exception e) {

        }



        if(Integer.parseInt(nType)==2)
        {
            String nMassage = subjecttext.getText().toString();
            Call<Userbean_Notification_Communication> notificationCall = apiInterfce.getParentSendNotificationComunication(id, "New Massage : " + nMassage, "4", "Communication", preferences.getString(getString(R.string.School_Name),""), preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            notificationCall.enqueue(new Callback<Userbean_Notification_Communication>() {
                @Override
                public void onResponse(Call<Userbean_Notification_Communication> call, Response<Userbean_Notification_Communication> response) {
                    if (response.code() == 200) {
                        /*Toast.makeText(Teacher_Compose_Mail.this, "s", Toast.LENGTH_SHORT).show();*/
                    } else {
                        /*Toast.makeText(Teacher_Compose_Mail.this, "f", Toast.LENGTH_SHORT).show();*/

                    }

                }

                @Override
                public void onFailure(Call<Userbean_Notification_Communication> call, Throwable t) {
                    /*Toast.makeText(Teacher_Compose_Mail.this, "i", Toast.LENGTH_SHORT).show();*/
                }
            });
        }
        else
        {
            String nMassage = subjecttext.getText().toString();
            Call<Userbean_Communication_Notification> callcomm = apiInterfce.notifycommunication(id, "New Massage : " +nMassage, "4", "Communication", preferences.getString(getString(R.string.School_Name),""), preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            final String finalId = id;
            callcomm.enqueue(new Callback<Userbean_Communication_Notification>() {
                @Override
                public void onResponse(Call<Userbean_Communication_Notification> call, Response<Userbean_Communication_Notification> response) {
                    // Toast.makeText(Teacher_Compose_Mail.this, ""+finalId, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Teacher_Compose_Mail.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                    if(response.code() == 200){

                        Toast.makeText(Teacher_Compose_Mail.this, "notification send", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Teacher_Compose_Mail.this, "Not send", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Userbean_Communication_Notification> call, Throwable t) {

                }
            });
        }



    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1010 && resultCode == Activity.RESULT_OK) {
            // Use the provided utility method to parse the result
            List<Uri> files = Utils.getSelectedFilesFromResult(intent);
            for (Uri uri : files) {
                file = Utils.getFileForUri(uri);
                // Do something with the result...
            }
            filenames.setText(file.getName().toString());

        }
    }


}
