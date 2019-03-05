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
public class Profile_second_Fragment extends Fragment {
    SharedPreferences preferences;
    ApiInterfce apiInterfce;


    public Profile_second_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_profile_second_, container, false);
        final EditText setJoining = view.findViewById(R.id.editjoining);
        final EditText setDesignation = view.findViewById(R.id.editdesignation);
        final EditText setDepartment = view.findViewById(R.id.editdepartment);
        final EditText setPayscale = view.findViewById(R.id.editpayscale);
        final EditText setBasic = view.findViewById(R.id.editbasic);
        final EditText setPf = view.findViewById(R.id.editpf);
        final EditText setesi = view.findViewById(R.id.editesi);
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

                        setJoining.setText(data.get(0).getSJoinDate());
                        setDesignation.setText(data.get(0).getSDesignation());
                        setDepartment.setText(data.get(0).getSDepartment());
                        setPayscale.setText(data.get(0).getSPayScale());
                        setBasic.setText(data.get(0).getMBasic() + "");
                        setPf.setText(data.get(0).getSPFNo());
                        setesi.setText(data.get(0).getSESINo());
                    }
                    else
                    {
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.teachersendprofile,new DataNotFoundFragment(),"teachersent").commit();

                    }
                }

                @Override
                public void onFailure(retrofit2.Call<Example> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "something went Wrong try after sometime", Toast.LENGTH_SHORT).show();
        }
        // Inflate the layout for this fragment
        return view;
    }

}
