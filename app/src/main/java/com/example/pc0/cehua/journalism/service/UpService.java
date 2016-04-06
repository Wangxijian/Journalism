package com.example.pc0.cehua.journalism.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lidroid.xutils.bitmap.download.Downloader;

/**
 * Created by pc0 on 2016/3/30.
 */
public class UpService extends Service {
    //安卓的下载类
    DownloadManager manager;
    //广播的接收类
    DownloadBrodcast brodcast;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String url = intent.getStringExtra("url");
            if (url != null) {
                url = Uri.encode(url, ":/\\\"\'[]()!@#$%^&*_-+=|");
                initDownManager(url);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //启动DownManager进行下载
    private void initDownManager(String url) {

        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        // 设置下载地址
        DownloadManager.Request down = new DownloadManager.Request(
                Uri.parse(url));
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        // 下载时，通知栏显示途中
        down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        // 显示下载界面
        down.setVisibleInDownloadsUi(true);
        // 设置下载后文件存放的位置  参数1：当前app 参数2：下载的地址  参数3：app的名字
        down.setDestinationInExternalFilesDir(this,
                Environment.DIRECTORY_DOWNLOADS,
                "QQ.apk");
        Log.e("AndroidRuntime", Environment.DIRECTORY_DOWNLOADS);
        // 将下载请求放入队列
        manager.enqueue(down);
        //实例化自定义广播
        brodcast = new DownloadBrodcast();
        //动态注册一个广播
        registerReceiver(brodcast, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //广播的接收端
    class DownloadBrodcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //接到广播后进行安装
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                //获取下载的文件的id
                Long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                //获取apk的存放地址
                Uri down_uri = manager.getUriForDownloadedFile(downId);
                //自动安装apk
                installAPK(down_uri);
                //服务自己关闭自己
                UpService.this.stopSelf();
            }
        }

        //安装的方法
        private void installAPK(Uri down_uri) {
            //1.
            Intent intent = new Intent("android.intent.action.VIEW");
            //2.设置 Data Type
            intent.setDataAndType(down_uri, "application/vnd.android.package-archive");
            //3.设置Flags
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //4。跳转到安装页面
            startActivity(intent);
            //5.关闭正在运行的程序
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除广播
        unregisterReceiver(brodcast);

    }
}
