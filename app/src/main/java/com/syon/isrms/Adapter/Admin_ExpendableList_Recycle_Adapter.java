package com.syon.isrms.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syon.isrms.Beans.EventCalenderMonthlyBean;
import com.syon.isrms.Beans.Event_Calender_Monthly_Data;
import com.syon.isrms.Beans.ParentEventMonthlyBean;
import com.syon.isrms.Beans.Parent_Event_Monthly_Bean_Data;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin_ExpendableList_Recycle_Adapter extends RecyclerView.Adapter<Admin_ExpendableList_Recycle_Adapter.ViewHolder> {
    List<com.syon.isrms.Beans.Parent_Event_Yealy_Bean_Data> data;
    /*    List<Event_Calender_Monthly_Data> data1;*/
    String[] paramter;
    /*, List<Event_calender_Inner_Data> data1*/
    Context context;
    Boolean flag = false;

    public Admin_ExpendableList_Recycle_Adapter(Context context, List<com.syon.isrms.Beans.Parent_Event_Yealy_Bean_Data> data, String[] parameter) {
        this.context = context;
        this.data = data;
        this.paramter = parameter;
        /*   this.data1 = data1;*/


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_expendablelist_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.header.setText(data.get(position).getSMonthYear());
        String smonth = data.get(position).getNMonth().toString();
        String syear = data.get(position).getNYear().toString();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ApiInterfce apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<EventCalenderMonthlyBean> call = apiInterfce.getEventMonthly(paramter[0], smonth, syear, paramter[1], paramter[2]);
        call.enqueue(new Callback<EventCalenderMonthlyBean>() {
            @Override
            public void onResponse(Call<EventCalenderMonthlyBean> call, Response<EventCalenderMonthlyBean> response) {
                if (response.isSuccessful()) {
                    EventCalenderMonthlyBean bean = response.body();
                    List<Event_Calender_Monthly_Data> data1 = bean.getData();

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                    holder.recyclerView.setLayoutManager(layoutManager);
                    holder.recyclerView.setAdapter(new Parent_ExpendableListData_Recycle_Adapter(context, data1));
                }


            }

            @Override
            public void onFailure(Call<EventCalenderMonthlyBean> call, Throwable t) {

            }
        });
        Picasso.with(context).load(data.get(position).getSMonthPhoto()).into(holder.head_image);





    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout l1;
        RelativeLayout r1;
        TextView header;
        ImageView head_image, list_arrow;
        RecyclerView recyclerView;
        List<Event_Calender_Monthly_Data> list_data;


        public ViewHolder(View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.txt_header);
            head_image = itemView.findViewById(R.id.head_image);
            list_arrow = itemView.findViewById(R.id.list_arrow);
            l1 = itemView.findViewById(R.id.child_layout);
            r1 = itemView.findViewById(R.id.header_layout);
            recyclerView = itemView.findViewById(R.id.child_recycler);


        }
    }
}
