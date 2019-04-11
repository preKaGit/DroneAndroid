package com.example.droneapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private boolean mConnected = false;

    CustomAnimations mCustomAnimations = new CustomAnimations();
    WiFiCommunicator mWiFiCommunicator = new WiFiCommunicator("",this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomAnimations.hideStatusBar(this);
        setContentView(R.layout.activity_main);
        navigate();


    }

    private void navigate() {
        final ImageView mImageView = findViewById(R.id.navigateToVideo);
        final ImageView mImageViewGPS = findViewById(R.id.navigateToMap);
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
    }


    @Override

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ImageView mWifiImage = findViewById(R.id.wifiImage);

        if(!mConnected){
            mCustomAnimations.wifiAnimation(mWifiImage).start();
        }
        else{

        }

    }
}