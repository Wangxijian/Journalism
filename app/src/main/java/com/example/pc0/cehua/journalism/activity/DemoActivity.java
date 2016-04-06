package com.example.pc0.cehua.journalism.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc0.cehua.journalism.R;

/**
 * Created by pc0 on 2016/3/23.
 */
public class DemoActivity extends AppCompatActivity {
    RequestQueue mqueuw;
    ImageView image_view;
    String src = "http://developer.android.com/images/home/aw_dac.png";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.xinwen_title);
        mqueuw = Volley.newRequestQueue(this);
        image_view = (ImageView) findViewById(R.id.xinwen_icon);

        //创建一个缓存区域
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
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(image_view, R.drawable.boutique_allshare_normal, R.drawable.left_back_button_normal);
        //3.调用
        imageLoader.get("http://developer.android.com/images/home/aw_dac.png", listener, 80, 80);

//        ImageRequest imageRequest = new ImageRequest(src, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap bitmap) {
//                image_view.setImageBitmap(bitmap);
//            }
//            //图片的宽高   图片的样式   失败后加载张图片
//        }, 80, 80, Bitmap.Config.RGB_565, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                image_view.setImageResource(R.drawable.ic_launcher);
//            }
//        });
//        mqueuw.add(imageRequest);
    }
}
