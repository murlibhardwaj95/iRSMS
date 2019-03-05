package com.syon.isrms.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.syon.isrms.Adapter.Admin_Notice_Board_Adapter;
import com.syon.isrms.Adapter.TeacherAdapter;
import com.syon.isrms.Adapter.Teacher_Notice_Board_Adapter;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_One;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_Post;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_Two;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Discusiion_Board extends AppCompatActivity {
  
    ApiInterfce apiInterfce;
    RecyclerView meassage;
    EditText body;
    SharedPreferences preferences;
    ImageButton send,refresh;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__discusiion__board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        doApiOperation();

    }
    private void doApiOperation() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        //  Call<Bean_Admin_Discussion_Board_One> call = apiInterfce.admindiscussion(preferences.getString(getString(R.string.adminSessionIdNo),""),preferences.getString(getString(R.string.adminSchCode),""));
        Call<Bean_Admin_Discussion_Board_One> call = apiInterfce.admindiscussion("112","MGS001");

        call.enqueue(new Callback<Bean_Admin_Discussion_Board_One>() {
            @Override
            public void onResponse(Call<Bean_Admin_Discussion_Board_One> call, Response<Bean_Admin_Discussion_Board_One> response) {
               if(response.code()==200){

                   Bean_Admin_Discussion_Board_One respo = response.body();
                   List<Bean_Admin_Discussion_Board_Two> data = respo.getData();

                   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Teacher_Discusiion_Board.this);
                   meassage.setLayoutManager(layoutManager);
                   meassage.smoothScrollToPosition(data.size());
                   meassage.setAdapter(new Teacher_Notice_Board_Adapter(Teacher_Discusiion_Board.this, data));

               }else{

                   Toast.makeText(Teacher_Discusiion_Board.this, "Data Not Found", Toast.LENGTH_SHORT).show();
               }


            }

            @Override
            public void onFailure(Call<Bean_Admin_Discussion_Board_One> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void init() {
        meassage = findViewById(R.id.teacher_messages_view);
        body = findViewById(R.id.teacher_body);
        send = findViewById(R.id.teacher_sendmeassage);
        refresh = findViewById(R.id.refresh);
        back_button = findViewById(R.id.back_button);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        doOperation();

    }

    private void doOperation() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacher_Discusiion_Board.super.onBackPressed();
            }
        });
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String body1 = body.getText().toString();
                        OkHttpClient.Builder builder = new OkHttpClient.Builder();
                        apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
                        Call<Bean_Admin_Discussion_Board_Post> call = apiInterfce.admindiscussionpost(preferences.getString(getString(R.string.EmpId),""),"2",body1,preferences.getString(getString(R.string.adminSessionIdNo),""),preferences.getString(getString(R.string.adminSchCode),""));
                        call.enqueue(new Callback<Bean_Admin_Discussion_Board_Post>() {
                            @Override
                            public void onResponse(Call<Bean_Admin_Discussion_Board_Post> call, Response<Bean_Admin_Discussion_Board_Post> response) {

                                if(response.code()==200){
                                    Bean_Admin_Discussion_Board_Post datapost = response.body();
                                    Toast.makeText(Teacher_Discusiion_Board.this, ""+datapost.getData(), Toast.LENGTH_SHORT).show();
                                    doApiOperation();
                                    body.setText("");
                                }
                                else{
                                    Toast.makeText(Teacher_Discusiion_Board.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Bean_Admin_Discussion_Board_Post> call, Throwable t) {

                            }
                        });
                    }
                });
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doApiOperation();
                    }
                });
            }

}
