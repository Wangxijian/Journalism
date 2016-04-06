package com.example.pc0.cehua.journalism.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.activity.MainActivity;
import com.example.pc0.cehua.journalism.adpater.TabPagerAdapter;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/16.
 */
public class ZuoFragment extends Fragment {
    private View view;
    private TabLayout main_tablayout;
    private ViewPager main_viewpager;
    private TabPagerAdapter adapter;
    private ArrayList<Fragment> list = new ArrayList<>();
    private ArrayList<String> taglist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, null);
        main_tablayout = (TabLayout) view.findViewById(R.id.main_tablayout);
        main_viewpager = (ViewPager) view.findViewById(R.id.main_viewpager);
        //viewpager贮备数据源


        shujujiazai();

        //viewpager-->adapter-->FragmentPageradapter
        //fragment和Activity交互一种方式
        MainActivity activity = (MainActivity) getActivity();
        adapter = new TabPagerAdapter
                (activity.getSupportFragmentManager(),
                        list, taglist, activity.getSupportFragmentManager());

        main_viewpager.setAdapter(adapter);
        //吧tablayout和viewpager关联起来
        main_tablayout
                .setupWithViewPager(main_viewpager);
        return view;
    }


    public void shujujiazai() {
//        社会新闻：http://api.huceo.com/social/?key=APIKEY&num=10
//        国内新闻：http://api.huceo.com/guonei/?key=APIKEY&num=10
//        国际新闻：http://api.huceo.com/world/?key=APIKEY&num=10
//        娱乐花边：http://api.huceo.com/huabian/?key=APIKEY&num=10
//        体育新闻：http://api.huceo.com/tiyu/?key=APIKEY&num=10
//        科技新闻：http://api.huceo.com/keji/?key=APIKEY&num=10
//        健康资讯：http://api.huceo.com/health/?key=APIKEY&num=10
//        苹果新闻：http://api.huceo.com/apple/?key=APIKEY&num=10
        list.add(new XinWenFragment("http://api.huceo.com/social/"));
        list.add(new XinWenFragment("http://api.huceo.com/guonei/"));
        list.add(new XinWenFragment("http://api.huceo.com/world/"));
        //list.add(new XinWenFragment("http://api.huceo.com/apple/"));
        list.add(new XinWenFragment("http://api.huceo.com/huabian/"));
        list.add(new XinWenFragment("http://api.huceo.com/tiyu/"));
        list.add(new XinWenFragment("http://api.huceo.com/keji/"));
        list.add(new XinWenFragment("http://api.huceo.com/health/"));
        //
        taglist.add("社会");
        taglist.add("国内");
        taglist.add("国际");
        //taglist.add("苹果");
        taglist.add("娱乐");
        taglist.add("体育");
        taglist.add("科技");
        taglist.add("健康");

    }
}
