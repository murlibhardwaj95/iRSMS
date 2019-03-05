package com.syon.isrms.Fragement;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CHAMP on 5/30/2018.
 */

public class FragementE extends Fragment {


    ListView listView;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    AdepterFeesRecpt adapter;
    AdepterFeesRecptNew adapternew;
    ListView listViewnew;
    Dialog dialog, dialog0;

    ArrayList<HashMap<String, String>> CatlistDes = new ArrayList<>();
    ArrayList<HashMap<String, String>> CatlistDes2 = new ArrayList<>();
    ArrayList<com.syon.isrms.Model_Class.GetterSetterFeesRcpt> array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterFeesRcpt>();
    ArrayList<com.syon.isrms.Model_Class.GetterSetDetail> array2 = new ArrayList<com.syon.isrms.Model_Class.GetterSetDetail>();

    private SharedPreferences mPrefs;

    public FragementE() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemente, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId = obj.getWebSessionId();
        sSchCode = obj.getsSch_Code();
        lUPIdNo = mPrefs.getString("lUPIdNo", "");

        FessData(lUPIdNo, lSessionId, sSchCode);


        return view;
    }

    private void FessData(String lUPIdNo, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"FeeRcpt/?lUPIdNo=" + lUPIdNo + "&lSessionId=" + lSessionId + "&sSchCode=" + sSchCode,
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
                                    map.put("nRcptNo", ob.getString("nRcptNo"));
                                    map.put("dtRcptDate", ob.getString("dtRcptDate"));
                                    map.put("sFeeBook", ob.getString("sFeeBook"));
                                    map.put("sPaidAmount", ob.getString("sPaidAmount"));
                                    map.put("sPayMode", ob.getString("sPayMode"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++) {
                                    HashMap<String, String> map = new HashMap<>();
                                    map = CatlistDes.get(i);
                                    com.syon.isrms.Model_Class.GetterSetterFeesRcpt wp = new com.syon.isrms.Model_Class.GetterSetterFeesRcpt(map.get("nRcptNo").toString(), map.get("dtRcptDate").toString(), map.get("sFeeBook").toString(), map.get("sPaidAmount").toString(), map.get("sPayMode").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterFeesRecpt(getActivity(), array);
                                listView.setAdapter(adapter);

                            } else if (obj.getString("status").equals("error")) {
                                pd.hide();


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception", e.toString());
                            // avloader.hide();
                            pd.dismiss();
                      /*      MyToast.show(getActivity(), "Server Data Error Try Again !!!", false);*/
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                 /*       MyToast.show(getActivity(), "Connection Time Out Error Try Again !!", false);*/
                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private class AdepterFeesRecpt extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<com.syon.isrms.Model_Class.GetterSetterFeesRcpt> customerDatalist = null;
        private ArrayList<com.syon.isrms.Model_Class.GetterSetterFeesRcpt> array;
        String sp_edit;


        public AdepterFeesRecpt(Context context, List<com.syon.isrms.Model_Class.GetterSetterFeesRcpt> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterFeesRcpt>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView rc_no, rc_date, rc_p_mode, rc_paid_Am, sFeeBook;
            Button detail;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public com.syon.isrms.Model_Class.GetterSetterFeesRcpt getItem(int position) {
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
            view = inflater.inflate(R.layout.listlayoutfeesrecpt, null);
            view.setTag(holder);
            holder.rc_no = (TextView) view.findViewById(R.id.rc_no);
            holder.rc_date = (TextView) view.findViewById(R.id.rc_date);
            holder.rc_p_mode = (TextView) view.findViewById(R.id.rc_p_mode);
            holder.rc_paid_Am = (TextView) view.findViewById(R.id.rc_paid_Am);
            holder.sFeeBook = (TextView) view.findViewById(R.id.sFeeBook);
            holder.detail = (Button) view.findViewById(R.id.detail);


            holder.rc_no.setPaintFlags(holder.rc_no.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.rc_date.setPaintFlags(holder.rc_date.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.rc_p_mode.setPaintFlags(holder.rc_p_mode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.rc_paid_Am.setPaintFlags(holder.rc_paid_Am.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


            holder.rc_no.setText(customerDatalist.get(position).getnRcptNo());
            holder.rc_date.setText(customerDatalist.get(position).getDtRcptDate());
            holder.rc_p_mode.setText(customerDatalist.get(position).getsPayMode());
            holder.rc_paid_Am.setText(getString(R.string.rupee)+""+customerDatalist.get(position).getsPaidAmount());
            holder.sFeeBook.setText(customerDatalist.get(position).getsFeeBook());


            holder.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.detail);
                    dialog.setTitle("Fee Receipt");
                    final EditText oldpass = (EditText) dialog.findViewById(R.id.oldpass);
                    ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
                    listViewnew = (ListView) dialog.findViewById(R.id.list);
                   /* TextView sFHeadName=(TextView)dialog.findViewById(R.id.sFHeadName);
                    TextView paidAmount=(TextView)dialog.findViewById(R.id.paidAmount);*/
                    /*  FessDataDetail(lUPIdNo, lSessionId, sSchCode);*/
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"FeeRcptHeadWise/?lUPIdNo=" + lUPIdNo + "&lFRBookId=" + 11 + "&lRcptNo=" + array.get(position).getnRcptNo()+""+ "&lSessionId=" + lSessionId + "&sSchCode=" + sSchCode,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("Resopnce----", response);
                                    if (!CatlistDes2.isEmpty())
                                        CatlistDes2.clear();
                                    if (!array2.isEmpty())
                                        array2.clear();
                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        if (obj.getString("status").equals("ok")) {
                                            JSONArray jsonArray = obj.getJSONArray("data");
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject ob = jsonArray.getJSONObject(i);
                                                HashMap<String, String> map = new HashMap<>();
                                                map.put("sFHeadName", ob.getString("sFHeadName"));
                                                map.put("paidAmount", ob.getString("paidAmount"));
                                                CatlistDes2.add(map);
                                            }
                                            for (int i = 0; i < CatlistDes2.size(); i++) {
                                                HashMap<String, String> map = new HashMap<>();
                                                map = CatlistDes2.get(i);
                                                com.syon.isrms.Model_Class.GetterSetDetail wp = new com.syon.isrms.Model_Class.GetterSetDetail(map.get("sFHeadName").toString(), map.get("paidAmount").toString());
                                                array2.add(wp);
                                            }
                                            adapternew = new AdepterFeesRecptNew(getActivity(), array2);
                                            listViewnew.setAdapter(adapternew);

                                        } else if (obj.getString("status").equals("error")) {


                                        }
                                    } catch (JSONException e) {
                                        Log.d("json Exception", e.toString());
                                        // avloader.hide();
                                        /*                                MyToast.show(getActivity(), "Server Data Error Try Again !!!", false);*/
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // avloader.hide();
                                    /*MyToast.show(getActivity(), "Connection Time Out Error Try Again !!", false);*/
                                }
                            }) {
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            100000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });

            return view;
        }

       /* private void FessDataDetail(String lUPIdNo, String lSessionId, String sSchCode) {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"FeeRcptHeadWise/?lUPIdNo=" + lUPIdNo + "&lFRBookId=" + 11 + "&lRcptNo=" + 2929 + "&lSessionId=" + lSessionId + "&sSchCode=" + sSchCode,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Resopnce----", response);
                            if (!CatlistDes2.isEmpty())
                                CatlistDes2.clear();
                            if (!array2.isEmpty())
                                array2.clear();
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (obj.getString("status").equals("ok")) {
                                    JSONArray jsonArray = obj.getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject ob = jsonArray.getJSONObject(i);
                                        HashMap<String, String> map = new HashMap<>();
                                        map.put("sFHeadName", ob.getString("sFHeadName"));
                                        map.put("paidAmount", ob.getString("paidAmount"));
                                        CatlistDes2.add(map);
                                    }
                                    for (int i = 0; i < CatlistDes2.size(); i++) {
                                        HashMap<String, String> map = new HashMap<>();
                                        map = CatlistDes2.get(i);
                                        com.syon.isrms.Model_Class.GetterSetDetail wp = new com.syon.isrms.Model_Class.GetterSetDetail(map.get("sFHeadName").toString(), map.get("paidAmount").toString());
                                        array2.add(wp);
                                    }
                                    adapternew = new AdepterFeesRecptNew(getActivity(), array2);
                                    listViewnew.setAdapter(adapternew);

                                } else if (obj.getString("status").equals("error")) {


                                }
                            } catch (JSONException e) {
                                Log.d("json Exception", e.toString());
                                // avloader.hide();
*//*                                MyToast.show(getActivity(), "Server Data Error Try Again !!!", false);*//*
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // avloader.hide();
                            *//*MyToast.show(getActivity(), "Connection Time Out Error Try Again !!", false);*//*
                        }
                    }) {
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    100000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        }
    }*/
    }

    private class AdepterFeesRecptNew extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<com.syon.isrms.Model_Class.GetterSetDetail> customerDatalist = null;
        private ArrayList<com.syon.isrms.Model_Class.GetterSetDetail> array;
        String sp_edit;


        public AdepterFeesRecptNew(Context context, List<com.syon.isrms.Model_Class.GetterSetDetail> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<com.syon.isrms.Model_Class.GetterSetDetail>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView rc_no, rc_date, rc_p_mode, rc_paid_Am, sFeeBook;
            Button detail;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public com.syon.isrms.Model_Class.GetterSetDetail getItem(int position) {
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
            view = inflater.inflate(R.layout.detailnew, null);
            view.setTag(holder);
            holder.rc_no = (TextView) view.findViewById(R.id.header);
            holder.rc_date = (TextView) view.findViewById(R.id.late);

            holder.rc_no.setText(customerDatalist.get(position).getsFHeadName());
            holder.rc_date.setText(getString(R.string.rupee)+""+customerDatalist.get(position).getPaidAmount());

            return view;
        }

    }
}

