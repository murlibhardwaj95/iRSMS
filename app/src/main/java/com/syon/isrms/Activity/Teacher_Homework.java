package com.syon.isrms.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nononsenseapps.filepicker.FilePickerActivity;
import com.nononsenseapps.filepicker.Utils;
import com.syon.isrms.Beans.FillClassBean;
import com.syon.isrms.Beans.FillSectionBean;
import com.syon.isrms.Beans.FillSubjectBean;
import com.syon.isrms.Beans.Fill_Class_Data;
import com.syon.isrms.Beans.Fill_Section_Data;
import com.syon.isrms.Beans.Fill_Subject_Data;
import com.syon.isrms.Beans.Userbean_Save;
import com.syon.isrms.HelperClasses.PostMultipartNews;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Homework extends AppCompatActivity {
    ArrayList<String> sub = new ArrayList<>();
    ArrayList<String> sub_list_id = new ArrayList<String>();
    ArrayList<String> class_name = new ArrayList<>();
    ArrayList<String> class_list_id = new ArrayList<String>();
    ArrayList<String> fill_section = new ArrayList<>();
    ArrayList<String> fill_section_id = new ArrayList<String>();
    ApiInterfce apiInterfce;
    Calendar homeworkFrom = Calendar.getInstance();
    Calendar completeto = Calendar.getInstance();
    SharedPreferences preferences;
    String subject_id, class_id, section_id, sub_name, class_Name, section_Name;
    ImageView back_button, cal1, cal2;
    EditText homework_description, homework_title;
    EditText homework_date, completion_date;
    ImageView attachment1, attachment2, attachment3, attachment4;
    TextView textFile1, textFile2, textFile3, textFile4;
    Button btn_view, btn_save;
    Spinner spinnner_subject, spinner_class, spinner_section;
    private static final int PICKFILE_RESULT_CODE = 1;
    Toast toast;
    File file;
    String filename;
    ImageView attach_file1;
    EditText homeworkUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__homework);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        doApiOperation();

    }

    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<FillSubjectBean> call = apiInterfce.getFillSubject(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<FillSubjectBean>() {
                @Override
                public void onResponse(Call<FillSubjectBean> call, Response<FillSubjectBean> response) {
                    if (response.code() == 200) {
                        FillSubjectBean fillSubjectBean = response.body();
                        List<Fill_Subject_Data> fill_subject_data = fillSubjectBean.getData();

                        for (int i = 0; i < fill_subject_data.size(); i++) {
                            sub_list_id.add(fill_subject_data.get(i).getLSubjectId().toString());
                            sub.add(fill_subject_data.get(i).getSSubject());

                        }
                        ArrayAdapter<String> adaptersub = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sub);
                        adaptersub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnner_subject.setAdapter(adaptersub);
                        spinnner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spinner_class.setEnabled(true);
                                class_list_id.clear();
                                class_name.clear();
                                sub_name = sub.get(position).toString();
                                subject_id = sub_list_id.get(position).toString();
                                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                                apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                                Call<FillClassBean> call = apiInterfce.getFillClass(preferences.getString(getString(R.string.EmpId), ""), sub_list_id.get(position), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
                                call.enqueue(new Callback<FillClassBean>() {
                                    @Override
                                    public void onResponse(Call<FillClassBean> call, Response<FillClassBean> response) {
                                        if (response.code() == 200) {
                                            FillClassBean classBean = response.body();
                                            List<Fill_Class_Data> fill_class_data = classBean.getData();
                                            for (int i = 0; i < fill_class_data.size(); i++) {
                                                class_list_id.add(fill_class_data.get(i).getLClassId().toString());
                                                class_name.add(fill_class_data.get(i).getSClassName());
                                            }
                                            ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, class_name);
                                            adapter_class.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            spinner_class.setAdapter(adapter_class);
                                            spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    spinner_section.setEnabled(true);
                                                    class_Name = class_name.get(position).toString();
                                                    class_id = class_list_id.get(position).toString();
                                                    fill_section.clear();
                                                    fill_section_id.clear();
                                                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                                                    apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                                                    Call<FillSectionBean> call = apiInterfce.getFillSection(preferences.getString(getString(R.string.EmpId), ""), sub_list_id.get(position), class_list_id.get(position), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
                                                    call.enqueue(new Callback<FillSectionBean>() {
                                                        @Override
                                                        public void onResponse(Call<FillSectionBean> call, Response<FillSectionBean> response) {
                                                            if (response.code() == 200) {
                                                                FillSectionBean fillSectionBean = response.body();
                                                                List<Fill_Section_Data> fill_section_data = fillSectionBean.getData();
                                                                for (int i = 0; i < fill_section_data.size(); i++) {
                                                                    fill_section.add(fill_section_data.get(i).getSSection().toString());
                                                                    fill_section_id.add(fill_section_data.get(i).getLSecId().toString());
                                                                }
                                                                ArrayAdapter<String> adapter_section = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, fill_section);
                                                                adapter_section.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                                spinner_section.setAdapter(adapter_section);
                                                                spinner_section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                    @Override
                                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                        section_Name = fill_section.get(position).toString();
                                                                        section_id = fill_section_id.get(position).toString();
                                                                    }

                                                                    @Override
                                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                                    }
                                                                });
                                                            } else {
                                                                Toast.makeText(Teacher_Homework.this, "Data not found", Toast.LENGTH_SHORT).show();
                                                            }

                                                        }

                                                        @Override
                                                        public void onFailure(Call<FillSectionBean> call, Throwable t) {
                                                            Toast.makeText(Teacher_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });
                                        } else {
                                            Toast.makeText(Teacher_Homework.this, "Data not found", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<FillClassBean> call, Throwable t) {
                                        Toast.makeText(Teacher_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }


                }

                @Override
                public void onFailure(Call<FillSubjectBean> call, Throwable t) {
                    Toast.makeText(Teacher_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
        }
    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Homework.this);
        back_button = findViewById(R.id.back_button);
        homework_date = findViewById(R.id.selecthomeworkdate);
        completion_date = findViewById(R.id.selectcompletiondate);
        homework_description = findViewById(R.id.homeworkdiscription);
        homework_title = findViewById(R.id.homeworktitle);
        spinnner_subject = findViewById(R.id.spinnersubject);
        spinner_class = findViewById(R.id.spinnerclass);
        spinner_section = findViewById(R.id.spinnersection);
        spinner_class.setEnabled(false);
        spinner_section.setEnabled(false);
        cal1 = findViewById(R.id.cal1);
        cal2 = findViewById(R.id.cal2);
        btn_save = findViewById(R.id.btn_save);
        btn_view = findViewById(R.id.btn_view);
        attach_file1 = findViewById(R.id.attach_file1);
        homeworkUpload = findViewById(R.id.homeworkUpload);
        doOperation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Homework.super.onBackPressed();
            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                homeworkFrom.set(Calendar.YEAR, year);
                homeworkFrom.set(Calendar.MONTH, monthOfYear);
                homeworkFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (completeto.before(homeworkFrom)) {
                    Toast.makeText(Teacher_Homework.this, "Home Work data is never greater than Completion date", Toast.LENGTH_SHORT).show();
                    homework_date.setText(null);

                } else {
                    updateLabel();
                }
            }

        };
        homework_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(Teacher_Homework.this, date, homeworkFrom
                        .get(Calendar.YEAR), homeworkFrom.get(Calendar.MONTH),
                        homeworkFrom.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                completeto.set(Calendar.YEAR, year);
                completeto.set(Calendar.MONTH, monthOfYear);
                completeto.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (completeto.before(homeworkFrom)) {
                    Toast.makeText(Teacher_Homework.this, "Completion date is always greater than Home Work date", Toast.LENGTH_SHORT).show();
                } else {
                    updateLabel1();
                }
            }

        };
        completion_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Teacher_Homework.this, date1, completeto
                        .get(Calendar.YEAR), completeto.get(Calendar.MONTH),
                        completeto.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title, description, from, to;
                from = homework_date.getText().toString().trim();
                to = completion_date.getText().toString().trim();
                title = homework_title.getText().toString().trim();
                description = homework_description.getText().toString().trim();

                if (from.toString().equals("")) {
                    Toast.makeText(Teacher_Homework.this, "Enter Home Work Date", Toast.LENGTH_SHORT).show();
                } else if (to.toString().equals("")) {
                    Toast.makeText(Teacher_Homework.this, "Enter Completion Date", Toast.LENGTH_SHORT).show();
                } else if (title.toString().equals("")) {
                    Toast.makeText(Teacher_Homework.this, "Enter Titile", Toast.LENGTH_SHORT).show();
                } else if (description.toString().equals("")) {
                    Toast.makeText(Teacher_Homework.this, "Enter Description", Toast.LENGTH_SHORT).show();
                } else {

                    doHomeworkUpload();
                    uploadHomework();
                }
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher_Homework.this, Teacher_View_Homework.class));
            }
        });
        attach_file1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectUploadFile();
            }
        });

    }

    private void uploadHomework() {
        PostMultipartNews postMultipart = new PostMultipartNews();
        String url = preferences.getString("BASE_URL", "");

        try {
            postMultipart.run(url, "HomeWork", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectUploadFile() {
        Intent i = new Intent(Teacher_Homework.this, FilePickerActivity.class);
        i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
        i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
        i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);

        i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());

        startActivityForResult(i, 1010);
    }

    private void doHomeworkUpload() {
        if (file != null) {
            filename = file.getName().toString();
        } else {
            filename = "";
        }


        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);

            Call<Userbean_Save> call = apiInterfce.save(preferences.getString(getString(R.string.EmpId), ""), homework_date.getText().toString(), completion_date.getText().toString(), subject_id, sub_name, class_id, class_Name, section_id, section_Name, homework_title.getText().toString(), homework_description.getText().toString(), filename, "", "", "", preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<Userbean_Save>() {
                @Override
                public void onResponse(Call<Userbean_Save> call, Response<Userbean_Save> response) {

                    if (response.code() == 200) {

                        Toast.makeText(Teacher_Homework.this, response.body().getData() + "", Toast.LENGTH_SHORT).show();


                        homework_date.setText("");
                        completion_date.setText("");
                        homework_title.setText("");
                        homework_description.setText("");

                    } else {
                        Toast.makeText(Teacher_Homework.this, "Data Not Found", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<Userbean_Save> call, Throwable t) {
                    Toast.makeText(Teacher_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
            final String bodymsg = "New Homework for " + sub_name + " Subject is Added. Click for more details.";
            OkHttpClient.Builder builder1 = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder1).create(ApiInterfce.class);
            Call<com.syon.isrms.Beans.Userbean_homework_Notification> hw = apiInterfce.notifyhomework(class_id, section_id, "" + bodymsg, "3", "HomeWork", preferences.getString(getString(R.string.School_Name), ""), preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            hw.enqueue(new Callback<com.syon.isrms.Beans.Userbean_homework_Notification>() {
                @Override
                public void onResponse(Call<com.syon.isrms.Beans.Userbean_homework_Notification> call, Response<com.syon.isrms.Beans.Userbean_homework_Notification> response) {


                    if (response.code() == 200) {

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<com.syon.isrms.Beans.Userbean_homework_Notification> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            Toast.makeText(Teacher_Homework.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateLabel1() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        completion_date.setText(sdf.format(completeto.getTime()));
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        homework_date.setText(sdf.format(homeworkFrom.getTime()));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1010 && resultCode == Activity.RESULT_OK) {
            // Use the provided utility method to parse the result
            List<Uri> files = Utils.getSelectedFilesFromResult(intent);
            for (Uri uri : files) {
                file = Utils.getFileForUri(uri);
                // Do something with the result...
            }
            homeworkUpload.setText(file.getName().toString());

        }
    }


}
