package com.example.android.task4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.task4.R;
import com.example.android.task4.activity.PlayShowActivity;
import com.example.android.task4.adapter.MyMusicFragmentAdapter;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.Sing;
import com.example.android.task4.utils.SQLBaseTool;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MyMusicFragment extends BaseFragment implements View.OnClickListener {

    private TextView textView;
    private ListView likeListView;

    private MyMusicFragmentAdapter adapter;

    private SQLBaseTool sqlBaseTool;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mymusic,container,false);

        likeListView = (ListView) ret.findViewById(R.id.mymusic_like_list);
        textView = (TextView) ret.findViewById(R.id.mymusic_textView);

        sqlBaseTool = new SQLBaseTool(getActivity());

        sqlBaseTool.getSongs();

        if (Config.LIKE_LIST != null) {
            adapter = new MyMusicFragmentAdapter(getActivity(),Config.LIKE_LIST);

            adapter.setOnItemChangeListener(this);

            likeListView.setAdapter(adapter);
        }

        return ret;
    }


    @Override
    public String getFragmentTitle() {
        return "音乐";
    }

    //adapter的item的监听
    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            //adapter里面每一个item的监听
            case R.id.mymusic_item:
                if (Config.LIKE_LIST != null) {
                    Config.CURRENT_LIST = new ArrayList();
                    for (int i = 0; i < Config.LIKE_LIST.size(); i++) {
                        String idOfSong = Config.LIKE_LIST.get(i).getSongid();
                        Config.CURRENT_LIST.add(i,idOfSong);
                    }
                    //得到item标签的position
                    Object tag = v.getTag(R.id.mymusic_item);
                    int position = (int) tag;

                    Sing sing = Config.LIKE_LIST.get(position);

                    String songId = sing.getSongid();

                    //跳转到播放界面
                    Intent intent = new Intent();
                    intent.putExtra("songId",songId);
                    intent.putExtra("position",position);
                    intent.setClass(getActivity(),PlayShowActivity.class);
                    getActivity().startActivity(intent);

                }
                break;
            case R.id.mymusic_decreasebutton:

                if (Config.LIKE_LIST != null) {
                    Object tag1 = v.getTag(R.id.mymusic_decreasebutton);
                    int position1 = (int) tag1;

                    Sing sing1 = Config.LIKE_LIST.get(position1);
                    String songId_decrease = sing1.getSongid();
                    String songTitle_decrease = sing1.getSongTitle();

                    //删除数据库中的数据
                   sqlBaseTool.delete(songId_decrease);
//                    MainActivity.db.delete("Like","songId = ?",new String[]{songId_decrease});
                    Toast.makeText(getActivity(),"已经删除" + songTitle_decrease,Toast.LENGTH_SHORT).show();
                    //从List中删除  使显示的时候可以马上看到效果
                    Config.LIKE_LIST.remove(position1);
                    // 强制刷新 Adapter，就会自动更新 数量 TextView
                    adapter.notifyDataSetChanged();
                }

                break;
            default:
                break;

        }


    }
}
