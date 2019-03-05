package com.syon.isrms.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.google.gson.Gson;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leave extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<com.syon.isrms.Model_Class.GetterSetLeave> array = new ArrayList<com.syon.isrms.Model_Class.GetterSetLeave>();

    private SharedPreferences mPrefs ;
    String lUPIdNo;
    String lSessionId;
    String sSchCode,getName;
    static String dataaa;
    Dialog dialog;
    Context ctx=this;
    EditText date1,date2;
    AdepterClassTi adapter;
    private Calendar mcalendar = Calendar.getInstance();

    int day,month,year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        day=mcalendar.get(Calendar.DAY_OF_MONTH);
        year=mcalendar.get(Calendar.YEAR);
        month=mcalendar.get(Calendar.MONTH);

        listView=(ListView)findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      /*  LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Leave.super.onBackPressed();
            }
        });
*/


        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leave.super.onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 dialog = new Dialog(Leave.this);
                dialog.setContentView(R.layout.leavedialog);
                dialog.setTitle("Forget Password");
                ImageView cancel=(ImageView)dialog.findViewById(R.id.cancel);
                 date1=(EditText) dialog.findViewById(R.id.date1);
                 date2=(EditText) dialog.findViewById(R.id.date2);
                final EditText reason=(EditText) dialog.findViewById(R.id.reason);
                Button submit=(Button)dialog.findViewById(R.id.submit);



                date1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DateDialog();
                    }
                });

                date2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DateDialog2();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String dat1=date1.getText().toString();
                        final String dat2=date2.getText().toString();
                        final String desc=reason.getText().toString();
                        LeaveSubmit(lUPIdNo,lSessionId,sSchCode,dat1,dat2,desc);
                    }
                });
                dialog.show();

            }
        });

        /*TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Leave");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timtab, 0, 0, 0);
*/
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

        mPrefs = PreferenceManager.getDefaultSharedPreferences(Leave.this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            getName = (String) bd.get("id");
        }

        ClassTiii(lUPIdNo,lSessionId,sSchCode);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


    private void LeaveSubmit(final String lUPIdNo, final String lSessionId, final String sSchCode, final String dat1, final String dat2, final String desc) {


        final ProgressDialog pd = new ProgressDialog(Leave.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request= new StringRequest(Request.Method.POST, ApiClient.BASE_URL+"SaveStudApplyLeave",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Login.longLog(response);
                        Log.e("my response is",response);
                        try {
                            JSONObject json=new JSONObject(response);
                            if (json.getString("status").equalsIgnoreCase("ok")){

                                Toast.makeText(ctx,"Entry Saved Successfully", Toast.LENGTH_SHORT).show();
                                pd.hide();
                                dialog.dismiss();
                                ClassTiii(lUPIdNo,lSessionId,sSchCode);

                                //   ForgetAgain();

                            } else if (json.getString("status").equalsIgnoreCase("error")){
                                Toast.makeText(ctx,"Error", Toast.LENGTH_SHORT).show();
                                pd.hide();

                            }

                        }catch (JSONException e){
                            Toast.makeText(ctx,"Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                            Login.longLog("Json --------- Exception..."+e.toString());
                            pd.hide();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.hide();
                        Toast.makeText(ctx,"Incorrect User Credentials", Toast.LENGTH_SHORT).show();
                        // MyToast.show(Login.this,"", false);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("upid",lUPIdNo);
                map.put("fromdate",dat1);
                map.put("todate",dat2);
                map.put("reason",desc);
                map.put("sessionid",lSessionId);
                map.put("schoolcode",sSchCode);

                Log.e("upid",lUPIdNo);
                Log.e("fromdate",dat1);
                Log.e("todate",dat2);
                Log.e("reason",desc);
                Log.e("sessionid",lSessionId);
                Log.e("schoolcode",sSchCode);
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

    private void DateDialog2() {

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String dateString = dateFormat.format(calendar.getTime());

                date2.setText(dateString);

            }};
        DatePickerDialog dpDialog=new DatePickerDialog(Leave.this, listener, year, month, day);

        dpDialog.show();
    }

    private void DateDialog() {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                String dateString = dateFormat.format(calendar.getTime());

                date1.setText(dateString);

            }};
        DatePickerDialog dpDialog=new DatePickerDialog(Leave.this, listener, year, month, day);
        dpDialog.show();

    }

    private void ClassTiii(String lUPIdNo, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Leave.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudApplyLeaveList/?lUPIdNo="+lUPIdNo +"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resopnce----",response);
                        if (!CatlistDes.isEmpty())
                            CatlistDes.clear();
                        if (!array.isEmpty())
                            array.clear();
                        try {
                            JSONObject obj=new JSONObject(response);
                            if(obj.getString("status").equals("ok")) {
                                JSONArray jsonArray = obj.getJSONArray("data");
                                for (int i=0;i<jsonArray.length();i++) {
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("fromDate", ob.getString("fromDate"));
                                    map.put("toDate", ob.getString("toDate"));
                                    map.put("reason", ob.getString("reason"));
                                    map.put("approved", ob.getString("approved"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    com.syon.isrms.Model_Class.GetterSetLeave wp = new com.syon.isrms.Model_Class.GetterSetLeave(map.get("fromDate").toString(),map.get("toDate").toString(),map.get("reason").toString(),map.get("approved").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterClassTi(Leave.this, array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();
                                LinearLayout layout=findViewById(R.id.datanotfound);
                                layout.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                           /* MyToast.show(Leave.this,"Server Data Error Try Again !!!",false);*/
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                       /* MyToast.show(Leave.this, "Connection Time Out Error Try Again !!",false);*/
                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Leave.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private class AdepterClassTi extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<com.syon.isrms.Model_Class.GetterSetLeave> customerDatalist = null;
        private ArrayList<com.syon.isrms.Model_Class.GetterSetLeave> array;
        String sp_edit;


        public AdepterClassTi(Context context, List<com.syon.isrms.Model_Class.GetterSetLeave> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<com.syon.isrms.Model_Class.GetterSetLeave>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView dtExamDate, sExamTime, sWeekDay,sSubject,sPaper;
            CheckBox bal;
            ImageView removetocart;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public com.syon.isrms.Model_Class.GetterSetLeave getItem(int position) {
            return customerDatalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.customvileave, null);
            view.setTag(holder);
            holder.dtExamDate = (TextView) view.findViewById(R.id.fromDate);
            holder.sExamTime = (TextView) view.findViewById(R.id.toDate);
            holder.sWeekDay = (TextView) view.findViewById(R.id.reason);
            holder.sSubject = (TextView) view.findViewById(R.id.approved);

            holder.dtExamDate.setText(customerDatalist.get(position).getFromDate());
            holder.sExamTime.setText(customerDatalist.get(position).getToDate());
            holder.sWeekDay.setText(customerDatalist.get(position).getReason());
            holder.sSubject.setText(customerDatalist.get(position).getApproved());

            return view;
        }

    }


}
