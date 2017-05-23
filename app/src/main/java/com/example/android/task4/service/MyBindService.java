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

    //App内广播
    private LocalBroadcastManager localBroadcastManager;
    private PlayReceiver playReceiver;

    //播放地址
    private String showLink;

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
//        intentFilter.addAction(Config.ACTION_CHANGE_SONG);
//        intentFilter.addAction(Config.ACTION_PLAY_LOCAL);
        intentFilter.addAction(Config.ACTION_FIRST_PLAY);
        intentFilter.addAction(Config.ACTION_CURRENT_PLAY);

        playReceiver = new PlayReceiver();
        localBroadcastManager.registerReceiver(playReceiver,intentFilter);

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        Log.e("service","已经停止");
        mediaPlayer.stop();
        mediaPlayer.release();
        localBroadcastManager.unregisterReceiver(playReceiver);
        super.onDestroy();
    }

    //播放和暂停
    public void play(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
            new ProgressThread().start();
        }

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

    //进度条的进程
    class ProgressThread extends Thread{
        @Override
        public void run() {
            try {
                while (mediaPlayer != null && mediaPlayer.isPlaying() ){
                    //获取当前播放位置
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    //歌曲总时长，单位毫秒
                    int sumLen = mediaPlayer.getDuration();

                    Intent intent = new Intent(Config.ACTION_PROGRES);
                    intent.putExtra(Config.EXTRA_PROGRESS_MAX,sumLen);
                    intent.putExtra(Config.EXTRA_PROGRESS_CURRENT,currentPosition);
//                    Log.e("broadcast","is send");
                    localBroadcastManager.sendBroadcast(intent);
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
//                String song = intent.getStringExtra("uri");
//                change(song,true);
            } else if (action.equals(Config.ACTION_CURRENT_PLAY)){

                int setting_play = intent.getIntExtra(Config.EXTRA_PLAY_SETTING,0);
                mediaPlayer.seekTo(setting_play);

            } else if (action.equals(Config.ACTION_PLAY_LOCAL)){
//                change(showLink,false);
            } else{
                showLink = intent.getStringExtra("showLink");
                change(showLink,false);
            }
        }
    }
}
