package com.example.pc0.cehua.journalism.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.fragment.ZhongFragment;
import com.example.pc0.cehua.journalism.fragment.ZuoFragment;
import com.example.pc0.cehua.journalism.util.AnimationUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BsaeActivity implements View.OnClickListener {
    private ImageView imageView[] = new ImageView[2], title_you, title_zuo;
    private RelativeLayout[] Relative = new RelativeLayout[2];
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment[] = new Fragment[2];
    private TextView title;
    private LinearLayout main_xia;
    AnimationUtil animationUtil;

    @Override
    public void setCountlayout() {
        setContentView(R.layout.activity_main);
        animationUtil = new AnimationUtil();
    }

    @Override
    public void initView() {
        title = (TextView) findViewById(R.id.title);
        title.setText("新闻首页");
        imageView[0] = (ImageView) findViewById(R.id.main_zuo);
        imageView[1] = (ImageView) findViewById(R.id.main_zhong);
        Relative[0] = (RelativeLayout) findViewById(R.id.mainRelative_zuo);
        Relative[1] = (RelativeLayout) findViewById(R.id.mainRelative_zhong);
        title_you = (ImageView) findViewById(R.id.title_you);
        title_zuo = (ImageView) findViewById(R.id.title_zuo);
        main_xia = (LinearLayout) findViewById(R.id.main_xia);
        //

        for (int j = 0; j < Relative.length; j++) {
            Relative[j].setOnClickListener(this);
        }
        //
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment[0] = new ZuoFragment();
        fragment[1] = new ZhongFragment(new ZhongFragment.Chexkinterface() {
            @Override
            public void Select(boolean Select_Check) {
                if (Select_Check == true) {
                    main_xia.startAnimation(animationUtil.Transla(0, 0, 0, 100, 1000, 0, false));
                    main_xia.setVisibility(View.GONE);
                } else if (Select_Check == false) {
                    main_xia.startAnimation(animationUtil.Transla(0, 0, 100, 0, 1000, 0, false));
                    main_xia.setVisibility(View.VISIBLE);
                }

            }
        });

        for (int i = 0; i < fragment.length; i++) {
            fragmentTransaction.add(R.id.main_fragment, fragment[i]);
        }
        fragmentTransaction.commit();
        XianYin(0);
    }

    public void XianYin(int i) {
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int k = 0; k < fragment.length; k++) {
            if (i == k) {
                fragmentTransaction.show(fragment[k]);
                imageView[k].setBackgroundResource(R.drawable.abc_btn_yes);
            } else {
                fragmentTransaction.hide(fragment[k]);
                imageView[k].setBackgroundResource(R.drawable.abc_btn_no);
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void AfterViewLogic() {
        title_zuo.setVisibility(View.GONE);
        title_you.setVisibility(View.GONE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            String str = data.getStringExtra("Name");
            String icon = data.getStringExtra("Icon");
            showToast(str + icon);
            //Log.e("asdsad", str);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainRelative_zuo:
                title.setText("新闻首页");
                XianYin(0);
                break;
            case R.id.mainRelative_zhong:
                title.setText("我的收藏");
                XianYin(1);
                break;

        }
    }

    //点击两下返回退出键
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 500); // 如果0.5秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }

}
