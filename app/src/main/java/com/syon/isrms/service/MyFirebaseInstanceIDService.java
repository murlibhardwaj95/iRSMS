package com.syon.isrms.service;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by abc on 4/27/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        // saveTocken(token);
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }


   /* private void saveTocken(String token) {
        //  pd.show();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, "http://earnwithstudy.com/api/posts/user_info/?userid="+MainActivityNavigation.userId+"&token="+token, null, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject response) {

// TODO Auto-generated method stub


            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

// TODO Auto-generated method stub
                //  pd.dismiss();
                Toast.makeText(getApplicationContext(),"Server Error Try Again !!!",Toast.LENGTH_SHORT).show();
            }

        });

        RequestQueue requestQueue = Volley.newRequestQueue(MyFirebaseInstanceIDService.this);
        requestQueue.add(jsObjRequest);
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                55000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }
*/

}