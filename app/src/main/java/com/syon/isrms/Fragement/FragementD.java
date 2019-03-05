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
import android.widget.LinearLayout;
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
import com.syon.isrms.Model_Class.GetterSetterHeadWise;
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

public class FragementD extends Fragment {

    LinearLayout listView;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    AdepterHeadWise adapter;

    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<GetterSetterHeadWise> array = new ArrayList<GetterSetterHeadWise>();

    private SharedPreferences mPrefs ;
    LayoutInflater inflator = null;


    public FragementD() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragementd, container, false);

        listView=(LinearLayout) view.findViewById(R.id.list);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudFeeInstlHeadWise/?lUPIdNo="+lUPIdNo + "&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                    final View item = inflator.inflate(R.layout.feeheader, null);
                                    TextView headertext = (TextView)item.findViewById(R.id.headername);
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    String grphead = jsonObject1.optString("grouphead");
                                    headertext.setText(grphead);
                                    listView.addView(item);
                                    JSONArray innerarray = jsonObject1.getJSONArray("data");
                                    if(innerarray.length() > 0) {
                                        for (int j = 0; j <innerarray.length(); j++) {
                                            final View itemone = inflator.inflate(R.layout.headwise_data, null);
                                            TextView head = (TextView)itemone.findViewById(R.id.sFHeadName);
                                            TextView due = (TextView)itemone.findViewById(R.id.due);
                                            TextView paid = (TextView)itemone.findViewById(R.id.paid);
                                            TextView bal = (TextView)itemone.findViewById(R.id.bal);
                                            JSONObject innerObj = innerarray.getJSONObject(j);
                                            String sFHeadName = innerObj.optString("sFHeadName");
                                            String dueAmount = innerObj.optString("dueAmount");
                                            String paidAmount = innerObj.optString("paidAmount");
                                            String balAmount = innerObj.optString("balAmount");

                                            head.setText(sFHeadName);
                                            due.setText(dueAmount);
                                            paid.setText(paidAmount);
                                            bal.setText(balAmount);

                                            listView.addView(itemone);

                                        }
                                    }


                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterHeadWise wp = new GetterSetterHeadWise(map.get("sInstlMonthYear").toString(),map.get("sFHeadName").toString(),map.get("dueAmount").toString(),map.get("paidAmount").toString(),map.get("balAmount").toString());
                                    array.add(wp);
                                }
                              /*  adapter = new AdepterHeadWise(getActivity(), array);
                                listView.setAdapter(adapter);*/

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();
                         /*   MyToast.show(getActivity(),"Server Data Error Try Again !!!",false);*/
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // avloader.hide();
                        pd.dismiss();
                       /* MyToast.show(getActivity(), "Connection Time Out Error Try Again !!",false);*/
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

    private class AdepterHeadWise extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<GetterSetterHeadWise> customerDatalist = null;
        private ArrayList<GetterSetterHeadWise> array;
        String sp_edit;


        public AdepterHeadWise(Context context, List<GetterSetterHeadWise> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<GetterSetterHeadWise>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView month_name,due,paid,bal,head;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public GetterSetterHeadWise getItem(int position) {
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
            view = inflater.inflate(R.layout.listlayoutheadwise, null);
            view.setTag(holder);
            holder.month_name = (TextView) view.findViewById(R.id.headername);
            holder.head = (TextView) view.findViewById(R.id.sFHeadName);
            holder.due = (TextView) view.findViewById(R.id.due);
            holder.paid = (TextView) view.findViewById(R.id.paid);
            holder.bal = (TextView) view.findViewById(R.id.bal);


            holder.month_name.setText(customerDatalist.get(position).getsInstl_MonthYr());
            holder.head.setText(customerDatalist.get(position).getsFHeadName());
            holder.due.setText(getString(R.string.rupee)+""+customerDatalist.get(position).getDueAmount());
            holder.paid.setText(getString(R.string.rupee)+""+customerDatalist.get(position).getPaidAmount());
            holder.bal.setText(getString(R.string.rupee)+""+customerDatalist.get(position).getBalAmount());

            return view;
        }
    }

}

