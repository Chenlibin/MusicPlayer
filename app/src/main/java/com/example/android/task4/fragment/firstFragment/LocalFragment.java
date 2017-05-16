package com.example.android.task4.fragment.firstFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.task4.R;
import com.example.android.task4.fragment.BaseFragment;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class LocalFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first_local,container,false);

        return ret;
    }

    @Override
    public String getFragmentTitle() {
        return "本地音乐";
    }
}
