package com.syon.isrms.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Beans.AdminLoginBean;
import com.syon.isrms.Beans.Admin_Login_Bean_Data;
import com.syon.isrms.Beans.LoginBean;
import com.syon.isrms.Beans.LoginData;
import com.syon.isrms.Home;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.Model_Class.ModelNews;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;
import com.syon.isrms.Utilty.MyToast;
import com.syon.isrms.service.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {
    ApiInterfce apiInterfce;
    Spinner spinner;
    String[] plants = new String[]{
            "Select Login As",
            "Student",
            "Parent",
            "Admin"
    };
    ArrayList<String> countr = new ArrayList<>();
    ArrayList<HashMap<String, String>> country_list = new ArrayList<>();
    Context ctx = this;
    TextView forgot, schName;
    Button login;
    EditText DOB;
    public static String tocken = "";
    BroadcastReceiver mRegistrationBroadcastReceiver;


    Dialog dialog, dialog0;
    private static final String TAG = Login.class.getSimpleName();
    String lSessionId;
    EditText username, password;
    ModelNews data;
    private Calendar mcalendar = Calendar.getInstance();

    int day, month, year;
    String lUPIdNo;
    ArrayList<ModelNews> list = new ArrayList<>();
    String sSchCode, logoStr, schname;
    ImageView logo;
    private SharedPreferences mPrefs;
    SharedPreferences teacherPreference;
    SharedPreferences log_data;
    Button select_school_lable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        day = mcalendar.get(Calendar.DAY_OF_MONTH);
        year = mcalendar.get(Calendar.YEAR);
        month = mcalendar.get(Calendar.MONTH);
        forgot = (TextView) findViewById(R.id.forgot);
        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        logo = (ImageView) findViewById(R.id.logo);
        schName = (TextView) findViewById(R.id.login_title);
        select_school_lable=findViewById(R.id.select_school_lable);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        log_data=PreferenceManager.getDefaultSharedPreferences(Login.this);

        teacherPreference=PreferenceManager.getDefaultSharedPreferences(Login.this);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    //   txtMessage.setText(message);
                }
            }
        };
        select_school_lable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,iSRMS_Choose_School.class));
                SharedPreferences.Editor editor=log_data.edit();
                editor.putString("isrms_screen","");
                editor.putInt("log",5);

                editor.commit();

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  startActivity(new Intent(Login.this, Home.class));
                overridePendingTransition(R.anim.slide_left, R.anim.slide_left);*/

                if (username.getText().toString().length() == 0) {

                    Toast.makeText(ctx, "Enter User Name", Toast.LENGTH_SHORT).show();

                }
                if (password.getText().toString().length() == 0) {
                    Toast.makeText(ctx, "Enter Password", Toast.LENGTH_SHORT).show();

                }

                if (spinner.getSelectedItem().toString().trim() == "Select Login As") {
                    Toast.makeText(Login.this, "Select Login As", Toast.LENGTH_SHORT).show();
                }

                if (isNetworkConnected()) {
                    //  startActivity(new Intent(getApplicationContext(),MainActivityNavigation.class));
                    // new LoginAsync().execute();
                    if (spinner.getSelectedItem().equals("Teacher")) {
                        teacherLogin();
                    } else if (spinner.getSelectedItem().equals("Parent")) {
                        LoginM();
                    }
                    else if (spinner.getSelectedItem().equals("Admin")) {
                        adminLogin();
                    }


                   /* Intent i=new Intent(Login_Activity.this,MainActivity.class);
                    startActivity(i);*/
                } else {
                    Toast.makeText(ctx, "Internet not found please check data connection or try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog0 = new Dialog(ctx);
                dialog0.setContentView(R.layout.alertdialogfundtransfer);
                dialog0.setTitle("Forget Password");
                ImageView cancel = (ImageView) dialog0.findViewById(R.id.cancel);
                final EditText Admissionno = (EditText) dialog0.findViewById(R.id.Admissionno);
                DOB = (EditText) dialog0.findViewById(R.id.DOB);
                final EditText mobile = (EditText) dialog0.findViewById(R.id.mobile);
                final EditText email = (EditText) dialog0.findViewById(R.id.email);
                Button submit = (Button) dialog0.findViewById(R.id.submit);

                DOB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DateDialog();

                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String Admissio = Admissionno.getText().toString();
                        String DO = DOB.getText().toString();
                        String mobil = mobile.getText().toString();

                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        final String em = email.getText().toString();

                        if (Admissionno.getText().toString().length() == 0) {
                            Toast.makeText(ctx, "Enter Valid Admission No ", Toast.LENGTH_SHORT).show();

                        } else if (DOB.getText().toString().length() == 0) {
                            Toast.makeText(ctx, "Enter Valid DOB", Toast.LENGTH_SHORT).show();
                        } else if (mobile.getText().toString().length() < 10) {
                            Toast.makeText(ctx, "Enter Valid Mobile No", Toast.LENGTH_SHORT).show();
                        } else if (em.matches(emailPattern)) {
                            ForgetPass(lUPIdNo, lSessionId, sSchCode, em, Admissio, DO, mobil);
                            //  Toast.makeText(ctx,"Enter Valid Email ID", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog0.dismiss();
                    }
                });
                dialog0.show();
            }
        });

        spinner = (Spinner) findViewById(R.id.sp1);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId = obj.getWebSessionId();
        sSchCode = obj.getsSch_Code();
        logoStr = obj.getSchoolLogo();
        schname = obj.getsSch_Name();
        lUPIdNo = mPrefs.getString("lUPIdNo", "");


        Picasso.with(getApplication())
                .load(logoStr)
                .into(logo);

        schName.setText(schname);

        getLOginType(lSessionId, sSchCode);


     /*   ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plants
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#DEC6B0"));
        }

    }

    private void adminLogin() {
        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        String user_name = username.getText().toString();
        String pass = password.getText().toString();
        String login_type = spinner.getSelectedItem().toString();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        final Call<AdminLoginBean> beanCall = apiInterfce.getAdminLogin(user_name, pass, login_type, sSchCode);
        beanCall.enqueue(new Callback<AdminLoginBean>() {
            @Override
            public void onResponse(Call<AdminLoginBean> call, retrofit2.Response<AdminLoginBean> response) {
                try {


                        if (response.code()==200) {
                            pd.dismiss();
                            Toast.makeText(ctx, "Login Successfully", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor=log_data.edit();
                            editor.putInt("log",4);
                            editor.commit();
                            String adminUserName,admin_name, admin_photo, admin_department, emp_id,empCode, school_code, session_id,adminDOB,adminJoinDate;
                            Admin_Login_Bean_Data data= response.body().getData();
                            adminUserName=data.getSUserName().toString();
                            admin_name=data.getSEmpName().toString();
                            admin_photo=data.getSAdminPhoto().toString();
                            admin_department=data.getSDeptName();
                            emp_id=data.getLEmpIdNo().toString();
                            empCode=data.getSEmpCode().toString();
                            school_code=data.getSSchCode().toString();
                            session_id=data.getLSessionIdNo().toString();
                            adminDOB=data.getSDOB().toString();
                            adminJoinDate=data.getSJoinDate().toString();
                            teacherPreference=PreferenceManager.getDefaultSharedPreferences(Login.this);
                            SharedPreferences.Editor teacher_editor = teacherPreference.edit();
                            teacher_editor.putString(getString(R.string.adminUserName), adminUserName);
                            teacher_editor.putString(getString(R.string.adminEmpName), admin_name);
                            teacher_editor.putString(getString(R.string.adminPhoto), admin_photo);
                            teacher_editor.putString(getString(R.string.adminDeptName), admin_department);
                            teacher_editor.putString(getString(R.string.adminEmpIdNo), emp_id);
                            teacher_editor.putString(getString(R.string.adminEmpCode), empCode);
                            teacher_editor.putString(getString(R.string.adminSchCode), school_code);
                            teacher_editor.putString(getString(R.string.adminSessionIdNo), session_id);
                            teacher_editor.putString(getString(R.string.adminDOB), adminDOB);
                            teacher_editor.putString(getString(R.string.adminJoinDate), adminJoinDate);
                            teacher_editor.commit();
                            startActivity(new Intent(Login.this, Admin_Dashboard.class));
                            finish();

                        }
                        else
                        {
                            pd.dismiss();
                            Toast.makeText(ctx, "username or password wrong", Toast.LENGTH_SHORT).show();
                        }


                } catch (Exception e) {
                    pd.dismiss();

                    Toast.makeText(ctx, "username or password wrong", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<AdminLoginBean> call, Throwable t) {
                pd.hide();
                dialog0.dismiss();
                Toast.makeText(ctx, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });

            }

    private void teacherLogin() {
        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        String user_name = username.getText().toString();
        String pass = password.getText().toString();
        String login_type = spinner.getSelectedItem().toString();
        /*teacherPreference=PreferenceManager.getDefaultSharedPreferences(Login.this);*/
        /*SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);*/
        String token_no=teacherPreference.getString(getString(R.string.FCM_TOKEN),"");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        final Call<LoginBean> beanCall = apiInterfce.getLoginData(user_name, pass, login_type, sSchCode, "", token_no);
        beanCall.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, retrofit2.Response<LoginBean> response) {

                try {

                    if (response.code()==200)
                    {
                        if (response.body().getStatus().equals("ok")) {
                            pd.dismiss();
                            Toast.makeText(ctx, "Login Successfully", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor=log_data.edit();
                            editor.putInt("log",3);
                            editor.commit();

                            String teacher_name, teacher_photo, teacher_designation, teacher_department, emp_id, school_code, session_id, department_id, nType;
                            LoginData loginData = response.body().getData();
                            teacher_name = loginData.getSEmpName().toString();
                            teacher_photo = loginData.getSTeacherPhoto().toString();
                            teacher_department = loginData.getSDeptName().toString();
                            emp_id = loginData.getLEmpIdNo().toString();
                            school_code = loginData.getSSchCode().toString();
                            session_id = loginData.getLSessionIdNo().toString();
                            department_id = loginData.getLDeptIdNo().toString();
                            nType = "2";
                            teacherPreference = PreferenceManager.getDefaultSharedPreferences(Login.this);
                            SharedPreferences.Editor teacher_editor = teacherPreference.edit();
                            teacher_editor.putString(getString(R.string.EmpName), teacher_name);
                            teacher_editor.putString(getString(R.string.TeacherPhoto), teacher_photo);
                            teacher_editor.putString(getString(R.string.DeptName), teacher_department);
                            teacher_editor.putString(getString(R.string.EmpId), emp_id);
                            teacher_editor.putString(getString(R.string.SchoolCode), school_code);
                            teacher_editor.putString(getString(R.string.SessionId), session_id);
                            teacher_editor.putString(getString(R.string.DeptIdNo), department_id);
                            teacher_editor.putString(getString(R.string.nType), nType);
                            teacher_editor.commit();
                            startActivity(new Intent(Login.this, Teacher_Dashboard.class));
                            finish();

                        }


                    }
                    else
                    {
                        pd.dismiss();
                        Toast.makeText(ctx, "username or password wrong", Toast.LENGTH_SHORT).show();
                    }



                } catch (Exception e) {
                    pd.dismiss();

                    Toast.makeText(ctx, "username or password wrong", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                pd.hide();
                dialog0.dismiss();
                Toast.makeText(ctx, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)) {
            tocken = regId;
        }

    }

    private void DateDialog() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DOB.setText(monthOfYear + 1 + "/" + dayOfMonth + "/" + year);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String dateString = dateFormat.format(calendar.getTime());

                DOB.setText(dateString);

            }
        };
        DatePickerDialog dpDialog = new DatePickerDialog(Login.this, listener, year, month, day);
        dpDialog.show();


    }

    private void ForgetPass(final String lUPIdNo, final String lSessionId, final String sSchCode, final String em, final String admissio, final String DO, final String mobil) {

        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, ApiClient.BASE_URL+"ForgotPassword",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        longLog(response);
                        Log.e("my response is", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getString("status").equalsIgnoreCase("ok")) {

                                Toast.makeText(ctx, "Send Successfully", Toast.LENGTH_SHORT).show();
                                pd.hide();
                                dialog0.dismiss();
                                //   ForgetAgain();

                            } else if (json.getString("status").equalsIgnoreCase("error")) {
                                Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();

                                pd.hide();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                            longLog("Json --------- Exception..." + e.toString());
                            pd.hide();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.hide();
                        Toast.makeText(ctx, "Incorrect User Credentials", Toast.LENGTH_SHORT).show();
                        // MyToast.show(Login.this,"", false);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("admno", admissio);
                map.put("dob", DO);
                map.put("mobile", mobil);
                map.put("email", em);
                map.put("sessionid", lSessionId);
                map.put("schoolcode", sSchCode);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }

    private void ForgetAgain() {

        dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.alertdialogfund);
        dialog.setTitle("Forget Password");
        final EditText oldpass = (EditText) dialog.findViewById(R.id.oldpass);
        ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
        final EditText newpass = (EditText) dialog.findViewById(R.id.newpass);
        final EditText newconf = (EditText) dialog.findViewById(R.id.newconf);
        Button submit = (Button) dialog.findViewById(R.id.submit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String oldpas = oldpass.getText().toString();
                String newpas = newpass.getText().toString();
                String newcon = newconf.getText().toString();


                if (oldpass.getText().toString().length() == 0) {
                    Toast.makeText(ctx, "Enter Old Password", Toast.LENGTH_SHORT).show();

                } else if (newpass.getText().toString().length() == 0) {
                    Toast.makeText(ctx, "Enter New Password", Toast.LENGTH_SHORT).show();
                } else if (newconf.getText().toString().length() == 0) {
                    Toast.makeText(ctx, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else {
                    ForgetPassNew(lUPIdNo, lSessionId, sSchCode, oldpas, newpas, newcon);
                    //  Toast.makeText(ctx,"Enter Valid Email ID", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dialog.show();

    }

    private void ForgetPassNew(final String lUPIdNo, final String lSessionId, String sSchCode, final String oldpas, final String newpas, final String newcon) {

        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.PUT, ApiClient.BASE_URL+"ChangePassword",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        longLog(response);
                        Log.e("my response is", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getString("status").equalsIgnoreCase("ok")) {
                                Toast.makeText(ctx, "Change Password Successfully", Toast.LENGTH_SHORT).show();
                                pd.hide();
                                dialog.dismiss();

                            } else if (json.getString("status").equalsIgnoreCase("error")) {
                                Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();

                                pd.hide();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                            longLog("Json --------- Exception..." + e.toString());
                            pd.hide();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.hide();
                        Toast.makeText(ctx, "Incorrect User Credentials", Toast.LENGTH_SHORT).show();
                        // MyToast.show(Login.this,"", false);
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("upid", lUPIdNo);
                params.put("oldpwd", oldpas);
                params.put("newpwd", newpas);
                params.put("confirmpwd", newcon);
                params.put("sessionid", "112");
                params.put("schoolcode", "MGS001");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void LoginM() {
        final String us = username.getText().toString();
        final String pa = password.getText().toString();
        final String type = spinner.getSelectedItem().toString();
        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Log.e("my response is", us);
        Log.e("my response is", pa);
        StringRequest request = new StringRequest(Request.Method.POST, ApiClient.BASE_URL+"UserParent",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        longLog(response);
                        Log.e("my response is", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getString("status").equalsIgnoreCase("ok")) {
                                JSONObject js = json.getJSONObject("data");
                                String lUPIdNo = js.getString("lUPIdNo");
                                String sStudName = js.getString("sStudName");
                                String sSession = js.getString("sSession");
                                String studPhoto = js.getString("studPhoto");
                                String lClass_IdNo = js.getString("lClass_IdNo");
                                String lSec_IdNo = js.getString("lSec_IdNo");
                                String nStudType = js.getString("nStudType");
                                String lStudIdNo=js.getString("lStudIdNo");
                                String lFac_IdNo=js.getString("lFac_IdNo");
                                String sSchCode=js.getString("sSchCode");
                                String lSessionIdNo=js.getString("lSessionIdNo");




                                Toast.makeText(ctx, "Logged in Successfully", Toast.LENGTH_SHORT).show();


                                startActivity(new Intent(ctx, Home.class));
                                overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
                                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                prefsEditor.putString(getString(R.string.lUPIdNo), lUPIdNo);
                                prefsEditor.putString("sStudName", sStudName);
                                prefsEditor.putString("sSession", sSession);
                                prefsEditor.putString("studPhoto", studPhoto);
                                prefsEditor.putString(getString(R.string.lClass_IdNo), lClass_IdNo);
                                prefsEditor.putString(getString(R.string.lSec_IdNo), lSec_IdNo);
                                prefsEditor.putString("nStudType", nStudType);
                                prefsEditor.putString(getString(R.string.lStudIdNo),lStudIdNo);
                                prefsEditor.putString("lFac_IdNo",lFac_IdNo);
                                prefsEditor.putString(getString(R.string.sSchCode),sSchCode);
                                prefsEditor.putString(getString(R.string.lSessionIdNo),lSessionIdNo);




                                SharedPreferences.Editor editor=log_data.edit();
                                editor.putInt("log",2);
                                editor.commit();
                             /*   SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("logged", "1");
                                editor.putString("isfirst", "1");
                                editor.putString("data", response);
                                editor.commit();*/
                                prefsEditor.commit();

                            } else if (json.getString("message").equalsIgnoreCase("An error has occurred.")) {
                                Toast.makeText(ctx, "Wrong Username or Password", Toast.LENGTH_SHORT).show();

                                pd.hide();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                            longLog("Json --------- Exception..." + e.toString());
                            pd.hide();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.hide();
                        Toast.makeText(ctx, "Incorrect User Credentials", Toast.LENGTH_SHORT).show();
                        // MyToast.show(Login.this,"", false);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user", us);
                map.put("pwd", pa);
                map.put("logintype", type);
                map.put("schoolcode", sSchCode);
                map.put("deviceid", "");
                map.put("tokenno", teacherPreference.getString(getString(R.string.FCM_TOKEN),""));
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    private void getLOginType(String lSessionId, String sSchCode) {

        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"LoginType?lSessionId=" + lSessionId + "&&sSchCode=" + sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  longLog("Log in Resopnce------------------------------------------------"+response);
                        longLog("response---" + response);
                        if (country_list.size() > 0) {
                            country_list.clear();
                        }
                        try {
                            int selectPos = 0;
                            JSONObject object = new JSONObject(response);
                            if (object.getString("status").equals("ok")) {
                                JSONArray jsonArray = object.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("nSrNo", obj.getString("nSrNo"));
                                    map.put("sLoginType", obj.getString("sLoginType"));
                                    country_list.add(map);
                                }
                            }
                            if (countr.size() > 0) {
                                countr.clear();
                            }
                            countr.add("---Select Login As---");
                            for (int i = 0; i < country_list.size(); i++) {
                                HashMap<String, String> map = new HashMap<>(country_list.get(i));
                                countr.add(map.get("sLoginType"));
                            }
                            ArrayAdapter adpthree = new ArrayAdapter(Login.this, R.layout.spinner_it, R.id.text, countr);
                            spinner.setAdapter(adpthree);
                            pd.dismiss();


                        } catch (JSONException e) {
                            longLog("Json Exception" + e.toString());
                            pd.dismiss();
                            MyToast.show(Login.this, "Error in reading data Try Again !!!", false);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        // pd.dismiss();
                        MyToast.show(Login.this, "Connection Time Out Try Again Later !!", false);

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    public static void longLog(String str) {
        if (str.length() > 4000) {
            Log.d("", str.substring(0, 4000));
            longLog(str.substring(4000));
        } else
            Log.d("", str);
    }


       /* final ApiIHandler apiService =
                ApiClient.getClient().create(ApiIHandler.class);
        final ProgressDialog pDialog = ProgressDialog.show(Login.this, "", "Please wait ...", true);
        Call<ModelNews> call = apiService.getLoginType(lSessionId, sSchCode);

        call.enqueue(new Callback<ModelNews>() {
            @Override
            public void onResponse(Call<ModelNews> call, retrofit2.Response<ModelNews> response) {
                 pDialog.dismiss();
                if (response.code() == 200) {
                  Toast.makeText(Login.this,"okay", Toast.LENGTH_SHORT).show();
                  data = response.body();
                  list.add(data);
                  showListinSpinner();
                  pDialog.hide();
                }
                else if (response.code()==400){
                    Toast.makeText(Login.this,"error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelNews> call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                 Toast.makeText(Login.this, getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });

*/

    private void showListinSpinner() {
        //String array to store all the book names
        String[] items = new String[list.size()];
        //Traversing through the whole list to get all the names
        for (int i = 0; i < list.size(); i++) {
            //Storing names to string array
            items[i] = list.get(i).getNEWS_ID();
        }

        //Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items);

        //setting adapter to spinner
        spinner.setAdapter(adapter);
        //Creating an array adapter for list view

    }
}
