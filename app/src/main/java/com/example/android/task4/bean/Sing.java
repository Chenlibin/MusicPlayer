package com.example.android.task4.bean;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class Sing {

    private String songid;
    private String songTitle;
    private String songArtist;
    private long duration;     //歌曲时长
    private long size;         //歌曲大小
    private String songPath;   //歌曲路径

    public Sing() {}

    public Sing(String songid, String songTitle, String songArtist, long duration, long size, String songPath) {
        this.songid = songid;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.duration = duration;
        this.size = size;
        this.songPath = songPath;
    }

    public Sing(String songid, long duration, long size, String songPath) {
        this.songid = songid;
        this.duration = duration;
        this.size = size;
        this.songPath = songPath;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }
}
