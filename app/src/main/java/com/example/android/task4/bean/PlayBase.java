package com.example.android.task4.bean;

import java.util.List;

/**
 * Created by Liber on 2017/5/15.
 */

public class PlayBase {


    /**
     * errorCode : 22000
     * data : {"xcode":"5bc78e655673ba8c493a6445124e6947","songList":[{"queryId":"7184772","songId":7184772,"songName":"I Do","artistId":"172451","artistName":"Colbie Caillat","albumId":7183900,"albumName":"All Of You","songPicSmall":"http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_90","songPicBig":"http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_150","songPicRadio":"http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_300","lrcLink":"http://musicdata.baidu.com/data2/lrc/240705072/240705072.lrc","version":"","copyType":0,"time":173,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/138057799/71847721494817261128.mp3?xcode=5bc78e655673ba8c26bdb4a8ac0cec0f","showLink":"http://yinyueshiting.baidu.com/data2/music/138057799/71847721494817261128.mp3?xcode=5bc78e655673ba8c26bdb4a8ac0cec0f","format":"mp3","rate":128,"size":2775603,"relateStatus":"0","resourceType":"0","source":"web"}]}
     */

    private int errorCode;
    private DataBean data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * xcode : 5bc78e655673ba8c493a6445124e6947
         * songList : [{"queryId":"7184772","songId":7184772,"songName":"I Do","artistId":"172451","artistName":"Colbie Caillat","albumId":7183900,"albumName":"All Of You","songPicSmall":"http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_90","songPicBig":"http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_150","songPicRadio":"http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_300","lrcLink":"http://musicdata.baidu.com/data2/lrc/240705072/240705072.lrc","version":"","copyType":0,"time":173,"linkCode":22000,"songLink":"http://yinyueshiting.baidu.com/data2/music/138057799/71847721494817261128.mp3?xcode=5bc78e655673ba8c26bdb4a8ac0cec0f","showLink":"http://yinyueshiting.baidu.com/data2/music/138057799/71847721494817261128.mp3?xcode=5bc78e655673ba8c26bdb4a8ac0cec0f","format":"mp3","rate":128,"size":2775603,"relateStatus":"0","resourceType":"0","source":"web"}]
         */

        private String xcode;
        private List<SongListBean> songList;

        public String getXcode() {
            return xcode;
        }

        public void setXcode(String xcode) {
            this.xcode = xcode;
        }

        public List<SongListBean> getSongList() {
            return songList;
        }

        public void setSongList(List<SongListBean> songList) {
            this.songList = songList;
        }

        public static class SongListBean {
            /**
             * queryId : 7184772
             * songId : 7184772
             * songName : I Do
             * artistId : 172451
             * artistName : Colbie Caillat
             * albumId : 7183900
             * albumName : All Of You
             * songPicSmall : http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_90
             * songPicBig : http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_150
             * songPicRadio : http://musicdata.baidu.com/data2/pic/89779061/89779061.jpg@s_0,w_300
             * lrcLink : http://musicdata.baidu.com/data2/lrc/240705072/240705072.lrc
             * version :
             * copyType : 0
             * time : 173
             * linkCode : 22000
             * songLink : http://yinyueshiting.baidu.com/data2/music/138057799/71847721494817261128.mp3?xcode=5bc78e655673ba8c26bdb4a8ac0cec0f
             * showLink : http://yinyueshiting.baidu.com/data2/music/138057799/71847721494817261128.mp3?xcode=5bc78e655673ba8c26bdb4a8ac0cec0f
             * format : mp3
             * rate : 128
             * size : 2775603
             * relateStatus : 0
             * resourceType : 0
             * source : web
             */

            private String queryId;
            private int songId;
            private String songName;
            private String artistId;
            private String artistName;
            private int albumId;
            private String albumName;
            private String songPicSmall;
            private String songPicBig;
            private String songPicRadio;
            private String lrcLink;
            private String version;
            private int copyType;
            private int time;
            private int linkCode;
            private String songLink;
            private String showLink;
            private String format;
            private int rate;
            private int size;
            private String relateStatus;
            private String resourceType;
            private String source;

            public String getQueryId() {
                return queryId;
            }

            public void setQueryId(String queryId) {
                this.queryId = queryId;
            }

            public int getSongId() {
                return songId;
            }

            public void setSongId(int songId) {
                this.songId = songId;
            }

            public String getSongName() {
                return songName;
            }

            public void setSongName(String songName) {
                this.songName = songName;
            }

            public String getArtistId() {
                return artistId;
            }

            public void setArtistId(String artistId) {
                this.artistId = artistId;
            }

            public String getArtistName() {
                return artistName;
            }

            public void setArtistName(String artistName) {
                this.artistName = artistName;
            }

            public int getAlbumId() {
                return albumId;
            }

            public void setAlbumId(int albumId) {
                this.albumId = albumId;
            }

            public String getAlbumName() {
                return albumName;
            }

            public void setAlbumName(String albumName) {
                this.albumName = albumName;
            }

            public String getSongPicSmall() {
                return songPicSmall;
            }

            public void setSongPicSmall(String songPicSmall) {
                this.songPicSmall = songPicSmall;
            }

            public String getSongPicBig() {
                return songPicBig;
            }

            public void setSongPicBig(String songPicBig) {
                this.songPicBig = songPicBig;
            }

            public String getSongPicRadio() {
                return songPicRadio;
            }

            public void setSongPicRadio(String songPicRadio) {
                this.songPicRadio = songPicRadio;
            }

            public String getLrcLink() {
                return lrcLink;
            }

            public void setLrcLink(String lrcLink) {
                this.lrcLink = lrcLink;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public int getCopyType() {
                return copyType;
            }

            public void setCopyType(int copyType) {
                this.copyType = copyType;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getLinkCode() {
                return linkCode;
            }

            public void setLinkCode(int linkCode) {
                this.linkCode = linkCode;
            }

            public String getSongLink() {
                return songLink;
            }

            public void setSongLink(String songLink) {
                this.songLink = songLink;
            }

            public String getShowLink() {
                return showLink;
            }

            public void setShowLink(String showLink) {
                this.showLink = showLink;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getRelateStatus() {
                return relateStatus;
            }

            public void setRelateStatus(String relateStatus) {
                this.relateStatus = relateStatus;
            }

            public String getResourceType() {
                return resourceType;
            }

            public void setResourceType(String resourceType) {
                this.resourceType = resourceType;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }
    }
}
