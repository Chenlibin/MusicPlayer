package com.example.android.task4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.task4.R;
import com.example.android.task4.bean.Sing;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class SongListAdapter extends BaseAdapter {

    private Context context;
    private List<Sing> list;

    public SongListAdapter(Context context, List<Sing> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (list != null) {
            count = list.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View ret = null;


        if (convertView != null) {
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.songlist_view,parent,false);
        }

        ViewHolder viewHolder = (ViewHolder) ret.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.songTitleTv = (TextView) ret.findViewById(R.id.songlist_title);
            viewHolder.songArtistTv = (TextView) ret.findViewById(R.id.songlist_artist);
            ret.setTag(viewHolder);
        }

        Sing sing = list.get(position);

        String songTitle = sing.getSongTitle();
        String songArtist = sing.getSongArtist();

//        viewHolder.tv.setText(songTitle + "\t\n\t" + songArtist);

        viewHolder.songTitleTv.setText(songTitle);
        viewHolder.songArtistTv.setText(songArtist);


        return ret;
    }

    private static class ViewHolder{
        public TextView songTitleTv,songArtistTv;
    }

}
