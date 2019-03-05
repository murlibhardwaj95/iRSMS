package com.syon.isrms.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.makeramen.roundedimageview.RoundedImageView;
import com.syon.isrms.Adapter.GridAdepter;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.syon.isrms.Activity.Login.longLog;

public class Teacher_Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView teacherName, teacherDesignation, navHeadName, navHeadLastVisit;
    RoundedImageView teacherPic, navPic;
    SharedPreferences preferences;
    View view;
    ApiInterfce apiInterfce;
    TextView header_text;

    String[] name = {"My Profile", "Attendance Entry", "Marks Entry", "Gen.Remark Entry", "Co-Sch Remark Entry", "Home Work", "My Leaves", "Salary Slip", "My Attendance", "Class Attendance", "Result Analysis", "Library Status"};
    int[] image = {R.drawable.tprofile, R.drawable.attendance_entry,
            R.drawable.marks_entry, R.drawable.gen_remark_entry,
            R.drawable.co_sch_remark_entry, R.drawable.thome_work,
            R.drawable.my_leaves, R.drawable.salary_slip,
            R.drawable.my_attendance, R.drawable.class_attendance,
            R.drawable.result_analysis, R.drawable.library_status};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        view = navigationView.getHeaderView(0);

        View navFooter1 = findViewById(R.id.btn__teacher_sing_out);
        navFooter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Dashboard.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("log", 1);
                editor.commit();
                startActivity(new Intent(Teacher_Dashboard.this, Login.class));
                finish();
            }
        });

        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Dashboard.this);
        init();
        setData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("last_visit", getDateTime());
        editor.commit();
        finish();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void setData() {
        try {
            teacherName.setText(preferences.getString(getString(R.string.EmpName), "").toString());
            teacherDesignation.setText(preferences.getString(getString(R.string.DeptName), "").toString());
            Picasso.with(Teacher_Dashboard.this).load(preferences.getString(getString(R.string.TeacherPhoto), "").toString()).placeholder(R.drawable.avtar)
                    .error(R.drawable.avtar).resize(60, 60).centerInside().into(teacherPic);
            navHeadName.setText(preferences.getString(getString(R.string.EmpName), "").toString());
            header_text.setText(preferences.getString(getString(R.string.School_Name), "School Name"));
            navHeadLastVisit.setText("Last Visited : " + preferences.getString("last_visit", ""));
            Picasso.with(Teacher_Dashboard.this).load(preferences.getString(getString(R.string.TeacherPhoto), "").toString()).placeholder(R.drawable.avtar)
                    .error(R.drawable.avtar).resize(60, 60).centerInside().into(navPic);

        }
        catch (Exception e)
        {
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        }

    }

    private void init() {
        teacherName = findViewById(R.id.teacherName);
        teacherPic = findViewById(R.id.teacherPic);
        teacherDesignation = findViewById(R.id.teacherDesignation);
        navPic = view.findViewById(R.id.navPic);
        navHeadName = view.findViewById(R.id.navTeacherName);
        navHeadLastVisit = view.findViewById(R.id.navTeacherLoggedInTime);
        GridView gridView = findViewById(R.id.gridView);
        header_text =findViewById(R.id.header_text);
        gridView.setAdapter(new GridAdepter(this, R.layout.home_grid_layout, name, image));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                Teacher_Dashboard.super.onBackPressed();
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.teacher__dashboard, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            final Dialog dialog;
            dialog = new Dialog(Teacher_Dashboard.this);
            dialog.setContentView(R.layout.teacher_notification_dialog);
            dialog.setTitle("My Notifications");

            ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
            final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.notification_recycler);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<com.syon.isrms.Beans.Userbean_Notification_Main> callnotify = apiInterfce.notifyteacher(preferences.getString(getString(R.string.EmpId),""),preferences.getString(getString(R.string.SessionId),""),preferences.getString(getString(R.string.SchoolCode),""));
            callnotify.enqueue(new Callback<com.syon.isrms.Beans.Userbean_Notification_Main>() {
                @Override
                public void onResponse(Call<com.syon.isrms.Beans.Userbean_Notification_Main> call, Response<com.syon.isrms.Beans.Userbean_Notification_Main> response) {
                    if(response.code() == 200){

                        com.syon.isrms.Beans.Userbean_Notification_Main usermain = response.body();
                        List<com.syon.isrms.Beans.Usebean_Notification_Data> list1 = usermain.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_Dashboard.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new com.syon.isrms.Adapter.Notification_Recycle_Adapter(Teacher_Dashboard.this,list1));


                    }else {
                        Toast.makeText(Teacher_Dashboard.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<com.syon.isrms.Beans.Userbean_Notification_Main> call, Throwable t) {
                    Toast.makeText(Teacher_Dashboard.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_news) {
            startActivity(new Intent(getApplicationContext(), Teacher_News.class));

        } else if (id == R.id.nav_circular) {
            startActivity(new Intent(getApplicationContext(), Teacher_Circular.class));

        } else if (id == R.id.nav_calender) {
            startActivity(new Intent(Teacher_Dashboard.this, Teacher_Event_Calender.class));

        } else if (id == R.id.nav_communication) {
            startActivity(new Intent(Teacher_Dashboard.this, Teacher_Comunication.class));

        }
        else if (id == R.id.nav_birthday) {
            startActivity(new Intent(Teacher_Dashboard.this, Teacher_Birthday.class));

        }
        else if (id == R.id.nav_db) {
            startActivity(new Intent(Teacher_Dashboard.this, Teacher_Discusiion_Board.class));
        }
        else if(id == R.id.nav_share){
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
                Call<com.syon.isrms.Beans.UserbeanShareRate> call = apiInterfce.share();
                call.enqueue(new Callback<com.syon.isrms.Beans.UserbeanShareRate>() {
                    @Override
                    public void onResponse(Call<com.syon.isrms.Beans.UserbeanShareRate> call, Response<com.syon.isrms.Beans.UserbeanShareRate> response) {
                        if (response.code() == 200) {
                            com.syon.isrms.Beans.UserbeanShareRate data = response.body();
                            String stc =  data.getShareApp();
                            Intent sharingintent= new Intent(Intent.ACTION_SEND);
                            sharingintent.setType("text/plain");
                            sharingintent.putExtra(Intent.EXTRA_SUBJECT,"iSRMS");
                            sharingintent.putExtra(Intent.EXTRA_TEXT,""+stc);
                            startActivity(Intent.createChooser(sharingintent,"share via"));



                        } else {
                            Toast.makeText(Teacher_Dashboard.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.syon.isrms.Beans.UserbeanShareRate> call, Throwable t) {

                        Toast.makeText(Teacher_Dashboard.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){

                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }


        }
        else if(id == R.id.nav_rating){
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
                Call<com.syon.isrms.Beans.UserbeanShareRate> call = apiInterfce.share();
                call.enqueue(new Callback<com.syon.isrms.Beans.UserbeanShareRate>() {
                    @Override
                    public void onResponse(Call<com.syon.isrms.Beans.UserbeanShareRate> call, Response<com.syon.isrms.Beans.UserbeanShareRate> response) {
                        if (response.code() == 200) {
                            com.syon.isrms.Beans.UserbeanShareRate data = response.body();
                            String stc =  data.getRateUs();
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""+stc));
                            startActivity(browserIntent);


                        } else {
                            Toast.makeText(Teacher_Dashboard.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.syon.isrms.Beans.UserbeanShareRate> call, Throwable t) {

                        Toast.makeText(Teacher_Dashboard.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e){

                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }


        }

        else if (id == R.id.nav_t_downloads) {
            Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/iSRMS/");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(selectedUri, "*/*");
            try{
                startActivity(Intent.createChooser(intent,"open Downloads"));
                /*startActivity(intent);*/
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(Teacher_Dashboard.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        }
        /*else if (id == R.id.nav_sms) {

        }*/ else if (id == R.id.nav_notification) {
            final Dialog dialog;
            dialog = new Dialog(Teacher_Dashboard.this);
            dialog.setContentView(R.layout.teacher_notification_dialog);
            dialog.setTitle("My Notifications");

            ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
            final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.notification_recycler);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<com.syon.isrms.Beans.Userbean_Notification_Main> callnotify = apiInterfce.notifyteacher(preferences.getString(getString(R.string.EmpId),""),preferences.getString(getString(R.string.SessionId),""),preferences.getString(getString(R.string.SchoolCode),""));
            callnotify.enqueue(new Callback<com.syon.isrms.Beans.Userbean_Notification_Main>() {
                @Override
                public void onResponse(Call<com.syon.isrms.Beans.Userbean_Notification_Main> call, Response<com.syon.isrms.Beans.Userbean_Notification_Main> response) {
                    if(response.code() == 200){

                        com.syon.isrms.Beans.Userbean_Notification_Main usermain = response.body();
                        List<com.syon.isrms.Beans.Usebean_Notification_Data> list1 = usermain.getData();

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_Dashboard.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new com.syon.isrms.Adapter.Notification_Recycle_Adapter(Teacher_Dashboard.this,list1));


                    }else {
                        Toast.makeText(Teacher_Dashboard.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<com.syon.isrms.Beans.Userbean_Notification_Main> call, Throwable t) {
                    Toast.makeText(Teacher_Dashboard.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            });



            dialog.show();

        } /*else if (id == R.id.nav_gallary) {

        }*/ else if (id == R.id.nav_change_password) {
            final Dialog dialog;
            dialog = new Dialog(Teacher_Dashboard.this);
            dialog.setContentView(R.layout.teacher_change_password);
            dialog.setTitle("Change Password");
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

                    final String old_password = oldpass.getText().toString();
                    final String new_password = newpass.getText().toString();
                    final String conferm_password = newconf.getText().toString();


                    if (oldpass.getText().toString().length() == 0) {
                        Toast.makeText(Teacher_Dashboard.this, "Enter Old Password", Toast.LENGTH_SHORT).show();

                    } else if (newpass.getText().toString().length() == 0) {
                        Toast.makeText(Teacher_Dashboard.this, "Enter New Password", Toast.LENGTH_SHORT).show();
                    } else if (newconf.getText().toString().length() == 0) {
                        Toast.makeText(Teacher_Dashboard.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    } else {
                        final ProgressDialog pd = new ProgressDialog(Teacher_Dashboard.this);
                        pd.setMessage("Loading...");
                        pd.setCancelable(false);
                        pd.show();

                        StringRequest request= new StringRequest(Request.Method.PUT, com.syon.isrms.Interfaces_Teacher.ApiClient.BASE_URL+"TchrChangePassword",
                                new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        longLog(response);
                                        Log.e("my response is",response);
                                        try {
                                            JSONObject json=new JSONObject(response);
                                            if (json.getString("status").equalsIgnoreCase("ok")){
                                                Toast.makeText(Teacher_Dashboard.this,"Change Password Successfully", Toast.LENGTH_SHORT).show();
                                                pd.hide();
                                                dialog.dismiss();

                                                startActivity(new Intent(Teacher_Dashboard.this, Login.class));

                                                finish();

                                            } else if (json.getString("status").equalsIgnoreCase("error")){
                                                Toast.makeText(Teacher_Dashboard.this,"Error", Toast.LENGTH_SHORT).show();

                                                pd.hide();
                                            }

                                        }catch (JSONException e){
                                            Toast.makeText(Teacher_Dashboard.this,"Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                                            longLog("Json --------- Exception..."+e.toString());
                                            pd.hide();

                                        }
                                    }
                                },
                                new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        pd.hide();
                                        Toast.makeText(Teacher_Dashboard.this,"Incorrect User Credentials", Toast.LENGTH_SHORT).show();
                                        // MyToast.show(Login.this,"", false);
                                    }
                                }) {

                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String>  params = new HashMap<String, String>();
                                params.put("empid",preferences.getString(getString(R.string.EmpId),""));
                                params.put("oldpwd",old_password);
                                params.put("newpwd",new_password);
                                params.put("confirmpwd",conferm_password);
                                params.put("sessionid",preferences.getString(getString(R.string.SessionId),""));
                                params.put("schoolcode",preferences.getString(getString(R.string.SchoolCode),""));
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(Teacher_Dashboard.this);
                        requestQueue.add(request);
                        request.setRetryPolicy(new DefaultRetryPolicy(
                                55000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));




                    }

                }
            });
            dialog.show();

        }/* else if (id == R.id.nav_logout) {
            startActivity(new Intent(Teacher_Dashboard.this, Login.class));
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("log", 1);
            editor.commit();
            finish();

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
