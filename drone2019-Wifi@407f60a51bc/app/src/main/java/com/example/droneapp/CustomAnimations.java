package com.example.droneapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class CustomAnimations extends Animation {
    private AlphaAnimation mFullscreenClick = new AlphaAnimation(1F, 0.8F);

    public void clickAnimation(ImageView mImageView){
        mImageView.startAnimation(mFullscreenClick);
    }

    public AnimationDrawable wifiAnimation(ImageView mImageview){
        AnimationDrawable wifiAmin;
        mImageview.setBackgroundResource(R.drawable.wifi_anim);
        wifiAmin = (AnimationDrawable) mImageview.getBackground();
        return wifiAmin;
    }

    public void logoAnimation(ImageView mImageView, Context mContext){
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(mContext, R.anim.fade_logo);
        mImageView.startAnimation(myFadeInAnimation);

    }

    public void hideStatusBar(Activity mActivity){
        mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void onBackPressed( Context mContext, String errorMessage, String mTitle, String mPositiveBtn,final Activity mActivity)
    {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(mContext);
        builder.setMessage(errorMessage);
        builder.setTitle(mTitle);
        builder.setCancelable(false);
        builder
                .setPositiveButton(
                        mPositiveBtn,
                        new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                mActivity.finish();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}





