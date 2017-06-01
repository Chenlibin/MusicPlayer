package com.example.android.task4.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.task4.R;
import com.example.android.task4.activity.MainActivity;
import com.example.android.task4.activity.PlayShowActivity;
import com.example.android.task4.bean.Config;
import com.example.android.task4.bean.Sing;
import com.example.android.task4.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liber on 2017/5/25.
 */

public class MyMusicFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<Sing> list;

    public MyMusicFragmentAdapter(Context context, List<Sing> list) {
        this.context = context;
        this.list = list;

    }

    public void refresh(List<Sing> nList){
        list = nList;
        notifyDataSetChanged();
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
            ret = LayoutInflater.from(context).inflate(R.layout.mymusic_view,parent,false);
        }

        ViewHolder viewHolder = (ViewHolder) ret.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.titleTv = (TextView) ret.findViewById(R.id.mymusic_title);
            viewHolder.artistTv = (TextView) ret.findViewById(R.id.mymusic_artist);
            viewHolder.itemLayout = (LinearLayout) ret.findViewById(R.id.mymusic_item);
            viewHolder.decreaseIB = (ImageButton) ret.findViewById(R.id.mymusic_decreasebutton);
            ret.setTag(viewHolder);

        }

        Sing sing = list.get(position);

        final String songTitle = sing.getSongTitle();
        String songArtist = sing.getSongArtist();
        final String songId = sing.getSongid();

        viewHolder.titleTv.setText(songTitle);
        viewHolder.artistTv.setText(songArtist);

        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.CURRENT_LIST = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    String idOfSong = list.get(i).getSongid();
                    Config.CURRENT_LIST.add(i,idOfSong);
                }

                Intent intent = new Intent();
                intent.putExtra("songId",songId);
                intent.putExtra("position",position);
                intent.setClass(context,PlayShowActivity.class);
                context.startActivity(intent);
            }
        });
        viewHolder.decreaseIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.delete("Like","songId = ?",new String[]{songId});
                Toast.makeText(context,"已经删除" + songTitle,Toast.LENGTH_SHORT).show();
            }
        });


        return ret;
    }

    private static class ViewHolder{
        public TextView titleTv,artistTv;
        public ImageButton decreaseIB;
        public LinearLayout itemLayout;
    }

}
