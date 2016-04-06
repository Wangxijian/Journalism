package com.example.pc0.cehua.journalism.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pc0.cehua.journalism.bean.DBInfo;

import java.util.ArrayList;

/**
 * Created by pc0 on 2016/3/24.
 */
public class DBManager {
/**
 * 该类用于创建数据库 ，创建表，以及初始化数据，数据库更新
 * @param context
 */
    /**
     * 所有可变性的数据的用变量来表达
     */
    /**
     * 对数据库的增删改查 SQLiteDatabase的 getWritableDatabase方法
     */
    private SQLiteDatabase db;
    private static DBManager dbManager;

    // 使用单例模式 为了让DBManager new一次
    private DBManager(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        // 去访问数据库，如果数据库不存在在调用
        db = dbHelper.getWritableDatabase();
    }

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }


    // ****************不用SQL语句的添加数据******************//
    public void add_Date(DBInfo info) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.TITLE, info.getTitle());
        values.put(DBHelper.DECVRIPTION, info.getDescription());
        values.put(DBHelper.CTIme, info.getCtime());
        values.put(DBHelper.PICURL, info.getPicUrl());
        values.put(DBHelper.URL, info.getUrl());
        db.insert(DBHelper.TABLE_NAME, null, values);
    }

    // ****************不用SQL语句的多个数据的添加******************//
    public void add_Dates(DBInfo... info) {
        ContentValues values = new ContentValues();
        for (int i = 0; i < info.length; i++) {
            add_Date(info[i]);
        }
    }

    // ******************查看（有游标）***************************//
    public ArrayList<DBInfo> look_AllBysql() {
        String sql = "select * from " + DBHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);// 返回值是一个游标
        ArrayList<DBInfo> list = new ArrayList<DBInfo>();
        list.clear();// 清空一下集合
        while (cursor.moveToNext()) {
            //int id, String title, String description, String ctime, String picUrl, String url
            list.add(new DBInfo(cursor.getInt(cursor.getColumnIndex(DBHelper.INFO_ID)), cursor.getString(cursor
                    .getColumnIndex(DBHelper.TITLE)), cursor.getString(cursor
                    .getColumnIndex(DBHelper.DECVRIPTION)), cursor.getString(cursor
                    .getColumnIndex(DBHelper.CTIme)), cursor.getString(cursor
                    .getColumnIndex(DBHelper.PICURL)), cursor.getString(cursor
                    .getColumnIndex(DBHelper.URL))));
        }
        cursor.close(); // 关闭游标
        System.gc();// 收收垃圾
        return list;
        // cursor.close();
        // cursor.moveToLast(); // 移动到最后行
        // cursor.moveToFirst();// 移动到第一行
        // cursor.moveToNext();// 移动到下一行
        // cursor.moveToPosition(position);// 移动到某一行
    }


    // ***************************删除数据(SQL版)*************************//
    public void delete_Bysql(int position) {
        db.execSQL("Delete From " + DBHelper.TABLE_NAME + " Where "
                + DBHelper.INFO_ID + "='" + position + "'");
    }

    // ***************************删除数据（android版）*************************//
    public void delete_sql(String title) {
        db.delete(DBHelper.TABLE_NAME, " WHERE " + DBHelper.TITLE.equals(title), null);
    }

    public void GuanBi() {
        db.close();
    }

}
