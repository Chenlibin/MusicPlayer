package com.example.android.task4.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
            ret = LayoutInflater.from(context).inflate(R.layout.songlist_view,parent,false);
        }

        ViewHolder viewHolder = (ViewHolder) ret.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.songTitleTv = (TextView) ret.findViewById(R.id.songlist_title);
            viewHolder.songArtistTv = (TextView) ret.findViewById(R.id.songlist_artist);
            viewHolder.itemLayout = (LinearLayout) ret.findViewById(R.id.songlist_item);
            viewHolder.likeButton = (ImageButton) ret.findViewById(R.id.songlist_likebutton);
            ret.setTag(viewHolder);
        }

        final Sing sing = list.get(position);

        String songTitle = sing.getSongTitle();
        String songArtist = sing.getSongArtist();

        viewHolder.songTitleTv.setText(songTitle);
        viewHolder.songArtistTv.setText(songArtist);

        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.CURRENT_LIST = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    String idOfSong = list.get(i).getSongid();
                    Config.CURRENT_LIST.add(i,idOfSong);
                }

                String songId = sing.getSongid();

                Intent intent = new Intent();
                intent.putExtra("songId",songId);
                intent.putExtra("position",position);
                intent.setClass(context,PlayShowActivity.class);
                context.startActivity(intent);
            }
        });

        viewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "添加到我喜欢的歌单" + list.get(position).getSongTitle(), Toast.LENGTH_SHORT).show();
                ContentValues values = new ContentValues();
                values.put("songId",list.get(position).getSongid());
                values.put("songTitle",list.get(position).getSongTitle());
                values.put("songArtist",list.get(position).getSongArtist());
                MainActivity.db.insert("Like",null,values);
                Log.e("likebutton",position + "");
            }
        });

        return ret;
    }

    private static class ViewHolder{
        public TextView songTitleTv,songArtistTv;
        public ImageButton likeButton;
        public LinearLayout itemLayout;
    }

}
