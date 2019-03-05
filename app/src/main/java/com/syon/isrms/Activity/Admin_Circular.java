package com.syon.isrms.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;
import com.nononsenseapps.filepicker.Utils;
import com.syon.isrms.Adapter.Admin_News_Department_Selction_Adapter;
import com.syon.isrms.Adapter.Admin_News_Selction_Adapter;
import com.syon.isrms.Beans.AdminCircularBean;
import com.syon.isrms.Beans.AdminClassFillBean;
import com.syon.isrms.Beans.AdminDepartmentFillBean;
import com.syon.isrms.Beans.AdminNewsNotificationBean;
import com.syon.isrms.Beans.Admin_Class_Fill_Bean_Data;
import com.syon.isrms.Beans.Admin_Department_Fill_Bean_Data;
import com.syon.isrms.Beans.Userbean_Communication_Notification;
import com.syon.isrms.HelperClasses.PostMultipartNews;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Circular extends AppCompatActivity {
    ImageView back_button;
    EditText set_date, set_classes, set_departments, set_title, set_description, edt_news_upload;
    Button btn_view, btn_save, btn_select_classes, btn_select_department, select_upload;
    Calendar calendar = Calendar.getInstance();
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    String class_id;
    String class_name;
    String department_id;
    String department_name;
    File file;
    String filename;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__circular);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        back_button = findViewById(R.id.back_button);
        set_date = findViewById(R.id.lbl_select_date);
        set_classes = findViewById(R.id.edt_set_class);
        set_departments = findViewById(R.id.edt_set_department);
        set_title = findViewById(R.id.news_Title);
        set_description = findViewById(R.id.news_Description);
        btn_view = findViewById(R.id.btn_news_view);
        btn_save = findViewById(R.id.btn_news_save);
        btn_select_classes = findViewById(R.id.btn_select_classes);
        btn_select_department = findViewById(R.id.btn_select_department);
        select_upload = findViewById(R.id.btn_news_select_upload);
        edt_news_upload = findViewById(R.id.edt_news_upload);

        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Circular.this);

        doOperation();
    }
    private void uploadFile() {

        PostMultipartNews postMultipart=new PostMultipartNews();
        String url=preferences.getString("BASE_URL","");

        try {
            postMultipart.run(url,"Circulars",file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Circular.super.onBackPressed();
            }
        });
        btn_select_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_classes.setText("");
                final Dialog dialog;
                dialog = new Dialog(Admin_Circular.this);
                dialog.setContentView(R.layout.admin_news_class_dialog);
                dialog.setTitle("Select Class");
                ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
                final ProgressBar progressBar = dialog.findViewById(R.id.class_progress);
                Button select_dialog = dialog.findViewById(R.id.news_dialog_select);
                final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.admin_class_recycler);
                try {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
                    Call<AdminClassFillBean> call = apiInterfce.getAdminFillClass(preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
                    call.enqueue(new Callback<AdminClassFillBean>() {
                        @Override
                        public void onResponse(Call<AdminClassFillBean> call, Response<AdminClassFillBean> response) {

                            if (response.code() == 200) {
                                AdminClassFillBean adminClassFillBean = response.body();
                                List<Admin_Class_Fill_Bean_Data> data = adminClassFillBean.getData();
                                progressBar.setVisibility(View.GONE);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                                recyclerView.setLayoutManager(new GridLayoutManager(Admin_Circular.this, 3));
                                recyclerView.setAdapter(new Admin_News_Selction_Adapter(Admin_Circular.this, data));

                            } else {
                                Toast.makeText(Admin_Circular.this, "Data Not Found", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<AdminClassFillBean> call, Throwable t) {
                            Toast.makeText(Admin_Circular.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(Admin_Circular.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("get_class_id");
                        editor.remove("get_class_name");

                        editor.commit();
                    }
                });
                select_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("get_class_id");
                        editor.remove("get_class_name");
                        editor.commit();
                        class_id = preferences.getString("class_id", "");
                        class_name = preferences.getString("class_name", "");
                        if (TextUtils.isEmpty(class_name)) {
                            set_classes.setText("select class");
                        } else {
                            class_name = class_name.substring(1, class_name.length() - 1);
                            set_classes.setText(class_name);
                        }


                        if (TextUtils.isEmpty(class_id)) {
                            set_classes.setText("select class");
                        } else {
                            class_id = class_id.substring(1, class_id.length() - 1);
                            set_classes.setText(class_name);
                        }


                    }
                });
                dialog.show();
            }
        });
        btn_select_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog;
                dialog = new Dialog(Admin_Circular.this);
                dialog.setContentView(R.layout.admin_news_department_dialog);
                dialog.setTitle("Select Department");
                ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
                Button select_dialog = dialog.findViewById(R.id.dialog_select_department);
                final ProgressBar progressBar=dialog.findViewById(R.id.department_progress);
                final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.admin_department_recycler);

                try {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
                    Call<AdminDepartmentFillBean> call = apiInterfce.getAdminFillDepartment(preferences.getString(getString(R.string.adminSchCode), ""));
                    call.enqueue(new Callback<AdminDepartmentFillBean>() {
                        @Override
                        public void onResponse(Call<AdminDepartmentFillBean> call, Response<AdminDepartmentFillBean> response) {

                            if (response.code() == 200) {
                                progressBar.setVisibility(View.GONE);
                                AdminDepartmentFillBean departmentFillBean = response.body();
                                List<Admin_Department_Fill_Bean_Data> data = departmentFillBean.getData();
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                                recyclerView.setLayoutManager(new GridLayoutManager(Admin_Circular.this, 3));
                                recyclerView.setAdapter(new Admin_News_Department_Selction_Adapter(Admin_Circular.this, data));

                            } else {
                                Toast.makeText(Admin_Circular.this, "Data Not Found", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<AdminDepartmentFillBean> call, Throwable t) {
                            Toast.makeText(Admin_Circular.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(Admin_Circular.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("get_department_id");
                        editor.remove("get_department_name");
                        editor.commit();
                    }
                });
                select_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("get_department_id");
                        editor.remove("get_department_name");
                        editor.commit();
                        department_id = preferences.getString("department_id", "");
                        department_name = preferences.getString("department_name", "");
                        if (TextUtils.isEmpty(department_name)) {
                            set_classes.setText("select department");
                        } else {
                            department_name = department_name.substring(1, department_name.length() - 1);
                            set_departments.setText(department_name);
                        }


                        if (TextUtils.isEmpty(department_id)) {
                            set_departments.setText("select class");
                        } else {
                            department_id = department_id.substring(1, department_id.length() - 1);
                            set_departments.setText(department_name);
                        }

                    }
                });
                dialog.show();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(set_date.getText().toString()))
                {
                    Toast.makeText(Admin_Circular.this, "please select date", Toast.LENGTH_SHORT).show();
                }
                else if (set_classes.getText().equals("select class") && set_departments.getText().equals("select department"))
                {
                    Toast.makeText(Admin_Circular.this, "please select class or department", Toast.LENGTH_SHORT).show();
                }

                else if (set_title.getText().length()<=0)
                {
                    Toast.makeText(Admin_Circular.this, "please fill title", Toast.LENGTH_SHORT).show();
                }
                else if (set_description.getText().length()<=0)
                {
                    Toast.makeText(Admin_Circular.this, "please fill description", Toast.LENGTH_SHORT).show();
                }

                else {
                    if(set_classes.getText().length()<=0 && set_departments.getText().length()>0)
                    {
                        postDepartmentCircular();
                        uploadFile();
                    }
                    else if (set_classes.getText().length()>0 && set_departments.getText().length()<=0)
                    {
                        postClassCircular();
                        uploadFile();
                    }
                    else if (set_classes.getText().length()<=0 && set_departments.getText().length()<=0)
                    {
                        Toast.makeText(Admin_Circular.this, "please select class or department", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        postClassCircular();
                        postDepartmentCircular();
                        uploadFile();
                    }
                }



            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Circular.this,Admin_View_Circulars.class));

            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }

        };
        set_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Admin_Circular.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        select_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin_Circular.this, FilePickerActivity.class);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);

                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());

                startActivityForResult(i, 1010);
            }
        });
    }

    private void postDepartmentCircular() {
        final String date=set_date.getText().toString();

        final String title=set_title.getText().toString();
        final String description=set_description.getText().toString();
        if (file!=null)
        {
            filename=file.getName().toString();
        }
        else {
            filename="";
        }


        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminCircularBean> call = apiInterfce.postCircular(preferences.getString(getString(R.string.adminEmpIdNo), ""),date,title,description,filename,preferences.getString(getString(R.string.adminEmpIdNo),""),"1",class_id,department_id,"2",preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminCircularBean>() {
                @Override
                public void onResponse(Call<AdminCircularBean> call, Response<AdminCircularBean> response) {

                    if (response.code()==200) {
                        AdminCircularBean circularBean = response.body();
                        Toast.makeText(Admin_Circular.this, response.body().getData().toString(), Toast.LENGTH_SHORT).show();
                        postDeptNotification();
                        set_date.setText("");
                        set_classes.setText("");
                        set_departments.setText("");
                        set_title.setText("");
                        set_description.setText("");

                    } else {
                        Log.d("response code",response.code()+"");
                        Log.d("debug",preferences.getString(getString(R.string.adminEmpIdNo), "")+" "+date+ " "+ title+ " " +description +" "+" "+preferences.getString(getString(R.string.adminEmpIdNo),"") + " 1"+ " " + class_id+" " +department_id+" " +"1 "+preferences.getString(getString(R.string.adminSessionIdNo), "")+ " "+ preferences.getString(getString(R.string.adminSchCode), "")+ "");
                        Toast.makeText(Admin_Circular.this, "Circular not published", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminCircularBean> call, Throwable t) {
                    Toast.makeText(Admin_Circular.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(Admin_Circular.this, "something went wrong try again after sometime", Toast.LENGTH_SHORT).show();
        }

    }

    private void postDeptNotification() {
        String title=set_title.getText().toString();
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminNewsNotificationBean> call = apiInterfce.postAdminNewsNotificationDept(department_id,title,"1","Circulars",preferences.getString(getString(R.string.School_Name),""),preferences.getString(getString(R.string.adminEmpIdNo),""),preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminNewsNotificationBean>() {
                @Override
                public void onResponse(Call<AdminNewsNotificationBean> call, Response<AdminNewsNotificationBean> response) {

                    if (response.code()==200) {


                    } else {


                    }
                }

                @Override
                public void onFailure(Call<AdminNewsNotificationBean> call, Throwable t) {
                    Toast.makeText(Admin_Circular.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(Admin_Circular.this, "something went wrong try again after sometime", Toast.LENGTH_SHORT).show();
        }
    }


    private void postNotification() {
        String title=set_title.getText().toString();
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminNewsNotificationBean> call = apiInterfce.postAdminNewsNotification(class_id,"Circulars","Circulars",title,preferences.getString(getString(R.string.School_Name),""),preferences.getString(getString(R.string.adminEmpIdNo),""),preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminNewsNotificationBean>() {
                @Override
                public void onResponse(Call<AdminNewsNotificationBean> call, Response<AdminNewsNotificationBean> response) {

                    if (response.code()==200) {


                    } else {


                    }
                }

                @Override
                public void onFailure(Call<AdminNewsNotificationBean> call, Throwable t) {
                    Toast.makeText(Admin_Circular.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(Admin_Circular.this, "something went wrong try again after sometime", Toast.LENGTH_SHORT).show();
        }
    }

    private void postClassCircular() {
        final String date=set_date.getText().toString();

        final String title=set_title.getText().toString();
        final String description=set_description.getText().toString();
        if (file!=null)
        {
            filename=file.getName().toString();
        }
        else {
            filename="";
        }


        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<AdminCircularBean> call = apiInterfce.postCircular(preferences.getString(getString(R.string.adminEmpIdNo), ""),date,title,description,filename,preferences.getString(getString(R.string.adminEmpIdNo),""),"1",class_id,department_id,"1",preferences.getString(getString(R.string.adminSessionIdNo), ""), preferences.getString(getString(R.string.adminSchCode), ""));
            call.enqueue(new Callback<AdminCircularBean>() {
                @Override
                public void onResponse(Call<AdminCircularBean> call, Response<AdminCircularBean> response) {

                    if (response.code()==200) {
                        AdminCircularBean circularBean = response.body();
                        Toast.makeText(Admin_Circular.this, response.body().getData().toString(), Toast.LENGTH_SHORT).show();
                        postNotification();
                        set_date.setText("");
                        set_classes.setText("");
                        set_departments.setText("");
                        set_title.setText("");
                        set_description.setText("");

                    } else {
                        Log.d("response code",response.code()+"");
                        Log.d("debug",preferences.getString(getString(R.string.adminEmpIdNo), "")+" "+date+ " "+ title+ " " +description +" "+" "+preferences.getString(getString(R.string.adminEmpIdNo),"") + " 1"+ " " + class_id+" " +department_id+" " +"1 "+preferences.getString(getString(R.string.adminSessionIdNo), "")+ " "+ preferences.getString(getString(R.string.adminSchCode), "")+ "");
                        Toast.makeText(Admin_Circular.this, "Circular not published", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AdminCircularBean> call, Throwable t) {
                    Toast.makeText(Admin_Circular.this, "Internet Connection Me be show or off", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            Toast.makeText(Admin_Circular.this, "something went wrong try again after sometime", Toast.LENGTH_SHORT).show();
        }

    }


    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        set_date.setText(sdf.format(calendar.getTime()));
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1010 && resultCode == Activity.RESULT_OK) {
            // Use the provided utility method to parse the result
            List<Uri> files = Utils.getSelectedFilesFromResult(intent);
            for (Uri uri : files) {
                file = Utils.getFileForUri(uri);
                // Do something with the result...
            }
            edt_news_upload.setText(file.getName().toString());

        }
    }


}
