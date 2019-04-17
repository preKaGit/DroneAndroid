package com.example.droneapp;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    CustomAnimations mCustomAnimations = new CustomAnimations();
    Network network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomAnimations.hideStatusBar(this);
        setContentView(R.layout.activity_main);
        navigate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        testClient();
        network.start();

    }
    public void testClient() {
        network = new Network(this);
    }

    private void navigate() {
        final ImageView mImageView = findViewById(R.id.navigateToVideo);
        final ImageView mImageViewGPS = findViewById(R.id.navigateToMap);
        final ImageView mImageViewDiagnostics = findViewById(R.id.navigateToDiagnostics);
        final ImageView mImageViewWiFi = findViewById(R.id.wifiImage);
        ImageView mLogoImage = findViewById(R.id.saab_logo);
        mCustomAnimations.logoAnimation(mLogoImage, this);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
                mCustomAnimations.clickAnimation(mImageView);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out
                );
            }
        });
        mImageViewGPS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                mCustomAnimations.clickAnimation(mImageViewGPS);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        mImageViewDiagnostics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GraphActivity.class);
                startActivity(intent);
                mCustomAnimations.clickAnimation(mImageViewDiagnostics);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        mImageViewWiFi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println(network.send("TEMP"));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        network.end();
    }

    @Override

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ImageView mWifiImage = findViewById(R.id.wifiImage);

        if(network.isAlive()){
            mCustomAnimations.wifiAnimation(mWifiImage).start();
        }
        else {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            int numberOfLevels = 5;
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
            switch (level){
                case 0:
                    mWifiImage.setImageResource(R.drawable.ic_signal_wifi_0_bar_black_24dp);
                case 1:
                    mWifiImage.setImageResource(R.drawable.ic_signal_wifi_1_bar_black_24dp);
                case 2:
                    mWifiImage.setImageResource(R.drawable.ic_signal_wifi_2_bar_black_24dp);
                case 3:
                    mWifiImage.setImageResource(R.drawable.ic_signal_wifi_3_bar_black_24dp);
                case 4:
                    mWifiImage.setImageResource(R.drawable.ic_signal_wifi_4_bar_black_24dp);
            }


        }

        }
}
