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
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.activity.ExhibitionActivity;
import com.example.pc0.cehua.journalism.activity.MainActivity;
import com.example.pc0.cehua.journalism.adpater.xinwenAdapter;
import com.example.pc0.cehua.journalism.bean.DBInfo;
import com.example.pc0.cehua.journalism.bean.xinweiRes;
import com.example.pc0.cehua.journalism.bean.xinwenInfo;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/18.
 */
public class XinWenFragment extends Fragment {
    View view;
    ListView xinwen_listview;
    private String address;
    private String app_key = "f4d176fd24b8cbc08fe7031a47a61f15";
    private RequestQueue queue;
    private Gson gson;
    private xinwenAdapter adapter;
    private SwipeRefreshLayout refreslayout;
    private RelativeLayout xinwen_relat;
    private Button xinwen_chongxi;
    private ArrayList<xinweiRes> list_Res = new ArrayList<>();
    private ArrayList<DBInfo> list = new ArrayList<>();

    public XinWenFragment(String address) {

        this.address = address;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                adapter.UpdateApapter(list);
                refreslayout.setRefreshing(false);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.xinwen_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        queue = Volley.newRequestQueue(activity);
        gson = new Gson();
        xinwen_listview = (ListView) view.findViewById(R.id.xinwen_listview);
        refreslayout = (SwipeRefreshLayout) view.findViewById(R.id.refreslayout);
        xinwen_relat = (RelativeLayout) view.findViewById(R.id.xinwen_relat);
        xinwen_chongxi = (Button) view.findViewById(R.id.xinwen_chongxi);
        adapter = new xinwenAdapter(list, activity);
        xinwen_listview.setAdapter(adapter);
        huoquData(address, 10);
        //给listView 设置跳转的
        xinwen_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ExhibitionActivity.class);
                intent.putExtra("url", list.get(position));
                intent.putExtra("boo", "1");
                startActivity(intent);
            }
        });
        //设置属性
        //给加载的旋转圈设置颜色(最多四种)
        refreslayout.setColorSchemeColors(Color.BLACK, Color.YELLOW);
        //设置发现最顶部下拉的时候执行的监听
        refreslayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //设置圆圈的出现和消失  true 显示  false  隐藏
                refreslayout.setRefreshing(true);
                //在一次加载数据
                list_Res.clear();
                huoquData(address, 10);
            }
        });
    }

    private void ZhuanHuan(ArrayList<xinweiRes> list_Res) {
        list.clear();
        for (int i = 0; i < list_Res.size(); i++) {
            list.add(new DBInfo(list_Res.get(i).getTitle(),
                    list_Res.get(i).getDescription(), list_Res.get(i).getCtime(),
                    list_Res.get(i).getPicUrl(), list_Res.get(i).getUrl()));
        }
    }

    public void huoquData(final String zhi, final int number) {
        final StringRequest request = new
                StringRequest(address + "?key=" + app_key + "&num=" + number, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //成功时候的回调
                xinwen_relat.setVisibility(View.GONE);
                refreslayout.setVisibility(View.VISIBLE);
                xinwenInfo info = gson.fromJson(s, xinwenInfo.class);
                list_Res.addAll(info.getNewslist());
                ZhuanHuan(list_Res);
                handler.sendEmptyMessage(1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                refreslayout.setRefreshing(false);
                xinwen_relat.setVisibility(View.VISIBLE);
                refreslayout.setVisibility(View.GONE);
                xinwen_chongxi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        huoquData(zhi, 10);
                    }
                });
                //错误时候的回调
            }
        });
        queue.add(request);
    }
}
