package com.example.android.task4.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class Http {

    public static byte[] getData(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setDoInput(true);

        conn.connect();
        InputStream in = conn.getInputStream();
        ByteArrayOutputStream bos= new ByteArrayOutputStream();
        byte[] arr = new byte[1024];
        int len = 0;
        while((len = in.read(arr))!=-1)
        {
            bos.write(arr,0,len);
        }

        in.close();
        return bos.toByteArray();
    }


}
