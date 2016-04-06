package com.example.pc0.cehua.journalism.adpater;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc0.cehua.journalism.bean.xinweiRes;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/16.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    ArrayList<String> title;
    FragmentManager fragmentManager;

    public TabPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> title, FragmentManager fragmentManager) {
        super(fm);
        this.list = list;
        this.title = title;
        this.fragmentManager = fragmentManager;
    }

    public void UpdateApapter(ArrayList<Fragment> list, ArrayList<String> title) {
        this.list = list;
        this.title = title;
        notifyDataSetChanged();// 刷新适配器 （系统自带的）
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//
//        //super.destroyItem(container, position, object);
//    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        //((ViewPager) container).removeView(View(object));
        list.remove(position);
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position % list.size());
    }

}
