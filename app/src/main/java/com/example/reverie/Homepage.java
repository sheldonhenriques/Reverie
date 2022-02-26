package com.example.reverie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {
    Button mbutton;
    Button Travel;
    Button Work;
    Button Morning;
    Button Sleep;
    Button Bored;
    Button Anxious;

    Switch WifiOn;



    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mbutton = (Button) findViewById(R.id.mainbutton);
        Travel = (Button) findViewById(R.id.travelbut);
        Work = (Button) findViewById(R.id.workbut);
        Morning = (Button) findViewById(R.id.mornbut);
        Sleep = (Button) findViewById(R.id.Sleepbut);
        Bored = (Button) findViewById(R.id.Chillbut);
        Anxious = (Button) findViewById(R.id.assibut);


        WifiOn = (Switch) findViewById(R.id.wifiswitch);
        WifiOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    WifiManager onWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    onWifi.setWifiEnabled(true);
                    Toast.makeText(getBaseContext(), "Wifi On",Toast.LENGTH_LONG);
                }else{
                    WifiManager onWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    onWifi.setWifiEnabled(false);
                    Toast.makeText(getBaseContext(), "Wifi Off",Toast.LENGTH_LONG);
                }
            }
        });

        ActivityCompat.requestPermissions(Homepage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        GPStracker g = new GPStracker(getApplicationContext());
        Location l = g.getLocation();
        if (l != null){
            double lat = l.getLatitude();
            double lon = l.getLongitude();
            Toast.makeText(getApplicationContext(),"Latitude: "+lat + "\n Longitude: " + lon ,Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onBackPressed() {


        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else{
            Toast.makeText(getBaseContext(),"Press again to exit", Toast.LENGTH_LONG).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
