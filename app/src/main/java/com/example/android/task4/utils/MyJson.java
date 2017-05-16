package com.example.android.task4.utils;

import android.util.Log;

import com.example.android.task4.bean.SingMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class MyJson  {


    public static List<SingMessage> parserData(String jsonString) throws JSONException {
        List<SingMessage> list = new ArrayList<SingMessage>();

        JSONObject object = new JSONObject(jsonString);
        SingMessage singMessage = null;
        JSONArray result = object.getJSONArray("result");
        JSONObject result1 = result.getJSONObject(0);

//        Log.e("result1", result1 + "");
        JSONArray channellist = result1.getJSONArray("channellist");

        if (channellist != null) {
            for (int i = 0; i < channellist.length(); i++) {
                JSONObject jsonData = channellist.getJSONObject(i);

                singMessage = new SingMessage();

                String name = jsonData.getString("name");
                String thumb = jsonData.getString("thumb");
                String ch_name = jsonData.getString("ch_name");

                singMessage.setRePhoto(thumb);
                singMessage.setReText(name);
                singMessage.setCh_name(ch_name);
               // singMessage.setRePhoto(jsonData.getString("thumb"));



                list.add(singMessage);
                Log.e("singMessage", singMessage+"");

            }
        }
        return list;
        //Log.e("list", list.toString());
    }

}
