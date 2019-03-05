package com.syon.isrms.HelperClasses;

import android.util.Log;
import android.webkit.MimeTypeMap;

import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JSONParser {


    private static final String URL_UPLOAD_IMAGE = "http://www.webschoolapi.syontechnologies.com/api/FileUpload";


    public static JSONObject uploadImage(String sourceImageFile) {

        try {
            File sourceFile = new File(sourceImageFile);

            Log.d("TAG", "File...::::" + sourceFile + " : " + sourceFile.exists());

            String extension = MimeTypeMap.getFileExtensionFromUrl(sourceImageFile);
            Log.d("TAG", "File...::::" + MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)));
            final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");

            String filename = sourceImageFile.substring(sourceImageFile.lastIndexOf("/")+1);


            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("folder", "Communication")
                    .addFormDataPart("file", filename, RequestBody.create(MediaType.parse(MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)), sourceFile))
                    .build();

            Request request = new Request.Builder()
                    .url(URL_UPLOAD_IMAGE)
                    .post(requestBody)
                    .build();


            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            Log.e("TAG", "Error: " + res);
            return new JSONObject(res);

        } catch (UnknownHostException | UnsupportedEncodingException e) {
            Log.e("TAG", "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            Log.e("TAG", "Other Error: " + e.getLocalizedMessage());
        }
        return null;
    }
}
