package com.example.pc0.cehua.journalism.activity;

import android.content.Intent;
import android.util.Log;

import com.example.pc0.cehua.journalism.R;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by pc0 on 2016/3/31.
 */
public class SigninActivity extends BsaeActivity {
    @Override
    public void setCountlayout() {
        setContentView(R.layout.activity_signin);
        ShareSDK.initSDK(this);
        login(QQ.NAME);


    }

    @Override
    public void initView() {
        Platform plat_qq = ShareSDK.getPlatform(SigninActivity.this,
                QQ.NAME);
        plat_qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                // platform.getId();
                //Log.e("smg", i + "   11111111111");
                Intent intent = new Intent();
                intent.putExtra("Name", platform.getDb().getUserName());
                intent.putExtra("Icon", platform.getDb().getUserIcon());
                setResult(1, intent);
                finish();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("smg", platform.getName() + "222222");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("smg", "333333");
            }
        });
//
    }

    @Override
    protected void AfterViewLogic() {

    }

    private void login(String s) {
        Platform qq = ShareSDK.getPlatform(this, s);
        qq.SSOSetting(false);
        qq.authorize();
        qq.showUser(null);
        // qq.getDb().getUserId().toString();
    }
}
