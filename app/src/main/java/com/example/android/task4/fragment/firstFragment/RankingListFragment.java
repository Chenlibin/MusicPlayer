package com.example.android.task4.fragment.firstFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.task4.R;
import com.example.android.task4.fragment.BaseFragment;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class RankingListFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first_rl,container,false);

        return ret;
    }

    @Override
    public String getFragmentTitle() {
        return "排行榜";
    }
}
