package com.syon.isrms.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Adapter.AdminGridAdepter;
import com.syon.isrms.Beans.AdminChangePasswordBean;
import com.syon.isrms.Beans.UserbeanShareRate;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class Admin_Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    Dialog dialog;
    View view;
    TextView navHeadName, navHeadLastVisit, school_name;
    RoundedImageView teacherPic, navPic;
    ImageView header_school_photo;

    String[] name = {"Daily Report", "Yearly Report", "School Strength", "Result Analysis", "Topper List", "Leave Approval", "Event Calender", "Monthly Salary Summary", "Class Time Table", "Class Attendance", "Employee Attendance", "Communication"};
    int[] image = {R.drawable.admin_blue_box, R.drawable.admin_green_box,
            R.drawable.admin_yello_box, R.drawable.admin_red_box,
            R.drawable.admin_lite_green_box, R.drawable.admin_purple_box,
            R.drawable.admin_blue_box, R.drawable.admin_green_box,
            R.drawable.admin_yello_box, R.drawable.admin_red_box,
            R.drawable.admin_lite_green_box, R.drawable.admin_purple_box};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__dashboard);
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
        GridView gridView = (GridView) findViewById(R.id.adminGridview);
        gridView.setAdapter(new AdminGridAdepter(this, R.layout.admin_dashboard_grid_layout, name, image));
        preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Dashboard.this);

        header_school_photo=findViewById(R.id.header_school_photo);
        navPic = view.findViewById(R.id.imageView);
        navHeadName = view.findViewById(R.id.head_admin_name);
        navHeadLastVisit = view.findViewById(R.id.admin_last_visit);
        school_name = findViewById(R.id.admin_header_text);
        school_name.setText(preferences.getString(getString(R.string.School_Name), "School Name"));
        navHeadName.setText(preferences.getString(getString(R.string.adminEmpName), "").toString());
        navHeadLastVisit.setText("Last Visited : " + preferences.getString("last_visit", ""));
        Picasso.with(Admin_Dashboard.this).load(preferences.getString(getString(R.string.adminPhoto), "").toString()).placeholder(R.drawable.avtar)
                .error(R.drawable.avtar).resize(60, 60).centerInside().into(navPic);
        Picasso.with(Admin_Dashboard.this).load(preferences.getString("SCHOOL_PHOTO", "").toString()).placeholder(R.drawable.schoolheader)
                .error(R.drawable.avtar).into(header_school_photo);

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("last_visit", getDateTime());
        editor.commit();
        finish();
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
                                finish();
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
        getMenuInflater().inflate(R.menu.admin__dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Admin_Dashboard.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("log", 1);
            editor.commit();
            startActivity(new Intent(Admin_Dashboard.this, Login.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_a_news) {
            startActivity(new Intent(Admin_Dashboard.this, Admin_News.class));
            // Handle the camera action
        } else if (id == R.id.nav_a_circular) {
            startActivity(new Intent(Admin_Dashboard.this, Admin_Circular.class));

        } else if (id == R.id.nav_a_change_password) {
            dialog = new Dialog(Admin_Dashboard.this);
            dialog.setContentView(R.layout.change_password_admin);
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


                    String empid = preferences.getString(getString(R.string.adminEmpIdNo), "");
                    String oldpas = oldpass.getText().toString();
                    String newpas = newpass.getText().toString();
                    String newcon = newconf.getText().toString();
                    String sessionid = preferences.getString(getString(R.string.adminSessionIdNo), "");
                    ;
                    String schoolcode = preferences.getString(getString(R.string.adminSchCode), "");


                    if (oldpass.getText().toString().length() == 0) {
                        Toast.makeText(Admin_Dashboard.this, "Enter Old Password", Toast.LENGTH_SHORT).show();

                    } else if (newpass.getText().toString().length() == 0) {
                        Toast.makeText(Admin_Dashboard.this, "Enter New Password", Toast.LENGTH_SHORT).show();
                    } else if (newconf.getText().toString().length() == 0) {
                        Toast.makeText(Admin_Dashboard.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    } else {

                        ForgetPassNew(empid, oldpas, newpas, newcon, sessionid, schoolcode);
                        //  Toast.makeText(ctx,"Enter Valid Email ID", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            dialog.show();

        } else if (id == R.id.nav_a_discussion) {
            startActivity(new Intent(Admin_Dashboard.this, Admin_Discussion_Board.class));

        } else if (id == R.id.nav_a_share) {

            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
                Call<UserbeanShareRate> call = apiInterfce.share();
                call.enqueue(new Callback<UserbeanShareRate>() {
                    @Override
                    public void onResponse(Call<UserbeanShareRate> call, retrofit2.Response<UserbeanShareRate> response) {
                        if (response.code() == 200) {
                            UserbeanShareRate data = response.body();
                            String stc = data.getShareApp();
                            Intent sharingintent = new Intent(Intent.ACTION_SEND);
                            sharingintent.setType("text/plain");
                            sharingintent.putExtra(Intent.EXTRA_SUBJECT, "iSRMS");
                            sharingintent.putExtra(Intent.EXTRA_TEXT, "" + stc);
                            startActivity(Intent.createChooser(sharingintent, "share via"));


                        } else {
                            Toast.makeText(Admin_Dashboard.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserbeanShareRate> call, Throwable t) {

                        Toast.makeText(Admin_Dashboard.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {

                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }


        } else if (id == R.id.nav_a_rate_us) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
                Call<UserbeanShareRate> call = apiInterfce.share();
                call.enqueue(new Callback<UserbeanShareRate>() {
                    @Override
                    public void onResponse(Call<UserbeanShareRate> call, retrofit2.Response<UserbeanShareRate> response) {
                        if (response.code() == 200) {
                            UserbeanShareRate data = response.body();
                            String stc = data.getRateUs();
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("" + stc));
                            startActivity(browserIntent);


                        } else {
                            Toast.makeText(Admin_Dashboard.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserbeanShareRate> call, Throwable t) {

                        Toast.makeText(Admin_Dashboard.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {

                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ForgetPassNew(final String empid, final String oldpas, final String newpas, final String newcon, final String sessionid, final String schoolcode) {

        final ProgressDialog pd = new ProgressDialog(Admin_Dashboard.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ApiInterfce apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<AdminChangePasswordBean> call = apiInterfce.getChangePasswordAdmin(empid, oldpas, newpas, newcon, sessionid, schoolcode);
        call.enqueue(new Callback<AdminChangePasswordBean>() {
            @Override
            public void onResponse(Call<AdminChangePasswordBean> call, retrofit2.Response<AdminChangePasswordBean> response) {
                if (response.code() == 200) {
                    pd.dismiss();
                    dialog.dismiss();
                    Toast.makeText(Admin_Dashboard.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Admin_Dashboard.this, "incorrect user credential", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminChangePasswordBean> call, Throwable t) {
                Toast.makeText(Admin_Dashboard.this, "check internet connection", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
