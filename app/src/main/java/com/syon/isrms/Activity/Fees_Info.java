package com.syon.isrms.Activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syon.isrms.Fragement.FragementD;
import com.syon.isrms.Fragement.FragementE;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.syon.isrms.Activity.Login.longLog;

public class Fees_Info extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Button bt1,bt2,bt3;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    ProgressDialog pd;
    private SharedPreferences mPrefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fees__info);
        bt1=(Button)findViewById(R.id.bt_due);
        bt2=(Button)findViewById(R.id.bt_paid);
        bt3=(Button)findViewById(R.id.bt_bal);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        Log.d("lSessionId",lSessionId);
        Log.d("sSchCode",sSchCode);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fees_Info.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Fee Info");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.feeinfo, 0, 0, 0);
*/

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        setupBtPrice(lUPIdNo,lSessionId,sSchCode);
        ImageView back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fees_Info.super.onBackPressed();
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


    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if ( pd!=null && pd.isShowing() ){
            pd.cancel();
        }
        finish();
    }
    private void setupBtPrice(String lUPIdNo, String lSessionId, String sSchCode) {

        pd = new ProgressDialog(Fees_Info.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudFeeInstlSumry?lUPIdNo="+lUPIdNo+ "&lSessionId="+lSessionId+"&sSchCode="+sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  longLog("Log in Resopnce------------------------------------------------"+response);
                        longLog("response---" + response);

                        try {
                            JSONObject json=new JSONObject(response);
                            pd.hide();
                            bt1.setText(getString(R.string.rupee)+" "+json.getString("dueAmount"));
                            bt2.setText(getString(R.string.rupee)+" "+json.getString("paidAmount"));
                            bt3.setText(getString(R.string.rupee)+" "+json.getString("balAmount"));

                        }catch (JSONException e){
                   /*         MyToast.show(Fees_Info.this,"Error while reading data try again !!!", false);*/
                            longLog("Json --------- Exception..."+e.toString());
                            pd.hide();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        // pd.dismiss();



                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Fees_Info.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab1, null);
        tabOne.setText("FEE SUMMARY");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.background_tab, 0, 0);
      //  tabOne.setCompoundDrawableTintList(ColorStateList.valueOf(R.color.black));
        tabLayout.getTabAt(0).setCustomView(tabOne);


        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab1, null);
        tabTwo.setText("HEAD WISE DETAIL");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.background_tab, 0, 0);
      //  tabTwo.setCompoundDrawableTintList(ColorStateList.valueOf(R.color.black));
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab1, null);
        tabThree.setText("RECEIPT DETAIL");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.background_tab, 0, 0);
      //  tabThree.setCompoundDrawableTintList(ColorStateList.valueOf(R.color.black));
        tabLayout.getTabAt(2).setCustomView(tabThree);

      /*  View view1 = getLayoutInflater().inflate(R.layout.customtab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.fee);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));


        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.fee);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));

        View view3 = getLayoutInflater().inflate(R.layout.customtab, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.fee);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new com.syon.isrms.Fragement.FragementC(), "FEE SUMMARY");
        adapter.addFrag(new FragementD(), "HEAD WISE DETAIL");
        adapter.addFrag(new FragementE(), "RECEIPT DETAIL");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}