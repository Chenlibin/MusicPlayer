package com.example.android.task4.activity;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.task4.R;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.PlayBase;
import com.example.android.task4.service.MyBindService;
import com.example.android.task4.utils.Http;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static com.example.android.task4.bean.Config.ACTION_STOP_OR_START_SONG;

/**
 * Created by Liber on 2017/5/14.
 *
 * 播放界面
 *
 */

public class PlayShowActivity extends Activity implements View.OnClickListener {

    //网址
    private String songPath;
    private String songId;
    //app内广播
    private LocalBroadcastManager localBroadcastManager;
    //UI控件
    private ImageView showImage;
    private TextView song_title,song_artist,currentTv,maxTv;
    private ImageButton playButton,previousButton,nextButton;
    private SeekBar progressBar;

    //广播
    private ServiceReceiver serviceReceiver;

    //线程
    private PlayThread playThread;

    //当前是否播放
    boolean isPlaying;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_show);

        Intent dataIntent = getIntent();
        songId = dataIntent.getStringExtra("songId");
        //播放网址
        songPath = Config.SING_URL + songId;
//        Log.e("songPath",songPath);

        song_artist = (TextView) findViewById(R.id.show_song_artist);
        song_title = (TextView) findViewById(R.id.show_song_title);
        showImage = (ImageView) findViewById(R.id.show_image);
        playButton = (ImageButton) findViewById(R.id.show_play_stop);
        previousButton = (ImageButton) findViewById(R.id.show_previous);
        nextButton = (ImageButton) findViewById(R.id.show_next);
        progressBar = (SeekBar) findViewById(R.id.show_progress);
        currentTv = (TextView) findViewById(R.id.show_current_progress);
        maxTv = (TextView) findViewById(R.id.show_max_progress);

        //app广播
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //获得网络数据和解析
        playThread = new PlayThread();
        playThread.start();
        //监听按钮
        playButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.ACTION_PROGRES);
        serviceReceiver = new ServiceReceiver();
        localBroadcastManager.registerReceiver(serviceReceiver,intentFilter);


        //进度条的监听
        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                int seekPosition = seekBar.getProgress();
                Intent progressIntent = new Intent(Config.ACTION_CURRENT_PLAY);
                progressIntent.putExtra(Config.EXTRA_PLAY_SETTING,seekPosition);
                localBroadcastManager.sendBroadcast(progressIntent);


            }
        });

    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(serviceReceiver);
        super.onDestroy();
    }

    //主线程上改UI界面
    Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            PlayBase songData = (PlayBase) msg.obj;

            String imagePath = songData.getData().getSongList().get(0).getSongPicRadio();
            Glide
                    .with(PlayShowActivity.this)
                    .load(imagePath)
                    .centerCrop()
                    .into(showImage);

            //显示作者和歌名
            song_title.setText(songData.getData().getSongList().get(0).getSongName());
            song_artist.setText(songData.getData().getSongList().get(0).getArtistName());

            super.handleMessage(msg);
        }
    };

    //监听播放、下一首、上一首的按钮
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.show_play_stop:

                Intent playOrStop = new Intent(Config.ACTION_STOP_OR_START_SONG);
                if (isPlaying){
                    playButton.setImageResource(R.mipmap.show_play);
                    isPlaying = false;
                }else {
                    playButton.setImageResource(R.mipmap.show_stop);
                    isPlaying = true;
                }
                localBroadcastManager.sendBroadcast(playOrStop);
                break;
            case R.id.show_previous:

                break;
            case R.id.show_next:


                break;
        }
    }


    //子线程获得数据
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

                        String showLink = playBase.getData().getSongList().get(0).getShowLink();
//                        Log.e("showLink",showLink);

                        //广播带数据给service
                        Intent startPlay = new Intent(Config.ACTION_FIRST_PLAY);
                        startPlay.putExtra("showLink",showLink);
                        isPlaying = true;
                        localBroadcastManager.sendBroadcast(startPlay);

                        Message mainMsg = mainHandler.obtainMessage();
                        mainMsg.obj = playBase;
                        mainHandler.sendMessage(mainMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    //与service交互的数据
    class ServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Config.ACTION_PROGRES)){

                int max = intent.getIntExtra(Config.EXTRA_PROGRESS_MAX,0);
                int current = intent.getIntExtra(Config.EXTRA_PROGRESS_CURRENT,0);

                String max_time = format.format(max);
                String current_time = format.format(current);

                maxTv.setText(max_time);
                currentTv.setText(current_time);

                progressBar.setMax(max);
                progressBar.setProgress(current);


            }
        }
    }




}
