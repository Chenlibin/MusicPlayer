package com.example.android.task4.utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.android.task4.bean.Config;

/**
 * Created by Liber on 2017/5/24.
 */

public class MySQLite extends SQLiteOpenHelper {

//    public static final String CREATE_LIKE = "create table Like(" + "id integer primary key autoincrement," + " songId String," + "songTitle String," + "songArtist String)";
    public static final String CREATE_LIKE = "create table "+ Config.DB_NAME +"(" + "id integer primary key autoincrement," + " songId String," + "songTitle String," + "songArtist String)";

    public MySQLite(Context context) {
        //第二个参数:创建一个文件名字是music 第四个是版本号。。用于更新
        super(context, "music.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIKE);
        Log.e("SQLite","is create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //版本更新
        if (newVersion > oldVersion){
        }

    }
}
