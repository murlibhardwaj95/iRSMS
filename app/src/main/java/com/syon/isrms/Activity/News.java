package com.syon.isrms.Activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.syon.isrms.Adapter.NewsAdapter;
import com.syon.isrms.Beans.Userbean_News_Data;
import com.syon.isrms.Beans.Userbean_News_Main;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.Model_Class.GetterSetterNews;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class News extends AppCompatActivity {

    ListView listView;
    String lUPIdNo,lClass_IdNo,lSec_IdNo,nStudType;
    String lSessionId;
    String sSchCode;
    static String dataaa;
    Context ctx=this;
    RecyclerView listnews;
    ApiInterfce apiInterfce;

  /*  AdepterNews adapter;*/
    private static final String TAG = "Something";

    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<GetterSetterNews> array = new ArrayList<GetterSetterNews>();

    private SharedPreferences mPrefs ;
    private static final int PERMISSION_REQUEST_CODE = 1;

    DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView=(ListView)findViewById(R.id.list);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(News.this);

        SharedPreferences.Editor countEditor=mPrefs.edit();
        countEditor.putInt("News",0);
        countEditor.commit();


        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {

            // Code for Below 23 API Oriented Device
            // Do next code
        }


        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");
        lClass_IdNo=mPrefs.getString("lClass_IdNo","");
        lSec_IdNo=mPrefs.getString("lSec_IdNo","");
        nStudType=mPrefs.getString("nStudType","");

        FessData(lClass_IdNo,lSec_IdNo,nStudType,lSessionId,sSchCode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      /*  LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                News.super.onBackPressed();
            }
        });
        TextView logoicon=(TextView)mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("News");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.newss, 0, 0, 0);*/
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News.super.onBackPressed();
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
        init();
        doApiOperation();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }



    private void doApiOperation() {

    }

    private void init() {
        listnews=findViewById(R.id.listnews);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(News.this);

    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(News.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(News.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(News.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(News.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
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

    private void FessData(String lClass_IdNo, String lSec_IdNo, String nStudType, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(News.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        try{


            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Userbean_News_Main> newscall = apiInterfce.newsparent(lClass_IdNo,"1",lSessionId,sSchCode);
            newscall.enqueue(new Callback<Userbean_News_Main>() {
                @Override
                public void onResponse(Call<Userbean_News_Main> call, retrofit2.Response<Userbean_News_Main> response) {
                    pd.dismiss();

                    Userbean_News_Main userbean_news_main = response.body();
                    if (response.code() == 200){

                        //Userbean_News_Main userbean_news_main = response.body();
                        List<Userbean_News_Data> data = userbean_news_main.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        listnews.setLayoutManager(layoutManager);
                        listnews.setAdapter(new NewsAdapter(News.this,data));
                    }
                    else{
                        LinearLayout linearLayout=findViewById(R.id.datanotfound);
                        linearLayout.setVisibility(View.VISIBLE);
                        listnews.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onFailure(Call<Userbean_News_Main> call, Throwable t) {
                    Toast.makeText(News.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        }catch(Exception e){

        }
       /* StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL+"NewsEvents?lClassId="+ lClass_IdNo + "&nType="+1+"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    map.put("sNEDate", ob.getString("sNEDate"));
                                    map.put("sTitle", ob.getString("sTitle"));
                                    map.put("sDescp", ob.getString("sDescp"));
                                    map.put("sAttachment", ob.getString("sAttachment"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterNews wp = new GetterSetterNews(map.get("sNEDate").toString(),map.get("sTitle").toString(),map.get("sDescp").toString(),map.get("sAttachment").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterNews(News.this, array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {

                                pd.hide();
                                MyToast.show(News.this,"No Data Available !!!",false);



                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                            MyToast.show(News.this,"Server Data Error Try Again !!!",false);
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                        MyToast.show(News.this, "Connection Time Out Error Try Again !!",false);
                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(News.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/

    }

   /* private class AdepterNews extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<GetterSetterNews> customerDatalist = null;
        private ArrayList<GetterSetterNews> array;
        String sp_edit;


        public AdepterNews(Context context, List<GetterSetterNews> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<GetterSetterNews>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView heading,date,des,readmore;
            CheckBox bal;
            View vie;
            ImageView removetocart;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public GetterSetterNews getItem(int position) {
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
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.news_item, null);
            view.setTag(holder);
            holder.heading = (TextView) view.findViewById(R.id.heading);
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.des = (TextView) view.findViewById(R.id.des);
            holder.removetocart=(ImageView)view.findViewById(R.id.removetocart);
            holder.readmore=(TextView)view.findViewById(R.id.readmore);
            holder.vie=(View)view.findViewById(R.id.view);


            dataaa=customerDatalist.get(position).getsDescp();

            holder.heading.setText(customerDatalist.get(position).getsTitle());
            holder.date.setText(customerDatalist.get(position).getsNEDate());

            String da=customerDatalist.get(position).getsDescp();

            int strLength  = da.length();

            Log.e("size", String.valueOf(strLength));


            if (strLength>=100){
                holder.readmore.setVisibility(View.VISIBLE);
                holder.vie.setVisibility(View.VISIBLE);
                holder.des.setText(customerDatalist.get(position).getsDescp().substring(0,99)+"...");

            }
            else {
                holder.des.setText(customerDatalist.get(position).getsDescp());

            }


            if (customerDatalist.get(position).getsDescp().equals("")){

            }
            else {
                makeTextViewResizable(holder.des, 3, "View More", true);
            }


        holder.readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(News.this);
                dialog.setContentView(R.layout.alertdi);
                TextView tx=(TextView)dialog.findViewById(R.id.tx);
                TextView heading=(TextView)dialog.findViewById(R.id.heading);
                ImageView im=(ImageView)dialog.findViewById(R.id.cancel);
                tx.setText(customerDatalist.get(position).getsDescp());
                heading.setText(customerDatalist.get(position).getsTitle());
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

            holder.removetocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(News.this);
                    builder.setMessage("Do you want to download the attachment?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                                        if (customerDatalist.get(position).getsAttachment().equalsIgnoreCase("")){
                                            Toast.makeText(getApplication(),"There is no Attachment !!!",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Uri Download_Uri = Uri.parse(customerDatalist.get(position).getsAttachment().trim());

                                            String fileExtension = customerDatalist.get(position).getsAttachment().trim();

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
            });



            return view;
        }*/
    }



//}

