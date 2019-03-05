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
import android.view.LayoutInflater;
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
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Detail_Tuckshop extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__tuckshop);
        webView=(WebView)findViewById(R.id.webview);
        datanotfound=(LinearLayout)findViewById(R.id.datanotfound);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(Detail_Tuckshop.this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        FessDataaa(lUPIdNo,lSessionId,sSchCode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Detail_Tuckshop.super.onBackPressed();
            }
        });
        TextView logoicon=(TextView)mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Tuckshop Detail");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tuc, 0, 0, 0);

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

    private void FessDataaa(final String lUPIdNo, final String lSessionId, final String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Detail_Tuckshop.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiClient.BASE_URL+"StudTSTransDetail",
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

                            }
                            else if (obj.getString("status").equals("error")) {
                                pd.hide();
                                webView.setVisibility(View.GONE);
                                datanotfound.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                            webView.setVisibility(View.GONE);
                            datanotfound.setVisibility(View.VISIBLE);
                        /*    MyToast.show(Detail_Tuckshop.this,"Server Data Error Try Again !!!",false);*/
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                        webView.setVisibility(View.GONE);
                        datanotfound.setVisibility(View.VISIBLE);
               /*         MyToast.show(Detail_Tuckshop.this, "Connection Time Out Error Try Again !!",false);*/
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
        RequestQueue requestQueue = Volley.newRequestQueue(Detail_Tuckshop.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }



    public class myWebClient extends WebViewClient
    {
        ProgressDialog prDialog;
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
// TODO Auto-generated method stub
            prDialog = ProgressDialog.show(Detail_Tuckshop.this, null, "Loading...");
            prDialog.setCancelable(false);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            prDialog.dismiss();
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

