package com.example.droneapp;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Network extends Thread implements Serializable{

    private Socket mSocket;
    private SendReceive mSendReceive;
    private String raspIP = "192.168.12.1";
    private int port = 3400;
    private Context mContext;

    public Network(Context context){
        this.mContext = context;
    }

    @Override
    public void run() {

        this.mSocket = new Socket();
        try {
            mSocket.connect(new InetSocketAddress(raspIP, port));
            mSendReceive = new SendReceive(mSocket, this.mContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String send(String mData){
        if (mSendReceive != null)
            return mSendReceive.sendData(mData);
        else{
            return null;
        }
    }



    public void end() {
        try {
            mSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
