package com.syon.isrms.Activity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.syon.isrms.Beans.ParentMedicalRecordBean;
import com.syon.isrms.Beans.Parent_Medical_Record_Bean_Data;
import com.syon.isrms.Fragement.Parent_Data_not_Found_Fragment;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.Model_Class.GetterSetterNews;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class   Medical_Record extends AppCompatActivity {

    ListView listView;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    static String dataaa;
    Context ctx=this;
    private static final String TAG = "Something";
    ApiInterfce apiInterfce;

    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<GetterSetterNews> array = new ArrayList<GetterSetterNews>();

    private SharedPreferences mPrefs ;
    private static final int PERMISSION_REQUEST_CODE = 1;
    LinearLayout datanotfound;
    DownloadManager downloadManager;
    TextView madical_header;
    EditText sHeight,sWeight,sBMI,sLeftEyeVision,sRightEyeVision,sDentalHygiene,sBloodGroup,sAllergy,sHealthProblem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical__record);
       /* datanotfound=(LinearLayout)findViewById(R.id.datanotfound);*/
        sHeight=findViewById(R.id.sHeight);
        sWeight=findViewById(R.id.sWeight);
        sBMI=findViewById(R.id.sBMI);
        sLeftEyeVision=findViewById(R.id.sLeftEyeVision);
        sRightEyeVision=findViewById(R.id.sRightEyeVision);
        sDentalHygiene=findViewById(R.id.sDentalHygiene);
        sBloodGroup=findViewById(R.id.sBloodGroup);
        sAllergy=findViewById(R.id.sAllergy);
        sHealthProblem=findViewById(R.id.sHealthProblem);
      /*  madical_header=findViewById(R.id.madical_header);*/
      /*  madical_header.setPaintFlags(madical_header.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);*/

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(Medical_Record.this);



        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        FessData(lUPIdNo,lSessionId,sSchCode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Medical_Record.super.onBackPressed();
            }
        });
        TextView logoicon=(TextView)mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Medical Record");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.medi, 0, 0, 0);*/

        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medical_Record.super.onBackPressed();
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





    private void FessData(String lUPIdNo, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(Medical_Record.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();


        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<ParentMedicalRecordBean> call = apiInterfce.getParentMedicalData(lUPIdNo, lSessionId, sSchCode);
            call.enqueue(new Callback<ParentMedicalRecordBean>() {
                @Override
                public void onResponse(Call<ParentMedicalRecordBean> call, retrofit2.Response<ParentMedicalRecordBean> response) {

                    if (response.code() == 200) {
                        pd.dismiss();
                        ParentMedicalRecordBean parentMedicalRecordBean = response.body();
                        List<Parent_Medical_Record_Bean_Data> data = parentMedicalRecordBean.getData();

                        sHeight.setText(data.get(0).getSHeight().toString());
                        sWeight.setText(data.get(0).getSWeight().toString());
                        sBMI.setText(data.get(0).getSBMI().toString());
                        sLeftEyeVision.setText(data.get(0).getSLeftEyeVision().toString());
                        sRightEyeVision.setText(data.get(0).getSRightEyeVision().toString());
                        sDentalHygiene.setText(data.get(0).getSDentalHygiene().toString());
                        sBloodGroup.setText(data.get(0).getSBloodGroup().toString());
                        sAllergy.setText(data.get(0).getSAllergy().toString());
                        sHealthProblem.setText(data.get(0).getSHealthProblem().toString());
                    } else {
                        pd.dismiss();
                   /*     datanotfound.setVisibility(View.VISIBLE);*/
                   getSupportFragmentManager().beginTransaction().add(R.id.medical_container,new Parent_Data_not_Found_Fragment(),"datanotfound").commit();
                    }


                }

                @Override
                public void onFailure(Call<ParentMedicalRecordBean> call, Throwable t) {
                    Toast.makeText(Medical_Record.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (Exception e) {
            pd.dismiss();
            datanotfound.setVisibility(View.VISIBLE);
        }







       /* StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL+"StudHealthStatus?lUPIdNo="+lUPIdNo +"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    sHeight.setText(ob.getString("sHeight"));
                                    sWeight.setText(ob.getString("sWeight"));
                                    sBMI.setText(ob.getString("sBMI"));
                                    sLeftEyeVision.setText(ob.getString("sLeftEyeVision"));
                                    sRightEyeVision.setText(ob.getString("sRightEyeVision"));
                                    sDentalHygiene.setText(ob.getString("sDentalHygiene"));
                                    sBloodGroup.setText(ob.getString("sBloodGroup"));
                                    sAllergy.setText(ob.getString("sAllergy"));
                                    sHealthProblem.setText(ob.getString("sHealthProblem"));
                                }

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();
                                datanotfound.setVisibility(View.VISIBLE);
                              //  listView.setVisibility(View.GONE);



                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                            datanotfound.setVisibility(View.VISIBLE);
                          //  listView.setVisibility(View.GONE);
                            MyToast.show(Medical_Record.this,"Server Data Error Try Again !!!",false);
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
                       // listView.setVisibility(View.GONE);
                        MyToast.show(Medical_Record.this, "Connection Time Out Error Try Again !!",false);
                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Medical_Record.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }*/


    }

}

