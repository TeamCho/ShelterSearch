package com.teamcho.sheltersearch.controllers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;

import com.teamcho.sheltersearch.R;
import com.teamcho.sheltersearch.model.Database;
import com.teamcho.sheltersearch.model.Shelter;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Database localDb = Database.getInstance();
    ArrayList<Shelter> shelters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        shelters = localDb.getShelterList();

        //move camera to Atlanta
        LatLng atlanta = new LatLng(33.75, -84.39);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atlanta, 11));

        for (int i = 0; i < shelters.size(); i++) {
            Shelter s = shelters.get(i);
            LatLng position = new LatLng(s.getLatitude(), s.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(position).title(s.getName()).snippet(s.getPhoneNum()));
        }
    }

    @Override
    public void onBackPressed() {
        localDb.clearData();
        localDb.loadData();
        Intent intent = new Intent(MapsActivity.this, UserHomeScreenActivity.class);
        startActivity(intent);
    }
}
