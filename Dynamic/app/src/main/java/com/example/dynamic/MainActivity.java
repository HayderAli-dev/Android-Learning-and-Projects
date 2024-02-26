package com.example.dynamic;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.list);
String url="https://jsonplaceholder.typicode.com/users";

        ArrayList<String> arrNames=new ArrayList<>();
        //Creatig http client
OkHttpClient client=new OkHttpClient();
Request request=new Request.Builder().url(url).build();
client.newCall(request).enqueue(new Callback() {
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            try {
                String responseBody = response.body().string();

                // Parse the JSON array response
                JSONArray jsonArray = new JSONArray(responseBody);

                // Process each object in the JSON array
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject address=jsonObject.getJSONObject("address");
                    String street=address.getString("street");

                    // Extract values from the JSON object
                    String name = jsonObject.getString("name");
                    String userName = jsonObject.getString("username");
                    arrNames.add(name+" "+userName+" "+street);

                }

                // Since this is an asynchronous callback, update the UI on the main thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update the UI here if needed.
                        // For example, you can display the extracted values in TextViews.
                        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1
                                ,arrNames);
                        listView.setAdapter(adapter);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
                // Handle JSON parsing error
            }

        } else {
            // Handle non-successful response
        }
    }
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        e.printStackTrace();
        Log.d("Error",e.toString());
    }
});

    }
}