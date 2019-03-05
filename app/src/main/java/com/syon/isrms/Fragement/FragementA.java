package com.syon.isrms.Fragement;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.syon.isrms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CHAMP on 5/30/2018.
 */

public class FragementA extends Fragment {

TextView tv1,tv2,tv3,tv4,tv5;

    ListView listView;
    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<com.syon.isrms.Model_Class.GetterSetterFragementA> array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterFragementA>();

    private SharedPreferences mPrefs ;
    private static final int PERMISSION_REQUEST_CODE = 1;

    DownloadManager downloadManager;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    static String dataaa;
    AdepterClassTimeTable adapter;
    LinearLayout datanotfound;

    public FragementA() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        listView=(ListView)view.findViewById(R.id.list);
        datanotfound=(LinearLayout)view.findViewById(R.id.datanotfound);
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
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
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        ClassTimeT(lUPIdNo,lSessionId,sSchCode);
        return view;
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getActivity(), "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
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
    private void ClassTimeT(String lUPIdNo, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudTransport/?lUPIdNo="+lUPIdNo +"&lSessionId="+ lSessionId +"&sSchCode="+ sSchCode,
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
                                JSONObject jsonArray = obj.getJSONObject("data");
                                    HashMap<String, String> mapp = new HashMap<>();
                                mapp.put("sRoute", jsonArray.getString("sRoute"));
                                mapp.put("sPickPoint", jsonArray.getString("sPickPoint"));
                                mapp.put("sPickUp_Time", jsonArray.getString("sPickUp_Time"));
                                mapp.put("sDrop_Time", jsonArray.getString("sDrop_Time"));
                                mapp.put("sBus_No", jsonArray.getString("sBus_No"));
                                    CatlistDes.add(mapp);

                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    com.syon.isrms.Model_Class.GetterSetterFragementA wp = new com.syon.isrms.Model_Class.GetterSetterFragementA(map.get("sRoute").toString(),map.get("sPickPoint").toString(),map.get("sPickUp_Time").toString(),map.get("sDrop_Time").toString(),map.get("sBus_No").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterClassTimeTable(getActivity(), array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();

                                datanotfound.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);


                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();

                            datanotfound.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
                           /* MyToast.show(getActivity(),"Server Data Error Try Again !!!",false);*/
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
                /*        MyToast.show(getActivity(), "Connection Time Out Error Try Again !!",false);*/
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

    private class AdepterClassTimeTable extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<com.syon.isrms.Model_Class.GetterSetterFragementA> customerDatalist = null;
        private ArrayList<com.syon.isrms.Model_Class.GetterSetterFragementA> array;
        String sp_edit;


        public AdepterClassTimeTable(Context context, List<com.syon.isrms.Model_Class.GetterSetterFragementA> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<com.syon.isrms.Model_Class.GetterSetterFragementA>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView sRoute, sPickPoint, sPickUp_Time,sDrop_Time,sBus_No;
            CheckBox bal;
            ImageView removetocart;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public com.syon.isrms.Model_Class.GetterSetterFragementA getItem(int position) {
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
            view = inflater.inflate(R.layout.customfraga, null);
            view.setTag(holder);
            holder.sRoute = (TextView) view.findViewById(R.id.sRoute);
            holder.sPickPoint = (TextView) view.findViewById(R.id.sPickPoint);
            holder.sPickUp_Time = (TextView) view.findViewById(R.id.sPickUp_Time);
            holder.sDrop_Time = (TextView) view.findViewById(R.id.sDrop_Time);
            holder.sBus_No = (TextView) view.findViewById(R.id.sBus_No);


            holder.sRoute.setText(customerDatalist.get(position).getsRoute());
            holder.sPickPoint.setText(customerDatalist.get(position).getsPickPoint());
            holder.sPickUp_Time.setText(customerDatalist.get(position).getsPickUp_Time());
            holder.sDrop_Time.setText(customerDatalist.get(position).getsDrop_Time());
            holder.sBus_No.setText(customerDatalist.get(position).getsBus_No());


            return view;
        }

    }
}

