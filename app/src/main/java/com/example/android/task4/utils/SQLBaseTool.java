package com.example.android.task4.utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.task4.activity.MainActivity;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.Sing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liber on 2017/7/13.
 */

public class SQLBaseTool {

    private MySQLite dbHelper;
    private Context context;
    private SQLiteDatabase read;
    private SQLiteDatabase write;

    public SQLBaseTool(Context context) {
        this.context = context;

        dbHelper = new MySQLite(context);
        read = dbHelper.getReadableDatabase();
        write = dbHelper.getWritableDatabase();
    }

    public long insert(List<Sing> list, int buttonPosition){

        String buttonSongId = list.get(buttonPosition).getSongid();
        String buttonSongTitle = list.get(buttonPosition).getSongTitle();
        String buttonSongArtist = list.get(buttonPosition).getSongArtist();

        ContentValues values = new ContentValues();
        values.put("songId",buttonSongId);
        values.put("songTitle",buttonSongTitle);
        values.put("songArtist",buttonSongArtist);

        Config.LIKE_LIST.add(list.get(buttonPosition));

        return write.insert(Config.DB_NAME,null,values);
    }

    public int delete(String songId){
        return write.delete(Config.DB_NAME,"songId = ?",new String[]{songId});
    }

    public List<Sing> getSongs(){

        Config.LIKE_LIST = new ArrayList<Sing>();

        //查询数据库中所有数据   后面是查询条件  都是null表示全部
        Cursor cursor = read.query("Like",null,null,null,null,null,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Sing sing = new Sing();
                    String songId = cursor.getString(cursor.getColumnIndex("songId"));
                    String songTitle = cursor.getString(cursor.getColumnIndex("songTitle"));
                    String songArtist = cursor.getString(cursor.getColumnIndex("songArtist"));

                    sing.setSongid(songId);
                    sing.setSongTitle(songTitle);
                    sing.setSongArtist(songArtist);

                    Config.LIKE_LIST.add(sing);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        return Config.LIKE_LIST;
    }


}
