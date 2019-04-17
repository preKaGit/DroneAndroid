package com.example.droneapp;

import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SendReceive {
    private static final String TAG = "WIFIactivity";
    OutputStream mOutputStream;
    BufferedReader mInputStream;
    Socket mSocket;
    BufferedReader in;
    Context mContext;
    DataOutputStream mDataOutPutStream;

    SendReceive(Socket socket, Context context){
        mSocket = socket;
        mContext = context;
        try {
            this.mOutputStream = socket.getOutputStream();
            this.mInputStream = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            this.mDataOutPutStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String sendData(String mData){
        try {
            mOutputStream.write(mData.getBytes("utf-8"));
            mOutputStream.flush();
            return mInputStream.readLine();
        } catch (IOException e) {
            Log.e("WIFI", e.getMessage());
            System.exit(0);
            return null;
        }
    }


}
