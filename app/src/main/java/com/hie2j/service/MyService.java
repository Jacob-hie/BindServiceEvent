package com.hie2j.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {

    private boolean isLive = true;
    private MyBinder myBinder = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        Log.e("MyService","onCreate");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isLive){
                    try{
                        Thread.sleep(2000);
                        Log.e("MyService",""+System.currentTimeMillis());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("MyService","onDestroy");
        isLive = false;
        super.onDestroy();
    }

    class MyBinder extends Binder{

        public String showTime(){
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return format.format(date);
        }

        public String transfomr(String str){
            String s[] = str.split(" ");
            String result = "";
            for (String s1 : s){
                char c[] = s1.toCharArray();
                String s2 = "";
                for (int i = s1.length();i>0;i--){
                    s2 += c[i-1];
                }
                result += s2+" ";
            }
            return result;
        }

    }
}
