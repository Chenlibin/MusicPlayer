package com.example.android.task4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.task4.R;
import com.example.android.task4.bean.SingMessage;

import java.util.List;

/**
 * Created by Administrator on 2016/9/19 0019.
 */
public class RecommendFragmentAdapter extends BaseAdapter {

    private Context context;
    private List<SingMessage> singMessages;

    public RecommendFragmentAdapter(Context context, List<SingMessage> singMessages) {
        this.context = context;
        this.singMessages = singMessages;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (singMessages != null) {
            count = singMessages.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return singMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View ret = null;

        if (convertView != null) {
            ret = convertView;
        }
        else {
            ret = LayoutInflater.from(context).inflate(R.layout.fragment_first_recommend_textview,parent,false);
        }

        ViewHolder viewHolder = (ViewHolder) ret.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) ret.findViewById(R.id.recommend_textview);
            viewHolder.iv = (ImageView) ret.findViewById(R.id.display_image);
            ret.setTag(viewHolder);
        }

        SingMessage list = singMessages.get(position);

        String reText = list.getReText();

        //显示图片
        Glide
                .with(context)
                .load(list.getRePhoto())
                .centerCrop()           //填充ImageView
                .into(viewHolder.iv);      //装载到布局中

        viewHolder.tv.setText(reText);


        return ret;
    }

    private static class ViewHolder{
        public TextView tv;
        public ImageView iv;
    }

}
