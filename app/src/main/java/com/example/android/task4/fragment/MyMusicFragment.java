package com.example.android.task4.fragment;

import android.content.ContentUris;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.task4.R;
import com.example.android.task4.activity.MainActivity;
import com.example.android.task4.adapter.MyMusicFragmentAdapter;
import com.example.android.task4.bean.Sing;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MyMusicFragment extends BaseFragment {

    private TextView textView;
    private ListView likeList;

    private List<Sing> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mymusic,container,false);

        likeList = (ListView) ret.findViewById(R.id.mymusic_like_list);
        textView = (TextView) ret.findViewById(R.id.mymusic_textView);

        //查询数据库中所有数据
        Cursor cursor = MainActivity.db.query("Like",null,null,null,null,null,null);

        list = new ArrayList<Sing>();

        if (cursor != null) {
            if (cursor.moveToFirst()){
                do {
                    Sing sing = new Sing();
                    String songId = cursor.getString(cursor.getColumnIndex("songId"));
                    String songTitle = cursor.getString(cursor.getColumnIndex("songTitle"));
                    String songArtist = cursor.getString(cursor.getColumnIndex("songArtist"));

                    sing.setSongid(songId);
                    sing.setSongTitle(songTitle);
                    sing.setSongArtist(songArtist);

                    list.add(sing);

                }while (cursor.moveToNext());
            }
            cursor.close();
            MyMusicFragmentAdapter adapter = new MyMusicFragmentAdapter(getActivity(),list);
            likeList.setAdapter(adapter);


        } else {
            textView.setText("还没有歌单，请自行添加");
        }
        return ret;
    }


    @Override
    public String getFragmentTitle() {
        return "音乐";
    }
}
