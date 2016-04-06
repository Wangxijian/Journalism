package com.example.pc0.cehua.journalism.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.bean.MyInfo;
import com.example.pc0.cehua.journalism.service.UpService;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by pc0 on 2016/3/30.
 */
public class SetupActivity extends BsaeActivity implements View.OnClickListener {
    private Button setup_edition;
    private RequestQueue queue;
    private Gson gson;
    private MyInfo info;
    private boolean threshold = true;
    private String address = "http://192.168.1.100:8080/MyWeb/appvesion.html";

    @Override
    public void setCountlayout() {
        setContentView(R.layout.activity_setup);

        gson = new Gson();
        queue = Volley.newRequestQueue(SetupActivity.this);
        HuoquDate();
    }

    @Override
    public void initView() {
        info = new MyInfo();
        setup_edition = (Button) findViewById(R.id.setup_edition);

    }

    @Override
    protected void AfterViewLogic() {
        setup_edition.setOnClickListener(this);
    }

    public void Dialog(final boolean boo) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SetupActivity.this);
        dialog.setTitle("版本更新");
        if (boo) {
            dialog.setMessage(info.getAppinfo().getUpdateInfo());
            dialog.setNegativeButton("取消", null);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(SetupActivity.this, UpService.class);
                    intent.putExtra("url", info.getAppinfo().getApkadress());
                    startService(intent);
                    threshold = false;

                }
            });
        } else {
            dialog.setMessage("当前以是最新版本，不需要更新");
            dialog.setNegativeButton("取消", null);
        }
        dialog.show();
    }


    public void HuoquDate() {
        StringRequest request = new StringRequest(address, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                info = gson.fromJson(s, MyInfo.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            //作用：将Json转化为String格式
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = null;
                try {
                    str = new String(response.data, "gbk");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(request);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setup_edition:
                try {
                    int banben = this.getPackageManager().getPackageInfo(this.getPackageName(), 1).versionCode;
                    //showToast(banben + "");
                    //int dangqian = Integer.parseInt(packageInfo.versionName);
                    int zuixin = Integer.parseInt(info.getAppinfo().getVersionCode());
                    showToast(banben + "   " + zuixin);
                    if (banben < zuixin) {
                        Dialog(true);
                    } else {
                        Dialog(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
