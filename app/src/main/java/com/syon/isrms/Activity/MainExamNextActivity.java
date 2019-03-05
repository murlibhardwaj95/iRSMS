package com.syon.isrms.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainExamNextActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<com.syon.isrms.Model_Class.GetterSet> array = new ArrayList<com.syon.isrms.Model_Class.GetterSet>();

    private SharedPreferences mPrefs ;
    String lUPIdNo,lClass_IdNo,lSec_IdNo;
    String lSessionId;
    String sSchCode,getName;
    static String dataaa;
    AdepterClassTi adapter;
    String examid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exam_next);
        listView=(ListView)findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        examid=getIntent().getStringExtra("id");
        /*LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainExamNextActivity.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Exam Time Table");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.timtab, 0, 0, 0);*/
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainExamNextActivity.super.onBackPressed();
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

        mPrefs = PreferenceManager.getDefaultSharedPreferences(MainExamNextActivity.this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");
        lClass_IdNo=mPrefs.getString("lClass_IdNo","");
        lSec_IdNo=mPrefs.getString(getString(R.string.lFac_IdNo),"");

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            getName = (String) bd.get("id");
        }

        ClassTiii(lClass_IdNo,lSec_IdNo,lSessionId,sSchCode,getName);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void ClassTiii(String lClass_IdNo, String lSec_IdNo, String lSessionId, String sSchCode, String getName) {
        final ProgressDialog pd = new ProgressDialog(MainExamNextActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"ExamTimeTable/?lClassId="+ lClass_IdNo + "&lStreamId="+ lSec_IdNo +"&lExamId="+ examid +"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    map.put("dtExamDate", ob.getString("dtExamDate"));
                                    map.put("sExamTime", ob.getString("sExamTime"));
                                    map.put("sWeekDay", ob.getString("sWeekDay"));
                                    map.put("sSubject", ob.getString("sSubject"));
                                    map.put("sPaper", ob.getString("sPaper"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    com.syon.isrms.Model_Class.GetterSet wp = new com.syon.isrms.Model_Class.GetterSet(map.get("dtExamDate").toString(),map.get("sExamTime").toString(),map.get("sWeekDay").toString(),map.get("sSubject").toString(),map.get("sPaper").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterClassTi(MainExamNextActivity.this, array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();
                              /*  MyToast.show(MainExamNextActivity.this,"No Data Available !!!",false);*/



                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                  /*          MyToast.show(MainExamNextActivity.this,"Server Data Error Try Again !!!",false);*/
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();


                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainExamNextActivity.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private class AdepterClassTi extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<com.syon.isrms.Model_Class.GetterSet> customerDatalist = null;
        private ArrayList<com.syon.isrms.Model_Class.GetterSet> array;
        String sp_edit;


        public AdepterClassTi(Context context, List<com.syon.isrms.Model_Class.GetterSet> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<com.syon.isrms.Model_Class.GetterSet>();
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
        public com.syon.isrms.Model_Class.GetterSet getItem(int position) {
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
            view = inflater.inflate(R.layout.customvi, null);
            view.setTag(holder);
            holder.dtExamDate = (TextView) view.findViewById(R.id.dtExamDate);
            holder.sExamTime = (TextView) view.findViewById(R.id.sExamTime);
            holder.sWeekDay = (TextView) view.findViewById(R.id.sWeekDay);
            holder.sSubject = (TextView) view.findViewById(R.id.sSubject);
            holder.sPaper = (TextView) view.findViewById(R.id.sPaper);

            holder.dtExamDate.setText(customerDatalist.get(position).getDtExamDate());
            holder.sExamTime.setText(customerDatalist.get(position).getsExamTime());
            holder.sWeekDay.setText(customerDatalist.get(position).getsWeekDay());
            holder.sSubject.setText(customerDatalist.get(position).getsSubject());
            holder.sPaper.setText(customerDatalist.get(position).getsPaper());




            return view;
        }

    }


}
