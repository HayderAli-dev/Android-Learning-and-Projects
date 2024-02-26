package com.example.postapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client=new OkHttpClient();

        String url = "https://wscubetech.org/android-course/get-data.php";

        // Create the form body with the parameter course_id=1
        RequestBody formBody = new FormBody.Builder()
                .add("course_id", "1")
                .build();
        Request request=new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
if(response.isSuccessful()){
    Log.d("Response Successfull",  response.toString());
    String responseBody=response.body().string();
    try {
        //Method 1
        JSONArray jsonArray=new JSONArray(responseBody);
        JSONObject jsonObject=jsonArray.getJSONObject(0);
        JSONObject details=jsonObject.getJSONObject("data");
        String name=details.getString("name");

        //Method 2
//        JSONObject job=new JSONObject(responseBody);
//        JSONObject next=jsonObject.getJSONObject("data");
//        next.getString("name");
    } catch (JSONException e) {
        throw new RuntimeException(e);
    }

}
else {
    Log.d("Response UNSuccessfull",  response.toString());
}
            }
        });
    }
}