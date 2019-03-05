package com.syon.isrms.Activity;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Calendar_No_Data_Recycle_Adapter;
import com.syon.isrms.Beans.ParentMonthPickBean;
import com.syon.isrms.Beans.Parent_Attendance_Monthly_Bean_Data;
import com.syon.isrms.Beans.Parent_Attendance_Yearly_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Parent_Attendance extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView previous, next;
    TextView setmonth, setyear;
    ApiInterfce apiInterfce;
    Calendar now;
    ImageView back_button;
    ProgressDialog progressDialog;
    TextView setPresent, setAbsent, setLeave, setHoliday, setMedicalLeave, setDutyLeave;
    TextView setworkingDays, setNoOfDays, setannualPersentage;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String[] stringmonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String[] date = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31","32", "33", "34", "35","36", "37", "38","39","40"};
    SharedPreferences preferences;
    ArrayList<String> monthPicker;
    int startmonth, endmonth, startyear, endyear;
    int dayofmonth;
    TextView current_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(Parent_Attendance.this);
        progressDialog = new ProgressDialog(Parent_Attendance.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }
        init();
        mMonth = Calendar.getInstance().getTime().getMonth();
        mYear = Calendar.getInstance().get(Calendar.YEAR);
        doApiOperation(stringmonth[Calendar.getInstance().getTime().getMonth()], Calendar.getInstance().getTime().getYear());
        doMonthlyApiOperation(stringmonth[Calendar.getInstance().getTime().getMonth()], Calendar.getInstance().getTime().getYear());
        doYearlyApiOperation(stringmonth[Calendar.getInstance().getTime().getMonth()], Calendar.getInstance().getTime().getYear());
        progressDialog.show();
        doMonthPicker();

        if (mMonth == 3 || mMonth == 5 || mMonth == 8 || mMonth == 10) {
            dayofmonth = 30;

        } else if (mMonth == 1) {
            dayofmonth = (leapYear(mYear)) ? 29 : 28;

        } else {
            dayofmonth = 31;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, mMonth);
        cal.set(Calendar.YEAR, 2018);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = cal.getTime();

        DateFormat sdf = new SimpleDateFormat("EEEEEEEE");
        int firstday = 0;
        if (sdf.format(firstDayOfMonth).toString().equals("Sun"))
        {
            firstday=0;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Mon"))
        {
            firstday=1;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Tue"))
        {
            firstday=2;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Wed"))
        {
            firstday=3;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Thu"))
        {
            firstday=4;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Fri"))
        {
            firstday=5;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Sat"))
        {
            firstday=6;
        }


        recyclerView.setLayoutManager(new GridLayoutManager(Parent_Attendance.this, 7));
        recyclerView.setAdapter(new Calendar_No_Data_Recycle_Adapter(Parent_Attendance.this, date, dayofmonth, setmonth.getText().toString(),firstday));




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void doMonthPicker() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<ParentMonthPickBean> parentMonthPickBeanCall = apiInterfce.GetParentMonthPicker(preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));
        parentMonthPickBeanCall.enqueue(new Callback<ParentMonthPickBean>() {
            @Override
            public void onResponse(Call<ParentMonthPickBean> call, Response<ParentMonthPickBean> response) {
                if (response.isSuccessful()) {
                    startmonth = response.body().getData().get(0).getNStMonth();
                    endmonth = response.body().getData().get(0).getNEndMonth();
                    startyear = response.body().getData().get(0).getNStYear();
                    endyear = response.body().getData().get(0).getNEndYear();
                }
            }

            @Override
            public void onFailure(Call<ParentMonthPickBean> call, Throwable t) {

            }
        });


    }


    public void onMonthClick(View v) {
        if (v.getId() == R.id.cus_cal_month) {
try{
    MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(Parent_Attendance.this,
            new MonthPickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(int selectedMonth, int selectedYear) {
                    if (selectedMonth == 3 || selectedMonth == 5 || selectedMonth == 8 || selectedMonth == 10) {
                        dayofmonth = 30;

                    } else if (selectedMonth == 1) {
                        dayofmonth = (leapYear(selectedYear)) ? 29 : 28;

                    } else {
                        dayofmonth = 31;

                    }
                    setmonth.setText(stringmonth[selectedMonth].toString());
                    setyear.setText(selectedYear+"");
                    doApiOperation(stringmonth[selectedMonth], selectedYear);
                    doMonthlyApiOperation(stringmonth[selectedMonth], selectedYear);
                    doYearlyApiOperation(stringmonth[selectedMonth], selectedYear);


                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH));



    builder.setActivatedMonth(Calendar.JULY)
            .setMinYear(startyear)
            .setActivatedYear(2017)
            .setMaxYear(endyear)
            .setMinMonth(Calendar.APRIL)
            .setTitle("Select Month & Year")
            .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
            // .setMaxMonth(Calendar.OCTOBER)
            // .setYearRange(1890, 1890)
            // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
            //.showMonthOnly()
            // .showYearOnly()
            .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                @Override
                public void onMonthChanged(int selectedMonth) {


                }
            })
            .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                @Override
                public void onYearChanged(int selectedYear) {

                }
            })


            .build()
            .show();
}
catch (Exception e)
{

}




           /* new RackMonthPicker(this)
                    .setLocale(Locale.ENGLISH)
                    .setPositiveButton(new DateMonthDialogListener() {
                        @Override
                        public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {

                            setmonth.setText(stringmonth[month - 1] + "");
                            setyear.setText(year + "");
                            doApiOperation(stringmonth[month - 1], year);
                            doMonthlyApiOperation(stringmonth[month - 1], year);
                            doYearlyApiOperation(stringmonth[month - 1], year);
                        }
                    })
                    .setNegativeButton(new OnCancelMonthDialogListener() {
                        @Override
                        public void onCancel(AlertDialog dialog) {
                            dialog.dismiss();

                        }
                    }).show();
*/
        }
    }

    public void onYearClick(View v) {

        if (v.getId() == R.id.cus_cal_year) {
            try{
                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(Parent_Attendance.this,
                        new MonthPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int selectedMonth, int selectedYear) {
                                if (selectedMonth == 3 || selectedMonth == 5 || selectedMonth == 8 || selectedMonth == 10) {
                                    dayofmonth = 30;

                                } else if (selectedMonth == 1) {
                                    dayofmonth = (leapYear(selectedYear)) ? 29 : 28;

                                } else {
                                    dayofmonth = 31;

                                }
                                setmonth.setText(stringmonth[selectedMonth].toString());
                                setyear.setText(selectedYear+"");
                                doApiOperation(stringmonth[selectedMonth], selectedYear);
                                doMonthlyApiOperation(stringmonth[selectedMonth], selectedYear);
                                doYearlyApiOperation(stringmonth[selectedMonth], selectedYear);


                            }
                        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH));



                builder.setActivatedMonth(Calendar.JULY)
                        .setMinYear(startyear)
                        .setActivatedYear(2017)
                        .setMaxYear(endyear)
                        .setMinMonth(Calendar.APRIL)
                        .setTitle("Select Month & Year")
                        .setMonthRange(Calendar.JANUARY, Calendar.DECEMBER)
                        // .setMaxMonth(Calendar.OCTOBER)
                        // .setYearRange(1890, 1890)
                        // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
                        //.showMonthOnly()
                        // .showYearOnly()
                        .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                            @Override
                            public void onMonthChanged(int selectedMonth) {


                            }
                        })
                        .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                            @Override
                            public void onYearChanged(int selectedYear) {

                            }
                        })


                        .build()
                        .show();
            }
            catch (Exception e)
            {

            }

           /* MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(Parent_Attendance.this,
                    new MonthPickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(int selectedMonth, int selectedYear) {
                        }// on date set }
                    }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

            builder.setActivatedMonth(Calendar.JULY)
                    .setMinYear(1990)
                    .setActivatedYear(2017)
                    .setMaxYear(2030)
                    .setMinMonth(Calendar.FEBRUARY)
                    .setTitle("Select trading month")
                    .setMonthRange(Calendar.FEBRUARY, Calendar.NOVEMBER)
                    // .setMaxMonth(Calendar.OCTOBER)
                    // .setYearRange(1890, 1890)
                    // .setMonthAndYearRange(Calendar.FEBRUARY, Calendar.OCTOBER, 1890, 1890)
                    //.showMonthOnly()
                    // .showYearOnly()
                    .setOnMonthChangedListener(new MonthPickerDialog.OnMonthChangedListener() {
                        @Override
                        public void onMonthChanged(int selectedMonth) {} // on month selected } })
       .setOnYearChangedListener(new MonthPickerDialog.OnYearChangedListener() {
                                @Override
                                public void onYearChanged(int selectedYear) {} // on year selected } })
        .build().show();*/



           /* final Dialog dialog;
            dialog = new Dialog(Parent_Attendance.this);
            dialog.setContentView(R.layout.monthpicker_dailog);
            dialog.setTitle("Month Picker");

            ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
            final RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.notification_recycler);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            RecyclerView monthRecycler=dialog.findViewById(R.id.month_recycler);
            monthRecycler.setLayoutManager(new GridLayoutManager(Parent_Attendance.this,4));
            monthRecycler.setAdapter(new Month_Picker_Recycle_Adapter(Parent_Attendance.this,stringmonth));*/






            /*  dialog.show();*/



            /*new RackMonthPicker(this)
                    .setLocale(Locale.ENGLISH)
                    .setPositiveButton(new DateMonthDialogListener() {
                        @Override
                        public void onDateMonth(int month, int startDate, int endDate, int year, String monthLabel) {

                            setmonth.setText(month - 1 + "");
                            setyear.setText(year + "");
                            doApiOperation(stringmonth[month - 1], year);
                            doMonthlyApiOperation(stringmonth[month - 1], year);
                            doYearlyApiOperation(stringmonth[month - 1], year);
                        }
                    })
                    .setNegativeButton(new OnCancelMonthDialogListener() {
                        @Override
                        public void onCancel(AlertDialog dialog) {
                            dialog.dismiss();

                        }
                    }).show();*/
          /*  Toast.makeText(this, "Year", Toast.LENGTH_SHORT).show();
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            setmonth.setText(stringmonth[monthOfYear] + "");
                            setyear.setText(year + "");
                            doApiOperation(stringmonth[monthOfYear],year);
                            doMonthlyApiOperation(stringmonth[monthOfYear],year);
                            doYearlyApiOperation(stringmonth[monthOfYear],year);
                            progressDialog.show();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();*/
        }
    }


    private void init() {
        recyclerView = findViewById(R.id.attendance_recycle);
        setmonth = findViewById(R.id.cus_cal_month);
        setyear = findViewById(R.id.cus_cal_year);
        setworkingDays = findViewById(R.id.total_working_days);
        setNoOfDays = findViewById(R.id.number_of_prasent_days);
        setannualPersentage = findViewById(R.id.annualparsentage);
        setmonth.setText(stringmonth[Calendar.getInstance().get(Calendar.MONTH)] + "");
        setyear.setText(Calendar.getInstance().get(Calendar.YEAR) + "");
        setPresent = findViewById(R.id.monthPresent);
        setAbsent = findViewById(R.id.monthAbsent);
        setHoliday = findViewById(R.id.monthHolidays);
        setLeave = findViewById(R.id.monthLeave);
        setDutyLeave = findViewById(R.id.monthDuty);
        setMedicalLeave = findViewById(R.id.monthMedicalLeave);
        previous = findViewById(R.id.cus_cal_previous);
        next = findViewById(R.id.cus_cal_next);
        back_button = findViewById(R.id.back_button);
        current_date = findViewById(R.id.daypview);
        doOperation();
      /*  doMonthlyApiOperation();
        doYearlyApiOperation();*/


    }

    private void doOperation() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        current_date.setText(simpleDateFormat.format(calendar.getTime()).toString());
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parent_Attendance.super.onBackPressed();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMonth < startmonth && mYear <= startyear) {
                    Toast.makeText(Parent_Attendance.this, "No Data Found", Toast.LENGTH_SHORT).show();
                } else {

                    mMonth--;
                    if (mMonth < 0) {
                        mMonth = 11;
                        mYear--;

                    }
                    if (mMonth > 11) {
                        mMonth = 0;
                        mYear++;

                    }

                    if (mMonth == 3 || mMonth == 5 || mMonth == 8 || mMonth == 10) {
                        dayofmonth = 30;

                    } else if (mMonth == 1) {
                        dayofmonth = (leapYear(mYear)) ? 29 : 28;

                    } else {
                        dayofmonth = 31;

                    }
                    setmonth.setText(stringmonth[mMonth]);
                    setyear.setText(mYear + "");
                    doApiOperation(setmonth.getText().toString(), mYear);
                    doMonthlyApiOperation(setmonth.getText().toString(), mYear);
                    doYearlyApiOperation(setmonth.getText().toString(), mYear);
                }


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mMonth + 1 >= endmonth && mYear >= endyear) {
                    Toast.makeText(Parent_Attendance.this, "No Data Found", Toast.LENGTH_SHORT).show();
                } else {
                    mMonth++;
                    if (mMonth > 11) {
                        mMonth = 0;
                        mYear++;

                    }

                    if (mMonth == 3 || mMonth == 5 || mMonth == 8 || mMonth == 10) {
                        dayofmonth = 30;

                    } else if (mMonth == 1) {
                        dayofmonth = (leapYear(mYear)) ? 29 : 28;

                    } else {
                        dayofmonth = 31;

                    }

                    setmonth.setText(stringmonth[mMonth]);
                    setyear.setText(mYear + "");
                    doApiOperation(setmonth.getText().toString(), mYear);
                    doMonthlyApiOperation(setmonth.getText().toString(), mYear);
                    doYearlyApiOperation(setmonth.getText().toString(), mYear);
                }

            }


        });
    }

    private boolean leapYear(int mYear) {
        if (mYear % 400 == 0 || mYear % 100 != 0 && (mYear % 4 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    private void doMonthlyApiOperation(String month, int year) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<com.syon.isrms.Beans.ParentAttendanceMonthlyBean> attendanceMonthlyBeanCall = apiInterfce.GetParentAttendanceMonthly(preferences.getString(getString(R.string.lUPIdNo), ""), month, preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));
        attendanceMonthlyBeanCall.enqueue(new Callback<com.syon.isrms.Beans.ParentAttendanceMonthlyBean>() {
            @Override
            public void onResponse(Call<com.syon.isrms.Beans.ParentAttendanceMonthlyBean> call, Response<com.syon.isrms.Beans.ParentAttendanceMonthlyBean> response) {
                progressDialog.dismiss();

                if (response.code() == 200) {
                    com.syon.isrms.Beans.ParentAttendanceMonthlyBean attendanceMonthlyBean = response.body();
                    List<Parent_Attendance_Monthly_Bean_Data> data = attendanceMonthlyBean.getData();
                    setPresent.setText(data.get(0).getNMonthPresent().toString());
                    setAbsent.setText(data.get(0).getNMonthAbsent().toString());
                    setLeave.setText(data.get(0).getNMonthLeave().toString());
                    setHoliday.setText(data.get(0).getNMonthHoliday().toString());
                    setDutyLeave.setText(data.get(0).getNMonthDutyLeave().toString());
                    setMedicalLeave.setText(data.get(0).getNMonthMedical().toString());


                } else {
                    setPresent.setText("0");
                    setAbsent.setText("0");
                    setLeave.setText("0");
                    setHoliday.setText("0");
                    setDutyLeave.setText("0");
                    setMedicalLeave.setText("0");

                }

            }

            @Override
            public void onFailure(Call<com.syon.isrms.Beans.ParentAttendanceMonthlyBean> call, Throwable t) {
                Toast.makeText(Parent_Attendance.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doYearlyApiOperation(String month, int year) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<com.syon.isrms.Beans.ParentAttendanceYealyBean> attendanceYealyBeanCall = apiInterfce.GetParentAttendanceYearly(preferences.getString(getString(R.string.lUPIdNo), ""), preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));
        attendanceYealyBeanCall.enqueue(new Callback<com.syon.isrms.Beans.ParentAttendanceYealyBean>() {
            @Override
            public void onResponse(Call<com.syon.isrms.Beans.ParentAttendanceYealyBean> call, Response<com.syon.isrms.Beans.ParentAttendanceYealyBean> response) {
                progressDialog.dismiss();

                if (response.code() == 200) {
                    com.syon.isrms.Beans.ParentAttendanceYealyBean parentAttendanceYealyBean = response.body();
                    List<Parent_Attendance_Yearly_Data> data = parentAttendanceYealyBean.getData();
                    setworkingDays.setText("Total Working Days: " + data.get(0).getNTotalAttnd().toString());
                    setNoOfDays.setText("Number of days present: " + data.get(0).getNTotalPresent().toString().toString());
                    setannualPersentage.setText(data.get(0).getDPresentPercent().toString() + "%");

                } else {
                    setworkingDays.setText("Total Working Days: " + "0");
                    setNoOfDays.setText("Number of days present: " + "0");
                    setannualPersentage.setText("0" + "%");
                }

            }

            @Override
            public void onFailure(Call<com.syon.isrms.Beans.ParentAttendanceYealyBean> call, Throwable t) {
                Toast.makeText(Parent_Attendance.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void doApiOperation(String month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, mMonth);
        cal.set(Calendar.YEAR, 2018);

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = cal.getTime();

        DateFormat sdf = new SimpleDateFormat("EEEEEEEE");
        int firstday = 0;
        if (sdf.format(firstDayOfMonth).toString().equals("Sun"))
        {
            firstday=0;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Mon"))
        {
            firstday=1;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Tue"))
        {
            firstday=2;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Wed"))
        {
            firstday=3;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Thu"))
        {
            firstday=4;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Fri"))
        {
            firstday=5;
        }
        else if (sdf.format(firstDayOfMonth).toString().equals("Sat"))
        {
            firstday=6;
        }


        recyclerView.setLayoutManager(new GridLayoutManager(Parent_Attendance.this, 7));
        recyclerView.setAdapter(new Calendar_No_Data_Recycle_Adapter(Parent_Attendance.this, date, dayofmonth, setmonth.getText().toString(),firstday));

    }
}





