package com.syon.isrms.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.LinearLayout;
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

public class Observation extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<com.syon.isrms.Model_Class.GetterSetterObservation> array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterObservation>();

    private SharedPreferences mPrefs ;
    String lUPIdNo;
    String lSessionId;
    String sSchCode,getName;
    static String dataaa;
    LinearLayout datanotfound;
    AdepterClassTi adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);
        datanotfound=(LinearLayout)findViewById(R.id.datanotfound);
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
                Observation.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Observation");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ob, 0, 0, 0);*/
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observation.super.onBackPressed();
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

        mPrefs = PreferenceManager.getDefaultSharedPreferences(Observation.this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        ClassTiii(lUPIdNo,lSessionId,sSchCode);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void ClassTiii(String getName, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Observation.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudObservation/?lUPIdNo="+getName +"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    map.put("dtObsDate", ob.getString("dtObsDate"));
                                    map.put("sTeacherName", ob.getString("sTeacherName"));
                                    map.put("sRemark", ob.getString("sRemark"));
                                    map.put("lTeacherId", ob.getString("lTeacherId"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    com.syon.isrms.Model_Class.GetterSetterObservation wp = new com.syon.isrms.Model_Class.GetterSetterObservation(map.get("dtObsDate").toString(),map.get("sTeacherName").toString(),map.get("sRemark").toString(),map.get("lTeacherId").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterClassTi(Observation.this, array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {

                                datanotfound.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                                pd.hide();
                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                            datanotfound.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                         /*   MyToast.show(Observation.this,"Server Data Error Try Again !!!",false);*/
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
                        listView.setVisibility(View.GONE);
                       /* MyToast.show(Observation.this, "Connection Time Out Error Try Again !!",false);*/
                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Observation.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private class AdepterClassTi extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<com.syon.isrms.Model_Class.GetterSetterObservation> customerDatalist = null;
        private ArrayList<com.syon.isrms.Model_Class.GetterSetterObservation> array;
        String sp_edit;


        public AdepterClassTi(Context context, List<com.syon.isrms.Model_Class.GetterSetterObservation> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterObservation>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView dtObsDate, sTeacherName, sRemark,readmore,sPaper;
            CheckBox bal;
            View vie;
            ImageView removetocart;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public com.syon.isrms.Model_Class.GetterSetterObservation getItem(int position) {
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
            view = inflater.inflate(R.layout.custom_ob, null);
            view.setTag(holder);
            holder.dtObsDate = (TextView) view.findViewById(R.id.dtObsDate);
            holder.sTeacherName = (TextView) view.findViewById(R.id.sTeacherName);
            holder.sRemark = (TextView) view.findViewById(R.id.sRemark);
            holder.readmore=(TextView)view.findViewById(R.id.readmore);
            holder.vie=(View)view.findViewById(R.id.view);

            holder.dtObsDate.setText(customerDatalist.get(position).getDtObsDate());
            holder.sTeacherName.setText(customerDatalist.get(position).getsTeacherName());

            String da=customerDatalist.get(position).getsRemark();

            int strLength  = da.length();

            Log.e("size", String.valueOf(strLength));


            if (strLength>=100){
                holder.readmore.setVisibility(View.VISIBLE);
                holder.vie.setVisibility(View.VISIBLE);
                holder.sRemark.setText(customerDatalist.get(position).getsRemark().substring(0,99)+"...");

            }
            else {
                holder.sRemark.setText(customerDatalist.get(position).getsRemark());

            }



            holder.readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(Observation.this);
                    dialog.setContentView(R.layout.alertdi);
                    TextView tx=(TextView)dialog.findViewById(R.id.tx);
                    TextView heading=(TextView)dialog.findViewById(R.id.heading);
                    ImageView im=(ImageView)dialog.findViewById(R.id.cancel);
                    tx.setText(customerDatalist.get(position).getsRemark());
                    heading.setText(customerDatalist.get(position).getsTeacherName());
                    im.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.setTitle("Forget Password");
                    dialog.show();



                }
            });


            return view;
        }

    }


}
