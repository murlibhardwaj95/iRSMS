package com.syon.isrms.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.syon.isrms.Activity.Homework;
import com.syon.isrms.Activity.Parent_Add_Acoount;
import com.syon.isrms.Beans.ParentManageAccountAddBean;
import com.syon.isrms.Beans.ParentManageAccountDeleteBean;
import com.syon.isrms.Beans.Parent_Manage_Account_Bean_Data;
import com.syon.isrms.Home;
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

import static com.syon.isrms.Activity.Login.longLog;

public class Parent_Add_Account_Adapter extends RecyclerView.Adapter<Parent_Add_Account_Adapter.ViewHolder> {
    Context ctx;
    List<Parent_Manage_Account_Bean_Data> data;
    ProgressDialog progressDialog;


    public Parent_Add_Account_Adapter(Context ctx, List<Parent_Manage_Account_Bean_Data> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_account_template, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.added_account_name.setText(data.get(position).getSStudName().toString());
        Picasso.with(ctx).load(data.get(position).getStudPhoto().toString()).placeholder(R.drawable.avtar).into(holder.added_account_pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, data.get(position).toString() + "", Toast.LENGTH_SHORT).show();
                login(data.get(position).getSUserName().toString(), data.get(position).getSPassword().toString());

            }
        });
        holder.add_account_class.setText(data.get(position).getSClass().toString());
        holder.delete_added_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
                builder1.setMessage("Are you sure you want to delete this account?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    progressDialog = new ProgressDialog(ctx);
                                    progressDialog.setMessage("Deleting...");
                                    progressDialog.setCancelable(true);
                                    progressDialog.show();
                                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
                                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                                    ApiInterfce apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                                    Call<ParentManageAccountDeleteBean> call = apiInterfce.getDeleteLoggedAcocunt(preferences.getString(ctx.getString(R.string.lStudIdNo), ""), data.get(position).getLStudIdNo().toString(), preferences.getString(ctx.getString(R.string.lSessionIdNo), ""), preferences.getString(ctx.getString(R.string.sSchCode), ""));
                                    call.enqueue(new Callback<ParentManageAccountDeleteBean>() {
                                        @Override
                                        public void onResponse(Call<ParentManageAccountDeleteBean> call, retrofit2.Response<ParentManageAccountDeleteBean> response) {
                                            if (response.code() == 200) {

                                                Toast.makeText(ctx, data.get(position).getSStudName() + " deleted successfully", Toast.LENGTH_SHORT).show();
                                                ((Parent_Add_Acoount)ctx).doApiOperation();
                                                progressDialog.dismiss();

                                            } else {

                                                progressDialog.dismiss();
                                                Toast.makeText(ctx, "Something went wrong", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<ParentManageAccountDeleteBean> call, Throwable t) {
                                            Toast.makeText(ctx, "Check Internet Connection", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder1.create();
                alert.show();

            }
        });
    }

    private void login(String s, String s1) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        final String us = s;
        final String pa = s1;
        final String type = "Parent";
        final ProgressDialog pd = new ProgressDialog(ctx);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Log.e("my response is", us);
        Log.e("my response is", pa);
        StringRequest request = new StringRequest(Request.Method.POST, ApiClient.BASE_URL + "UserParent",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        longLog(response);
                        Log.e("my response is", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json.getString("status").equalsIgnoreCase("ok")) {
                                JSONObject js = json.getJSONObject("data");
                                String lUPIdNo = js.getString("lUPIdNo");
                                String sStudName = js.getString("sStudName");
                                String sSession = js.getString("sSession");
                                String studPhoto = js.getString("studPhoto");
                                String lClass_IdNo = js.getString("lClass_IdNo");
                                String lSec_IdNo = js.getString("lSec_IdNo");
                                String nStudType = js.getString("nStudType");
                                String lStudIdNo = js.getString("lStudIdNo");
                                String lFac_IdNo = js.getString("lFac_IdNo");
                                String sSchCode = js.getString("sSchCode");
                                String lSessionIdNo = js.getString("lSessionIdNo");

                                SharedPreferences.Editor prefsEditor = preferences.edit();
                                prefsEditor.putString(ctx.getString(R.string.lUPIdNo), lUPIdNo);
                                prefsEditor.putString("sStudName", sStudName);
                                prefsEditor.putString("sSession", sSession);
                                prefsEditor.putString("studPhoto", studPhoto);
                                prefsEditor.putString(ctx.getString(R.string.lClass_IdNo), lClass_IdNo);
                                prefsEditor.putString(ctx.getString(R.string.lSec_IdNo), lSec_IdNo);
                                prefsEditor.putString("nStudType", nStudType);
                                prefsEditor.putString(ctx.getString(R.string.lStudIdNo), lStudIdNo);
                                prefsEditor.putString("lFac_IdNo", lFac_IdNo);
                                prefsEditor.putString(ctx.getString(R.string.sSchCode), sSchCode);
                                prefsEditor.putString(ctx.getString(R.string.lSessionIdNo), lSessionIdNo);
                                prefsEditor.commit();

                                Toast.makeText(ctx, "Logged in Successfully", Toast.LENGTH_SHORT).show();


                                ctx.startActivity(new Intent(ctx, Home.class));


                            } else if (json.getString("message").equalsIgnoreCase("An error has occurred.")) {
                                Toast.makeText(ctx, "Wrong Username or Password", Toast.LENGTH_SHORT).show();

                                pd.hide();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error while reading data try again !!!", Toast.LENGTH_SHORT).show();
                            longLog("Json --------- Exception..." + e.toString());
                            pd.hide();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.hide();
                        Toast.makeText(ctx, "Incorrect User Credentials", Toast.LENGTH_SHORT).show();
                        // MyToast.show(Login.this,"", false);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("user", us);
                map.put("pwd", pa);
                map.put("logintype", type);
                map.put("schoolcode", "MGS001");
                map.put("deviceid", "");
                map.put("tokenno", preferences.getString(ctx.getString(R.string.FCM_TOKEN), ""));
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView added_account_name, add_account_class;
        ImageView delete_added_account;
        RoundedImageView added_account_pic;

        public ViewHolder(View itemView) {
            super(itemView);
            added_account_name = itemView.findViewById(R.id.added_account_name);
            added_account_pic = itemView.findViewById(R.id.add_account_pic);
            add_account_class = itemView.findViewById(R.id.add_account_class);
            delete_added_account = itemView.findViewById(R.id.delete_added_account);

        }
    }
}
