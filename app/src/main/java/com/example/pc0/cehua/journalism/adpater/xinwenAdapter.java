package com.example.pc0.cehua.journalism.adpater;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc0.cehua.journalism.R;
import com.example.pc0.cehua.journalism.bean.DBInfo;
import com.example.pc0.cehua.journalism.bean.xinweiRes;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/18.
 */
public class xinwenAdapter extends BaseAdapter {
    private ArrayList<DBInfo> list;
    private Context context;
    private RequestQueue mqueuw;
    private boolean Select_Check = false;

    public xinwenAdapter(ArrayList<DBInfo> list, Context context) {
        this.list = list;
        this.context = context;
        mqueuw = Volley.newRequestQueue(context);
    }

    public void Select(Boolean Select_Check) {
        this.Select_Check = Select_Check;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void UpdateApapter(ArrayList<DBInfo> list) {
        if (list != null) {
            this.list = list;
        }
        notifyDataSetChanged();// 刷新适配器 （系统自带的）
    }

    public void addDate(ArrayList<DBInfo> list) {
        if (list != null) {
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class HolderView {
        ImageView xinwen_icon;
        TextView xinwen_tilte, xinwen_content, xinwen_pdate;
        CheckBox xinwen_select;
    }

    HolderView holder;

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            holder = new HolderView();
            view = LayoutInflater.from(context).inflate(R.layout.xinwen_title, null);
            holder.xinwen_icon = (ImageView) view.findViewById(R.id.xinwen_icon);
            holder.xinwen_tilte = (TextView) view.findViewById(R.id.xinwen_tilte);
            holder.xinwen_content = (TextView) view.findViewById(R.id.xinwen_content);
            holder.xinwen_pdate = (TextView) view.findViewById(R.id.xinwen_pdate);
            holder.xinwen_select = (CheckBox) view.findViewById(R.id.xinwen_select);
            view.setTag(holder);
        } else {
            holder = (HolderView) view.getTag();
        }
        holder.xinwen_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setQuanxuan(isChecked);
            }
        });
        if (list.get(position).isQuanxuan() == true) {
            holder.xinwen_select.setChecked(true);
        } else {
            holder.xinwen_select.setChecked(false);
        }
        if (Select_Check == true) {
            holder.xinwen_select.setVisibility(View.VISIBLE);
        } else {
            holder.xinwen_select.setVisibility(View.GONE);
        }
        //Log.e("111111111", list.get(position).getPicUrl() + "");
        HuoquBitmap(list.get(position).getPicUrl(), holder.xinwen_icon);
        holder.xinwen_tilte.setText(list.get(position).getTitle());
        holder.xinwen_content.setText(list.get(position).getDescription());
        holder.xinwen_pdate.setText(list.get(position).getCtime());
        return view;
    }

    public void HuoquBitmap(String src, final ImageView imageView) {
        int MAX = (int) (Runtime.getRuntime().maxMemory() / 1024);//获取当前app的运行内存大小
        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(MAX / 6) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回每一个元素有多大小
                //每一图片的大小 用getByteCount()方法
                return value.getByteCount();
            }
        };
        ImageLoader imageLoader = new ImageLoader(mqueuw, new ImageLoader.ImageCache() {

            @Override
            public Bitmap getBitmap(String s) {
                //从缓存里面拿图片
                return lruCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                //将图片放到缓存里面
                if (lruCache.get(s) != bitmap) {
                    lruCache.put(s, bitmap);
                }
            }
        });
        //2.
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.tabbar_icon_picture_normal, R.drawable.tabbar_icon_picture_normal);
        //3.调用
        imageLoader.get(src, listener);
    }

}
