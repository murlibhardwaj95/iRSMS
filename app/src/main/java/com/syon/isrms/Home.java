package com.syon.isrms;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Activity.Birthday;
import com.syon.isrms.Activity.Leave;
import com.syon.isrms.Activity.Login;
import com.syon.isrms.Activity.MyTeacher;
import com.syon.isrms.Activity.Observation;
import com.syon.isrms.Activity.Parent_Add_Acoount;
import com.syon.isrms.Activity.Parent_Communication;
import com.syon.isrms.Activity.Parent_Event_Calender;
import com.syon.isrms.Activity.Parent_Sms;
import com.syon.isrms.Activity.Tracking;
import com.syon.isrms.Adapter.Parent_Notification_Recycle_Adapter;
import com.syon.isrms.Beans.ParentNotificationBean;
import com.syon.isrms.Beans.Parent_Notification_Bean_Data;
import com.syon.isrms.Beans.UserbeanShareRate;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.Model_Class.SchoolNameResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    GridView gridView;
    Dialog dialog, dialog0;
    String lUPIdNo;
    private SharedPreferences mPrefs;
    SharedPreferences pref;
    String lSessionId;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    String sSchCode, logoStr, schname;
    Context ctx = this;
    public static String[] osNameList = {
            "Profile",
            "Attendance",
            "Fee Info",
            "Online Payment",
            "Home Work",
            "Exam Result",
            "News/Event",
            "Circulars",
            "Time Table",
            "Transport",
            "Library",
            "Medical Record",
    };
    public static int[] osImages = {
            com.syon.isrms.R.drawable.parent_profile,
            com.syon.isrms.R.drawable.parent_calender,
            com.syon.isrms.R.drawable.parent_fee,
            com.syon.isrms.R.drawable.parent_online_payment,
            com.syon.isrms.R.drawable.parent_homework,
            com.syon.isrms.R.drawable.parent_result,
            com.syon.isrms.R.drawable.parent_news_event,
            com.syon.isrms.R.drawable.parent_circular,
            com.syon.isrms.R.drawable.parent_exam_timetable,
            com.syon.isrms.R.drawable.parent_transport,
            com.syon.isrms.R.drawable.parent_library,
            com.syon.isrms.R.drawable.parent_medical_record};

    com.makeramen.roundedimageview.RoundedImageView im, im1;
    TextView userNameHeader, tv_lastseen, aname, texView;
    ImageView bt1;

    String sStudName, sSession, studPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.syon.isrms.R.layout.activity_home);

        im = (com.makeramen.roundedimageview.RoundedImageView) findViewById(com.syon.isrms.R.id.dpHeader);
        userNameHeader = (TextView) findViewById(com.syon.isrms.R.id.userNameHeader);
        tv_lastseen = (TextView) findViewById(com.syon.isrms.R.id.tv_lastseen);


        Toolbar toolbar = (Toolbar) findViewById(com.syon.isrms.R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(Home.this);
        drawer = (DrawerLayout) findViewById(com.syon.isrms.R.id.drawer_layout);
      /*  ArrayList<Integer> noti = new ArrayList<>();
        noti.add(0);
        noti.add(0);
        noti.add(0);
        noti.add(0);
        noti.add(preferences.getInt("Homework",0));
        noti.add(0);
        noti.add(preferences.getInt("News", 0));
        noti.add(preferences.getInt("Circular", 0));
        noti.add(0);
        noti.add(0);
        noti.add(0);
        noti.add(0);*/

        gridView = (GridView) findViewById(com.syon.isrms.R.id.grid);
     /*   gridView.setAdapter(new CustomAdapter(this, osNameList, osImages, noti));*/
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(com.syon.isrms.R.layout.customactionbarafterlogged, null);
        toolbar.addView(mCustomView);
        LinearLayout drower = (LinearLayout) mCustomView.findViewById(com.syon.isrms.R.id.drower);
        TextView logoicon = (TextView) mCustomView.findViewById(com.syon.isrms.R.id.logoicon);
        /*       logoicon.setText(preferences.getString(getString(R.string.School_Name),"School Name"));*/
        logoicon.setText("School Name");
        //   logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.homee, 0, 0, 0);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }


        NavigationView navigationView = (NavigationView) findViewById(com.syon.isrms.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        /*   bt1 = (ImageView) header.findViewById(R.id.btn_sing_in);*/
      /*  bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("log", 1);
                editor.commit();
                startActivity(new Intent(Home.this, Login.class));
                overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
                finish();
            }
        });
*/

        im1 = (com.makeramen.roundedimageview.RoundedImageView) header.findViewById(com.syon.isrms.R.id.dpHer);
        aname = (TextView) header.findViewById(com.syon.isrms.R.id.aname);
        texView = (TextView) header.findViewById(com.syon.isrms.R.id.textVw);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        Date myDate = new Date(sp.getLong("LAST_LAUNCH_DATE_MS", 0));
        CharSequence s = DateFormat.format("dd/MM/yyyy HH:mm:ss", myDate);


        Log.e("time", String.valueOf(s));

        texView.setText(getString(com.syon.isrms.R.string.last) + " " + s);
        View footer = findViewById(com.syon.isrms.R.id.btn_sing_out);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("log", 1);
                editor.commit();
                startActivity(new Intent(Home.this, com.syon.isrms.Activity.Login.class));
                overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                finish();
            }
        });


        toolbar.setTitleMargin(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            toolbar.setPaddingRelative(0, 0, 0, 0);
        }
        toolbar.setPadding(0, 0, 0, 0);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(Home.this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        sStudName = mPrefs.getString("sStudName", "");
        sSession = mPrefs.getString("sSession", "");
        studPhoto = mPrefs.getString("studPhoto", "");
        lUPIdNo = mPrefs.getString("lUPIdNo", "");
        sSchCode = obj.getsSch_Code();
        lSessionId = obj.getWebSessionId();

        userNameHeader.setText(sStudName);
        tv_lastseen.setText(sSession);

        Picasso.with(getApplication())
                .load(studPhoto)
                .into(im);


        Picasso.with(getApplication())
                .load(studPhoto)
                .into(im1);

        aname.setText(sStudName);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        sStudName = mPrefs.getString("sStudName", "");
        sSession = mPrefs.getString("sSession", "");
        studPhoto = mPrefs.getString("studPhoto", "");
        lUPIdNo = mPrefs.getString("lUPIdNo", "");

        sSchCode = obj.getsSch_Code();
        lSessionId = obj.getWebSessionId();

        userNameHeader.setText(sStudName);
        tv_lastseen.setText(sSession);

        Picasso.with(getApplication())
                .load(studPhoto)
                .into(im);


        Picasso.with(getApplication())
                .load(studPhoto)
                .into(im1);

        aname.setText(sStudName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Integer> noti = new ArrayList<>();
        noti.add(0);
        noti.add(0);
        noti.add(0);
        noti.add(0);
        noti.add(preferences.getInt("Homework",0));
        noti.add(0);
        noti.add(preferences.getInt("News", 0));
        noti.add(preferences.getInt("Circular", 0));
        noti.add(0);
        noti.add(0);
        noti.add(0);
        noti.add(0);
        gridView.setAdapter(new CustomAdapter(this, osNameList, osImages, noti));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            finishAffinity();
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

    public class CustomAdapter extends BaseAdapter {

        String[] result;
        Context context;
        int[] imageId;
        ArrayList<Integer> notification;
        private LayoutInflater inflater = null;

        public CustomAdapter(Home mainActivity, String[] osNameList, int[] osImages, ArrayList<Integer> notification) {
            // TODO Auto-generated constructor stub
            result = osNameList;
            context = mainActivity;
            imageId = osImages;
            this.notification = notification;
            inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder {
            TextView os_text;
            ImageView os_img;
            TextView osNotification;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            CustomAdapter.Holder holder = new CustomAdapter.Holder();
            View rowView;
            rowView = inflater.inflate(com.syon.isrms.R.layout.sample_gridlayout, null);
            holder.os_text = (TextView) rowView.findViewById(com.syon.isrms.R.id.os_texts);
            holder.os_img = (ImageView) rowView.findViewById(com.syon.isrms.R.id.os_images);

            holder.osNotification = rowView.findViewById(com.syon.isrms.R.id.nCounter);

            // holder.os_text.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slideleft));
            //  holder.os_img.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slideleft));

            holder.os_text.setText(result[position]);
            holder.os_img.setImageResource(imageId[position]);
            holder.osNotification.setText(notification.get(position).toString());
            /*SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home.this);
            SharedPreferences.Editor editor = preferences.edit();


            for (String s : getIntent().getExtras().keySet()) {
                if (s.equals("click_action")) {
                    String action = getIntent().getExtras().getString(s);
                    if (action.equals("Circular")) {
                        int cir = 0;
                        cir++;
                        editor.putInt("Circular", cir);
                        editor.commit();
                    }
                    if (action.equals("News"))
                    {
                        int news=0;
                        news++;
                        editor.putInt("News",news);
                        editor.commit();
                    }
                }
            }*/

            if (result[position].equals("Home Work") && notification.get(position)!=0|| result[position].equals("News/Event") && notification.get(position)!=0 || result[position].equals("Circulars") && notification.get(position)!=0) {
                holder.osNotification.setVisibility(View.VISIBLE);
            }

            else {
                holder.osNotification.setVisibility(View.GONE);
            }

            rowView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    /*Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();*/
                    if (position == 0) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Profile.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);

                    } else if (position == 1) {
                        /* startActivity(new Intent(Home.this, Attend.class));*/
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Parent_Attendance.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 2) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Fees_Info.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);

                    } else if (position == 3) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Online_Payment.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 4) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Homework.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);

                    } else if (position == 5) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Exam_Result.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 6) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.News.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 7) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Circular.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 8) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Time_Table.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 9) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Transport.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 10) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Library.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    } else if (position == 11) {
                        startActivity(new Intent(Home.this, com.syon.isrms.Activity.Medical_Record.class));
                        overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
                    }

                }
            });

            return rowView;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.syon.isrms.R.menu.home, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.syon.isrms.R.id.action_notification1) {
            final Dialog dialog;
            dialog = new Dialog(Home.this);
            dialog.setContentView(com.syon.isrms.R.layout.parent_notification_dialog);
            dialog.setTitle("My Notifications");

            ImageView cancel = (ImageView) dialog.findViewById(com.syon.isrms.R.id.cancel);
            final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(com.syon.isrms.R.id.notification_recycler);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<com.syon.isrms.Beans.ParentNotificationBean> call = apiInterfce.getParentNotification(preferences.getString("lStudIdNo", ""),preferences.getString(getString(com.syon.isrms.R.string.lSessionIdNo),""), preferences.getString(getString(com.syon.isrms.R.string.sSchCode),""));
            call.enqueue(new Callback<com.syon.isrms.Beans.ParentNotificationBean>() {
                @Override
                public void onResponse(Call<com.syon.isrms.Beans.ParentNotificationBean> call, retrofit2.Response<com.syon.isrms.Beans.ParentNotificationBean> response) {
                    if (response.code() == 200) {
                        com.syon.isrms.Beans.ParentNotificationBean notificationBean = response.body();
                        List<Parent_Notification_Bean_Data> data = notificationBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Home.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Parent_Notification_Recycle_Adapter(Home.this, data));

                    } else {
                        Toast.makeText(Home.this, "No Notification", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<com.syon.isrms.Beans.ParentNotificationBean> call, Throwable t) {

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

        if (id == com.syon.isrms.R.id.Observation) {
            startActivity(new Intent(Home.this, Observation.class));
            overridePendingTransition(com.syon.isrms.R.anim.slide_left, com.syon.isrms.R.anim.slide_left);
            // Handle the camera action
        } else if (id == com.syon.isrms.R.id.Tuckshop) {
            startActivity(new Intent(Home.this, com.syon.isrms.Activity.Tuckshop.class));
            overridePendingTransition(com.syon.isrms.R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.nav_br) {
            startActivity(new Intent(Home.this, Birthday.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.Vehicle) {
            startActivity(new Intent(Home.this, Tracking.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.leave) {
            startActivity(new Intent(Home.this, Leave.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.Teachers) {
            startActivity(new Intent(Home.this, MyTeacher.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.nav_Cal) {
            startActivity(new Intent(Home.this, Parent_Event_Calender.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.nav_meeting) {
            startActivity(new Intent(Home.this, Parent_Communication.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.nav_sms) {
            startActivity(new Intent(Home.this, Parent_Sms.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        }
        else if (id == R.id. nav_p_add_account) {
            startActivity(new Intent(Home.this, Parent_Add_Acoount.class));
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        }




        else if (id == R.id.nav_schoolwebsite) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                Call<UserbeanShareRate> call = apiInterfce.share();
                call.enqueue(new Callback<UserbeanShareRate>() {
                    @Override
                    public void onResponse(Call<UserbeanShareRate> call, retrofit2.Response<UserbeanShareRate> response) {
                        if (response.code() == 200) {
                            UserbeanShareRate data = response.body();
                            String stc = data.getSchoolWebsite();
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("" + stc));
                            startActivity(browserIntent);


                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<UserbeanShareRate> call, Throwable t) {


                    }
                });
            } catch (Exception e) {


            }


        } else if (id == R.id.nav_facebook) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                Call<UserbeanShareRate> call = apiInterfce.share();
                call.enqueue(new Callback<UserbeanShareRate>() {
                    @Override
                    public void onResponse(Call<UserbeanShareRate> call, retrofit2.Response<UserbeanShareRate> response) {
                        if (response.code() == 200) {
                            UserbeanShareRate data = response.body();
                            String stc = data.getSchoolFacebook();
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("" + stc));
                            startActivity(browserIntent);


                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<UserbeanShareRate> call, Throwable t) {


                    }
                });
            } catch (Exception e) {


            }


        } else if (id == R.id.nav_p_notification) {
            final Dialog dialog;
            dialog = new Dialog(Home.this);
            dialog.setContentView(R.layout.parent_notification_dialog);
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
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);

            Call<ParentNotificationBean> call = apiInterfce.getParentNotification(preferences.getString("lStudIdNo", ""), preferences.getString(getString(R.string.lSessionIdNo),""), preferences.getString(getString(R.string.sSchCode),""));
            call.enqueue(new Callback<ParentNotificationBean>() {
                @Override
                public void onResponse(Call<ParentNotificationBean> call, retrofit2.Response<ParentNotificationBean> response) {
                    if (response.code() == 200) {
                        ParentNotificationBean notificationBean = response.body();
                        List<Parent_Notification_Bean_Data> data = notificationBean.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Home.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(new Parent_Notification_Recycle_Adapter(Home.this, data));
                    } else {
                        Toast.makeText(Home.this, "No Notification", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ParentNotificationBean> call, Throwable t) {

                }
            });

            dialog.show();

        } else if (id == R.id.nav_p_downloads) {
            Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/iSRMS/");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(selectedUri, "*/*");
            try {
                startActivity(Intent.createChooser(intent, "open Downloads"));
                /*startActivity(intent);*/
            } catch (ActivityNotFoundException e) {
                Toast.makeText(Home.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
            overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
        } else if (id == R.id.nav_p_share) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
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
                            Toast.makeText(Home.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserbeanShareRate> call, Throwable t) {

                        Toast.makeText(Home.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {

                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_p_rating) {
            try {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
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
                            Toast.makeText(Home.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserbeanShareRate> call, Throwable t) {

                        Toast.makeText(Home.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {

                Toast.makeText(this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.change_pass) {
            dialog = new Dialog(ctx);
            dialog.setContentView(R.layout.change_password);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ForgetPassNew(final String lUPIdNo, final String lSessionId, final String sSchCode, final String oldpas, final String newpas, final String newcon) {


        final ProgressDialog pd = new ProgressDialog(Home.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.PUT, ApiClient.BASE_URL + "ChangePassword",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Login.longLog(response);
                        Log.e("my response is", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getString("status").equalsIgnoreCase("ok")) {
                                Toast.makeText(ctx, "Change Password Successfully", Toast.LENGTH_SHORT).show();
                                pd.hide();
                                dialog.dismiss();
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("profile", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.remove("logged");
                                editor.remove("data");
                                editor.commit();
                                startActivity(new Intent(Home.this, Login.class));
                                overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
                                finish();

                            } else if (json.getString("status").equalsIgnoreCase("error")) {
                                Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT).show();

                                pd.hide();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                            Login.longLog("Json --------- Exception..." + e.toString());
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
                params.put("sessionid", lSessionId);
                params.put("schoolcode", sSchCode);
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

}
