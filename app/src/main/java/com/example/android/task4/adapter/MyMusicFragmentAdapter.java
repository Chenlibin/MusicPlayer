package com.example.android.task4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.task4.R;
import com.example.android.task4.bean.Sing;

import java.util.List;

/**
 * Created by Liber on 2017/5/25.
 */

public class MyMusicFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<Sing> list;

    //监听
    private View.OnClickListener onItemChangeListener;

    public MyMusicFragmentAdapter() {    }

    //由Activity或者fragment来监听  传递监听
    public void setOnItemChangeListener(View.OnClickListener onItemChangeListener){
        this.onItemChangeListener = onItemChangeListener;
    }

    public MyMusicFragmentAdapter(Context context, List<Sing> list) {
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
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View ret = null;

        if (convertView != null) {
            ret = convertView;
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            ret = layoutInflater.inflate(R.layout.mymusic_view,parent,false);
        }

        //getTag(),用处是重复利用以下控件
        ViewHolder viewHolder = (ViewHolder) ret.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.titleTv = (TextView) ret.findViewById(R.id.mymusic_title);
            viewHolder.artistTv = (TextView) ret.findViewById(R.id.mymusic_artist);
            viewHolder.itemLayout = (LinearLayout) ret.findViewById(R.id.mymusic_item);
            //每个item都要有的监听,由Activity或者fragment来监听
            viewHolder.itemLayout.setOnClickListener(onItemChangeListener);
            viewHolder.decreaseIB = (ImageButton) ret.findViewById(R.id.mymusic_decreasebutton);
            viewHolder.decreaseIB.setOnClickListener(onItemChangeListener);
            ret.setTag(viewHolder);
        }

        Sing sing = list.get(position);

        String songTitle = sing.getSongTitle();
        String songArtist = sing.getSongArtist();

        viewHolder.titleTv.setText(songTitle);
        viewHolder.artistTv.setText(songArtist);

        //给按钮和item设置“标签”传递position
        viewHolder.decreaseIB.setTag(R.id.mymusic_decreasebutton,position);
        viewHolder.itemLayout.setTag(R.id.mymusic_item,position);

        return ret;
    }

    private static class ViewHolder{
        public TextView titleTv,artistTv;
        public ImageButton decreaseIB;
        public LinearLayout itemLayout;
    }

}
