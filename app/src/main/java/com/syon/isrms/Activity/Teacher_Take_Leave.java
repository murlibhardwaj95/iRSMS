package com.syon.isrms.Activity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syon.isrms.Beans.Userbean_Submit_Leave;
import com.syon.isrms.Beans.Userbean_type_Main;
import com.syon.isrms.Beans.Userbean_type_data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Take_Leave extends AppCompatActivity {
    EditText leavereason, leavefromdate, leavetodate, leavetakentext1, leavetakentext2, leavetakentext3, leavetakentext4, leavetakentext5;

    TextView leavetypetext1, leavetypetext2, leavetypetext3, leavetypetext4, leavetypetext5, leavetotaltext, leaveavailabletext1, leaveavailabletext2, leaveavailabletext3, leaveavailabletext4, leaveavailabletext5;
    Calendar leavefromCalendar, leavetoCalendar;
    Button submitleave;
    Integer leave;
    Float f,output;
    Toast toast;
    RecyclerView leavetakenrecycler;
    ImageView back_button_leave1;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;
    Date date1, date2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__take__leave);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doOperation();
        doApiOperation();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(Teacher_Take_Leave.this);
        leavefromCalendar = Calendar.getInstance();
        leavetoCalendar = Calendar.getInstance();

        leavetypetext1 = findViewById(R.id.leavetypetext1);
        leavetypetext2 = findViewById(R.id.leavetypetext2);
        leavetypetext3 = findViewById(R.id.leavetypetext3);
        leavetypetext4 = findViewById(R.id.leavetypetext4);
        leavetypetext5 = findViewById(R.id.leavetypetext5);

        leaveavailabletext1 = findViewById(R.id.leaveavailabletext1);
        leaveavailabletext2 = findViewById(R.id.leaveavailabletext2);
        leaveavailabletext3 = findViewById(R.id.leaveavailabletext3);
        leaveavailabletext4 = findViewById(R.id.leaveavailabletext4);
        leaveavailabletext5 = findViewById(R.id.leaveavailabletext5);

        leavetakentext1 = findViewById(R.id.leavetakentext1);
        leavetakentext3 = findViewById(R.id.leavetakentext3);
        leavetakentext2 = findViewById(R.id.leavetakentext2);
        leavetakentext4 = findViewById(R.id.leavetakentext4);
        leavetakentext5 = findViewById(R.id.leavetakentext5);

        leavereason = findViewById(R.id.leavereason);
        leavetotaltext = findViewById(R.id.leavetotaltext);

        leavefromdate = findViewById(R.id.selectfromdate);
        leavetodate = findViewById(R.id.selecttodate);


        submitleave = findViewById(R.id.leavesubmit);
        back_button_leave1 = findViewById(R.id.back_button);


    }

    private void doOperation() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                leavefromCalendar.set(Calendar.YEAR, year);
                leavefromCalendar.set(Calendar.MONTH, monthOfYear);
                leavefromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (leavetoCalendar.before(leavefromCalendar)) {
                    toast = Toast.makeText(getApplicationContext(), "From data is never greater than to date", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    //Toast.makeText(Teacher_Take_Leave.this, "From data is never greater than to date", Toast.LENGTH_SHORT).show();
                    leavefromdate.setText(null);
                } else {
                    updateLabel();

                }


            }

        };
        leavefromdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(Teacher_Take_Leave.this, date, leavefromCalendar
                        .get(Calendar.YEAR), leavefromCalendar.get(Calendar.MONTH),
                        leavefromCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                leavetoCalendar.set(Calendar.YEAR, year);
                leavetoCalendar.set(Calendar.MONTH, monthOfYear);
                leavetoCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if (leavetoCalendar.before(leavefromCalendar)) {
                    toast = Toast.makeText(getApplicationContext(), "To Date is always greater than from Date", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    //Toast.makeText(Teacher_Take_Leave.this, "To Date is always greater than from Date", Toast.LENGTH_SHORT).show();
                } else {
                    updateLabe2();
                }

            }
        };
        leavetodate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(Teacher_Take_Leave.this, date1, leavetoCalendar
                        .get(Calendar.YEAR), leavetoCalendar.get(Calendar.MONTH),
                        leavetoCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        back_button_leave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Take_Leave.super.onBackPressed();
            }
        });

        submitleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

