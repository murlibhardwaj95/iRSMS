package com.syon.isrms.Fragement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import com.syon.isrms.Model_Class.GetterSetterFessDetail;
import com.syon.isrms.Model_Class.SchoolNameResponse;
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

public class FragementC extends Fragment {

    ListView listView;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    AdepterFees adapter;

    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<GetterSetterFessDetail> array = new ArrayList<GetterSetterFessDetail>();

    private SharedPreferences mPrefs ;
    public FragementC() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragementc, container, false);
        listView=(ListView)view.findViewById(R.id.list);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        FessData(lUPIdNo,lSessionId,sSchCode);


        return view;
    }

    private void FessData(String lUPIdNo, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudFeeInstlWise?lUPIdNo="+lUPIdNo + "&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    map.put("sInstl_MonthYr", ob.getString("sInstl_MonthYr"));
                                    map.put("dueAmount", ob.getString("dueAmount"));
                                    map.put("paidAmount", ob.getString("paidAmount"));
                                    map.put("balAmount", ob.getString("balAmount"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterFessDetail wp = new GetterSetterFessDetail(map.get("sInstl_MonthYr").toString(),map.get("dueAmount").toString(),map.get("paidAmount").toString(),map.get("balAmount").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterFees(getActivity(), array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
      /*                      MyToast.show(getActivity(),"Server Data Error Try Again !!!",false);*/
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                  /*      MyToast.show(getActivity(), "Connection Time Out Error Try Again !!",false);*/
                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private class AdepterFees extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<GetterSetterFessDetail> customerDatalist = null;
        private ArrayList<GetterSetterFessDetail> array;
        String sp_edit;


        public AdepterFees(Context context, List<GetterSetterFessDetail> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<GetterSetterFessDetail>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView month_name,due,paid,bal;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public GetterSetterFessDetail getItem(int position) {
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
            view = inflater.inflate(R.layout.listlayoutfeesdetail, null);
            view.setTag(holder);
            holder.month_name = (TextView) view.findViewById(R.id.month_name);
            holder.due = (TextView) view.findViewById(R.id.due);
            holder.paid = (TextView) view.findViewById(R.id.paid);
            holder.bal = (TextView) view.findViewById(R.id.bal);


            holder.month_name.setText(customerDatalist.get(position).getsInstl_MonthYr());
            holder.due.setText(getString(R.string.rupee)+" "+customerDatalist.get(position).getDueAmount());
            holder.paid.setText(getString(R.string.rupee)+" "+customerDatalist.get(position).getPaidAmount());
            holder.bal.setText(getString(R.string.rupee)+" "+customerDatalist.get(position).getBalAmount());

            return view;
        }
    }

}

