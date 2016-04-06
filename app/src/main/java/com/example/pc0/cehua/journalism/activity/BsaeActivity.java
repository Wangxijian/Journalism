package com.example.pc0.cehua.journalism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public abstract class BsaeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCountlayout();
        initView();
        AfterViewLogic();
    }

    //初始化布局
    public abstract void setCountlayout();

    //初始化控件
    public abstract void initView();

    //初始化控件之后的操作
    protected abstract void AfterViewLogic();

    // 点击后跳转事件的方法 参数1：那个控件 参数2：跳到那个activity 参数3：是否关闭前一个activity
    public void setDianji(View view, final Class<?> context, final boolean boo) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getApplicationContext()获取当前上下文对象
                Intent intent = new Intent(getApplicationContext(), context);
                startActivity(intent);
                if (!boo) {
                    finish();
                }
            }
        });
    }

    // 点击后跳转事件的方法 参数1：那个控件 参数2：跳到那个activity 参数3：是否关闭前一个activity
    public void setDianji(final Class<?> context, final boolean boo) {
        // getApplicationContext()获取当前上下文对象
        Intent intent = new Intent(this, context);
        startActivity(intent);
        if (!boo) {
            finish();
        }
    }

    // 监听 back键
    boolean aa = true;
    int cishu = 0;

    //弹吐司的方法 参数：String类型
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    //弹吐司的方法 参数：int类型
    public void showToast(int shu) {
        Toast.makeText(this, shu + "", Toast.LENGTH_SHORT).show();
    }


    //转码的方法

}