try {
    Date d1, d2;
    d2 = leavetoCalendar.getTime();
    d1 = leavefromCalendar.getTime();
    int ndiff = (int) (d2.getTime() - d1.getTime());
    long secondsInMilli = 1000;
    long minutesInMilli = secondsInMilli * 60;
    long hoursInMilli = minutesInMilli * 60;
    long daysInMilli = hoursInMilli * 24;
    int daysdifference = (int) Math.abs(ndiff / daysInMilli) + 1;


    try {
        float a, b, c, d, e;
        if (leavetakentext1.getText().length() <= 0) {
            a = 0f;
        }
        if (leavetakentext2.getText().length() <= 0) {
            b = 0f;
        }
        if (leavetakentext3.getText().length() <= 0) {
            c = 0f;
        }
        if (leavetakentext4.getText().length() < 0) {
            d = 0f;
        }
        if (leavetakentext5.getText().length() < 0) {
            e = 0f;
        } else {
            a = Float.parseFloat(leavetakentext1.getText().toString());
            b = Float.parseFloat(leavetakentext2.getText().toString());
            c = Float.parseFloat(leavetakentext3.getText().toString());
            d = Float.parseFloat(leavetakentext4.getText().toString());
            e = Float.parseFloat(leavetakentext5.getText().toString());

            f = a + b + c + d + e;
        }

    } catch (Exception e) {
    }
    leavetotaltext.setText("" + f);

    Float total = Float.valueOf(leavetotaltext.getText().toString());
               /* float totalround = total;
                NumberFormat nf = DecimalFormat.getPercentInstance();
                nf.setMaximumFractionDigits(0);
                output = Float.valueOf(nf.format(totalround));*/
    Float leave1 = null, leave2 = null, leave3 = null, leave4 = null, leave5 = null;


    try {

        if (leavetakentext1.getText().length() == 0) {
            leave1 = 0f;
            // leaveavail1 = 0;
        }
        if (leavetakentext2.getText().length() == 0) {
            leave1 = 0f;
            //leaveavail2 = 0;
        }

        if (leavetakentext3.getText().length() == 0) {
            leave1 = 0f;
            // leaveavail3 = 0;
        }
        if (leavetakentext4.getText().length() < 0) {
            leave4 = 0f;

        }
        if (leavetakentext5.getText().length() < 0) {
            leave5 = 0f;

        } else {
            leave1 = Float.parseFloat(leavetakentext1.getText().toString());
            leave2 = Float.parseFloat(leavetakentext2.getText().toString());
            leave3 = Float.parseFloat(leavetakentext3.getText().toString());
            leave4 = Float.parseFloat(leavetakentext4.getText().toString());
            leave5 = Float.parseFloat(leavetakentext5.getText().toString());
            // Toast.makeText(Teacher_Take_Leave.this, ""+leaveavail1, Toast.LENGTH_SHORT).show();

        }
    } catch (Exception e) {
    }

    try {

    } catch (Exception e) {
    }
    String fromdate, todate, leavetakenstring, reason, lt1, lt2, lt3, lt4, lt5;
    float leaveavail4, leaveavail5, leaveavail6, leaveavail7, leaveavail8;
    leaveavail4 = Float.parseFloat(leaveavailabletext1.getText().toString());
    leaveavail5 = Float.parseFloat(leaveavailabletext2.getText().toString());
    leaveavail6 = Float.parseFloat(leaveavailabletext3.getText().toString());
    leaveavail7 = Float.parseFloat(leaveavailabletext2.getText().toString());
    leaveavail8 = Float.parseFloat(leaveavailabletext3.getText().toString());

    fromdate = leavefromdate.getText().toString();
    todate = leavetodate.getText().toString();
    lt1 = leavetakentext1.getText().toString();
    lt2 = leavetakentext2.getText().toString();
    lt3 = leavetakentext3.getText().toString();
    lt4 = leavetakentext4.getText().toString();
    lt5 = leavetakentext5.getText().toString();

    String l1, l2, l3, l4, l5;
    l1 = leavetypetext1.getText().toString();
    l2 = leavetypetext1.getText().toString();
    l3 = leavetypetext1.getText().toString();
    l4 = leavetypetext1.getText().toString();
    l5 = leavetypetext1.getText().toString();


    leavetakenstring = leavetypetext1.getText().toString() + ":" + leavetakentext1.getText().toString() + "," + leavetypetext2.getText().toString() + ":" + leavetakentext2.getText().toString() + "," + leavetypetext3.getText().toString() + ":" + leavetakentext3.getText().toString();
    reason = leavereason.getText().toString();
                    /*Toast.makeText(Teacher_Take_Leave.this, "" + leaveavail4, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Teacher_Take_Leave.this, "" + leaveavail5, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Teacher_Take_Leave.this, "" + leaveavil6, Toast.LENGTH_SHORT).show();
                    */
    if (leave1 > leaveavail4) {
        Toast.makeText(Teacher_Take_Leave.this, "" + leaveavail4, Toast.LENGTH_LONG).show();
        toast = Toast.makeText(getApplicationContext(), l1 + " leave taken should not be greater than Available", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        leavetakentext1.setText("0");
    } else if (leave2 > leaveavail5) {
        toast = Toast.makeText(getApplicationContext(), l2 + " leave taken should not be greater than Available", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        leavetakentext2.setText("0");
    } else if (leavetakentext1.getText().length() == 0) {
        leavetakentext1.setText("0");
    } else if (leavetakentext2.getText().length() == 0) {
        leavetakentext2.setText("0");
    } else if (leavetakentext3.getText().length() == 0) {
        leavetakentext3.setText("0");
    } else if (leavetakentext4.getText().length() < 0) {
        leavetakentext4.setText("0");
    } else if (leavetakentext5.getText().length() < 0) {
        leavetakentext5.setText("0");
    } else if (leave3 > leaveavail6) {
        toast = Toast.makeText(getApplicationContext(), l3 + " leave taken should not be greater than Available", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        leavetakentext3.setText("0");
    } else if (leave4 > leaveavail7) {
        toast = Toast.makeText(getApplicationContext(), l4 + " leave taken should not be greater than Available", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        leavetakentext4.setText("0");
    } else if (leave5 > leaveavail8) {
        toast = Toast.makeText(getApplicationContext(), l5 + "Leave taken should not be greater than Available", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        leavetakentext5.setText("0");
    } else if (fromdate.trim().length() == 0) {
        toast = Toast.makeText(getApplicationContext(), "From date Can't be empty", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    } else if (todate.trim().length() == 0) {
        toast = Toast.makeText(getApplicationContext(), "To Date Can't be empty", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    } else if (lt1.equals("0") && lt2.equals("0") && lt3.equals("0") && lt4.equals("0") && lt5.equals("0")) {
        toast = Toast.makeText(getApplicationContext(), "Leave taken fields Can't be Zero", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    } else if (Math.round(total) != daysdifference) {
        //  Toast.makeText(Teacher_Take_Leave.this, ""+total, Toast.LENGTH_SHORT).show();
        toast = Toast.makeText(getApplicationContext(), "Leaves Taken is equals to " + daysdifference, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    } else if (reason.trim().length() == 0) {
        toast = Toast.makeText(getApplicationContext(), "Enter Reason for leave", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    } else {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Userbean_Submit_Leave> call = apiInterfce.submitleave(preferences.getString(getString(R.string.EmpId), ""), fromdate, todate, leavetakenstring, reason, preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
        call.enqueue(new Callback<Userbean_Submit_Leave>() {
            @Override
            public void onResponse(Call<Userbean_Submit_Leave> call, Response<Userbean_Submit_Leave> response) {
                if (response.code() == 200) {
                    Userbean_Submit_Leave list = response.body();

                    if (list.getStatus().equals("ok")) {
                        toast = Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        leavefromdate.setText("Select date");
                        leavetodate.setText("Select date");
                        leavetakentext1.setText("0");
                        leavetakentext2.setText("0");
                        leavetakentext3.setText("0");
                        leavetakentext4.setText("0");
                        leavetakentext5.setText("0");
                        leavereason.setText("");
                        leavetotaltext.setText("0");

                    } else if (list.getStatus().equals("error")) {
                        toast = Toast.makeText(getApplicationContext(), "Data not Submitted", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                } else {
                    Toast.makeText(Teacher_Take_Leave.this, "Data not Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Userbean_Submit_Leave> call, Throwable t) {
                Toast.makeText(Teacher_Take_Leave.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}catch(Exception e) {
    toast = Toast.makeText(getApplicationContext(), "Invalid Data Format", Toast.LENGTH_LONG);
    toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    toast.show();

}

            }
        });

    }

    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<Userbean_type_Main> call = apiInterfce.tleavestatus(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
        call.enqueue(new Callback<Userbean_type_Main>() {
            @Override
            public void onResponse(Call<Userbean_type_Main> call, Response<Userbean_type_Main> response) {

                //   Toast.makeText(Teacher_Take_Leave.this, ""+data.get(0).getSBalLeave(), Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    Userbean_type_Main userbean_type_main = response.body();
                    List<Userbean_type_data> data = userbean_type_main.getData();

                    try {

                        leaveavailabletext1.setText(data.get(0).getSBalLeave());
                        leaveavailabletext2.setText(data.get(1).getSBalLeave());
                        leaveavailabletext3.setText(data.get(2).getSBalLeave());
                        leaveavailabletext4.setText(data.get(3).getSBalLeave());
                        leaveavailabletext5.setText(data.get(4).getSBalLeave());
                        leavetypetext1.setText(data.get(0).getSLeaveType());
                        leavetypetext2.setText(data.get(1).getSLeaveType());
                        leavetypetext3.setText(data.get(2).getSLeaveType());
                        leavetypetext4.setText(data.get(3).getSLeaveType());
                        leavetypetext5.setText(data.get(4).getSLeaveType());
                    } catch (Exception e) {
                        try {
                            if (!data.get(0).getSBalLeave().equals("")) {
                                leaveavailabletext1.setText(data.get(0).getSBalLeave());
                                leavetypetext1.setText(data.get(0).getSLeaveType());

                            }
                        } catch (Exception e1) {
                            /*  leaveavailabletext1.setVisibility(View.GONE);*/
                            leavetypetext1.setVisibility(View.GONE);
                            leavetakentext1.setVisibility(View.GONE);
                            View view1 = findViewById(R.id.view1);
                            View view2 = findViewById(R.id.view2);
                            view1.setVisibility(View.GONE);
                            view2.setVisibility(View.GONE);
                        }
                        try {
                            if (!data.get(1).getSBalLeave().equals("")) {
                                leaveavailabletext2.setText(data.get(1).getSBalLeave());
                                leavetypetext2.setText(data.get(1).getSLeaveType());
                            }
                        } catch (Exception e2) {
                            leaveavailabletext2.setVisibility(View.GONE);
                            leavetypetext2.setVisibility(View.GONE);
                            leavetakentext2.setVisibility(View.GONE);
                            View view3 = findViewById(R.id.view3);
                            View view4 = findViewById(R.id.view4);
                            view3.setVisibility(View.GONE);
                            view4.setVisibility(View.GONE);
                        }
                        try {
                            if (!data.get(2).getSBalLeave().equals("")) {
                                leaveavailabletext3.setText(data.get(2).getSBalLeave());
                                leavetypetext3.setText(data.get(2).getSLeaveType());
                            }
                        } catch (Exception e3) {
                            leaveavailabletext3.setVisibility(View.GONE);
                            leavetypetext3.setVisibility(View.GONE);
                            leavetakentext3.setVisibility(View.GONE);
                            View view5 = findViewById(R.id.view5);
                            View view6 = findViewById(R.id.view6);
                            view5.setVisibility(View.GONE);
                            view6.setVisibility(View.GONE);

                        }
                        try {
                            if (!data.get(3).getSBalLeave().equals("")) {
                                leaveavailabletext4.setText(data.get(3).getSBalLeave());
                                leavetypetext4.setText(data.get(3).getSLeaveType());
                            }
                        } catch (Exception e4) {
                            leaveavailabletext4.setVisibility(View.GONE);
                            leavetypetext4.setVisibility(View.GONE);
                            leavetakentext4.setVisibility(View.GONE);
                            View view7 = findViewById(R.id.viewla4);
                            View view8 = findViewById(R.id.viewlt4);
                            view7.setVisibility(View.GONE);
                            view8.setVisibility(View.GONE);

                        }
                        try {
                            if (!data.get(4).getSBalLeave().equals("")) {
                                leaveavailabletext5.setText(data.get(4).getSBalLeave());
                                leavetypetext5.setText(data.get(4).getSLeaveType());
                            }
                        } catch (Exception e5) {
                            leaveavailabletext5.setVisibility(View.GONE);
                            leavetypetext5.setVisibility(View.GONE);
                            leavetakentext5.setVisibility(View.GONE);
                            View view5 = findViewById(R.id.viewla5);
                            View view6 = findViewById(R.id.viewlt5);
                            view5.setVisibility(View.GONE);
                            view6.setVisibility(View.GONE);

                        }
                    }

                } else if (response.code() == 400) {
                    Toast.makeText(Teacher_Take_Leave.this, "No data found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Userbean_type_Main> call, Throwable t) {

            }
        });
    }

    private void updateLabe2() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        leavetodate.setText(sdf.format(leavetoCalendar.getTime()));
        date1 = leavetoCalendar.getTime();

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat, Locale.US);
        leavefromdate.setText(sdf1.format(leavefromCalendar.getTime()));
        date2 = leavetoCalendar.getTime();
       /* int differenc = date1.compareTo(date2);
        Toast.makeText(Teacher_Take_Leave.this, ""+differenc, Toast.LENGTH_SHORT).show();
*/
    }

}
