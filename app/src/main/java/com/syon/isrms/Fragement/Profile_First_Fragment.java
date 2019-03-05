package com.syon.isrms.Fragement;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.syon.isrms.Beans.Example;
import com.syon.isrms.Beans.Userbean_Personal;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_First_Fragment extends Fragment {
    SharedPreferences preferences;
    ApiInterfce apiInterfce;

    public Profile_First_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile__first_, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final EditText setGender = view.findViewById(R.id.editgender);
        final EditText setDob = view.findViewById(R.id.editdob);
        final EditText setAadhar = view.findViewById(R.id.editaadhar);
        final EditText setPan = view.findViewById(R.id.editpan);
        final EditText setMobile = view.findViewById(R.id.editmobile);
        final EditText setEmail = view.findViewById(R.id.editemail);
        final EditText setAddress = view.findViewById(R.id.editaddress);
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            retrofit2.Call<Example> call = apiInterfce.Login(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(retrofit2.Call<Example> call, Response<Example> response) {
                    if (response.code() == 200) {
                        Example example = response.body();
                        List<Userbean_Personal> data = example.getData();
                        setGender.setText(data.get(0).getSGender());
                        setDob.setText(data.get(0).getSDOB());
                        setAadhar.setText(data.get(0).getSAadharNo());
                        setPan.setText(data.get(0).getSPANNo());
                        setEmail.setText(data.get(0).getSEmail());
                        setAddress.setText(data.get(0).getSAddress());
                        setMobile.setText(data.get(0).getMobileNo());
                    } else {
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.tacherProfileFirst,new DataNotFoundFragment(),"teachersent").commit();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<Example> call, Throwable t) {

                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


}
