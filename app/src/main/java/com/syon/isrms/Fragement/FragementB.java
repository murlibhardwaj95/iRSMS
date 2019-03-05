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
import com.syon.isrms.Model_Class.GetterSetterCond;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CHAMP on 5/30/2018.
 */

public class FragementB extends Fragment {

    ListView listView;
    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<GetterSetterCond> array = new ArrayList<GetterSetterCond>();

    private SharedPreferences mPrefs ;
    private static final int PERMISSION_REQUEST_CODE = 1;

    DownloadManager downloadManager;
    String lUPIdNo;
    String lSessionId;
    String sSchCode;
    static String dataaa;
    AdepterClassTimeTable adapter;
    LinearLayout datanotfound;
    public FragementB() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
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
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

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
                                mapp.put("sBusIncharge_Name", jsonArray.getString("sBusIncharge_Name"));
                                mapp.put("sBusIncharge_ContactNo", jsonArray.getString("sBusIncharge_ContactNo"));
                                mapp.put("sDriver_Name", jsonArray.getString("sDriver_Name"));
                                mapp.put("sDriver_ContactNo", jsonArray.getString("sDriver_ContactNo"));
                                mapp.put("sConductor_Name", jsonArray.getString("sConductor_Name"));
                                mapp.put("sConductor_ContactNo", jsonArray.getString("sConductor_ContactNo"));
                                CatlistDes.add(mapp);

                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterCond wp = new GetterSetterCond(map.get("sBusIncharge_Name").toString(),map.get("sBusIncharge_ContactNo").toString(),map.get("sDriver_Name").toString(),map.get("sDriver_ContactNo").toString(),map.get("sConductor_Name").toString(),map.get("sConductor_ContactNo").toString());
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

    private class AdepterClassTimeTable extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        private List<GetterSetterCond> customerDatalist = null;
        private ArrayList<GetterSetterCond> array;
        String sp_edit;


        public AdepterClassTimeTable(Context context, List<GetterSetterCond> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<GetterSetterCond>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView sBusIncharge_Name, sBusIncharge_ContactNo, sDriver_Name,sDriver_ContactNo,sConductor_Name,sConductor_ContactNo;
            CheckBox bal;
            ImageView removetocart;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public GetterSetterCond getItem(int position) {
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
            view = inflater.inflate(R.layout.customcond, null);
            view.setTag(holder);
            holder.sBusIncharge_Name = (TextView) view.findViewById(R.id.sBusIncharge_Name);
            holder.sBusIncharge_ContactNo = (TextView) view.findViewById(R.id.sBusIncharge_ContactNo);
            holder.sDriver_Name = (TextView) view.findViewById(R.id.sDriver_Name);
            holder.sDriver_ContactNo = (TextView) view.findViewById(R.id.sDriver_ContactNo);
            holder.sConductor_Name = (TextView) view.findViewById(R.id.sConductor_Name);
            holder.sConductor_ContactNo = (TextView) view.findViewById(R.id.sConductor_ContactNo);


            holder.sBusIncharge_Name.setText(customerDatalist.get(position).getsBusIncharge_Name());
            holder.sBusIncharge_ContactNo.setText(customerDatalist.get(position).getsBusIncharge_ContactNo());
            holder.sDriver_Name.setText(customerDatalist.get(position).getsDriver_Name());
            holder.sDriver_ContactNo.setText(customerDatalist.get(position).getsDriver_ContactNo());
            holder.sConductor_Name.setText(customerDatalist.get(position).getsConductor_Name());
            holder.sConductor_ContactNo.setText(customerDatalist.get(position).getsConductor_ContactNo());


            return view;
        }

    }
}

