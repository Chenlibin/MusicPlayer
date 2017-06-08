package com.example.android.task4.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.task4.R;
import com.example.android.task4.activity.MainActivity;
import com.example.android.task4.activity.PlayShowActivity;
import com.example.android.task4.activity.SongListActivity;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.Sing;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class SongListAdapter extends BaseAdapter {

    private Context context;
    private List<Sing> list;

    //到fragment监听
    private View.OnClickListener onItemChangeListener;

    public void setOnItemChangeListener(View.OnClickListener onItemChangeListener){
        this.onItemChangeListener = onItemChangeListener;
    }

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View ret = null;


        if (convertView != null) {
            ret = convertView;
        }else {
            LayoutInflater inflater = LayoutInflater.from(context);
            ret = inflater.inflate(R.layout.songlist_view,parent,false);
        }

        ViewHolder viewHolder = (ViewHolder) ret.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.songTitleTv = (TextView) ret.findViewById(R.id.songlist_title);
            viewHolder.songArtistTv = (TextView) ret.findViewById(R.id.songlist_artist);
            viewHolder.itemLayout = (LinearLayout) ret.findViewById(R.id.songlist_item);
            viewHolder.itemLayout.setOnClickListener(onItemChangeListener);
            viewHolder.likeButton = (ImageButton) ret.findViewById(R.id.songlist_likebutton);
            viewHolder.likeButton.setOnClickListener(onItemChangeListener);
            ret.setTag(viewHolder);
        }

        final Sing sing = list.get(position);

        String songTitle = sing.getSongTitle();
        String songArtist = sing.getSongArtist();

        viewHolder.songTitleTv.setText(songTitle);
        viewHolder.songArtistTv.setText(songArtist);

        viewHolder.itemLayout.setTag(R.id.songlist_item,position);
        viewHolder.likeButton.setTag(R.id.songlist_likebutton,position);

        return ret;
    }

    private static class ViewHolder{
        public TextView songTitleTv,songArtistTv;
        public ImageButton likeButton;
        public LinearLayout itemLayout;
    }

}
