package com.example.droneapp;

import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    CustomAnimations mCustomAnimations = new CustomAnimations();
    private ArrayList<Marker> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomAnimations.hideStatusBar(this);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final TextView txt = findViewById(R.id.distanceMarkers);
        txt.setText("N/A");
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (markers.size() < 2){
                    markers.add(mMap.addMarker(new MarkerOptions().position(latLng)));
                    if (markers.size() == 2){
                        Polyline line = mMap.addPolyline(new PolylineOptions()
                                .add(markers.get(0).getPosition(), markers.get(1).getPosition())
                                .width(10)
                                .color(Color.BLUE));

                        float[] results = new float[1];
                        Location.distanceBetween(markers.get(0).getPosition().latitude, markers.get(0).getPosition().longitude,
                                markers.get(1).getPosition().latitude, markers.get(1).getPosition().latitude, results);

                        String distance=Float.toString(results[0]/1000);
                        txt.setText(distance + " KM");

                    }
                }

            }
        });


    }

}
