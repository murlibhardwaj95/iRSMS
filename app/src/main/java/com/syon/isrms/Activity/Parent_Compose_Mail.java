package com.syon.isrms.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;
import com.nononsenseapps.filepicker.Utils;
import com.syon.isrms.Beans.Userbean_Notification_Communication;
import com.syon.isrms.Beans.Userbean_Parent_SendMail;
import com.syon.isrms.HelperClasses.PostMultipart;
import com.syon.isrms.HelperClasses.PostMultipartNews;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Parent_Compose_Mail extends AppCompatActivity {
    ImageView getContact, back_button;
    EditText fromtext, totext, subjecttext, descriptiontext;
    Button submitcommunication, resetparent;
    String totext_admin = null;
    String totext_class_teacher = null;
    String totext_subject_teacher = null;
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    String final_names = null;
    String to_a_id, to_c_id, to_s_id;
    String nType;
    ImageButton upload;
    EditText filenames;
    ProgressDialog progressDialog;
    TextView str1;

    InputStream is1;
    Uri mediaPath;
    File file;
    String filename;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__compose__mail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
            init();
            doOperation();
            nType = getIntent().getStringExtra("n_Type");
            if (nType == null) {

            }
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("parent_admin_id", "");
        editor.putString("parent_admin_name", "");
        editor.putString("parent_class_teacher_id", "");
        editor.putString("parent_class_teacher_name", "");
        editor.putString("parent_subject_teacher_id", "");
        editor.putString("parent_subject_teacher_name", "");
        editor.commit();
        finish();

    }

    private void doOperation() {

        getContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Parent_Compose_Mail.this, Parent_Compose_DataList.class));
                finish();

            }
        });
        resetparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totext.setText("");
                subjecttext.setText("");
                descriptiontext.setText("");

            }
        });
        submitcommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (subjecttext.getText().length() < 0) {
                    Toast.makeText(Parent_Compose_Mail.this, "Don't fill Blank Subject", Toast.LENGTH_SHORT).show();
                } else if (descriptiontext.getText().length() < 0) {
                    Toast.makeText(Parent_Compose_Mail.this, "Don't fill Blank Description", Toast.LENGTH_SHORT).show();
                } else {

                    doApiOpertion();

                    uploadFile();
                }

            }
              /*  if (filename.getText().length() > 0) {
                    if (subjecttext.length() < 0) {
                        Toast.makeText(Parent_Compose_Mail.this, "Don't fill Blank Subject", Toast.LENGTH_SHORT).show();
                    } else if (descriptiontext.length() < 0) {
                        Toast.makeText(Parent_Compose_Mail.this, "Don't fill Blank Description", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            uploadfile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        *//*doApiOpertion();*//*
                    }
                } else {
                    if (subjecttext.length() < 0) {
                        Toast.makeText(Parent_Compose_Mail.this, "Don't fill Blank Subject", Toast.LENGTH_SHORT).show();
                    } else if (descriptiontext.length() < 0) {
                        Toast.makeText(Parent_Compose_Mail.this, "Don't fill Blank Description", Toast.LENGTH_SHORT).show();
                    } else {

                        doApiOpertion();
                    }
                }
*/


        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Parent_Compose_Mail.super.onBackPressed();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("parent_admin_id", "");
                editor.putString("parent_admin_name", "");
                editor.putString("parent_class_teacher_id", "");
                editor.putString("parent_class_teacher_name", "");
                editor.putString("parent_subject_teacher_id", "");
                editor.putString("parent_subject_teacher_name", "");
                editor.commit();
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Parent_Compose_Mail.this, FilePickerActivity.class);
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



    private void doApiOpertion() {
        String id = "";
try {

    to_a_id = getIntent().getStringExtra("get_parent_admin_id");
    to_c_id = getIntent().getStringExtra("get_parent_class_teacher_id");
    to_s_id = getIntent().getStringExtra("get_parent_subject_teacher_id");


    if (to_a_id.length() <= 1 && to_c_id.length() <= 1 && to_s_id.length() <= 1) {
        totext.setText("");

    }
    if (to_a_id.length() > 1 && to_c_id.length() > 1 && to_s_id.length() > 1) {

        to_c_id = to_c_id.substring(1, to_c_id.length() - 1);
        id = to_a_id + to_c_id + to_s_id;
        id = id.substring(1, id.length() - 1);


    }
    if (to_a_id.length() > 1 && to_c_id.length() <= 1 && to_s_id.length() <= 1) {
        to_a_id = to_a_id.substring(1, to_a_id.length() - 1);
        id = to_a_id;

    }
    if (to_a_id.length() <= 1 && to_c_id.length() <= 1 && to_s_id.length() > 1) {
        to_s_id = to_s_id.substring(1, to_s_id.length() - 1);
        id = to_s_id;


    }
    if (to_a_id.length() > 1 && to_c_id.length() > 1 && to_s_id.length() <= 1) {
        to_a_id = to_a_id.substring(1, to_a_id.length() - 1);
        to_c_id = to_c_id.substring(0, to_c_id.length() - 1);
        id = to_a_id + to_c_id;


    }

    if (to_a_id.length() > 1 && to_c_id.length() <= 1 && to_s_id.length() > 1) {
        to_a_id = to_a_id.substring(1, to_a_id.length() - 1);
        to_s_id = to_s_id.substring(0, to_s_id.length() - 1);
        id = to_a_id + to_s_id;


    }

    if (to_a_id.length() <= 1 && to_c_id.length() > 1 && to_s_id.length() > 1) {
        to_c_id = to_c_id.substring(1, to_c_id.length() - 1);
        to_s_id = to_s_id.substring(0, to_s_id.length() - 1);
        id = to_c_id + to_s_id;


    }
    if (to_a_id.length() <= 1 && to_c_id.length() > 1 && to_s_id.length() <= 1) {
        to_c_id = to_c_id.substring(1, to_c_id.length() - 1);
        id = to_c_id;


    }
}
catch (Exception e)
{

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


            String subject = subjecttext.getText().toString();
            String message = descriptiontext.getText().toString();
            Call<Userbean_Parent_SendMail> compose_beanCall = apiInterfce.getParentSendMail(preferences.getString(getString(R.string.lUPIdNo), ""), id, subject, message, filename, preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));
            compose_beanCall.enqueue(new Callback<Userbean_Parent_SendMail>() {
                @Override
                public void onResponse(Call<Userbean_Parent_SendMail> call, Response<Userbean_Parent_SendMail> response) {
                    if (response.code() == 200) {
                        Userbean_Parent_SendMail userbean_send = response.body();
                        totext.setText("");
                        subjecttext.setText("");
                        descriptiontext.setText("");
                        Toast.makeText(Parent_Compose_Mail.this, response.body().getData() + "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Parent_Compose_Mail.this, "Mail doesn't Sent", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Userbean_Parent_SendMail> call, Throwable t) {
                    Toast.makeText(Parent_Compose_Mail.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (Exception e) {

        }


        String nMassage = subjecttext.getText().toString();
        Call<Userbean_Notification_Communication> notificationCall = apiInterfce.getParentSendNotificationComunication(id, "New Massage : " + nMassage, "4", "Communication", preferences.getString(getString(R.string.School_Name),""), preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));
        notificationCall.enqueue(new Callback<Userbean_Notification_Communication>() {
            @Override
            public void onResponse(Call<Userbean_Notification_Communication> call, Response<Userbean_Notification_Communication> response) {
                if (response.code() == 200) {

                } else {


                }

            }

            @Override
            public void onFailure(Call<Userbean_Notification_Communication> call, Throwable t) {

            }
        });






    }

    private void init() {
        fromtext = findViewById(R.id.fromtextparent);
        totext = findViewById(R.id.totextparent);
        subjecttext = findViewById(R.id.subjecttextparent);
        descriptiontext = findViewById(R.id.descriptiontextparent);
        getContact = findViewById(R.id.getparent);
        submitcommunication = findViewById(R.id.submitcommunicationparent);
        resetparent = findViewById(R.id.resetcommunicationparentparent);
        preferences = PreferenceManager.getDefaultSharedPreferences(Parent_Compose_Mail.this);
        back_button = findViewById(R.id.back_button);
        fromtext.setText(preferences.getString("sStudName", ""));
        upload = findViewById(R.id.btn_upload);
        filenames = findViewById(R.id.file_name);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        str1 = findViewById(R.id.file_name);


        try {
            totext_admin = getIntent().getStringExtra("get_parent_admin_name");
            totext_class_teacher = getIntent().getStringExtra("get_parent_class_teacher_name");
            totext_subject_teacher = getIntent().getStringExtra("get_parent_subject_teacher_name");


            if (totext_admin.length() <= 1 && totext_class_teacher.length() <= 1 && totext_subject_teacher.length() <= 1) {
                totext.setText("");

            }
            if (totext_admin.length() > 1 && totext_class_teacher.length() > 1 && totext_subject_teacher.length() > 1) {

                totext_class_teacher = totext_class_teacher.substring(1, totext_class_teacher.length() - 1);
                final_names = totext_admin + totext_class_teacher + totext_subject_teacher;
                final_names = final_names.substring(1, final_names.length() - 1);
                totext.setText(final_names);
            }
            if (totext_admin.length() > 1 && totext_class_teacher.length() <= 1 && totext_subject_teacher.length() <= 1) {
                totext_admin = totext_admin.substring(1, totext_admin.length() - 1);
                final_names = totext_admin;
                totext.setText(final_names);
            }
            if (totext_admin.length() <= 1 && totext_class_teacher.length() <= 1 && totext_subject_teacher.length() > 1) {
                totext_subject_teacher = totext_subject_teacher.substring(1, totext_subject_teacher.length() - 1);
                final_names = totext_subject_teacher;
                totext.setText(final_names);


            }
            if (totext_admin.length() > 1 && totext_class_teacher.length() > 1 && totext_subject_teacher.length() <= 1) {
                totext_admin = totext_admin.substring(1, totext_class_teacher.length() - 1);
                totext_class_teacher = totext_class_teacher.substring(0, totext_class_teacher.length() - 1);
                final_names = totext_admin + totext_class_teacher;
                totext.setText(final_names);

            }

            if (totext_admin.length() > 1 && totext_class_teacher.length() <= 1 && totext_subject_teacher.length() > 1) {
                totext_admin = totext_admin.substring(1, totext_admin.length() - 1);
                totext_subject_teacher = totext_subject_teacher.substring(0, totext_subject_teacher.length() - 1);
                final_names = totext_admin + totext_subject_teacher;
                totext.setText(final_names);

            }

            if (totext_admin.length() <= 1 && totext_class_teacher.length() > 1 && totext_subject_teacher.length() > 1) {
                totext_class_teacher = totext_class_teacher.substring(1, totext_class_teacher.length() - 1);
                totext_subject_teacher = totext_subject_teacher.substring(0, totext_subject_teacher.length() - 1);
                final_names = totext_class_teacher + totext_subject_teacher;
                totext.setText(final_names);

            }
            if (totext_admin.length() <= 1 && totext_class_teacher.length() > 1 && totext_subject_teacher.length() <= 1) {
                totext_class_teacher = totext_class_teacher.substring(1, totext_class_teacher.length() - 1);
                final_names = totext_class_teacher;
                totext.setText(final_names);

            }


        } catch (Exception e) {


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
