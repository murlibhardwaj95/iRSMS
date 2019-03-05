package com.syon.isrms.Fragement;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Model_Class.SchoolNameResponse;
import com.syon.isrms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.syon.isrms.Activity.Login.longLog;

/**
 * Created by CHAMP on 5/30/2018.
 */

public class FragementF extends Fragment {

    EditText aadharno,stype,smb,sgmail,sadd;

    ProgressDialog pd;
    private SharedPreferences mPrefs ;
    String sSchCode,lUPIdNo,lSessionId;

    public FragementF() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_f, container, false);

        aadharno=view.findViewById(R.id.aadharno);
        stype=view.findViewById(R.id.stype);
        smb=view.findViewById(R.id.smb);
        sgmail=view.findViewById(R.id.sgmail);
        sadd=view.findViewById(R.id.sadd);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        SchoolNameResponse obj = gson.fromJson(json, SchoolNameResponse.class);

        lSessionId =obj.getWebSessionId();
        sSchCode =obj.getsSch_Code();
        lUPIdNo=mPrefs.getString("lUPIdNo", "");

        ProfileData(lUPIdNo,lSessionId,sSchCode);

        return view;
    }

    private void ProfileData(String lUPIdNo, String lSessionId, String sSchCode) {

      /*  pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
*/
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiClient.BASE_URL+"StudentInfo?lUPIdNo="+lUPIdNo+ "&lSessionId="+lSessionId+"&sSchCode="+sSchCode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  longLog("Log in Resopnce------------------------------------------------"+response);
                        longLog("response---" + response);

                        try {
                            JSONObject json=new JSONObject(response);
                          //  pd.hide();
                            JSONArray jsonArray = json.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject ob = jsonArray.getJSONObject(i);
                                aadharno.setText(ob.getString("sStudAadharNo"));
                                stype.setText(ob.getString("sStudType"));
                                smb.setText(ob.getString("sPhoneNo"));
                                sgmail.setText(ob.getString("sEmail"));
                                sadd.setText(ob.getString("sAddress"));


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
                        //pd.dismiss();
                        // pd.dismiss();
                       /* MyToast.show(getActivity(),"Connection Time Out Error Try Again !!", false);*/

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

