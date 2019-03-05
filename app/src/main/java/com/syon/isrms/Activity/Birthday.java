package com.syon.isrms.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.syon.isrms.Adapter.Parent_BIrthdayAdapter;
import com.syon.isrms.Beans.ParentBirthdayBean;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.Model_Class.GetterSetterBR;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class Birthday extends AppCompatActivity {

    LinearLayout listView;
    String lUPIdNo,lClass_IdNo,lSec_IdNo;
    String lSessionId;
    String sSchCode;
    LinearLayout datanotfound;
    LayoutInflater inflator = null;
  /*  AdepterBr adapter;*/
    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<GetterSetterBR> array = new ArrayList<GetterSetterBR>();
    ApiInterfce apiInterfce;
    RecyclerView recyclerView;

    private SharedPreferences mPrefs ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);

        recyclerView=findViewById(R.id.parent_birthday_recycler);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(Birthday.this);
        inflator = (LayoutInflater) Birthday.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");
        lClass_IdNo=mPrefs.getString("lClass_IdNo","");
        lSec_IdNo=mPrefs.getString("lSec_IdNo","");

        FessData(lClass_IdNo,lSec_IdNo,lSessionId,sSchCode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      /*  LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Birthday.super.onBackPressed();
            }
        });
        TextView logoicon=(TextView)mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Birthday");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.br, 0, 0, 0);
*/

        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Birthday.super.onBackPressed();
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

    private void FessData(String lClass_IdNo, String lSec_IdNo, String lSessionId, String sSchCode) {


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<ParentBirthdayBean> call = apiInterfce.studbirthday(lClass_IdNo,lSec_IdNo,lSessionId,sSchCode);
        call.enqueue(new Callback<ParentBirthdayBean>() {
            @Override
            public void onResponse(Call<ParentBirthdayBean> call, retrofit2.Response<ParentBirthdayBean> response) {
                if(response.code()==200)
                {
                    ParentBirthdayBean birthdayBean=response.body();
                    List<com.syon.isrms.Beans.Parent_Birthday_Bean_Data> data=birthdayBean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new Parent_BIrthdayAdapter(Birthday.this,data));
                }
                else {
                    Toast.makeText(Birthday.this, "Data not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParentBirthdayBean> call, Throwable t) {

            }
        });

      /*  final ProgressDialog pd = new ProgressDialog(Birthday.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL+"StudBirthdayList?lClassId="+lClass_IdNo + "&lSecId="+lSec_IdNo+"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    final View item = inflator.inflate(R.layout.birthdayheader, null);
                                    TextView headertext = (TextView)item.findViewById(R.id.headertextview);
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    String grphead = jsonObject1.optString("grouphead");
                                    headertext.setText(grphead);
                                    listView.addView(item);
                                    JSONArray innerarray = jsonObject1.getJSONArray("data");
                                    if(innerarray.length() > 0) {
                                        for (int j = 0; j <innerarray.length(); j++) {
                                            final View itemone = inflator.inflate(R.layout.br_item, null);
                                            TextView heading = (TextView) itemone.findViewById(R.id.heading);
                                            ImageView iv_offers=(RoundedImageView)itemone.findViewById(R.id.iv_offers);
                                            JSONObject innerObj = innerarray.getJSONObject(j);
                                            String nSrNo = innerObj.optString("nSrNo");
                                            String studId = innerObj.optString("studId");
                                            String studName = innerObj.optString("studName");
                                            String studPhoto = innerObj.optString("studPhoto");
                                            String sessionId = innerObj.optString("sessionId");
                                            String schCode = innerObj.optString("schCode");
                                             heading.setText(studName);
                                            Picasso.with(getApplication())
                                                    .load(studPhoto)
                                                    .into(iv_offers);
                                            listView.addView(itemone);

                                        }
                                    }


                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterBR wp = new GetterSetterBR(map.get("sBirthday").toString(),map.get("sStudName").toString(),map.get("sStudPhoto").toString());
                                    array.add(wp);
                                }
                                //adapter = new AdepterBr(Birthday.this, array);
                                //listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();
                                datanotfound.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
                                MyToast.show(Birthday.this,"No Data !!!",false);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                            datanotfound.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                            MyToast.show(Birthday.this,"Server Data Error Try Again !!!",false);
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
                        MyToast.show(Birthday.this, "No Data !!!",false);
                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Birthday.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
*/
    }

  /*  private class AdepterBr extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<GetterSetterBR> customerDatalist = null;
        private ArrayList<GetterSetterBR> array;
        String sp_edit;


        public AdepterBr(Context context, List<GetterSetterBR> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<GetterSetterBR>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView heading,date,des;
            CheckBox bal;
            Button bt;
            RoundedImageView iv_offers;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public GetterSetterBR getItem(int position) {
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
            view = inflater.inflate(R.layout.br_item, null);
            view.setTag(holder);
            holder.heading = (TextView) view.findViewById(R.id.heading);
           // holder.bt = (Button) view.findViewById(R.id.bt);
            holder.iv_offers=(RoundedImageView)view.findViewById(R.id.iv_offers);

            if (customerDatalist.get(position).getsStudPhoto().equals("")){
                Picasso.with(getApplication())
                        .load(R.drawable.profile)
                        .into(holder.iv_offers);

            }

            holder.heading.setText(customerDatalist.get(position).getsStudName());
           // holder.bt.setText(customerDatalist.get(position).getsBirthday());
            Picasso.with(getApplication())
                    .load(customerDatalist.get(position).getsStudPhoto())
                    .into(holder.iv_offers);

            return view;
        }
    }
*/
}

