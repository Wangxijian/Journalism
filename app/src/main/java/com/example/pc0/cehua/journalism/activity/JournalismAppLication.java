package com.example.pc0.cehua.journalism.activity;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by pc0 on 2016/3/31.
 */
public class JournalismAppLication extends Application {
    //RequestQueue queue = Volley.newRequestQueue(this);
    @Override
    public void onCreate() {
        super.onCreate();
        //推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
