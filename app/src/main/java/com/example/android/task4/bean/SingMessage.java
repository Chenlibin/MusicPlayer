package com.example.android.task4.bean;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class SingMessage {

    private String reText;
    private String rePhoto;
    private String ch_name;

    public SingMessage(){}

    public SingMessage(String reText, String rePhoto, String ch_name) {
        this.reText = reText;
        this.rePhoto = rePhoto;
        this.ch_name = ch_name;
    }

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public String getReText() {
        return reText;
    }

    public void setReText(String reText) {
        this.reText = reText;
    }


    public String getRePhoto() {
        return rePhoto;
    }

    public void setRePhoto(String rePhoto) {
        this.rePhoto = rePhoto;
    }
}
