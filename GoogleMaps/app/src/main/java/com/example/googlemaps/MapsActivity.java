package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //To find fragment id we use fragment manager
        //It return fragment , then we type cast this into Support map type

        SupportMapFragment MapFragment=(SupportMapFragment)(getSupportFragmentManager().findFragmentById(R.id.map));
        MapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;

        //For Setting Default Location
        LatLng latLng=new LatLng(30.045246,72.348869);
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Haider City");
        mMap.addMarker(markerOptions);

//For Moving to default location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//For Zooming in to default location
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16f));


        //For Cicle
//        mMap.addCircle(new CircleOptions().center(latLng).radius(1000).fillColor(Color.GREEN).strokeColor(Color.BLUE));

        //For Polygon
//
//        mMap.addPolygon(new PolygonOptions().add(new LatLng(30.045246,72.348869),
//                new LatLng(30.045246,73.348869),
//                new LatLng(31.045246,73.348869),
//                new LatLng(31.045246,72.348869),
//                new LatLng(30.045246,72.348869)).fillColor(Color.GRAY).strokeColor(Color.BLACK));

        //For Image

//        mMap.addGroundOverlay(new GroundOverlayOptions().position(latLng,1000f,1000f).
//                image(BitmapDescriptorFactory.fromResource(R.drawable.spider)).clickable(true));
mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //For getting locations
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
mMap.addMarker(new MarkerOptions().position(latLng).title("Haider City"));

                Geocoder geocoder=new Geocoder(MapsActivity.this);
                try {
                    ArrayList<Address> arrAdr=( ArrayList<Address>)geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    Log.d("Addr",arrAdr.get(0).getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        Geocoder geocoder=new Geocoder(MapsActivity.this);
        try {
            ArrayList<Address> arrAdr=( ArrayList<Address>)geocoder.getFromLocation(30.045246,72.348869,1);
            Log.d("Addr",arrAdr.get(0).getAddressLine(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}