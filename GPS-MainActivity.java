// Inside your MainActivity.java
package com.example.gpsapp;

// Inside your MainActivity.java

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView locationText;
    private LocationManager locationManager;
    private Handler handler;
    private static final int LOCATION_UPDATE_INTERVAL = 5000;
    // Inside your MainActivity.java
   



    // Constants for permissions
    private static final int PERMISSION_REQUEST_CODE = 1;

    // Check and request location permissions
    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }
    }
// Update location every 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationText = findViewById(R.id.locationText);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        handler = new Handler();
        locationText.setText("Location is : " + lat + ", " + lon);
        //locationText.setText("Location: is standard" );

        // Request location permission
        requestLocationPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Request location updates at regular intervals
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 0, this);

            // Initial location update
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                onLocationChanged(lastKnownLocation);
            }
        }
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        // Handle new location data
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        //lat=latitude;
        //lon=longitude;

        // Update the UI with the location data
        locationText.setText("Location is : " + latitude + ", " + longitude);
    }

    // Implement other LocationListener methods (onStatusChanged, onProviderEnabled, onProviderDisabled) as needed

    // Add appropriate error handling and permission checking for production use
}
