package com.example.droneapp;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;

public class WiFiCommunicator extends Thread implements Serializable {

    private Context mContext;
    private String pieIP;

    public WiFiCommunicator(String ip, Context context){
        this.mContext = context;
        pieIP = ip;
    }

    public void run()
        {
            try
            {
                Socket socket = new Socket(pieIP, 8080);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                Toast.makeText(mContext, response, Toast.LENGTH_SHORT).show();
                socket.close();
            }
            catch(Exception e) {}
        }



}
