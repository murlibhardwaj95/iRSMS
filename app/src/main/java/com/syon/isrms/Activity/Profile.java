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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.syon.isrms.Activity.Login.longLog;

public class Profile extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    private SharedPreferences mPrefs ;
    String sSchCode,lUPIdNo,lSessionId;
    ProgressDialog pd;
    com.makeramen.roundedimageview.RoundedImageView list_image;
    EditText adm,rollno,dob,bg,house;
    TextView xx,xxx;
    ImageView back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adm=findViewById(R.id.duration);
        rollno=findViewById(R.id.duratio);
        dob=findViewById(R.id.durati);
        bg=findViewById(R.id.durat);
        house=findViewById(R.id.dura);
        list_image=findViewById(R.id.list_image);
        xx=findViewById(R.id.xx);
        xxx=findViewById(R.id.xxx);
        back_button=findViewById(R.id.back_button);

      /*  LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.customactionbar, null);
        toolbar.addView(mCustomView);
        ImageView drower = (ImageView) mCustomView.findViewById(R.id.search_imageview);
        drower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile.super.onBackPressed();
            }
        });
        TextView logoicon = (TextView) mCustomView.findViewById(R.id.logoicon);
        logoicon.setText("Profile");
        logoicon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ldpi, 0, 0, 0);*/


        mPrefs = PreferenceManager.getDefaultSharedPreferences(Profile.this);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        ProfileData(lUPIdNo,lSessionId,sSchCode);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile.super.onBackPressed();
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
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void ProfileData(String lUPIdNo, String lSessionId, String sSchCode) {
        pd = new ProgressDialog(Profile.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudentInfo?lUPIdNo="+lUPIdNo+ "&lSessionId="+lSessionId+"&sSchCode="+sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  longLog("Log in Resopnce------------------------------------------------"+response);
                        longLog("response---" + response);

                        try {
                            JSONObject json=new JSONObject(response);
                            pd.hide();
                            JSONArray jsonArray = json.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject ob = jsonArray.getJSONObject(i);
                                adm.setText(ob.getString("sAdmNo"));
                                rollno.setText(ob.getString("sRollNo"));
                                dob.setText(ob.getString("sDOB"));
                                bg.setText(ob.getString("sBloodGroup"));
                                house.setText(ob.getString("sHouse"));
                                xx.setText(ob.getString("sStudName"));
                                xxx.setText(ob.getString("sClass")+" - "+ob.getString("sSection"));

                        /*        adm.setPaintFlags(adm.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                                rollno.setPaintFlags(rollno.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                                dob.setPaintFlags(dob.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                                bg.setPaintFlags(bg.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                                house.setPaintFlags(house.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                                xx.setPaintFlags(xx.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                                xxx.setPaintFlags(xxx.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
*/
                                Picasso.with(getApplication())
                                        .load(ob.getString("studPhoto").toString())
                                        .into(list_image);

                            }


                        }catch (JSONException e){
                          /*  MyToast.show(Profile.this,"Error while reading data try again !!!", false);*/
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
                      /*  MyToast.show(Profile.this,"Connection Time Out Error Try Again !!", false);*/

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Personal Detail");
        //tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);


        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Parents Detail");
      //  tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.icon, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);



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
        adapter.addFrag(new com.syon.isrms.Fragement.FragementF(), "Personal Detail");
        adapter.addFrag(new com.syon.isrms.Fragement.FragementG(), "Parents Detail");
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
