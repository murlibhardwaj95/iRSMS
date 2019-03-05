package com.syon.isrms.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.syon.isrms.Model_Class.ResponseAttendances;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;
import com.syon.isrms.Utilty.PrefManager;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;

public class Attend extends AppCompatActivity implements View.OnClickListener {

    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    Date green_presentDate = null;
    PrefManager prefManager;
    TextView no_attendance;
    TextView tworkingdays,noofdays,annual;

    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();
        String str_p = "L";

    }

    Button pr,ab,le,ho,me;

    String sSchCode,lUPIdNo,lSessionId;
    private SharedPreferences mPrefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);

        tworkingdays=(TextView)findViewById(R.id.tworkingdays);
        noofdays=(TextView)findViewById(R.id.noofdays);
        annual=(TextView)findViewById(R.id.annual);
        pr=(Button)findViewById(R.id.pr);
        ab=(Button)findViewById(R.id.ab);
        le=(Button)findViewById(R.id.le);
        ho=(Button)findViewById(R.id.ho);
        me=(Button)findViewById(R.id.me);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Attend.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Attendance");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.att, 0, 0, 0);


        caldroidFragment = new CaldroidFragment();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }
        toolbar.setTitleMargin(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            toolbar.setPaddingRelative(0, 0, 0, 0);
        }
        toolbar.setPadding(0, 0, 0, 0);

       // no_attendance = (TextView) findViewById(R.id.no_attendance);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        mPrefs = PreferenceManager.getDefaultSharedPreferences(Attend.this);


        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);


        lSessionId = obj.getWebSessionId();
        sSchCode = obj.getsSch_Code();
        lUPIdNo = mPrefs.getString("lUPIdNo", "");

        Calendar calk=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_nam = month_date.format(calk.getTime());


        DataNEWFROMAPI(lUPIdNo, lSessionId, sSchCode);

        MonthlyAttendance(lUPIdNo, lSessionId, sSchCode, month_nam);

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, false);
            //args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);


            //args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
            caldroidFragment.setArguments(args);
            /*cal.set(Calendar.DAY_OF_MONTH, 1); // 1st day of current month
            caldroidFragment.setMinDate(cal.getTime());

            int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, maxDay);
            caldroidFragment.setMaxDate(cal.getTime()); //last day of current month*/
        }
        //setCustomResourceForDates();


      //  getAttendancefromserverMonthData(lUPIdNo,month_name,lSessionId,sSchCode);
        FragmentTransaction t = Attend.this.getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

            }

            @Override
            public void onChangeMonth(int month, int year) {
                // String text = "month: " + month + " year: " + year;
                String month_name = getMonthName(month);
                getAttendancefromserverMonthData(lUPIdNo,month_name,lSessionId,sSchCode);
                MonthlyAttendance(lUPIdNo, lSessionId, sSchCode,month_name);

            }

            @Override
            public void onLongClickDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {
                /*if (caldroidFragment.getLeftArrowButton() != null) {

                }*/

            }
        };

        caldroidFragment.setCaldroidListener(listener);


    }

    private void MonthlyAttendance(String lUPIdNo, String lSessionId, String sSchCode, String month_name) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, com.syon.isrms.Interfaces_Teacher.ApiClient.BASE_URL+"StudAttndMonthWise?lUPIdNo=" + lUPIdNo +"&sMonth=" + month_name + "&lSessionId=" + lSessionId + "&sSchCode=" + sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resopnce----", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("status").equals("ok")) {
                                JSONArray jsonArray = obj.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    pr.setText(ob.getString("nMonthPresent"));
                                    ab.setText(ob.getString("nMonthAbsent"));
                                    me.setText(ob.getString("nMonthMedical"));
                                    le.setText(ob.getString("nMonthLeave"));
                                    ho.setText(ob.getString("nMonthHoliday"));

                                }

                            } else if (obj.getString("status").equals("error")) {
                              /*  MyToast.show(Attend.this, "No Data !!!", false);*/

                            }
                        } catch (JSONException e) {
                            Log.d("json Exception", e.toString());
                            // avloader.hide();
                    /*        MyToast.show(Attend.this, "Server Data Error Try Again !!!", false);*/
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();

               /*         MyToast.show(Attend.this, "Connection Time Out Error Try Again !!", false);*/
                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Attend.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));




}

    private void DataNEWFROMAPI(String lUPIdNo, String lSessionId, String sSchCode) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, com.syon.isrms.Interfaces_Teacher.ApiClient.BASE_URL+"StudAttndYearWise?lUPIdNo=" + lUPIdNo + "&lSessionId=" + lSessionId + "&sSchCode=" + sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resopnce----", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("status").equals("ok")) {
                                JSONArray jsonArray = obj.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    tworkingdays.setText(ob.getString("nTotalAttnd"));
                                    noofdays.setText(ob.getString("nTotalPresent"));
                                    annual.setText(ob.getString("dPresentPercent"));
                                }

                            } else if (obj.getString("status").equals("error")) {


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception", e.toString());
                            // avloader.hide();
             /*               MyToast.show(Attend.this, "Server Data Error Try Again !!!", false);*/
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();

              /*          MyToast.show(Attend.this, "Connection Time Out Error Try Again !!", false);*/
                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Attend.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getDrawableCustom_GrikInfotechAttendance(String attendance_grik, Calendar cal, int date, int month, int year) {

        if (attendance_grik.equalsIgnoreCase("P")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.P));
            caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
           // Drawable green_present = getResources().getDrawable(R.drawable.green);
            //caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
        }
        if (attendance_grik.equalsIgnoreCase("A")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.A));
            caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
          // Drawable d = getResources().getDrawable(R.drawable.circle);
           // ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.absent));
            //caldroidFragment.setBackgroundDrawableForDate(d, green_presentDate);
        }
        if (attendance_grik.equalsIgnoreCase("L")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.L));
            caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
          //  Drawable leave = getResources().getDrawable(R.drawable.yellow);
           // ColorDrawable leave = new ColorDrawable(getResources().getColor(R.color.leavet));
           // caldroidFragment.setBackgroundDrawableForDate(leave, green_presentDate);
        }
        if (attendance_grik.equalsIgnoreCase("H")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.H));
            caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
           // Drawable leave_H = getResources().getDrawable(R.drawable.blu);
          //  ColorDrawable leave_H = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
           // caldroidFragment.setBackgroundDrawableForDate(leave_H, green_presentDate);
        }

        if (attendance_grik.equalsIgnoreCase("M")) {
            cal.set(Calendar.DATE, date);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            green_presentDate = cal.getTime();
            ColorDrawable green_present = new ColorDrawable(getResources().getColor(R.color.M));
            caldroidFragment.setBackgroundDrawableForDate(green_present, green_presentDate);
          //  Drawable leave_H = getResources().getDrawable(R.drawable.orange);
            //  ColorDrawable leave_H = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
           // caldroidFragment.setBackgroundDrawableForDate(leave_H, green_presentDate);
        }

        caldroidFragment.refreshView();
    }


    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "January";

            case 2:
                return "February";

            case 3:
                return "March";

            case 4:
                return "April";

            case 5:
                return "May";

            case 6:
                return "June";

            case 7:
                return "July";

            case 8:
                return "August";

            case 9:
                return "September";

            case 10:
                return "October";

            case 11:
                return "November";

            case 12:
                return "December";
        }
        return "";
    }

    @Override
    public void onClick(View view) {

    }

    public void getAttendancefromserverMonthData(String dateMonth, String april, String date_month, String st_id) {
        final com.syon.isrms.interfaces.custom.ApiIHandler apiService =
                com.syon.isrms.urlsApimanage.ApiClient.getClient().create(com.syon.isrms.interfaces.custom.ApiIHandler.class);
        System.out.println("Size data:date_month" + date_month);
        final ProgressDialog pDialog = ProgressDialog.show(Attend.this, "", "Please wait ...", true);

        Call<ResponseAttendances> call = apiService.getAttendanceData(dateMonth, april,date_month,st_id);
        call.enqueue(new Callback<ResponseAttendances>() {
            @Override
            public void onResponse(Call<ResponseAttendances> call, retrofit2.Response<ResponseAttendances> response) {
                try {
                    Thread.sleep(2000);
                    pDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (response.code() == 200) {
                    ResponseAttendances data = response.body();
                    if (data.getData() != null) {
                        if (data.getData().size() > 0) {
                          //  no_attendance.setVisibility(View.VISIBLE);
                            System.out.println("Size data:" + data.getData().size());
                            for (int i = 0; i < data.getData().size(); i++) {
                                //String at_id = data.getResult().get(i).getATTENDANCE_ID();
                                String at_type = data.getData().get(i).getAttndStatus();
                                Calendar cal = Calendar.getInstance();
                                String at_date = data.getData().get(i).getAttndDate();
                                Log.d("at_date", at_date);
                                String[] separated_at_date = at_date.split("-");
                                Log.d("separated_at_date", String.valueOf(separated_at_date));
                                String year = separated_at_date[0];
                                String month = separated_at_date[1];
                                String date_ = separated_at_date[2];
                                Log.d("year", year);
                                Log.d("date", date_);
                                /*char c=date_.charAt(0);
                                if(c=='0')
                                {
                                    date_=date_.replace("0","");
                                    date_=date_.trim();
                                }*/
                                TimeZone timezone = TimeZone.getDefault();
                                Calendar calendar = new GregorianCalendar(timezone);

                                switch (Integer.parseInt(month)) {
                                    case 1:
                                        calendar.set(Integer.parseInt(year), Calendar.JANUARY, Integer.parseInt(date_));
                                        break;
                                    case 2:
                                        calendar.set(Integer.parseInt(year), Calendar.FEBRUARY, Integer.parseInt(date_));
                                        break;
                                    case 3:
                                        calendar.set(Integer.parseInt(year), Calendar.MARCH, Integer.parseInt(date_));
                                        break;
                                    case 4:
                                        calendar.set(Integer.parseInt(year), Calendar.APRIL, Integer.parseInt(date_));
                                        break;
                                    case 5:
                                        calendar.set(Integer.parseInt(year), Calendar.MAY, Integer.parseInt(date_));
                                        break;
                                    case 6:
                                        calendar.set(Integer.parseInt(year), Calendar.JUNE, Integer.parseInt(date_));
                                        break;
                                    case 7:
                                        calendar.set(Integer.parseInt(year), Calendar.JULY, Integer.parseInt(date_));
                                        break;
                                    case 8:
                                        calendar.set(Integer.parseInt(year), Calendar.AUGUST, Integer.parseInt(date_));
                                        break;
                                    case 9:
                                        calendar.set(Integer.parseInt(year), Calendar.SEPTEMBER, Integer.parseInt(date_));
                                        break;
                                    case 10:
                                        calendar.set(Integer.parseInt(year), Calendar.OCTOBER, Integer.parseInt(date_));
                                        break;
                                    case 11:
                                        calendar.set(Integer.parseInt(year), Calendar.NOVEMBER, Integer.parseInt(date_));
                                        break;
                                    case 12:
                                        calendar.set(Integer.parseInt(year), Calendar.DECEMBER, Integer.parseInt(date_));
                                        break;
                                }

                                int reslut = calendar.get(Calendar.DAY_OF_WEEK);
                                System.out.println("CAll DAta calendar" + reslut);
                                switch (reslut) {
                                    case Calendar.SUNDAY:
                                        at_type = "H";
                                        break;
                                }
                                String datadd = "CAll DAta" + at_type + Integer.parseInt(date_) + Integer.parseInt(month) + Integer.parseInt(year);
                                System.out.println(datadd);
                                getDrawableCustom_GrikInfotechAttendance(at_type, cal, Integer.parseInt(date_), Integer.parseInt(month), Integer.parseInt(year));
                            }
                        } else {
                          //  no_attendance.setVisibility(View.VISIBLE);
                            //no_attendance.setText(data.getMessage());
                        }
                    } else {
       /*                 MyToast.show(Attend.this,"error", false);*/

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseAttendances> call, Throwable t) {
                if (pDialog != null && pDialog.isShowing())
                    pDialog.hide();
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_tryagain), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
