package com.example.pc0.cehua.journalism.bean;

import java.io.Serializable;

/**
 * Created by pc0 on 2016/3/20.
 */
public class xinweiRes implements Serializable {
    private String ctime; //具体时间
    private String title; //标题
    private String description;//描述
    private String picUrl;//图片地址
    private String url;//来源地址

//    "ctime": "2015-07-17",
//            "title": "那个抱走王明涵的，你上微信吗？看完这个你会心软吗？",
//            "description": "中国传统文化",
//            "picUrl": "http://zxpic.gtimg.com/infonew/0/wechat_pics_-667708.jpg/640",
//            "url": "h

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public xinweiRes(String title, String description, String ctime, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.ctime = ctime;
        this.picUrl = picUrl;
        this.url = url;
    }

    @Override
    public String toString() {
        return "xinweiRes{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ctime='" + ctime + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
