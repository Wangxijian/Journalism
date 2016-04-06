package com.example.pc0.cehua.journalism.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc0 on 2016/3/24.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static String dbName = "Journalism.db";// 什么什么点db
    public static int version = 1;// 版本号
    public static final String TABLE_NAME = "journa_info";// 表名
    public static final String INFO_ID = "_id";// 列名id
    public static final String TITLE = "title"; //标题
    public static final String DECVRIPTION = "description";//内容
    public static final String CTIme = "ctime";//来源地址
    public static final String PICURL = "picUrl";//图片
    public static final String URL = "url";//具体时间

    public DBHelper(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" + INFO_ID
                + " integer primary key autoincrement," + TITLE + " text, "
                + DECVRIPTION + " text," + CTIme + " text," + PICURL + " text," + URL + " text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}


