package com.syon.isrms.HelperClasses;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostMultipartNews {


    private MediaType MEDIA_TYPE_FILE = MediaType.get("*/*");

    private final OkHttpClient client = new OkHttpClient();


    public void run(String base_url,String type,File file) throws Exception {

        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("folder", type)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MEDIA_TYPE_FILE, new File(file.getPath())))
                .build();

        Request request = new Request.Builder()
                .url(base_url+"FileUpload")
                .post(requestBody)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "Response: " + "Fail");

                Log.e("TAG", "error: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("TAG", "Response: " + "Success");

                Log.e("TAG", "Response: " + response.body());


            }
        });
/*
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }*/
    }
}
