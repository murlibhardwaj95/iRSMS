package com.syon.isrms.Activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
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
import com.syon.isrms.Adapter.Parent_Dates_Adapter;
import com.syon.isrms.Beans.ParentHomeworkDateBean;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.Model_Class.GetterSetterHomework;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class Homework extends AppCompatActivity {

    LinearLayout scrollView;
    ArrayList<Integer> totalImages = new ArrayList<>();
    TextView monthname, dat, dayname;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();
    String lUPIdNo, lClass_IdNo, lSec_IdNo;
    List<String> data;
    String lSessionId;
    String sSchCode, lClassId;
    ImageView increment, decrement;
    static String dataaa;
    Context ctx = this;
    String str1, dt1, daysName;
    String date_self, sHWDate = "04_05_2017";
    ProgressDialog progressDialog;
    ApiInterfce apiInterfce;
    int month;
    int yer;
    RecyclerView recyclerView;
    String sHWDae = "07_22_2018";
    ProgressDialog dialog;
    String indexvalue = "";

    Date dt;
    DownloadManager downloadManager;

    SimpleDateFormat dateFormat;

    ArrayList<HashMap<String, String>> CatlistDes = new ArrayList<>();
    ArrayList<GetterSetterHomework> array = new ArrayList<GetterSetterHomework>();

    private SharedPreferences mPrefs;
    private static final int PERMISSION_REQUEST_CODE = 1;

    private HorizontalScrollView horizontalScrollView;
    private TextView preview, next, monthview, yearview, dayview;
    LinearLayout linearLayout;
    private List monthlist;
    private int count = 0, countformonth = 0;
    LinearLayout datanotfound;
    ImageView back_button;

    String monthvalue = "";
    Calendar c;
    private List dayslist;
    LayoutInflater inflator = null;
    int mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(Homework.this);
        SharedPreferences.Editor countEditor = mPrefs.edit();
        countEditor.putInt("Homework", 0);
        countEditor.commit();
        dayname = (TextView) findViewById(R.id.dayname);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalscroll);
        preview = (TextView) findViewById(R.id.previous);
        next = (TextView) findViewById(R.id.nextview);
        monthview = (TextView) findViewById(R.id.monthview);
        yearview = (TextView) findViewById(R.id.yearview);
        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Homework.super.onBackPressed();
            }
        });
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        dayview = (TextView) findViewById(R.id.dayview);
        datanotfound = (LinearLayout) findViewById(R.id.datanotfound);
        recyclerView = findViewById(R.id.parent_date_Recycler);
        mMonth = Calendar.getInstance().getTime().getMonth() + 1;
        mYear = Calendar.getInstance().get(Calendar.YEAR);
        dialog = new ProgressDialog(Homework.this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(true);
        dialog.show();
        doDateApi(mMonth, mYear);

        linearLayout = (LinearLayout) findViewById(R.id.horilinear);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId = obj.getWebSessionId();
        sSchCode = obj.getsSch_Code();
        lUPIdNo = mPrefs.getString("lUPIdNo", "");
        lClass_IdNo = mPrefs.getString("lClass_IdNo", "");
        lSec_IdNo = mPrefs.getString("lSec_IdNo", "");

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        } else {

            // Code for Below 23 API Oriented Device
            // Do next code
        }


        // preparing list data
        //  prepareListData();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Homework.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Homework");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.homework, 0, 0, 0);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {

                if (groupPosition != previousItem)
                    expListView.collapseGroup(previousItem);
                previousItem = groupPosition;


                /*lastExpandedGroupPosition = groupPosition;*/
            }
        });


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        final int groupPosition, final int childPosition, long id) {
                // TODO Auto-generated method

                if (listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals("")) {

                } else {
                    if (childPosition == 3) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Homework.this);
                        builder.setMessage("Do you want to download the attachment?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            com.syon.isrms.Broadcast_Recievers.MyReceiver myReceiver = new com.syon.isrms.Broadcast_Recievers.MyReceiver();
                                            Homework.this.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                            new DownloadHomeworkFile().execute(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            Uri Download_Uri = Uri.parse(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            String fileExtension = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                                            String fileExt = fileExtension.substring(fileExtension.lastIndexOf("."));

                                            String fileNameWithoutExtension = fileExtension.substring(0, fileExtension.lastIndexOf('.'));


                                            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                                            request.setAllowedOverRoaming(false);
                                            request.setTitle("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setDescription("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setVisibleInDownloadsUi(true);
                                            request.setDestinationInExternalPublicDir("/iSRMS", fileExt);


                                            downloadManager.enqueue(request);


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
                    } else if (childPosition == 4) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Homework.this);
                        builder.setMessage("Do you want to download the attachment?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            com.syon.isrms.Broadcast_Recievers.MyReceiver myReceiver = new com.syon.isrms.Broadcast_Recievers.MyReceiver();
                                            Homework.this.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                            new DownloadHomeworkFile().execute(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            Uri Download_Uri = Uri.parse(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            String fileExtension = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                                            String fileExt = fileExtension.substring(fileExtension.lastIndexOf("."));

                                            String fileNameWithoutExtension = fileExtension.substring(0, fileExtension.lastIndexOf('.'));


                                            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                                            request.setAllowedOverRoaming(false);
                                            request.setTitle("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setDescription("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setVisibleInDownloadsUi(true);
                                            request.setDestinationInExternalPublicDir("/iSRMS", fileExt);


                                            downloadManager.enqueue(request);


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

                    } else if (childPosition == 5) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Homework.this);
                        builder.setMessage("Do you want to download the attachment?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            com.syon.isrms.Broadcast_Recievers.MyReceiver myReceiver = new com.syon.isrms.Broadcast_Recievers.MyReceiver();
                                            Homework.this.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                            new DownloadHomeworkFile().execute(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            Uri Download_Uri = Uri.parse(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            String fileExtension = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);

                                            String fileExt = fileExtension.substring(fileExtension.lastIndexOf("."));

                                            String fileNameWithoutExtension = fileExtension.substring(0, fileExtension.lastIndexOf('.'));


                                            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                                            request.setAllowedOverRoaming(false);
                                            request.setTitle("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setDescription("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setVisibleInDownloadsUi(true);
                                            request.setDestinationInExternalPublicDir("/iSRMS", fileExt);


                                            downloadManager.enqueue(request);


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
                    } else if (childPosition == 6) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Homework.this);
                        builder.setMessage("Do you want to download the attachment?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            com.syon.isrms.Broadcast_Recievers.MyReceiver myReceiver = new com.syon.isrms.Broadcast_Recievers.MyReceiver();
                                            Homework.this.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                            new DownloadHomeworkFile().execute(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            Uri Download_Uri = Uri.parse(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));
                                            String fileExtension = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                                            String fileExt = fileExtension.substring(fileExtension.lastIndexOf("."));
                                            String fileNameWithoutExtension = fileExtension.substring(0, fileExtension.lastIndexOf('.'));
                                            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                                            request.setAllowedOverRoaming(false);
                                            request.setTitle("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setDescription("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setVisibleInDownloadsUi(true);
                                            request.setDestinationInExternalPublicDir("/iSRMS", fileExt);


                                            downloadManager.enqueue(request);


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
                return false;
            }

        });

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

        c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String c_date = format1.format(c.getTime());
        month = c.get(Calendar.MONTH) + 1;
        yer = c.get(Calendar.YEAR);
        final int da = c.get(Calendar.DAY_OF_MONTH);
        yearview.setText("" + yer);

      /*  SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
*/
        // dayname.setText(dayOfTheWeek);
        dayview.setText(c_date);

        prepare(lClass_IdNo, lSec_IdNo, c_date, lSessionId, sSchCode);

        dayslist = new ArrayList();
        inflator = (LayoutInflater) Homework.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (month == 1) {
            monthvalue = "January";
        } else if (month == 2) {
            monthvalue = "February";
        } else if (month == 3) {
            monthvalue = "March";
        } else if (month == 4) {
            monthvalue = "April";
        } else if (month == 5) {
            monthvalue = "May";
        } else if (month == 6) {
            monthvalue = "June";
        } else if (month == 7) {
            monthvalue = "July";
        } else if (month == 8) {
            monthvalue = "August";
        } else if (month == 9) {
            monthvalue = "September";
        } else if (month == 10) {
            monthvalue = "October";
        } else if (month == 11) {
            monthvalue = "November";
        } else if (month == 12) {
            monthvalue = "December";
        }
        monthview.setText(monthvalue);

      /*  String s= String.valueOf((new StringBuilder()
                // Month is 0 based so add 1
                .append(yer).append("_")
                .append(monthvalue).append("_")
                .append(da)));

        prepare(lUPIdNo,lClassId,s,lSessionId,sSchCode);*/
        count = month - 1;
        monthlist = new ArrayList();
        monthlist.clear();
        monthlist.add("January");
        monthlist.add("February");
        monthlist.add("March");
        monthlist.add("April");
        monthlist.add("May");
        monthlist.add("June");
        monthlist.add("July");
        monthlist.add("August");
        monthlist.add("September");
        monthlist.add("October");
        monthlist.add("November");
        monthlist.add("December");

        if (count < monthlist.size() - 1) {
            if (month - 1 != count || !yearview.getText().toString().equalsIgnoreCase("" + yer)) {
                count++;
                monthview.setText(monthlist.get(count).toString());
            } else {
            }
        } else {
            if (count == monthlist.size() - 1 && !yearview.getText().toString().equalsIgnoreCase("" + yer)) {
                count = 0;
                countformonth--;
                monthview.setText(monthlist.get(count).toString());
                yearview.setText("" + getpreviousyear(countformonth));
            }

        }

        linearLayout.removeAllViews();
        int year = Integer.parseInt(yearview.getText().toString());
        int maxdays = getdays(year, count);
        for (int i = 1; i <= maxdays; i++) {
            View detailview = inflator.inflate(R.layout.buttonview, null);
            Button button = (Button) detailview.findViewById(R.id.buttonview);
            TextView textView = detailview.findViewById(R.id.home_exits);
            button.setText("" + i);
            button.setTag(i - 1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (Integer) view.getTag();
                    //  Toast.makeText(getApplicationContext(),""+pos,Toast.LENGTH_SHORT).show();
                    dayview.setText(dayslist.get(pos).toString());
                    prepare(lClass_IdNo, lSec_IdNo, dayslist.get(pos).toString(), lSessionId, sSchCode);

                }
            });
            linearLayout.addView(detailview);
        }
        //horizontalScrollView.addView(linearLayout);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                mMonth++;
                mYear++;
                doDateApi(mMonth, mYear);

                if (count < monthlist.size() - 1) {
                    if (month - 1 != count || !yearview.getText().toString().equalsIgnoreCase("" + yer)) {
                        count++;
                        monthview.setText(monthlist.get(count).toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Homework can't be fetched for upcoming months", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (count == monthlist.size() - 1 && !yearview.getText().toString().equalsIgnoreCase("" + yer)) {
                        count = 0;
                        countformonth--;
                        monthview.setText(monthlist.get(count).toString());
                        yearview.setText("" + getpreviousyear(countformonth));
                    }

                }

                linearLayout.removeAllViews();
                int year = Integer.parseInt(yearview.getText().toString());
                int maxdays = getdays(year, count);
                for (int i = 1; i <= maxdays; i++) {
                    View detailview = inflator.inflate(R.layout.buttonview, null);
                    Button button = (Button) detailview.findViewById(R.id.buttonview);
                    button.setText("" + i);
                    button.setTag(i - 1);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int pos = (Integer) view.getTag();
                            //  Toast.makeText(getApplicationContext(),""+pos,Toast.LENGTH_SHORT).show();
                            dayview.setText(dayslist.get(pos).toString());
                            prepare(lClass_IdNo, lSec_IdNo, dayslist.get(pos).toString(), lSessionId, sSchCode);

                        }
                    });
                    linearLayout.addView(detailview);
                }
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                mMonth--;
                mYear--;
                if (mMonth <= 0) {
                    mMonth = 12;
                }
                if (mMonth >= 12) {
                    mMonth = 1;
                }
                doDateApi(mMonth, mYear);

                if (count > 0) {
                    count--;
                    monthview.setText(monthlist.get(count).toString());
                } else {
                    count = 11;
                    countformonth++;
                    yearview.setText("" + getpreviousyear(countformonth));
                    monthview.setText(monthlist.get(count).toString());
                }
                linearLayout.removeAllViews();
                int year = Integer.parseInt(yearview.getText().toString());
                int maxdays = getdays(year, count);
                for (int i = 1; i <= maxdays; i++) {
                    View detailview = inflator.inflate(R.layout.buttonview, null);
                    Button button = (Button) detailview.findViewById(R.id.buttonview);
                    button.setText("" + i);
                    button.setTag(i - 1);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int pos = (Integer) view.getTag();
                            //Toast.makeText(getApplicationContext(),""+pos,Toast.LENGTH_SHORT).show();
                            dayview.setText(dayslist.get(pos).toString());
                            prepare(lClass_IdNo, lSec_IdNo, dayslist.get(pos).toString(), lSessionId, sSchCode);

                        }
                    });
                    linearLayout.addView(detailview);
                }
            }
        });
    }


    public void dodata(int pos, String day) {
        prepare(lClass_IdNo, lSec_IdNo, dayslist.get(pos).toString(), lSessionId, sSchCode);
      /* ArrayList<String> m_month=new ArrayList<>();*/
       switch (monthview.getText().toString().toUpperCase())
       {
           case "JANUARY" : indexvalue="01";
           break;
           case "FEBRUARY" : indexvalue="02";
               break;
           case "MARCH" : indexvalue="03";
               break;
           case "APRIL" : indexvalue="04";
               break;
           case "MAY" : indexvalue="05";
               break;
           case "JUNE" : indexvalue="06";
               break;
           case "JULY" : indexvalue="07";
               break;
           case "AUGUST" : indexvalue="08";
               break;
           case "SEPTEMBER" : indexvalue="09";
               break;
           case "OCTOBER" : indexvalue="10";
               break;
           case "NOVEMBER" : indexvalue="11";
               break;
           case "DECEMBER" : indexvalue="12";
               break;
       }
      /*  m_month.add("January");
        m_month.add("February");
        m_month.add("March");
        m_month.add("April");
        m_month.add("May");
        m_month.add("June");
        m_month.add("July");
        m_month.add("August");
        m_month.add("September");
        m_month.add("October");
        m_month.add("November");
        m_month.add("December");
       for(int i=0;i<m_month.size();i++)
       {
           if (m_month.get(i).equals(monthview.getText().toString()));
           {
               indexvalue = i + 1;
               break;
           }
       }*/
      if(day.length()==1)
      {
          dayview.setText("0"+day + "-" + indexvalue+ "-" + yearview.getText().toString());
      }
      else
      {
          dayview.setText(day + "-" + indexvalue+ "-" + yearview.getText().toString());
      }



    }

    private void doDateApi(int month, int i) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);

        Call<ParentHomeworkDateBean> call = apiInterfce.getParentHomeworkDates(mPrefs.getString(getString(R.string.lClass_IdNo), ""), mPrefs.getString(getString(R.string.lFac_IdNo), ""), String.valueOf(mMonth), String.valueOf(mYear), mPrefs.getString(getString(R.string.lSessionIdNo), ""), mPrefs.getString(getString(R.string.sSchCode), ""));
        call.enqueue(new Callback<ParentHomeworkDateBean>() {
            @Override
            public void onResponse(Call<ParentHomeworkDateBean> call, retrofit2.Response<ParentHomeworkDateBean> response) {
                if (response.code() == 200) {
                    dialog.dismiss();
                    ParentHomeworkDateBean parentHomeworkDateBean = response.body();
                    dialog.dismiss();
                    List<com.syon.isrms.Beans.Parent_Homework_Date_Data_Bean> data_bean = parentHomeworkDateBean.getData();

                    recyclerView.setLayoutManager(new LinearLayoutManager(Homework.this, LinearLayoutManager.HORIZONTAL, false));
                    recyclerView.scrollToPosition(Calendar.getInstance().get(Calendar.DATE));
                    recyclerView.setAdapter(new Parent_Dates_Adapter(Homework.this, data_bean));

                } else {
                    Toast.makeText(Homework.this, "data not found", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    /*Toast.makeText(Homework.this, mMonth+" "+mYear, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Homework.this, "Something went wrong", Toast.LENGTH_SHORT).show();*/
                }
            }

            @Override
            public void onFailure(Call<ParentHomeworkDateBean> call, Throwable t) {
                Toast.makeText(Homework.this, "check internet connection", Toast.LENGTH_SHORT).show();

            }
        });

        /*call.enqueue(new Callback<ParentHomeworkDateBean>() {
            @Override
            public void onResponse(Call<ParentHomeworkDateBean> call, retrofit2.Response<ParentHomeworkDateBean> response) {
                if (response.code() == 200) {
                    ParentHomeworkDateBean parentHomeworkDateBean = response.body();
                    List<Parent_Homework_Date_Data_Bean> data = parentHomeworkDateBean.getData();
                    Toast.makeText(Homework.this, data.get(4) + "", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("TAG", response.code() + "");
                    Log.e("Tag", lClass_IdNo + " " + lSec_IdNo + " " + "4 " + "2017 " + lSessionId + " " + sSchCode);
                    Toast.makeText(Homework.this, lClass_IdNo + "" + lSec_IdNo + "4" + "2017" + lSessionId + sSchCode + "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParentHomeworkDateBean> call, Throwable t) {
                Toast.makeText(Homework.this, "check internet connection", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Homework.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Homework.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Homework.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Homework.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    public int getpreviousyear(int value) {
        Calendar prevYear = Calendar.getInstance();
        // Toast.makeText(Homework.this, "" + value, Toast.LENGTH_SHORT).show();
        int yer = prevYear.get(Calendar.YEAR) - value;
        return yer;
    }

    public int getcurrentyear(int value) {
        Calendar prevYear = Calendar.getInstance();
        int yer = prevYear.get(Calendar.YEAR) + value;
        return yer;
    }

    public int getdays(int year, int month) {
        int iYear = year;
        int iMonth = month;
        int iDay = 1;
        dayslist.clear();
      /*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int numDays = calendar.getActualMaximum(Calendar.DATE);
*/
        Calendar mycal = new GregorianCalendar(iYear, iMonth, iDay);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");

        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int myMonth = mycal.get(Calendar.MONTH);
        Calendar mycalone = new GregorianCalendar(iYear, iMonth, 0);
        mycalone.add(Calendar.DAY_OF_MONTH, 1);
        dayslist.add("" + format1.format(mycalone.getTime()));
        while (myMonth == mycal.get(Calendar.MONTH)) {
            System.out.print(mycal.getTime());
            mycal.add(Calendar.DAY_OF_MONTH, 1);
            dayslist.add("" + format1.format(mycal.getTime()));
        }
        return daysInMonth;
    }

    public void t() {
        Toast.makeText(ctx, "hello", Toast.LENGTH_SHORT).show();
    }

    public void prepare(String lClass_IdNo, String lSec_IdNo, String sHWDate, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Homework.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL + "StudHomeWork/?lClassId=" + lClass_IdNo + "&lSecId=" + lSec_IdNo + "&sHWDate=" + sHWDate + "&lSessionId=" + lSessionId + "&sSchCode=" + sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!listDataHeader.isEmpty())
                            listDataHeader.clear();

                        if (!listDataChild.isEmpty())
                            listDataChild.clear();
                        Log.d("Resopnce----", response);
                        try {

                            JSONObject obj = new JSONObject(response);

                            if (obj.getString("status").equals("ok")) {
                                JSONArray jsonArray = obj.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    String complDate = ob.getString("complDate");
                                    String sSubject = ob.getString("sSubject");
                                    String sTitle = ob.getString("sTitle");
                                    String sHomeWork = ob.getString("sHomeWork");
                                    String attachment1 = ob.getString("attachment1");
                                    String attachment2 = ob.getString("attachment2");
                                    String attachment3 = ob.getString("attachment3");
                                    String attachment4 = ob.getString("attachment4");
                                    listDataHeader.add(sSubject);
                                    if (attachment1.isEmpty() && attachment2.isEmpty() && attachment3.isEmpty() && attachment4.isEmpty()) {
                                        data = new ArrayList<>();
                                        data.add(complDate);
                                        data.add(sTitle);
                                        data.add(sHomeWork);
                                    } else if (attachment2.isEmpty() && attachment3.isEmpty() && attachment4.isEmpty()) {
                                        data = new ArrayList<>();
                                        data.add(complDate);
                                        data.add(sTitle);
                                        data.add(sHomeWork);
                                        data.add(attachment1);
                                    } else if (attachment3.isEmpty() && attachment4.isEmpty()) {
                                        data = new ArrayList<>();
                                        data.add(complDate);
                                        data.add(sTitle);
                                        data.add(sHomeWork);
                                        data.add(attachment1);
                                        data.add(attachment2);
                                    } else if ((attachment4.isEmpty())) {
                                        data = new ArrayList<>();
                                        data.add(complDate);
                                        data.add(sTitle);
                                        data.add(sHomeWork);
                                        data.add(attachment1);
                                        data.add(attachment2);
                                        data.add(attachment3);
                                    } else if (!attachment1.isEmpty() && !attachment2.isEmpty() && !attachment3.isEmpty() && !attachment4.isEmpty()) {

                                        data = new ArrayList<>();
                                        data.add(complDate);
                                        data.add(sTitle);
                                        data.add(sHomeWork);
                                        data.add(attachment1);
                                        data.add(attachment2);
                                        data.add(attachment3);
                                        data.add(attachment4);
                                    }
                                    listDataChild.put(listDataHeader.get(i), data);

                                }

                                listAdapter = new ExpandableListAdapter(Homework.this, listDataHeader, listDataChild);
                                // setting list adapter
                                expListView.setAdapter(listAdapter);
                                expListView.setVisibility(View.VISIBLE);
                              /*  for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterHomework wp = new GetterSetterHomework(map.get("sSubject").toString(),map.get("sTitle").toString(),map.get("sHomeWork").toString(),map.get("attachment1").toString());
                                    array.add(wp);
                                }
                                listAdapter = new ExpandableListAdapter(Homework.this, array);
                                expListView.setAdapter(listAdapter);*/

                            } else if (obj.getString("status").equals("error")) {
                                pd.hide();
                                datanotfound.setVisibility(View.VISIBLE);
                                expListView.setVisibility(View.GONE);


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception", e.toString());
                            // avloader.hide();
                            pd.dismiss();
                            datanotfound.setVisibility(View.VISIBLE);
                            expListView.setVisibility(View.GONE);
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                        datanotfound.setVisibility(View.VISIBLE);
                        expListView.setVisibility(View.GONE);

                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Homework.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    @Override
    protected void onResume() {
        super.onResume();


    }


    public class ExpandableListAdapter extends BaseExpandableListAdapter {
        private Context context;
        private List<String> listDataHeader;
        private HashMap<String, List<String>> listHashMap;

        public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
            this.context = context;
            this.listDataHeader = listDataHeader;
            this.listHashMap = listHashMap;
        }

        @Override
        public int getGroupCount() {
            return listDataHeader.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return listHashMap.get(listDataHeader.get(i)).size();
        }

        @Override
        public Object getGroup(int i) {
            return listDataHeader.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return listHashMap.get(listDataHeader.get(i)).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            String headerTitle = (String) getGroup(i);

            Log.e("grrr", String.valueOf(getGroup(i)));

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_group, null);
            }
            TextView lblListHeader = (TextView) view.findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            final String childText = (String) getChild(i, i1);
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_item, null);
            }
            TextView textView = view.findViewById(R.id.lblListItemtext);
            TextView txtListChild = view.findViewById(R.id.lblListItem);
            ImageView btn = view.findViewById(R.id.downloadbtn);

            String filen = childText.substring(childText.lastIndexOf("/") + 1);
            if (i1 == 0) {
                textView.setText("Completion Date : ");
                txtListChild.setText(childText);
                btn.setVisibility(View.GONE);
            }
            if (i1 == 1) {
                textView.setText("Title : ");
                txtListChild.setText(childText);
                /* txtListChild.setText(Html.fromHtml("Title : <u>" + childText + "</u>"));*/
                btn.setVisibility(View.GONE);
            }
            if (i1 == 2) {
                textView.setText("Description : ");
                txtListChild.setText(childText);
                btn.setVisibility(View.GONE);
            }
            if (i1 == 3) {
                textView.setText("Attachment : ");
                String filen1 = childText.substring(childText.lastIndexOf("/") + 1);
                if (filen1.length() >= 1) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
                txtListChild.setText(filen1);
            }
            if (i1 == 4) {
                textView.setText("Attachment : ");
                String filen2 = childText.substring(childText.lastIndexOf("/") + 1);
                if (filen2.length() >= 1) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
                txtListChild.setText(filen2);
            }
            if (i1 == 5) {
                textView.setText("Attachment : ");
                String filen3 = childText.substring(childText.lastIndexOf("/") + 1);
                if (filen3.length() >= 1) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
                txtListChild.setText(filen3);

            }
            if (i1 == 6) {
                textView.setText("Attachment : ");
                String filen4 = childText.substring(childText.lastIndexOf("/") + 1);
                if (filen4.length() >= 1) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
                txtListChild.setText(filen4);

            }

            /*txtListChild.setText(Html.fromHtml("Title : <u>"+childText+"</u>"));*/

            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {

            return true;
        }

    }

    public class DownloadHomeworkFile extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            progressDialog = new ProgressDialog(Homework.this);
            // Set your progress dialog Title
            progressDialog.setTitle("Homework");
            // Set your progress dialog Message
            progressDialog.setMessage("Downloading, Please Wait!");
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // Show progress dialog
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... Url) {
            try {
                URL url = new URL(Url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // Detect the file lenghth
                int fileLength = connection.getContentLength();

                // Locate storage location
                String filepath = Environment.getExternalStorageDirectory()
                        .getPath();

                // Download the file
                InputStream input = new BufferedInputStream(url.openStream());

                // Save the downloaded file
                OutputStream output = new FileOutputStream(filepath + "/"
                        + "testimage.jpg");

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Publish the progress
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }

                // Close connection
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                // Error Log
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            progressDialog.setProgress(progress[0]);
            // Dismiss the progress dialog
            if (progressDialog.getProgress() == 100) {
                progressDialog.dismiss();
            }
            /* progressDialog.dismiss();*/
        }
    }

}
