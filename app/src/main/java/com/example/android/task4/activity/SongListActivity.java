package com.example.android.task4.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.task4.R;
import com.example.android.task4.adapter.SongListAdapter;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.Sing;
import com.example.android.task4.utils.Http;
import com.example.android.task4.utils.SongJson;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liber on 2017/5/11.
 *
 * 歌单界面
 *
 */

public class SongListActivity extends Activity implements View.OnClickListener {

    //控件
    private ListView songListView;
    private TextView songText;

    //网址
    private String songListPath;

    private SongListAdapter adapter;

    //线程
    private Handler songListHandler;
    private MyThread myThread = null;

    //数据
    List<Sing> list = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        Intent intent = getIntent();
        String ch_name = intent.getStringExtra("ch_name");
        String songlistTitle = intent.getStringExtra("songlistTitle");

        songListPath = Config.RADIO_SING_LIST + ch_name;

//        Log.e("path",songListPath);

        songListView = (ListView) findViewById(R.id.recommend_songlist_song);
        songText = (TextView) findViewById(R.id.recommend_songlist_title);

        songText.setText(songlistTitle);

        //启动子线程
        myThread = new MyThread();
        myThread.start();

        //防止出现空指针异常
        while (null == songListHandler){}

        //发送消息
        myThread.sendMessage();

    }


    //主线程上，更新数据
    Handler main = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 11:
                    if (list != null) {
                        adapter = new SongListAdapter(SongListActivity.this,list);

                        adapter.setOnItemChangeListener(SongListActivity.this);


                        songListView.setAdapter(adapter);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.songlist_item:
                Config.CURRENT_LIST = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    String idOfSong = list.get(i).getSongid();
                    Config.CURRENT_LIST.add(i,idOfSong);
                }

                Object itemTag = v.getTag(R.id.songlist_item);
                int itemPosition = (int) itemTag;

                Sing itemSing = list.get(itemPosition);

                String songId = itemSing.getSongid();

                Intent intent = new Intent();
                intent.putExtra("songId",songId);
                intent.putExtra("position",itemPosition);
                intent.setClass(SongListActivity.this,PlayShowActivity.class);
                startActivity(intent);

                break;
            case R.id.songlist_likebutton:

                Object buttonTag = v.getTag(R.id.songlist_likebutton);
                int buttonPosition = (int) buttonTag;

                Toast.makeText(this, "添加到我喜欢的歌单" + list.get(buttonPosition).getSongTitle(), Toast.LENGTH_SHORT).show();

                String buttonSongId = list.get(buttonPosition).getSongid();
                String buttonSongTitle = list.get(buttonPosition).getSongTitle();
                String buttonSongArtist = list.get(buttonPosition).getSongArtist();

                ContentValues values = new ContentValues();
                values.put("songId",buttonSongId);
                values.put("songTitle",buttonSongTitle);
                values.put("songArtist",buttonSongArtist);
                MainActivity.db.insert("Like",null,values);
//                Log.e("likebutton",position + "");
                Config.LIKE_LIST.add(list.get(buttonPosition));

                break;
        }

    }


    //此线程用于获得网络数据并进行json解析
    private class MyThread extends Thread{
        @Override
        public void run() {
            Looper.prepare();
            songListHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case 1:
                            if (songListPath != null) {
                                int id = 1;
                                byte[] arr;
                                try {
                                    arr = Http.getData(songListPath);
                                    String json = new String(arr,"utf-8");
                                    if (json != null) {
                                        list = SongJson.parserSong(json,id);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            //返回主线程
                            main.sendEmptyMessage(11);
                            break;
                        case 2:
                            Looper.myLooper().quit();
                            break;
                    }
                    super.handleMessage(msg);
                }
            };
            Looper.loop();
        }
        public void sendMessage(){
            //启动任务（消息只有标识，立即投递）
            songListHandler.sendEmptyMessage(1);
            //结束线程
            songListHandler.sendEmptyMessageDelayed(2,5000);
        }
    }







}
