package com.example.android.task4.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.PlayBase;
import com.example.android.task4.utils.Http;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class MyBindService extends Service {

    //播放
    private MediaPlayer mediaPlayer;
    private PlayReceiver receiver;

    //播放地址
    private String songPath;
    private String showLink;

    //App内广播
    private LocalBroadcastManager localBroadcastManager;
    private PlayReceiver playReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("service", "service已经启动");

        mediaPlayer = new MediaPlayer();

        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction(Config.ACTION_STOP_OR_START_SONG);
        intentFilter.addAction(Config.ACTION_CHANGE_SONG);

        playReceiver = new PlayReceiver();
        localBroadcastManager.registerReceiver(playReceiver,intentFilter);



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        songPath = intent.getStringExtra("songPath");
        Log.e("songPath StartCommand",songPath);

        new PlayThread().start();


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("service","已经停止");
        mediaPlayer.stop();
        mediaPlayer.reset();
        localBroadcastManager.unregisterReceiver(playReceiver);
        super.onDestroy();
    }

    //播放和暂停
    public void play(){

        Uri uri = Uri.parse(showLink);
        try {
            mediaPlayer.setDataSource(this,uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }



//        if (mediaPlayer.isPlaying()){
//            mediaPlayer.pause();
//        }else{
//            mediaPlayer.start();
//        }
//        Uri uri = Uri.parse("http://yinyueshiting.baidu.com/data2/music/d3b63254a58afaf8bad7fef4afd16b5f/259024958/5489769205200128.mp3?xcode=8ea0e5e3a9f17b0ad1823dca873f4874");
//        try {
//            mediaPlayer.setDataSource(this,uri);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    //切换歌曲
    public void change(String uri,boolean isLocal){

        try {
            mediaPlayer.reset();
            if (isLocal){
                mediaPlayer.setDataSource(uri);
            }else {
                mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(uri));
            }
            //prepareAsync()方法在后台开始准备媒体而且马上就返回,当media准备完成，通过设置setOnPreparedListener()的onPrepared()方法就会调用
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    play();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //接受广播
    class PlayReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Config.ACTION_STOP_OR_START_SONG)){
                play();
            } else if (action.equals(Config.ACTION_CHANGE_SONG)){

                String song = intent.getStringExtra("uri");
                change(song,false);

            }
        }
    }


    class PlayThread extends Thread{
        @Override
        public void run() {
            if (songPath != null) {

                byte[] arr ;

                try {
                    arr = Http.getData(songPath);
                    String json = new String(arr,"utf-8");
                    if (json != null) {

                        Gson gson = new Gson();
                        PlayBase playBase = gson.fromJson(json,PlayBase.class);

                        showLink = playBase.getData().getSongList().get(0).getShowLink();
                        Log.e("showLink",showLink);

                        play();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }



}
