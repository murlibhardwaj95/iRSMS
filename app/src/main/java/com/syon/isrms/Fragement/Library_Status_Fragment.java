package com.syon.isrms.Fragement;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.syon.isrms.Adapter.LibraryStatus_Recycle_Adapter;
import com.syon.isrms.Beans.LibraryStatusBean;
import com.syon.isrms.Beans.Library_Status_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Library_Status_Fragment extends Fragment {


    ApiInterfce apiInterfce;
    RecyclerView recyclerView;
    SharedPreferences preferences;

    public Library_Status_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library__status_, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerView = view.findViewById(R.id.libraryStatusRecycle);
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<LibraryStatusBean> call = apiInterfce.getLibraryStatus(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<LibraryStatusBean>() {
                @Override
                public void onResponse(Call<LibraryStatusBean> call, Response<LibraryStatusBean> response) {
                    LibraryStatusBean bookBean = response.body();
                    if (response.code()==200) {
                        try {
                            List<Library_Status_Data> library_status_data = bookBean.getData();
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(new LibraryStatus_Recycle_Adapter(getContext(), library_status_data));
                        }
                        catch(Exception e)
                        {
                            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.libray_status_frame,new DataNotFoundFragment(),"notfound").commit();
                        }
                    }
                    else if(response.code()==400)
                    {
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.libray_status_frame,new DataNotFoundFragment(),"notfound").commit();
                    }
                }

                @Override
                public void onFailure(Call<LibraryStatusBean> call, Throwable t) {

                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }

    }
}
