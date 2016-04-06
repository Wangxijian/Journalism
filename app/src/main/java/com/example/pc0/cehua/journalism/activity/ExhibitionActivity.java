package com.example.pc0.cehua.journalism.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.bean.DBInfo;
import com.example.pc0.cehua.journalism.db.DBManager;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by pc0 on 2016/3/20.
 */
public class ExhibitionActivity extends BsaeActivity implements View.OnClickListener {
    private WebView exhibition_web;
    private TextView title, exhib_shoucang, exhib_jindu, exhib_fenxiang;
    private DBManager dbManager;
    private ImageView title_zuo, title_you;
    private Boolean bool;
    LinearLayout xinwen_zhanshi;
    RelativeLayout xinwen_jiazai;
    ProgressBar wenjain_nei_bar;
    ArrayList<DBInfo> list = new ArrayList<>();
    private DBInfo shuju;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            exhib_jindu.setText(msg.what + "%");
        }
    };

    @Override
    public void setCountlayout() {
        setContentView(R.layout.activity_exhibition);
        dbManager = DBManager.getInstance(this);
    }

    @Override

    public void initView() {
        exhibition_web = (WebView) findViewById(R.id.exhibition_web);
        title = (TextView) findViewById(R.id.title);
        title_zuo = (ImageView) findViewById(R.id.title_zuo);
        title_you = (ImageView) findViewById(R.id.title_you);
        exhib_shoucang = (TextView) findViewById(R.id.exhib_shoucang);
        //
        xinwen_zhanshi = (LinearLayout) findViewById(R.id.xinwen_zhanshi);
        xinwen_jiazai = (RelativeLayout) findViewById(R.id.xinwen_jiazai);
        exhib_jindu = (TextView) findViewById(R.id.exhib_jindu);
        wenjain_nei_bar = (ProgressBar) findViewById(R.id.wenjain_nei_bar);
        exhib_fenxiang = (TextView) findViewById(R.id.exhib_fenxiang);
        title_you.setVisibility(View.GONE);
        title.setText("咨讯");
        Intent intent = getIntent();
        shuju = (DBInfo) intent.getSerializableExtra("url");
        if (intent.getStringExtra("boo").equals("1")) {
            bool = true;
            exhib_shoucang.setText("收藏");
            AddDate();
        } else {
            bool = false;
            exhib_shoucang.setText("取消收藏");
        }
        title_zuo.setOnClickListener(this);
        exhib_shoucang.setOnClickListener(this);
        exhib_fenxiang.setOnClickListener(this);
    }

    @Override
    protected void AfterViewLogic() {
        exhibition_web.setWebChromeClient(new WebChromeClient() {
            //加载数据的方法
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                wenjain_nei_bar.setProgress(newProgress);
                handler.sendEmptyMessage(newProgress);
            }
        });
        //拦截的方法
        exhibition_web.setWebViewClient(new WebViewClient() {
            //从新加载一个新的页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            //数据加载完成之后的展示
            @Override
            public void onPageFinished(WebView view, String url) {
                xinwen_jiazai.setVisibility(View.GONE);
                xinwen_zhanshi.setVisibility(View.VISIBLE);
            }
        });
        exhibition_web.loadUrl(shuju.getUrl());

    }


//监听BACK键的事件  点击之后返回 最初的网页

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_zuo:
                finish();
                break;
            case R.id.exhib_shoucang:
                if (bool == true) {
                    if (exhib_shoucang.getText().equals("收藏")) {
                        dbManager.add_Date(shuju);
                        showToast("收藏成功！！");
                        exhib_shoucang.setText("取消收藏");
                    } else {
                        exhib_shoucang.setText("收藏");
                        deleteDate();
                    }
                } else if (bool == false) {
                    deleteDate();
                    finish();
                }
                break;
            case R.id.exhib_fenxiang:
                showShare();
                break;
        }
    }

    //收藏的方法
    boolean quxiaosc = true;

    public void AddDate() {
        if (dbManager.look_AllBysql() != null) {
            list.clear();
            list = dbManager.look_AllBysql();
            for (int i = 0; i < list.size(); i++) {
                if (shuju.getTitle().equals(list.get(i).getTitle())) {
                    exhib_shoucang.setText("取消收藏");
                    return;
                }
            }
            exhib_shoucang.setText("收藏");
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(shuju.getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(shuju.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(shuju.getTitle());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(shuju.getTitle());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(shuju.getTitle());
        // site是分享此内容的网站名称，仅在QQ空间使用
        //oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shuju.getUrl());

// 启动分享GUI
        oks.show(this);
    }


    //移除的方法
    public void deleteDate() {
        list.clear();
        list = dbManager.look_AllBysql();
        for (int i = 0; i < list.size(); i++) {
            if (shuju.getTitle().equals(list.get(i).getTitle())) {
                dbManager.delete_Bysql(list.get(i).getId());
                showToast("取消成功");
                break;
            }
        }
    }
}
