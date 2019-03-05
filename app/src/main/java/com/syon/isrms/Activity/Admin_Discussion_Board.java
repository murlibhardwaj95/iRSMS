package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_Notice_Board_Adapter;
import com.syon.isrms.Adapter.Birthday_Recycle_Adapter;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_One;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_Post;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_Two;
import com.syon.isrms.Beans.Userbean_Admin_Leave_Approval_Put;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_Discussion_Board extends AppCompatActivity {

    ApiInterfce apiInterfce;
    RecyclerView meassage;
    EditText body;
    ImageView back_button;
    SharedPreferences preferences;
    ImageButton send,refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__discussion__board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#075e54"));
        }

        init();
        doApiOperation();
    }
    private void doApiOperation() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
            Call<Bean_Admin_Discussion_Board_One> call = apiInterfce.admindiscussion(preferences.getString(getString(R.string.adminSessionIdNo),""),preferences.getString(getString(R.string.adminSchCode),""));
            call.enqueue(new Callback<Bean_Admin_Discussion_Board_One>() {
                @Override
                public void onResponse(Call<Bean_Admin_Discussion_Board_One> call, Response<Bean_Admin_Discussion_Board_One> response) {
                    if(response.code()==200){

                        Bean_Admin_Discussion_Board_One respo = response.body();
                        List<Bean_Admin_Discussion_Board_Two> data = respo.getData();
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Admin_Discussion_Board.this);
                        meassage.setLayoutManager(layoutManager);
                        meassage.smoothScrollToPosition(data.size());
                        meassage.setAdapter(new Admin_Notice_Board_Adapter(Admin_Discussion_Board.this, data));

                    }else{

                    } }

                @Override
                public void onFailure(Call<Bean_Admin_Discussion_Board_One> call, Throwable t) {

                }
            });
        }catch (Exception e){

        }
    }

    private void init() {
        meassage = findViewById(R.id.messages_view);
        body = findViewById(R.id.body);
        send = findViewById(R.id.sendmeassage);
        back_button = findViewById(R.id.back_button);
        refresh = findViewById(R.id.admin_refresh);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        doOperation();

    }

  private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Discussion_Board.super.onBackPressed();
            }
        });
      refresh.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              doApiOperation();
          }
      });

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String body1 = body.getText().toString();
                        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                        Call<Bean_Admin_Discussion_Board_Post> call = apiInterfce.admindiscussionpost(preferences.getString(getString(R.string.adminEmpIdNo),""),"3",body1,preferences.getString(getString(R.string.adminSessionIdNo),""),preferences.getString(getString(R.string.adminSchCode),""));
                        call.enqueue(new Callback<Bean_Admin_Discussion_Board_Post>() {
                            @Override
                            public void onResponse(Call<Bean_Admin_Discussion_Board_Post> call, Response<Bean_Admin_Discussion_Board_Post> response) {

                                if(response.code()==200){
                                    Bean_Admin_Discussion_Board_Post datapost = response.body();
                                    Toast.makeText(Admin_Discussion_Board.this, ""+datapost.getData(), Toast.LENGTH_SHORT).show();
                                    doApiOperation();
                                    body.setText("");
                                }
                                else{
                                    Toast.makeText(Admin_Discussion_Board.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Bean_Admin_Discussion_Board_Post> call, Throwable t) {

                            }
                        });
                    }
                });
            }
}
