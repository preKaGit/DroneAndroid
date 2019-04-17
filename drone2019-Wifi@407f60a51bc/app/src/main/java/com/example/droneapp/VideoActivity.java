package com.example.droneapp;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.Socket;


public class VideoActivity extends AppCompatActivity {
    CustomAnimations mCustomAnimations = new CustomAnimations();
    WebView mVideoView;
    String url = "http://192.168.12.1:8080/stream";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomAnimations.hideStatusBar(this);

        setContentView(R.layout.activity_video);
        TextView txt = findViewById(R.id.textView2);
        mVideoView = (WebView) findViewById(R.id.videoView);
        mVideoView.loadUrl(url);
        mVideoView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ImageView mWifiImage = findViewById(R.id.videoWifi);
        mCustomAnimations.wifiAnimation(mWifiImage).start();

    }
}
