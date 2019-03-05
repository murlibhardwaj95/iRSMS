package com.syon.isrms.UploadData;

import android.util.Log;

import com.google.gson.Gson;
import com.syon.isrms.Beans.ParentComposeAttechmentBean;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Pratik Butani
 */
public class Upload_Data {
    String uploadResponse;
    /**
     * Upload URL of your folder with php file name...
     * You will find this file in php_upload folder in this project
     * You can copy that folder and paste in your htdocs folder...
     */
    private static final String URL_UPLOAD_IMAGE = "http://www.webschoolapi.syontechnologies.com/api/FileUpload";

    /**
     * Upload Image
     *
     * @param sourceImageFile
     * @return
     */
    public String uploadImage(String sourceImageFile) {

        try {
            File sourceFile = new File(sourceImageFile);

            Log.d("TAG", "File...::::" + sourceFile + " : " + sourceFile.exists());

            final MediaType MEDIA_TYPE_PNG = MediaType.parse("*/*");

            String filename = sourceImageFile.substring(sourceImageFile.lastIndexOf("/") + 1);


            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("folder", "Communication")
                    .addFormDataPart("file", filename, RequestBody.create(MEDIA_TYPE_PNG, sourceFile))

                    .build();

            Request request = new Request.Builder()
                    .url(URL_UPLOAD_IMAGE)
                    .post(requestBody)
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            String res = response.body().string();
            Gson gson = new Gson();
            ParentComposeAttechmentBean bean = gson.fromJson(res, ParentComposeAttechmentBean.class);
            uploadResponse = bean.getData().toString();
            Log.e("TAG", "Response: " + uploadResponse);
            Log.e("TAG", "Error: " + res);
            return uploadResponse;

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e("TAG", "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("TAG", "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }
}
