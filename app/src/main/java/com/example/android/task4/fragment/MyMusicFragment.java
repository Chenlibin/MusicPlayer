package com.example.android.task4.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.task4.R;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MyMusicFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_mymusic,container,false);


        return ret;
    }

    @Override
    public String getFragmentTitle() {
        return "音乐";
    }
}
