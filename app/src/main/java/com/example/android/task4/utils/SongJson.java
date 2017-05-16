package com.example.android.task4.utils;

import com.example.android.task4.bean.Sing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class SongJson {

    public static List<Sing> parserSong(String jsonString,int id) throws JSONException {


        List<Sing> list = new ArrayList<Sing>();
        JSONObject object = new JSONObject(jsonString);
        Sing sing = null;

        switch (id){
            case 1:
                JSONObject result = object.getJSONObject("result");
                JSONArray songlist = result.getJSONArray("songlist");

                for (int i = 0; i < songlist.length(); i++) {
                    JSONObject jsonData = songlist.getJSONObject(i);

                    sing = new Sing();

                    String songid = jsonData.getString("songid");
                    String songTitle = jsonData.getString("title");
                    String songArtist = jsonData.getString("artist");

                    sing.setSongid(songid);
                    sing.setSongTitle(songTitle);
                    sing.setSongArtist(songArtist);

                    list.add(sing);
                }
                break;


            case 2:

                break;
        }

        return list;

    }



}


