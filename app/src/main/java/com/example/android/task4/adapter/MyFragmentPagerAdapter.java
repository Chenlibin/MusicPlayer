package com.example.android.task4.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.task4.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;

        if (list != null) {
            ret = list.size();
        }

        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String ret = null;

        BaseFragment baseFragment = (BaseFragment) list.get(position);

        ret = baseFragment.getFragmentTitle();

        return ret;
    }
}
