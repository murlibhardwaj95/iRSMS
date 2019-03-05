

package com.syon.isrms.Interfaces_Teacher;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static String BASE_URL="";
    public static Retrofit retrofit = null;


    public ApiClient(String url){
        this.BASE_URL=url;

    }

    public static Retrofit getApiClient(OkHttpClient.Builder httpClient)
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build();
        }
        return retrofit;
    }
}
