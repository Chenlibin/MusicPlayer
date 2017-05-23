package com.example.android.task4.fragment.firstFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.task4.R;
import com.example.android.task4.activity.SongListActivity;
import com.example.android.task4.adapter.RecommendFragmentAdapter;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.SingMessage;
import com.example.android.task4.fragment.BaseFragment;
import com.example.android.task4.utils.Http;
import com.example.android.task4.utils.MyJson;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class RecommendFragment extends BaseFragment {

    private GridView reTitle;

    private MyThread myThread = null;
    private Handler myHandler = null;
    private String path = null;
    List<SingMessage> list = null;


    private Context context;
    private FragmentManager fragmentManager;

    public RecommendFragment(){}

    public RecommendFragment(Context context,FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first_recommend,container,false);

        reTitle = (GridView) ret.findViewById(R.id.recommend_title);

        myThread = new MyThread();
        myThread.start();

        //防止出现空指针异常
        while (null == myHandler){
        }
        //发送消息
        myThread.sengMessagetoDoWork();


        return ret;
    }

    @Override
    public void onResume() {

        myThread = new MyThread();
        myThread.start();

        //防止出现空指针异常
        while (null == myHandler){
        }
        //发送消息
        myThread.sengMessagetoDoWork();

        super.onResume();
    }

    @Override
    public String getFragmentTitle() {
        return "个性推荐";
    }




    private class MyThread extends Thread{

        @Override
        public void run() {
            Looper.prepare();

            myHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {

                    switch (msg.what){
                        case 1:
                            path = Config.RADIO_URL;
                            if (path != null) {
                                byte[] arr;
                                try {
//                                    Log.e("adress", path);
                                    arr = Http.getData(path);
//                                    Log.e("HttpAdress",arr+"");
                                    String json = new String(arr,"utf-8");
//                                    Log.e("JsonData",json);
                                    if (json != null) {
                                        list = MyJson.parserData(json);
//                                        Log.e("listData",list+"");
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            mainHandler.sendEmptyMessage(11);

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

        public void sengMessagetoDoWork(){
            //启动任务(消息只有标识，立即投递)
            myHandler.sendEmptyMessage(1);

//            //开始任务(在myHandler的消息列队中获取一个Message对象，避免重复构造)
//            Message msg1 = myHandler.obtainMessage(1);
//            msg1.obj = "This is task1";
//            myHandler.sendMessage(msg1);
//
//            //开启任务二(和上面一样)
//            Message msg2 = Message.obtain();
//            msg2.what = 1;
//            myHandler.sendMessage(msg2);

            //结束任务
            myHandler.sendEmptyMessageDelayed(2,5000);

        }
    }



    //不可以做耗时操作(主线程)
    Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 11:
                    if (list != null) {

                        RecommendFragmentAdapter adapter = new RecommendFragmentAdapter(context,list);
                        reTitle.setAdapter(adapter);


                        reTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                SingMessage songList = list.get(position);

                                Intent intent = new Intent();
                                intent.putExtra("ch_name",songList.getCh_name());
                                intent.putExtra("songlistTitle",songList.getReText());
                                intent.setClass(context,SongListActivity.class);
                                context.startActivity(intent);

                            }
                        });
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };



}
