package com.example.android.task4.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class Config {

    /**
     * 接口路径
     */
    public static final String INTERFACE_URL = "interface_url";
    /**
     * 电台URL    第一步
     */
    public static final String RADIO_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getCategoryList&format=json";
    /**
     * 歌手URL
     */
    public static final String SINGER_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.artist.get72HotArtist&format=jsonℴ=1&offset=0&limit=20";

    /**
     * 某个电台下的歌曲列表   第二步，获得第一步中的"ch_name"
     */

//    public static final String RADIO_SING_LIST = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&rn=50&channelname=";
    public static final String RADIO_SING_LIST = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&rn=50&channelname=public_tuijian_billboard";

    /**
     * 通过歌曲的Id查找歌曲的详情       播放第二步中的songId
     */
    public static final String SING_URL = "http://ting.baidu.com/data/music/links?songIds=";
//    public static final String SING_URL = "http://ting.baidu.com/data/music/links?songIds=5489769";
    /**
     * 当前播放的音乐
     */
    public static SingMessage SONGMESSAGE = null;
    /**
     * 当前播放音乐的信息
     */
    public static Sing SING = null;
    /**
     * 当前的播放歌曲的列表
     */
    public static List<Sing> SONGLIST = null;

    public static final String ACTION_CHANGE_SONG = "action_change_song";
    public static final String ACTION_PREVIOUS_SONG = "action_previous_song";
    public static final String ACTION_NEXT_SONG = "action_next_song";
    public static final String ACTION_STOP_OR_START_SONG = "action_stop_or_start_song";
    public static final String ACTION_PLAY_LOCAL = "action_play_local";
    public static final String ACTION_REFLSH_DOWNLOAD = "action_refash_download";
    //通知播放界面更改图标
    public static final String ACTION_REFLASH = "reflashUI";

    public static final String SONG_ID = "song_id";
    public static final String SONG_URL = "song_url";

    public static final String EXTRA_PLAY_PREVIOUS_SONG = "action_play_previous_song";
    public static final String EXTRA_START_OR_STOP_SONG = "action_stop_or_stop_song";
    public static final String EXTRA_PLAY_NEXT_SONG = "action_play_next_song";

    public static final String MUSIC_URL = "music_url";
    /**
     * 更新进度
     */
    public static final String ACTION_PROGRES = "progerss";
    public static final String EXTRA_PROGRESS_MAX = "progerss_max";
    public static final String EXTRA_PROGRESS_CURRENT = "progerss_current";
    /**
     * 拖动进度条时通知service播放新的位置
     *
     */
    public static final String ACTION_CURRENT_PLAY = "current_paly";
    public static final String EXTRA_PLAY_SETTING = "playsetting";
    /**
     * 最近播放歌曲列表
     */

    public static final String DB_NAME = "singmessage";
    public static final String DB_SING_NAME = "sing";
    public static final String DB_DOWNLOAD_MUSIC = "download_music";

}
