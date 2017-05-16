package com.example.android.task4.fragment.nearbyFragments;

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
public class FriendsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_nearby_friends,container,false);

        return ret;
    }

    public String getFragmentTitle() {
        return "好友";
    }
}
