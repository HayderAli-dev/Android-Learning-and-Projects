package com.example.chatbotimagegenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
ProgressBar pgBar;
EditText edText;
MaterialButton btn;
ImageView imageView;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgBar=findViewById(R.id.progress_circular);
        edText=findViewById(R.id.edittext);
        btn=findViewById(R.id.btn);
        imageView=findViewById(R.id.img);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=edText.getText().toString();
                if (query.isEmpty()){
                    edText.setError("Prompt can't be Empty");
                }
                callApi(query);
            }
        });
    }

    private void callApi(String query) {
        JSONObject jsonBody=new JSONObject();
        try {
            jsonBody.put("prompt",query);
            jsonBody.put("size","256x256");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestBody requestBody=RequestBody.create(jsonBody.toString(),JSON);
        Request request=new Request.Builder().url("https://api.openai.com/v1/images/generations")
                .header("Authorization","Bearer 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")
                .post(requestBody).build();
        OkHttpClient client=new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(MainActivity.this,"Response Failed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
if (response.isSuccessful()){
    try {
        JSONObject jsonObject=new JSONObject(response.body().string());
        String imgURL=jsonObject.getJSONArray("data").getJSONObject(0).getString("url");
       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               loadImage(imgURL);
           }
       });

    } catch (JSONException e) {
        throw new RuntimeException(e);
    }
}
            }
        });

    }
    void loadImage(String url){
runOnUiThread(new Runnable() {
    @Override
    public void run() {
        Picasso.get().load(url).into(imageView);
    }
});
    }
}