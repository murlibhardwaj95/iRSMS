package com.syon.isrms.Fragement;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.syon.isrms.Broadcast_Recievers.MyReceiver;
import com.syon.isrms.Model_Class.GetterSetterClassTimeTable;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.syon.isrms.Interfaces_Teacher.ApiClient.BASE_URL;

/**
 * Created by CHAMP on 5/30/2018.
 */

public class FragementClassTimeTable extends Fragment {

    ListView listView;
    ArrayList<HashMap<String,String>> CatlistDes=new ArrayList<>();
    ArrayList<GetterSetterClassTimeTable> array = new ArrayList<GetterSetterClassTimeTable>();

    private SharedPreferences mPrefs ;
    private static final int PERMISSION_REQUEST_CODE = 1;

    DownloadManager downloadManager;
    String lUPIdNo,lClass_IdNo,lSec_IdNo,nStudType;
    String lSessionId;
    String sSchCode;
    static String dataaa;
    AdepterClassTimeTable adapter;
    LinearLayout datanotfound;


    public FragementClassTimeTable() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_class_time_table, container, false);
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
        lClass_IdNo=mPrefs.getString("lClass_IdNo","");
        lSec_IdNo=mPrefs.getString("lSec_IdNo","");
        nStudType=mPrefs.getString("nStudType","");
        ClassTimeT(lClass_IdNo,lSec_IdNo,nStudType,lSessionId,sSchCode);
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
    private void ClassTimeT(String lClass_IdNo, String lSec_IdNo, String nStudType, String lSessionId, String sSchCode) {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL+"ClassTimeTable/?lClassId="+lClass_IdNo + "&lSecId="+lSec_IdNo+"&lSessionId="+ lSessionId +"&sSchCode="+sSchCode,
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
                                    map.put("lCTTId", ob.getString("lCTTId"));
                                    map.put("attachment", ob.getString("attachment"));
                                    map.put("sClass", ob.getString("sClass"));
                                    map.put("sSection", ob.getString("sSection"));
                                    CatlistDes.add(map);
                                }
                                for (int i = 0; i < CatlistDes.size(); i++)
                                {
                                    HashMap<String,String> map=new HashMap<>();
                                    map=CatlistDes.get(i);
                                    GetterSetterClassTimeTable wp = new GetterSetterClassTimeTable(map.get("lCTTId").toString(),map.get("attachment").toString(),map.get("sClass").toString(),map.get("sSection").toString());
                                    array.add(wp);
                                }
                                adapter = new AdepterClassTimeTable(getActivity(), array);
                                listView.setAdapter(adapter);

                            }else if (obj.getString("status").equals("error")) {
                                pd.hide();

                                datanotfound.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.GONE);
//                                MyToast.show(getActivity(),"No Data Available !!!",false);



                            }
                        } catch (JSONException e) {
                            Log.d("json Exception",e.toString());
                            // avloader.hide();
                            pd.dismiss();

                            datanotfound.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.GONE);
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

                        datanotfound.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                   /*     MyToast.show(getActivity(),"No Data Available !!!",false);*/
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
        private List<GetterSetterClassTimeTable> customerDatalist = null;
        private ArrayList<GetterSetterClassTimeTable> array;
        String sp_edit;


        public AdepterClassTimeTable(Context context, List<GetterSetterClassTimeTable> custumerList) {
            mContext = context;
            this.customerDatalist = custumerList;
            inflater = LayoutInflater.from(mContext);
            this.array = new ArrayList<GetterSetterClassTimeTable>();
            this.array.addAll(customerDatalist);
        }

        public class ViewHolder {
            TextView Class, Section, Attachment;
            CheckBox bal;
            ImageView removetocart;
        }

        @Override
        public int getCount() {
            return customerDatalist.size();
        }

        @Override
        public GetterSetterClassTimeTable getItem(int position) {
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
            view = inflater.inflate(R.layout.customviewclasstime, null);
            view.setTag(holder);
            holder.Class = (TextView) view.findViewById(R.id.Class);
            holder.Section = (TextView) view.findViewById(R.id.Section);
            holder.removetocart = view.findViewById(R.id.download_time);
            holder.Attachment = (TextView) view.findViewById(R.id.Attachment);


            holder.Class.setText(customerDatalist.get(position).getsClass());
            holder.Section.setText(customerDatalist.get(position).getsSection());


            String filename=customerDatalist.get(position).getAttachment();
            String filen=filename.substring(filename.lastIndexOf("/")+1);

            holder.Attachment.setText(filen);



            holder.removetocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Do you want to download the attachment?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                                        if (customerDatalist.get(position).getAttachment().equalsIgnoreCase("")){
                                            Toast.makeText(getActivity(),"There is no Attachment !!!",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            MyReceiver myReceiver = new MyReceiver();
                                            mContext.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

                                            Uri Download_Uri = Uri.parse(customerDatalist.get(position).getAttachment().trim());

                                            String fileExtension = customerDatalist.get(position).getAttachment().trim();

                                            String fileExt = fileExtension.substring(fileExtension.lastIndexOf("."));

                                            String fileNameWithoutExtension = fileExtension.substring(0, fileExtension.lastIndexOf('.'));


                                            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                                            request.setAllowedOverRoaming(false);
                                            request.setTitle("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setDescription("Downloading " + fileNameWithoutExtension + fileExt);
                                            request.setVisibleInDownloadsUi(true);
                                            request.setDestinationInExternalPublicDir("/iSRMS", fileExt);


                                            downloadManager.enqueue(request);


                                        }


                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });


            return view;
        }

    }

}

