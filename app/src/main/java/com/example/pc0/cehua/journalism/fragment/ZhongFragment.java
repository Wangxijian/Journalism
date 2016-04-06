package com.example.pc0.cehua.journalism.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.activity.ExhibitionActivity;
import com.example.pc0.cehua.journalism.adpater.xinwenAdapter;
import com.example.pc0.cehua.journalism.bean.DBInfo;
import com.example.pc0.cehua.journalism.db.DBManager;

import java.util.ArrayList;


/**
 * Created by pc0 on 2016/3/16.
 */
public class ZhongFragment extends Fragment implements View.OnClickListener {
    private View view;
    private DBManager dbManager;
    private xinwenAdapter adapter;
    private ListView zhong_listview;
    private SwipeRefreshLayout refreslayout;
    private LinearLayout zhong_check;
    private TextView zhong_quxiao;
    private CheckBox zhong_quanxuan;
    private boolean threshold = false;
    ArrayList<DBInfo> list = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                adapter.UpdateApapter(list);
                refreslayout.setRefreshing(false);
            }
            if (msg.what == 2) {
                adapter.UpdateApapter(list);
                adapter.Select((Boolean) msg.obj);
            }
        }
    };
    private Chexkinterface chexkinterfac;

    public ZhongFragment(Chexkinterface chexkinterfac) {
        this.chexkinterfac = chexkinterfac;
    }

    public ZhongFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhong, null);
        dbManager = DBManager.getInstance(getActivity());
        Huoqu();
        //初始化控件
        initView();
        //初始化控件之后的操作
        AfterViewLogic();
        return view;
    }

    private void AfterViewLogic() {
        refreslayout.setColorSchemeColors(Color.BLACK, Color.YELLOW);
        //设置发现最顶部下拉的时候执行的监听
        refreslayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //设置圆圈的出现和消失  true 显示  false  隐藏
                refreslayout.setRefreshing(true);
                //在一次加载数据
                Huoqu();
            }
        });
        zhong_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ExhibitionActivity.class);
                intent.putExtra("url", list.get(position));
                intent.putExtra("boo", "2");
                startActivity(intent);
            }
        });
    }

    private void initView() {
        zhong_quanxuan = (CheckBox) view.findViewById(R.id.zhong_quanxuan);
        zhong_quxiao = (TextView) view.findViewById(R.id.zhong_quxiao);
        adapter = new xinwenAdapter(list, getActivity());
        zhong_listview = (ListView) view.findViewById(R.id.zhong_listview);
        refreslayout = (SwipeRefreshLayout) view.findViewById(R.id.refreslayout);
        zhong_check = (LinearLayout) view.findViewById(R.id.zhong_check);
        zhong_listview.setAdapter(adapter);

        zhong_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (threshold == false) {
                    sendMessage(true);
                    zhong_check.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
        zhong_quanxuan.setOnClickListener(this);
        zhong_quxiao.setOnClickListener(this);
    }

    private int index = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhong_quanxuan:
                if (index % 2 == 0) {
                    modifyCheck(true);
                } else {
                    modifyCheck(false);
                }
                index++;
                break;
            case R.id.zhong_quxiao:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isQuanxuan() == true) {
                        dbManager.delete_Bysql(list.get(i).getId());
                        list.remove(i);
                        i--;
                    }
                }
                sendMessage(false);
                zhong_check.setVisibility(View.GONE);
                handler.sendEmptyMessage(1);
                break;
        }
    }

    public void sendMessage(boolean boo) {
        if (boo == false) {
            chexkinterfac.Select(boo);
        }
        Message msg = new Message();
        msg.obj = boo;
        msg.what = 2;
        handler.sendMessage(msg);
        threshold = boo;
    }

    //修改CheckBox的值
    public void modifyCheck(boolean boo) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setQuanxuan(boo);
        }
        handler.sendEmptyMessage(1);
    }

    //从数据库中获取数据
    public void Huoqu() {
        list = dbManager.look_AllBysql();
        handler.sendEmptyMessage(1);
    }

    //就绪的方法
    @Override
    public void onResume() {
        super.onResume();
        Huoqu();
    }

    //内部接口实现下标签的隐藏和显示
    public interface Chexkinterface {
        void Select(boolean Select_Check);
    }
}

