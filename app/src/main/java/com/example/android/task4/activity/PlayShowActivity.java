package com.example.android.task4.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.android.task4.R;
import com.example.android.task4.bean.Config;
import com.example.android.task4.service.MyBindService;

/**
 * Created by Liber on 2017/5/14.
 *
 * 播放界面
 *
 */

public class PlayShowActivity extends Activity {

    private Intent serviceIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_show);

        Intent dataIntent = getIntent();
        String songId = dataIntent.getStringExtra("songId");
        String songTitle = dataIntent.getStringExtra("songTitle");
        String songArtist = dataIntent.getStringExtra("songArtist");
        //播放网址
        String songPath = Config.SING_URL + songId;

        TextView song_artist = (TextView) findViewById(R.id.show_song_artist);
        TextView song_title = (TextView) findViewById(R.id.show_song_title);

        //显示界面的歌手和歌名
        song_artist.setText(songArtist);
        song_title.setText(songTitle);

        //启动服务

        serviceIntent = new Intent(this, MyBindService.class);
        serviceIntent.putExtra("songPath",songPath);
        startService(serviceIntent);
    }

    @Override
    protected void onDestroy() {
        stopService(serviceIntent);
        Log.e("gg","app__h=gg");
        super.onDestroy();
    }
}
