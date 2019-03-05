package com.syon.isrms.Fragement;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import static com.syon.isrms.Activity.Login.longLog;

/**
 * Created by CHAMP on 5/30/2018.
 */

public class FragementG extends Fragment {

    ProgressDialog pd;
    private SharedPreferences mPrefs ;
    String sSchCode,lUPIdNo,lSessionId;

    EditText fname,fmb,fgmail,mname,mmob,mgmail;
    TextView f,m;


    public FragementG() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_g, container, false);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        fname=view.findViewById(R.id.fname);
        fmb=view.findViewById(R.id.fmb);
        fgmail=view.findViewById(R.id.fgmail);
        mname=view.findViewById(R.id.mname);
        mmob=view.findViewById(R.id.mmob);
        mgmail=view.findViewById(R.id.mgmail);
        f=view.findViewById(R.id.father_header);
        m=view.findViewById(R.id.mother_header);
        f.setPaintFlags(f.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        m.setPaintFlags(m.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        com.syon.isrms.Model_Class.SchoolNameResponse obj = gson.fromJson(json, com.syon.isrms.Model_Class.SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        ProfileData(lUPIdNo,lSessionId,sSchCode);
        return view;
    }

    private void ProfileData(String lUPIdNo, String lSessionId, String sSchCode) {

   /*     pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();*/

        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudentInfo?lUPIdNo="+lUPIdNo+ "&lSessionId="+lSessionId+"&sSchCode="+sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  longLog("Log in Resopnce------------------------------------------------"+response);
                        longLog("response---" + response);

                        try {
                            JSONObject json=new JSONObject(response);
                            //pd.hide();
                            JSONArray jsonArray = json.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject ob = jsonArray.getJSONObject(i);
                                fname.setText(ob.getString("sFatherName"));
                                mname.setText(ob.getString("sMotherName"));
                                fmb.setText(ob.getString("sFatherMobile"));
                                mmob.setText(ob.getString("sMotherMobile"));
                                fgmail.setText(ob.getString("sFatherEmail"));
                                mgmail.setText(ob.getString("sMotherEmail"));

                            }


                        }catch (JSONException e){
                         /*   MyToast.show(getActivity(),"Error while reading data try again !!!", false);*/
                            longLog("Json --------- Exception..."+e.toString());
                           // pd.hide();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  pd.dismiss();
                        // pd.dismiss();
                     /*   MyToast.show(getActivity(),"Connection Time Out Error Try Again !!", false);*/

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
}

