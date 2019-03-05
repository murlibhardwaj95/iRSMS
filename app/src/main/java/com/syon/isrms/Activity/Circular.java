package com.syon.isrms.Activity;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
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
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Circular extends AppCompatActivity {

    ListView listView;
    String lUPIdNo,lClass_IdNo,lSec_IdNo,nStudType;

    String lSessionId;
    static String dataa;
    private static final int PERMISSION_REQUEST_CODE = 1;
    String sSchCode;
    AdepterCircular adapter;


    ArrayList<HashMap<String, String>> CatlistDes = new ArrayList<>();
    ArrayList<com.syon.isrms.Model_Class.GetterSetterCirular> array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterCirular>();
    DownloadManager downloadManager;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);
        listView = (ListView) findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        setSupportActionBar(toolbar);
     /*   LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Circular.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Circulars");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circular, 0, 0, 0);*/
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Circular.super.onBackPressed();
            }
        });


        mPrefs = PreferenceManager.getDefaultSharedPreferences(Circular.this);
        SharedPreferences.Editor countEditor=mPrefs.edit();
        countEditor.putInt("Circular",0);
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

        lSessionId = obj.getWebSessionId();
        sSchCode = obj.getsSch_Code();
        lUPIdNo = mPrefs.getString("lUPIdNo", "");
        lClass_IdNo=mPrefs.getString("lClass_IdNo","");
        lSec_IdNo=mPrefs.getString("lSec_IdNo","");
        nStudType=mPrefs.getString("nStudType","");

        CircularData(lClass_IdNo,nStudType, lSessionId, sSchCode);

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
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Circular.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Circular.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(Circular.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(Circular.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
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

    private void CircularData(String lClass_IdNo, String nStudType, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Circular.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"Circulars?lClassId=" + lClass_IdNo +"&nType=" + 1 + "&lSessionId=" + lSessionId + "&sSchCode=" + sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Resopnce----", response);
                        if (!CatlistDes.isEmpty())
                            CatlistDes.clear();
                        if (!array.isEmpty())
                            array.clear();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.getString("status").equals("ok")) {
                                JSONArray jsonArray = obj.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject ob = jsonArray.getJSONObject(i);
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("sCirDate", ob.getString("sCirDate"));
                                    map.put("sTitle", ob.getString("sTitle"));
                                    map.put("sDescp", ob.getString("sDescp"));
                                    map.put("sAttachment", ob.getString("sAttachment"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++) {
                                    HashMap<String, String> map = new HashMap<>();
                                    map = CatlistDes.get(i);
                                    com.syon.isrms.Model_Class.GetterSetterCirular wp = new com.syon.isrms.Model_Class.GetterSetterCirular(map.get("sCirDate").toString(), map.get("sTitle").toString(), map.get("sDescp").toString(), map.get("sAttachment").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterCircular(Circular.this, array);
                                listView.setAdapter(adapter);

                            } else if (obj.getString("status").equals("error")) {
                                pd.hide();
                            /*    MyToast.show(Circular.this,"No Data Available !!!",false);*/


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception", e.toString());
                            // avloader.hide();
                            pd.dismiss();
           /*                 MyToast.show(Circular.this, "Server Data Error Try Again !!!", false);*/
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
   /*                     MyToast.show(Circular.this, "Connection Time Out Error Try Again !!", false);*/
                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Circular.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private class AdepterCircular extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private  List<com.syon.isrms.Model_Class.GetterSetterCirular> customerDatalist = null;
        private ArrayList<com.syon.isrms.Model_Class.GetterSetterCirular> array;
        String sp_edit;
        ProgressDialog progressDialog;


        public AdepterCircular(Context context, List<com.syon.isrms.Model_Class.GetterSetterCirular> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterCirular>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView heading, date, des,dateee,readmore;
            CheckBox bal;
            View vie;
            ImageView removetocart;
            ImageView circle_image;

        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public com.syon.isrms.Model_Class.GetterSetterCirular getItem(int position) {
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
            view = inflater.inflate(R.layout.layoutmainciruclar, null);
            view.setTag(holder);
            holder.heading = (TextView) view.findViewById(R.id.heading);
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.dateee = (TextView) view.findViewById(R.id.dateee);
            holder.vie=(View)view.findViewById(R.id.view);
            // holder.des = (TextView) view.findViewById(R.id.des);
            holder.removetocart = (ImageView) view.findViewById(R.id.removetocart);
            holder.readmore=(TextView)view.findViewById(R.id.readmore);
            holder.circle_image=(ImageView)view.findViewById(R.id.circle_image);


           // dataa=customerDatalist.get(position).getsDescp();

            String da=customerDatalist.get(position).getsDescp();

            int strLength  = da.length();

            Log.e("size", String.valueOf(strLength));


            if (strLength>=100){
                holder.readmore.setVisibility(View.VISIBLE);
                holder.vie.setVisibility(View.VISIBLE);
                holder.date.setText(customerDatalist.get(position).getsDescp().substring(0,99)+"...");

            }
            else {
                holder.vie.setVisibility(View.GONE);
                holder.date.setText(customerDatalist.get(position).getsDescp());

            }
            String download = customerDatalist.get(position).getsAttachment().toString();
            if (TextUtils.isEmpty(download)) {
                Picasso.with(getApplicationContext()).load(R.drawable.orange_circle).into(holder.circle_image);
                holder.removetocart.setVisibility(View.GONE);

            } else {
                Picasso.with(getApplicationContext()).load(R.drawable.purple_circle).into(holder.circle_image);
                holder.removetocart.setVisibility(View.VISIBLE);

            }


            holder.date.setText(customerDatalist.get(position).getsDescp());

            holder.readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(Circular.this);
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
                    dialog.setTitle("Circular");
                    dialog.show();
                }
            });


            holder.removetocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Circular.this);
                    builder.setMessage("Do you want to download the attachment?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    new DownloadCircularFile().execute(customerDatalist.get(position).getsAttachment());
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        if (customerDatalist.get(position).getsAttachment().equalsIgnoreCase("")){
                                            Toast.makeText(getApplication(),"There is no Attachment !!!",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            mContext.registerReceiver(new com.syon.isrms.Broadcast_Recievers.MyReceiver(),new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
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

            final SpannableStringBuilder sb = new SpannableStringBuilder(customerDatalist.get(position).getsCirDate());

            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold

            sb.setSpan(bss, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold


            holder.dateee.setText(sb);
            holder.heading.setText(customerDatalist.get(position).getsTitle());

            return view;
        }
        public class DownloadCircularFile extends AsyncTask<String, Integer, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Create progress dialog
                progressDialog = new ProgressDialog(mContext);
                // Set your progress dialog Title
                progressDialog.setTitle("Circular");
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



}