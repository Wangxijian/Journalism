package com.example.pc0.cehua.journalism.bean;

import java.io.Serializable;

/**
 * Created by pc0 on 2016/3/24.
 */
public class DBInfo implements Serializable {
    private int id = 0;
    private String title; //标题
    private String description;//描述
    private String ctime; //具体时间
    private String picUrl;//图片地址
    private String url;//来源地址
    private boolean quanxuan = false;//

    public DBInfo(String title, String description, String ctime, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.ctime = ctime;
        this.url = url;
    }

    public DBInfo(int id, String title, String description, String ctime, String picUrl, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ctime = ctime;
        this.picUrl = picUrl;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isQuanxuan() {
        return quanxuan;
    }

    public void setQuanxuan(boolean quanxuan) {
        this.quanxuan = quanxuan;
    }

    @Override
    public String toString() {
        return "DBInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ctime='" + ctime + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                ", quanxuan=" + quanxuan +
                '}';
    }
}
