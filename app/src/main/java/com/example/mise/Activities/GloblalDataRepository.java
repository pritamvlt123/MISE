package com.example.mise.Activities;

import android.content.Context;
import android.content.Intent;
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

public class GloblalDataRepository {
    private static GloblalDataRepository globalDataService = null;


    public static GloblalDataRepository getInstance() {
        if (globalDataService == null)
            globalDataService = new GloblalDataRepository();

        return globalDataService;
    }





//////////////////Complaint Create//////////////////////////
public void uploadImage(File file,
                         String email){
    try {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("fileName",file.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(file.getAbsolutePath())))
                .addFormDataPart("SR_Email", email)

                .build();
        Request request = new Request.Builder()
                .url("https://www.misexam.com/API/Upload_Profile_Image")
                .method("POST", body)

                .build();
        //   Response response = client.newCall(request).execute();

        //Log.d("TAG",response.body().string());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                Log.d("sender", "Broadcasting message"+response);
//                Intent intent = new Intent("custom-event-name");
//                intent.putExtra("message", response.body().string());
//                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}