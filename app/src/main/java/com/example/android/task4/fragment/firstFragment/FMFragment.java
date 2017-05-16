package com.example.android.task4.fragment.firstFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.task4.R;
import com.example.android.task4.fragment.BaseFragment;


public class FMFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first_fm,container,false);

        return ret;
    }

    @Override
    public String getFragmentTitle() {
        return "主播电台";
    }
}
