package com.example.highwaytohealth;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class EmergencyAlert2 extends WearableActivity implements LocationListener {

    final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 0;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_alert2);

        mImageView = (ImageView) findViewById(R.id.highWarning);

        // Enables Always-on
        setAmbientEnabled();

        //public class MainActivity extends AppCompatActivity  implements LocationListener {

        final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 0;

        //  @Override
        // protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        boolean permissionAccessFineLocationApproved =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;

        TextView myLabel = findViewById(R.id.locationLabel);
        if (permissionAccessFineLocationApproved) {

            myLabel.setText("User has previously provided permission.");

            startGPS();

        } else {

            myLabel.setText("User has not provided permission yet.");

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                finish();
            } else {
                startGPS();
            }
        }
    }

    private void startGPS() {

        boolean permissionAccessFineLocationApproved =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;
        if (permissionAccessFineLocationApproved) {

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }


    @Override
    public void onLocationChanged(Location location) {
        TextView myLabel = findViewById(R.id.locationLabel);
        myLabel.setText("Location: Gainesville, " + location.getLongitude() + ", " + location.getLatitude());
        //If I am In Paynes Prairie
        if (Math.abs(-82.2955414 - location.getLongitude()) < 0.001 &&
                Math.abs(29.520249 - location.getLatitude()) < 0.001) {
            Intent my_intent = new Intent(getBaseContext(), MainActivity.class);
            my_intent.putExtra("text", "Current Location: Gainesville, FL");

            // May be able to use something similar to the line below for drawing media

            startActivity(my_intent);
        }
    }
}
