package com.example.pc0.cehua.journalism.adpater;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/15.
 */
public class GuideAdpater extends PagerAdapter {
    ArrayList<View> list;
    Context context;

    public GuideAdpater(Context context, ArrayList<View> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list.size() == 0) {
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 给viewpager填充View的方法
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    // 把当前View从viewpager中移除的方法
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = list.get(position);
        container.removeView(view);
    }
}
