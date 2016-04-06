package com.example.pc0.cehua.journalism.bean;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/18.
 */
public class xinwenInfo {
    private int code;
    private String msg;
    private ArrayList<xinweiRes> newslist; //

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<xinweiRes> getNewslist() {
        return newslist;
    }

    public void setNewslist(ArrayList<xinweiRes> newslist) {
        this.newslist = newslist;
    }
}
