package com.syon.isrms.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syon.isrms.Adapter.Parent_Add_Account_Adapter;
import com.syon.isrms.Beans.ParentManageAccountAddBean;
import com.syon.isrms.Beans.ParentManageAccountBean;
import com.syon.isrms.Beans.Parent_Manage_Account_Bean_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class Parent_Add_Acoount extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView back_button;
    SharedPreferences preferences;
    ApiInterfce apiInterfce;
    Dialog dialog;
    ProgressDialog pd;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__add__acoount);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(Parent_Add_Acoount.this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(Parent_Add_Acoount.this);
                dialog.setContentView(R.layout.parent_add_account_dialog);
                dialog.setTitle("Add Account");
                final EditText username = (EditText) dialog.findViewById(R.id.add_new_user);
                ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
                final EditText password = (EditText) dialog.findViewById(R.id.add_new_password);
                ;
                Button add = (Button) dialog.findViewById(R.id.add_account);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final String user_name = username.getText().toString();
                        final String pass = password.getText().toString();


                        if (user_name.length() <= 0) {
                            Toast.makeText(Parent_Add_Acoount.this, "Enter username", Toast.LENGTH_SHORT).show();

                        } else if (pass.length() <= 0) {
                            Toast.makeText(Parent_Add_Acoount.this, "Enter Password", Toast.LENGTH_SHORT).show();
                        } else {

                            final String us = username.getText().toString();
                            final String pa = password.getText().toString();
                            pd = new ProgressDialog(Parent_Add_Acoount.this);
                            pd.setMessage("Loading...");
                            pd.setCancelable(false);
                            pd.show();

                            StringRequest request = new StringRequest(Request.Method.POST, ApiClient.BASE_URL + "UserParent",
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            Log.e("my response is", response);
                                            try {
                                                JSONObject json = new JSONObject(response);
                                                if (json.getString("status").equalsIgnoreCase("ok")) {
                                                    JSONObject js = json.getJSONObject("data");
                                                    String add_user_name = js.getString("sUserName");
                                                    String add_password = js.getString("sPassword");
                                                    String add_profile_pic = js.getString("studPhoto");
                                                    String add_profile_name = js.getString("sStudName");
                                                    String add_student_id = js.getString("lStudIdNo");
                                                    String loggined_id = preferences.getString(getString(R.string.lStudIdNo), "");
                                                    doAddAccountOperation(loggined_id, add_student_id, preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));

                                                } else if (json.getString("message").equalsIgnoreCase("An error has occurred.")) {
                                                    Toast.makeText(Parent_Add_Acoount.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();

                                                    pd.hide();
                                                }

                                            } catch (JSONException e) {
                                                Toast.makeText(Parent_Add_Acoount.this, "Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                                                pd.hide();

                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            pd.hide();
                                            Toast.makeText(Parent_Add_Acoount.this, "Incorrect User Credentials", Toast.LENGTH_SHORT).show();
                                            // MyToast.show(Login.this,"", false);
                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> map = new HashMap<String, String>();
                                    map.put("user", user_name);
                                    map.put("pwd", pass);
                                    map.put("logintype", "Parent");
                                    map.put("schoolcode", preferences.getString(getString(R.string.sSchCode), ""));
                                    map.put("deviceid", "");
                                    map.put("tokenno", preferences.getString(getString(R.string.FCM_TOKEN), ""));
                                    return map;
                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(Parent_Add_Acoount.this);
                            requestQueue.add(request);
                            request.setRetryPolicy(new DefaultRetryPolicy(
                                    55000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        }
                    }
                });
                dialog.show();

            }
        });
        init();
        doApiOperation();
    }

    private void doAddAccountOperation(String loggined_id, String add_student_id, String sessionid, String schoolcode) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<ParentManageAccountAddBean> call = apiInterfce.getAddAccount(loggined_id, add_student_id, sessionid, schoolcode);
        call.enqueue(new Callback<ParentManageAccountAddBean>() {
            @Override
            public void onResponse(Call<ParentManageAccountAddBean> call, retrofit2.Response<ParentManageAccountAddBean> response) {
                if (response.code() == 200) {
                    doApiOperation();


                    Toast.makeText(Parent_Add_Acoount.this, "Account added successfully", Toast.LENGTH_SHORT).show();

                    pd.dismiss();
                    dialog.dismiss();

                } else {
                    pd.dismiss();
                    dialog.dismiss();
                    Toast.makeText(Parent_Add_Acoount.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ParentManageAccountAddBean> call, Throwable t) {
                Toast.makeText(Parent_Add_Acoount.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<ParentManageAccountBean> call = apiInterfce.getLoggedAcocunt(preferences.getString(getString(R.string.lStudIdNo), ""), preferences.getString(getString(R.string.lSessionIdNo), ""), preferences.getString(getString(R.string.sSchCode), ""));
        call.enqueue(new Callback<ParentManageAccountBean>() {
            @Override
            public void onResponse(Call<ParentManageAccountBean> call, retrofit2.Response<ParentManageAccountBean> response) {
                if (response.code() == 200) {
                    layout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    ParentManageAccountBean groupBean = response.body();
                    List<Parent_Manage_Account_Bean_Data> data = groupBean.getData();
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new Parent_Add_Account_Adapter(Parent_Add_Acoount.this, data));

                } else {

                    layout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ParentManageAccountBean> call, Throwable t) {
                Toast.makeText(Parent_Add_Acoount.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        recyclerView = findViewById(R.id.Parent_Add_Account_Recycler);
        back_button = findViewById(R.id.back_button);
        layout = findViewById(R.id.add_account_layout);
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parent_Add_Acoount.super.onBackPressed();
            }
        });
    }

}
