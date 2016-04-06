package com.example.pc0.cehua.journalism.activity;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.adpater.GuideAdpater;
import com.example.pc0.cehua.journalism.util.AnimationUtil;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/15.
 */
public class LogoActivity extends BsaeActivity {
    ViewPager logo_pager;
    ArrayList<View> list_view;
    GuideAdpater adapter;
    AnimationUtil animationUtil = new AnimationUtil();
    ImageView yindao_image;

    @Override
    public void setCountlayout() {
        String yindao = GetDate();
        if (yindao.equals("null0")) {
            setContentView(R.layout.activity_yindao);
            saveDate();
            init();
        } else {
            setContentView(R.layout.activity_yindao);
            yindao_image = (ImageView) findViewById(R.id.yindao_image);
            AlphaAnimation fadeout = animationUtil.Fadeout(0, 1, 1500, 0, false);
            yindao_image.startAnimation(fadeout);
            fadeout.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    setDianji(MainActivity.class, false);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    public void init() {
        logo_pager = (ViewPager) findViewById(R.id.logo_pager);
        loadshuju();
        adapter = new GuideAdpater(LogoActivity.this, list_view);
        logo_pager.setAdapter(adapter);
    }

    @Override
    public void initView() {

    }

    @Override
    protected void AfterViewLogic() {

    }

    // SharedPreferences(系统提供的数据共享接口)
    // 存数据
    public void saveDate() {
        SharedPreferences sPreferences = this.getSharedPreferences("yindao",
                0);
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString("yindao", "第一次进入");
        editor.putInt("age", 22);
        editor.commit();
    }

    // 取数据
    public String GetDate() {
        try {
            SharedPreferences sPreferences = this.getSharedPreferences("yindao",
                    0);
            String yindao = sPreferences.getString("yindao", null);
            int age = sPreferences.getInt("age", 0);

            return yindao + age;
        } catch (Exception e) {
        }
        return "0";
    }

    //数据加载
    int[] tupian = {R.drawable.small, R.drawable.welcome, R.drawable.wy};

    public void loadshuju() {
        list_view = new ArrayList<View>();
        for (int i = 0; i < tupian.length; i++) {
            ImageView image = new ImageView(LogoActivity.this);
            image.setBackgroundResource(tupian[i]);
            list_view.add(image);
        }
        View view = LayoutInflater.from(LogoActivity.this).inflate(
                R.layout.activity_logo_yindao, null);
        Button button = (Button) view.findViewById(R.id.logo_kasihi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDianji(MainActivity.class, false);
            }
        });
        list_view.add(view);
    }
}
