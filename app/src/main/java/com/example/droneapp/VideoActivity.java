package com.example.droneapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class VideoActivity extends AppCompatActivity {
    CustomAnimations mCustomAnimations = new CustomAnimations();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomAnimations.hideStatusBar(this);
        setContentView(R.layout.activity_video);
        isPlaying();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ImageView mWifiImage = findViewById(R.id.videoWifi);
        mCustomAnimations.wifiAnimation(mWifiImage).start();

    }

    public void isPlaying(){
      /*  VideoView mVideoView = findViewById(R.id.videoView);
        if (!mVideoView.isPlaying()){
            mCustomAnimations.onBackPressed(this,"No connection", "Error wifi","OK", this);
        }*/
    }
}
