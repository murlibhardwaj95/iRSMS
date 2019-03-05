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

import com.syon.isrms.Adapter.IssueBook_Recycle_Adapter;
import com.syon.isrms.Beans.IssueBookBean;
import com.syon.isrms.Beans.Issued_Book_Data;
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
public class Issued_book_Fragment extends Fragment {
    RecyclerView recyclerView;
    ApiInterfce apiInterfce;
    SharedPreferences preferences;

    public Issued_book_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issued_book_, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerView = view.findViewById(R.id.issueBookRecycle);
        doApiOperation();
        return view;
    }

    private void doApiOperation() {
        try
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = com.syon.isrms.Interfaces_Teacher.ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<IssueBookBean> call = apiInterfce.getIssuedBookStatus(preferences.getString(getString(R.string.EmpId), ""), preferences.getString(getString(R.string.SessionId), ""), preferences.getString(getString(R.string.SchoolCode), ""));
            call.enqueue(new Callback<IssueBookBean>() {
                @Override
                public void onResponse(Call<IssueBookBean> call, Response<IssueBookBean> response) {
                    IssueBookBean bookBean = response.body();
                    if (response.code()==200) {
                        try
                        {
                            List<Issued_Book_Data> book_data = bookBean.getData();
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(new IssueBook_Recycle_Adapter(getContext(), book_data));
                        }

                        catch(Exception e)
                        {
                            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.issue_book_frame,new DataNotFoundFragment(),"notfound").commit();
                        }


                    }
                    else if(response.code()==400)
                    {
                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.issue_book_frame,new DataNotFoundFragment(),"notfound").commit();
                    }
                }

                @Override
                public void onFailure(Call<IssueBookBean> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
        }



    }
}