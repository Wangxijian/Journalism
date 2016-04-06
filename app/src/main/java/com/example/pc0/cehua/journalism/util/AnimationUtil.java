package com.example.pc0.cehua.journalism.util;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by pc0 on 2016/3/16.
 */
public class AnimationUtil {
    /**
     * 平移的动画 (4个参数)
     */
    // 参数1,2：X从什么坐标 X到什么坐标（以控件的左上角为坐标原点）
    // 参数3,4：Y从什么坐标 Y到什么坐标（以控件的左上角为坐标原点）
    // 参数5：时间 参数：6次数 参数7：是否来回播放
    public TranslateAnimation Transla(int fX, int tX, int fY, int tY,
                                      int shijian, int cishu, boolean boo) {
        TranslateAnimation translat = new TranslateAnimation(fX, tX, fY, tY);
        // 同时播放多个动画的写法
        SetUp(translat, shijian, cishu, boo);
        return translat;
    }

    /**
     * 平移的动画 (8个参数)
     */
    // 参数1,2：X从什么坐标 X到什么坐标（以控件的左上角为坐标原点）
    // 参数3,4：Y从什么坐标 Y到什么坐标（以控件的左上角为坐标原点）
    // 参数5：时间 参数：6次数 参数7：是否来回播放
    // 参数8：子自己或者父布局为参照点（1为自己 2为父布局）
    // 参数9： 平移的初始的X坐标 参数10：平移的初始的Y坐标
    public TranslateAnimation Transla(int fX, int tX, int fY, int tY,
                                      int shijian, int cishu, boolean boo, int canzhuaodian, float X,
                                      float Y) {
        TranslateAnimation translat;
        if (canzhuaodian == 2) {
            translat = new TranslateAnimation(fX, tX, fY, tY,
                    Animation.RELATIVE_TO_PARENT, X,
                    Animation.RELATIVE_TO_PARENT, Y);
        } else {
            translat = new TranslateAnimation(fX, tX, fY, tY,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
        }
        SetUp(translat, shijian, cishu, boo);
        return translat;
    }

    /**
     * 旋转的方法（2个参数）
     */
    // 参数1：从什么角度 参数2：转到什么角度 参数3：时间 参数：4 次数 参数5：是否来回播放
    public RotateAnimation Rotate(int fA, int tA, int shijian, int cishu,
                                  boolean boo) {
        RotateAnimation rotate = new RotateAnimation(fA, tA);
        SetUp(rotate, shijian, cishu, boo);
        return rotate;
    }

    /**
     * 旋转的方法（5个参数）
     */
    // 参数1：从什么角度 参数2：转到什么角度 参数3：动画的时间(毫秒值) 参数4：动画的次数
    // 参数5：是否来回播放（boolean值true代表是）参数6：子自己或者父布局为参照点（1为自己 2为父布局）
    // 参数7：旋转中心的X坐标 参数8：旋转中心的Y坐标
    public RotateAnimation Rotate(int fA, int tA, int shijian, int cishu,
                                  boolean boo, int canzhuaodian, float X, float Y) {
        // 参数1：从什么角度 参数2：转到什么角度
        // 参数3：Animation.RELATIVE_TO_SELF 根据自己的中心来做旋转
        // RELATIVE_TO_PARENT根据父布局旋转（X坐标）
        // 参数4：0.5f中心
        // 参数5：Animation.RELATIVE_TO_SELF 根据自己的中心来做旋转
        // RELATIVE_TO_PARENT根据父布局旋转（Y坐标）
        // 参数6：0.5f中心
        RotateAnimation rotate;
        if (canzhuaodian == 2) {
            rotate = new RotateAnimation(fA, tA, Animation.RELATIVE_TO_SELF, X,
                    Animation.RELATIVE_TO_PARENT, Y);
        } else {
            rotate = new RotateAnimation(fA, tA, Animation.RELATIVE_TO_SELF, X,
                    Animation.RELATIVE_TO_SELF, Y);
        }
        SetUp(rotate, shijian, cishu, boo);
        return rotate;
    }

    /**
     * 渐隐的方法（2个参数）
     */
    // 参数1：从多少的透明度 参数2：到多少的透明度 参数3：动画的时间(毫秒值) 参数4：动画的次数
    // 参数5：是否来回播放（boolean值true代表是）
    public AlphaAnimation Fadeout(int fA, int tA, int shijian, int cishu,
                                  boolean boo) {
        // 参数1：参数1：从什么的透明度 参数2：到什么的透明度
        AlphaAnimation alpha = new AlphaAnimation(fA, tA);
        SetUp(alpha, shijian, cishu, boo);
        return alpha;
    }

    /**
     * 缩放的方法（4个参数）
     */
    // 参数1,2：开始的X坐标 结束的X坐标
    // 参数3,4：开始的Y坐标 结束的Y坐标
    // 参数5：动画的时间(毫秒值) 参数6：动画的次数
    // 参数7：是否来回播放（boolean值true代表是）
    public ScaleAnimation Zoom(int fX, int tX, int fY, int tY, int shijian,
                               int cishu, boolean boo) {
        // 参数1,2：X从什么大小缩小到什么大小
        // 参数3,4：Y从什么大小缩小到什么大小
        ScaleAnimation scale = new ScaleAnimation(fX, tX, fY, tY);
        SetUp(scale, shijian, cishu, boo);
        return scale;
    }

    /**
     * 缩放的方法（6个参数）
     */
    // 参数1,2：开始的X坐标 结束的X坐标
    // 参数3,4：开始的Y坐标 结束的Y坐标
    // 参数5：缩放中心的X坐标 参数6：缩放中心的Y坐标
    // 参数7：动画的时间(毫秒值) 参数8：动画的次数
    // 参数9：是否来回播放（boolean值true代表是）
    public ScaleAnimation Zoom(int fX, int tX, int fY, int tY, int X, int Y,
                               int shijian, int cishu, boolean boo) {
        // 参数1,2：X从什么大小缩小到什么大小
        // 参数3,4：Y从什么大小缩小到什么大小
        ScaleAnimation scale = new ScaleAnimation(fX, tX, fY, tY, X, Y);
        SetUp(scale, shijian, cishu, boo);
        return scale;
    }

    /**
     * 缩放的方法（8个参数）
     */
    // 参数1,2：开始的X坐标 结束的X坐标
    // 参数3,4：开始的Y坐标 结束的Y坐标
    // 参数5：动画的时间(毫秒值) 参数6：动画的次数
    // 参数7：是否来回播放（boolean值true代表是）
    // 参数8：子自己或者父布局为参照点（1为自己 2为父布局）
    // 参数9,10：开始缩放的X，Y坐标
    public ScaleAnimation Zoom(int fX, int tX, int fY, int tY, int shijian,
                               int cishu, boolean boo, int canzhuaodian, float X, float Y) {
        // 参数1,2：X从什么大小缩小到什么大小
        // 参数3,4：Y从什么大小缩小到什么大小
        ScaleAnimation scale;
        if (canzhuaodian == 2) {
            scale = new ScaleAnimation(fX, tX, fY, tY,
                    AlphaAnimation.RELATIVE_TO_PARENT, X,
                    AlphaAnimation.RELATIVE_TO_PARENT, Y);
        } else {
            scale = new ScaleAnimation(fX, tX, fY, tY,
                    AlphaAnimation.RELATIVE_TO_SELF, X,
                    AlphaAnimation.RELATIVE_TO_SELF, Y);
        }

        SetUp(scale, shijian, cishu, boo);
        return scale;
    }

    /**
     * 给animation设置 时间 次数 是否来回移动的
     */
    // 参数1：那种类型的动画 参数2：动画的时间 参数3：动画的次数 参数4：是否来回播放
    private void SetUp(Animation rotate, int shijian, int cishu, boolean boo) {
        rotate.setDuration(shijian);// 时间
        rotate.setRepeatCount(cishu);// 次数
        if (boo) {
            rotate.setRepeatMode(1); // 是否来回转
            rotate.setRepeatMode(2); // 是否来回转
            rotate.setRepeatMode(Animation.REVERSE); // 是否来回转
        }
    }

    /**
     * 添加多个动画的方法 最少可以添加一个
     */
    public AnimationSet Set(Animation... anim) {
        AnimationSet set = new AnimationSet(true);
        for (int i = 0; i < anim.length; i++) {
            set.addAnimation(anim[i]);
        }

        return set;
    }
}
