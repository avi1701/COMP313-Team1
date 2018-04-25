package com.example.avneet.project;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    int id;
    String name;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent ob=getIntent();

        id=ob.getIntExtra("id",0);
        name=ob.getStringExtra("name");
        LatLng rest;
        if(id==1)
            rest = new LatLng( 43.7367476,-79.2746991);
        else if(id==2)
            rest = new LatLng(43.7820252,-79.2237602);
        else if(id==3)
            rest = new LatLng(43.7731073,-79.2232755);
        else
            rest = new LatLng(43.7847834,-79.2340124);
        mMap.addMarker(new MarkerOptions().position(rest).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rest));
    }
}
