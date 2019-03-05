package com.syon.isrms.Activity;

import android.app.DownloadManager;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Tuckshop extends AppCompatActivity {

    ListView listView;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    static String dataaa;
    Context ctx=this;
    private static final String TAG = "Something";

    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();

    private SharedPreferences mPrefs ;
    private static final int PERMISSION_REQUEST_CODE = 1;
    LinearLayout datanotfound;
    DownloadManager downloadManager;
    ImageView iv_offers;
    Button detail;
    TextView heading,sTxnDate,sRecvdAmt,sExpenseAmt,sBalAmt,sBloodGroup,sAllergy,sHealthProblem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuckshop);
        heading=(TextView)findViewById(R.id.heading);
        detail=(Button)findViewById(R.id.detail);
        sTxnDate=(TextView)findViewById(R.id.sTxnDate);
        sRecvdAmt=(TextView)findViewById(R.id.sRecvdAmt);
        sExpenseAmt=(TextView)findViewById(R.id.sExpenseAmt);
        sBalAmt=(TextView)findViewById(R.id.sBalAmt);
        iv_offers=(ImageView)findViewById(R.id.iv_offers);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(Tuckshop.this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        FessDataaa(lUPIdNo,lSessionId,sSchCode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tuckshop.super.onBackPressed();
            }
        });
        TextView logoicon=(TextView)mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Tuckshop");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tuc, 0, 0, 0);*/
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tuckshop.super.onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#A14D3F"));
        }
        toolbar.setTitleMargin(0,0,0,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            toolbar.setPaddingRelative(0,0,0,0);
        }
        toolbar.setPadding(0,0,0,0);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tuckshop.this, Detail_Tuckshop.class));
                overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


    private void FessDataaa(String lUPIdNo, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Tuckshop.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudTSTransSumry/?lUPIdNo=" + lUPIdNo +"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resopnce----",response);
                        if (!CatlistDes.isEmpty())
                            CatlistDes.clear();
                        try {
                            JSONObject obj=new JSONObject(response);
                            if(obj.getString("status").equals("ok")) {
                                JSONObject jsonArray = obj.getJSONObject("data");
                                // heading.setText(ob.getString("classTeacherName"));
                                // holder.bt.setText(customerDatalist.get(position).getsBirthday());
                                sTxnDate.setText(jsonArray.getString("sTxnDate"));
                                sRecvdAmt.setText(jsonArray.getString("sRecvdAmt"));
                                sExpenseAmt.setText(jsonArray.getString("sExpenseAmt"));
                                sBalAmt.setText(jsonArray.getString("sBalAmt"));
                                if (sTxnDate.getText().toString().length()<=0&&sRecvdAmt.getText().toString().length()<=0&&sExpenseAmt.getText().toString().length()<=0&&sBalAmt.getText().toString().length()<=0)
                                {
                                    LinearLayout linearLayout=findViewById(R.id.datanotfound);
                                    linearLayout.setVisibility(View.VISIBLE);
                                    LinearLayout linearLayout1=findViewById(R.id.tuck_data);
                                    linearLayout1.setVisibility(View.GONE);
                                }
                                else {
                                    LinearLayout linearLayout=findViewById(R.id.datanotfound);
                                    linearLayout.setVisibility(View.GONE);
                                    LinearLayout linearLayout1=findViewById(R.id.tuck_data);
                                    linearLayout1.setVisibility(View.VISIBLE);

                                }

                            }
                            else if (obj.getString("status").equals("error")) {
                                pd.hide();


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();

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
        RequestQueue requestQueue = Volley.newRequestQueue(Tuckshop.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }








}

