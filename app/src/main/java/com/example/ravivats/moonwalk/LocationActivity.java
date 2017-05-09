package com.example.ravivats.moonwalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@SuppressWarnings("ALL")
public class LocationActivity extends AppCompatActivity  {
    TextView latitudeField,longitudeField,distanceField;
    private Button getLocation;
    private TrackGPS gps;
    double longitude,latitude;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_location);
            getLocation = (Button)findViewById(R.id.getLocationBtn);
            latitudeField =(TextView) findViewById(R.id.latitudeTxt);
            longitudeField =(TextView) findViewById(R.id.longitudeTxt);
            distanceField =(TextView) findViewById(R.id.distanceTxt);

            getLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gps = new TrackGPS(LocationActivity.this);
                    if(gps.canGetLocation()){
                        longitude = gps.getLongitude();
                        latitude = gps .getLatitude();
                        distanceField.setText(R.string.distance);
                        latitudeField.setText("Latitude:  "+latitude);
                        longitudeField.setText("Longitude:  "+longitude);
                    }

                    else { gps.showSettingsAlert();}
                }
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            gps.stopUsingGPS();
        }
}
