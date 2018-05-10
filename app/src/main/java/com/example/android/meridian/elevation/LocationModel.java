package com.example.android.meridian.elevation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class LocationModel extends AppCompatActivity implements LocationListener {
    private final LocationManager locationManager;
    private final LocationCallback locationCallback;
    private final Criteria criteria;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    public LocationModel(Context context, LocationCallback locationCallback) {
        this.locationCallback = locationCallback;
        this.criteria = new Criteria();
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void updateLocation(Context context) {
        String locationProviderName = getBestLocationProviderName();// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted, request one
            ActivityCompat.requestPermissions(LocationModel.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION );

        } else {
            // Permission has already been granted
            if (locationProviderName != null) {
                locationManager.requestSingleUpdate(locationProviderName, this, Looper.getMainLooper());
            }
        }


    }

    private String getBestLocationProviderName() {
        if (locationManager != null) {
            // Let's get a good location provider.
            return locationManager.getBestProvider(criteria, true /*enabledOnly*/);
        }
        return null;
    }

    public Location getLastLocation(Context context) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            ActivityCompat.requestPermissions(LocationModel.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);


        } else {
            if (locationManager != null) {
                return locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true /*enabledOnly*/));
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            locationCallback.locationUpdated(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
