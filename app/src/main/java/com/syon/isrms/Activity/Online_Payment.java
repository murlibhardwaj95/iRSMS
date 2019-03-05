package com.syon.isrms.Activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.syon.isrms.Interfaces_Teacher.ApiClient.BASE_URL;

public class Online_Payment extends AppCompatActivity {

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
    WebView webView;
    TextView heading,sTxnDate,sRecvdAmt,sExpenseAmt,sBalAmt,sDentalHygiene,sBloodGroup,sAllergy,sHealthProblem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online__payment);

        webView=(WebView)findViewById(R.id.webview);
        datanotfound=(LinearLayout)findViewById(R.id.datanotfound);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(Online_Payment.this);

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
                Online_Payment.super.onBackPressed();
            }
        });
        TextView logoicon=(TextView)mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Online Payment");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.onlinepayment, 0, 0, 0);
*/
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Online_Payment.super.onBackPressed();
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
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


    private void FessDataaa(final String lUPIdNo, final String lSessionId, final String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Online_Payment.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL+"StudFeeOnlinePayment",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resopnce----",response);
                        if (!CatlistDes.isEmpty())
                            CatlistDes.clear();
                        try {
                            JSONObject obj=new JSONObject(response);
                            if(obj.getString("status").equals("ok")) {
                                webView.setWebViewClient(new myWebClient());
                                webView.getSettings().setJavaScriptEnabled(true);
                                webView.loadUrl(obj.getString("data"));
                                pd.hide();

                            }
                            else if (obj.getString("status").equals("error")) {
                                webView.setVisibility(View.GONE);
                                datanotfound.setVisibility(View.VISIBLE);
                                pd.hide();
                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            webView.setVisibility(View.GONE);
                            datanotfound.setVisibility(View.VISIBLE);
                            pd.hide();
                           /* MyToast.show(Online_Payment.this,"Server Data Error Try Again !!!",false);*/
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        webView.setVisibility(View.GONE);
                        datanotfound.setVisibility(View.VISIBLE);
                        pd.hide();
                 /*       MyToast.show(Online_Payment.this, "Connection Time Out Error Try Again !!",false);*/
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("upid",lUPIdNo);
                map.put("sessionid",lSessionId);
                map.put("schoolcode",sSchCode);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(Online_Payment.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public class myWebClient extends WebViewClient
    {
        /* ProgressDialog prDialog;*/
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
// TODO Auto-generated method stub
            /* prDialog = ProgressDialog.show(Online_Payment.this, null, "loading, please wait...");*/
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            /* if(prDialog.isShowing()) {
             *//*prDialog.hide();
                prDialog.dismiss();*//*
            }*/
            super.onPageFinished(view, url);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
// TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}



/*
    private void FessData(String lUPIdNo, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Online_Payment.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://www.webschoolapi.syontechnologies.com/api/FeeOutstanding/?lUPIdNo="+lUPIdNo + "&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    map.put("installment", ob.getString("installment"));
                                    map.put("dueAmount", ob.getString("dueAmount"));
                                    map.put("lateFee", ob.getString("lateFee"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterOnlinePay wp = new GetterSetterOnlinePay(map.get("installment").toString(),map.get("dueAmount").toString(),map.get("lateFee").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterOnlinePay(Online_Payment.this, array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                            MyToast.show(Online_Payment.this,"Server Data Error Try Again !!!",false);
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                        MyToast.show(Online_Payment.this, "Connection Time Out Error Try Again !!",false);
                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Online_Payment.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }
*/

/*
    private class AdepterOnlinePay extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<GetterSetterOnlinePay> customerDatalist = null;
        private ArrayList<GetterSetterOnlinePay> array;
        String sp_edit;


        public AdepterOnlinePay(Context context, List<GetterSetterOnlinePay> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<GetterSetterOnlinePay>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView month_name,due,paid;
            CheckBox bal;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public GetterSetterOnlinePay getItem(int position) {
            return customerDatalist.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {

                holder = new ViewHolder();
                view = inflater.inflate(R.layout.listlayoutonlinepay, null);
                view.setTag(holder);
                holder.month_name = (TextView) view.findViewById(R.id.month);
                holder.due = (TextView) view.findViewById(R.id.due);
                holder.paid = (TextView) view.findViewById(R.id.paid);
                holder.bal = (CheckBox) view.findViewById(R.id.bal);

            }
            else {
                holder = (ViewHolder) view.getTag();
                holder.bal.setOnCheckedChangeListener(null);
                //update the checkbox value from boolean array
            }


            holder.bal
                    .setTag(R.id.bal, position);
            holder.bal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,  boolean isChecked) {

                    int position = (Integer) buttonView
                            .getTag(R.id.bal);


                    int a= Integer.parseInt(customerDatalist.get(position).getDueAmount());
                    int b=Integer.parseInt(customerDatalist.get(position).getLateFee());
                    Log.e("a", String.valueOf(a));
                    Log.e("b", String.valueOf(b));
                    int c=a+b;

                    if (isChecked) {
                        sum=sum+c;
                        Log.e("sum", String.valueOf(sum));
                        finalvalue.setText(getString(R.string.rupee)+" "+String.valueOf(sum));
                    } else {
                        sum=sum-c;
                        finalvalue.setText(getString(R.string.rupee)+" "+String.valueOf(sum));
                    }
                }
            });
          */
/*  holder.bal.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (holder.bal.isChecked())
                    {

                        int a= Integer.parseInt(customerDatalist.get(position).getDueAmount());
                        int b=Integer.parseInt(customerDatalist.get(position).getLateFee());
                        int c=a+b;
                        finalvalue.setText(String.valueOf(c));

                        //Perform action when you touch on checkbox and it change to selected state
                    }
                    else
                    {
                        finalvalue.setText("0");
                        //Perform action when you touch on checkbox and it change to unselected state
                    }
                }
            });*//*


            holder.month_name.setText(customerDatalist.get(position).getInstallment());
            holder.due.setText(customerDatalist.get(position).getDueAmount());
            holder.paid.setText(customerDatalist.get(position).getLateFee());

            return view;
        }
    }
*/



