package com.example.weatherapp;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SplittableRandom;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
private ArrayList<WeatherModel> modelArrayList;
private Weather_rv_Adapter adapter;
private LocationManager locationManager;
private int PERMISSION_CODE=1;
private String cityName;
private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog=new ProgressDialog(MainActivity.this);
        modelArrayList=new ArrayList<>();
        adapter=new Weather_rv_Adapter(this,modelArrayList);
        binding.idRVWeather.setAdapter(adapter);
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.GET_PERMISSIONS&&
        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.GET_PERMISSIONS){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }
        Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        cityName=getCityName(location.getLatitude(),location.getLongitude());
        getWeatherInfo(cityName);
        binding.idIVSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city=binding.idETCity.getText().toString();
                dialog.setMessage("Loading Weather of "+city);
                dialog.setCancelable(false);
                dialog.show();
                if (city.isEmpty()){
                    binding.idETCity.setError("Kindly Enters City Name");
                } else {
                    hideKeyboard();
                    getWeatherInfo(city);
                }
            }
        });





    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permissions Granted",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Permissions not granted, Kindly grant permissions",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private String getCityName(double latitude, double longitude){
         cityName="City Not Found";
        Geocoder gcd=new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses=gcd.getFromLocation(latitude,longitude,6000);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                Log.d("GeocoderResults", "Address: " + address.toString());
                cityName = address.getLocality();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cityName;
    }
    public void getWeatherInfo(String cityName){
        String Url="https://api.weatherapi.com/v1/forecast.json?key=62e377b8ed2544029ab72210231908&q="+cityName+"&days=1&aqi=yes&alerts=yes";
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Url,null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();
                binding.idPBLoading.setVisibility(View.GONE);
                binding.idRLHome.setVisibility(View.VISIBLE);
runOnUiThread(new Runnable() {
    @Override
    public void run() {
        modelArrayList.clear();
        try {
            binding.idETCity.setText("");
            binding.idETCity.clearFocus();
            binding.idTVCityName.setText(cityName);
            String temperature=response.getJSONObject("current").getString("temp_c");
            binding.idTVTemperature.setText(temperature+"°C");
            String condition=response.getJSONObject("current").getJSONObject("condition").getString("text");
            String conditionIcon=response.getJSONObject("current").getJSONObject("condition").getString("icon");
            binding.idTVCondition.setText(condition);
            Picasso.get().load("https:".concat(conditionIcon)).into(binding.idIVIcon);
            int is_Day=response.getJSONObject("current").getInt("is_day");
                    if (is_Day==1){
                        //Morning
                        Picasso.get().load("https://i.imgur.com/422zYvW.jpeg").into(binding.idIVBackground);
                    }
                    else {
                       // Night
                        Picasso.get().load("https://i.imgur.com/94kP05R.jpg").into(binding.idIVBackground);
                    }
            JSONObject forecast=response.getJSONObject("forecast");
            JSONObject forecastday=forecast.getJSONArray("forecastday").getJSONObject(0);
            JSONArray hourArray=forecastday.getJSONArray("hour");
            for (int i=0;i<hourArray.length();i++){
                JSONObject hour=hourArray.getJSONObject(i);
                String time=hour.getString("time");
                String conditonImg=hour.getJSONObject("condition").getString("icon");
                String temp=hour.getString("temp_c");
                String windSpeed=hour.getString("wind_kph");
                modelArrayList.add(new WeatherModel(time,temp,windSpeed,conditonImg));
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
});

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                binding.idETCity.setText("");
                binding.idETCity.clearFocus();
Toast.makeText(MainActivity.this,"Kindly Enter Correct Location , No Weather update found!",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}