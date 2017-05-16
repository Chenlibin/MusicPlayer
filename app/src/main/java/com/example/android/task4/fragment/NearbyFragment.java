package com.example.android.task4.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.task4.R;
import com.example.android.task4.adapter.MyFragmentPagerAdapter;
import com.example.android.task4.fragment.nearbyFragments.DynamicFragment;
import com.example.android.task4.fragment.nearbyFragments.FriendsFragment;
import com.example.android.task4.fragment.nearbyFragments.NearFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class NearbyFragment extends BaseFragment {

    private TabLayout nearbyTabLayout;
    private ViewPager nearbyViewPager;

    private MyFragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_nearby,container,false);

        nearbyTabLayout = (TabLayout) ret.findViewById(R.id.nearby_tab_bar);
        nearbyViewPager = (ViewPager) ret.findViewById(R.id.nearby_view_pager);

        List<Fragment> nearbyList = new ArrayList<Fragment>();

        nearbyList.add(new DynamicFragment());
        nearbyList.add(new FriendsFragment());
        nearbyList.add(new NearFragment());

        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),nearbyList);

        nearbyViewPager.setAdapter(adapter);
        nearbyTabLayout.setupWithViewPager(nearbyViewPager);



        return ret;
    }

    @Override
    public String getFragmentTitle() {
        return "附近";
    }
}
