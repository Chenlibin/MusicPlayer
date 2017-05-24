package com.example.android.task4.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.task4.R;
import com.example.android.task4.activity.PlayShowActivity;
import com.example.android.task4.adapter.MyFragmentPagerAdapter;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.PlayBase;
import com.example.android.task4.fragment.firstFragment.FMFragment;
import com.example.android.task4.fragment.firstFragment.RankingListFragment;
import com.example.android.task4.fragment.firstFragment.RecommendFragment;
import com.example.android.task4.fragment.firstFragment.SongListFragment;
import com.example.android.task4.utils.Http;
import com.example.android.task4.utils.SongJson;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class FirstFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout firstTablayout;
    private ViewPager firstViewPager;
    private MyFragmentPagerAdapter adapter;

    //播放界面
    private ImageButton nextButton;
    private ImageButton playButton;
    private TextView text;
    private SeekBar musicProgress;      //可滑动的Progress
    private LinearLayout musicLayout;

    public LocalBroadcastManager localBroadcastManager;

    //切换歌单
    private MyThread myThread = null;
    private Handler songListHandler = null;
    private String[] aaa;

    //当前播放状态
    private boolean isPlaying = false;

    private Context context;

    public FirstFragment(){}

    public FirstFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first,container,false);

        firstTablayout = (TabLayout) ret.findViewById(R.id.first_tab_bar);
        firstViewPager = (ViewPager) ret.findViewById(R.id.first_view_pager);

        List<Fragment> firstList = new ArrayList<Fragment>();

        firstList.add(new RecommendFragment(context,getFragmentManager()));
        firstList.add(new SongListFragment());
        firstList.add(new FMFragment());
        firstList.add(new RankingListFragment());

        adapter = new MyFragmentPagerAdapter(getFragmentManager(),firstList);

        firstViewPager.setAdapter(adapter);
        firstTablayout.setupWithViewPager(firstViewPager);


        //播放界面
        nextButton = (ImageButton) ret.findViewById(R.id.music_next);
        playButton = (ImageButton) ret.findViewById(R.id.music_play);
        text = (TextView) ret.findViewById(R.id.music_text);
        musicProgress = (SeekBar) ret.findViewById(R.id.music_progress);
        musicLayout = (LinearLayout) ret.findViewById(R.id.music_layout);

        //按钮的监听
        nextButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        musicLayout.setOnClickListener(this);

        //启动线程
        myThread = new MyThread();
        myThread.start();

        //防止出现空指针异常
        while (null == songListHandler){
        }
        //发送消息
        myThread.sendMessage();



        return ret;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public String getFragmentTitle() {
        return "首页";
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            Looper.prepare();

            songListHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {

                    switch (msg.what){
                        case 1:
                            String listPath = Config.RADIO_SING_LIST;
                            if (listPath != null) {
                                int id = 1;
                                byte[] arr;
                                try {
                                    arr = Http.getData(listPath);
                                    String json = new String(arr,"utf-8");
                                    if (json != null) {
                                        Config.SONGLIST = SongJson.parserSong(json,id);
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

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
            //启动任务(消息只有标识，立即投递)
            songListHandler.sendEmptyMessage(1);
            //结束线程
            songListHandler.sendEmptyMessageDelayed(2,2000);
        }
    }

    //按钮的监听
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.music_play:

                if (!isPlaying){
                    isPlaying = !isPlaying;
                    playButton.setImageResource(R.drawable.play_fm_btn_pause);
                }else {
                    isPlaying = !isPlaying;
                    playButton.setImageResource(R.drawable.play_fm_btn_play);
                }
                Intent intent = new Intent(Config.ACTION_STOP_OR_START_SONG);
                localBroadcastManager.sendBroadcast(intent);

                break;
            case R.id.music_next:

                break;
            case R.id.music_layout:
                Intent playShowIntent = new Intent();
                playShowIntent.setClass(getActivity(), PlayShowActivity.class);
                startActivity(playShowIntent);

                break;
        }
    }
}
