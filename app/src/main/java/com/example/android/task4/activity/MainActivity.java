package com.example.android.task4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;

import com.example.android.task4.R;
import com.example.android.task4.adapter.MyFragmentPagerAdapter;
import com.example.android.task4.fragment.FirstFragment;
import com.example.android.task4.fragment.MyMusicFragment;
import com.example.android.task4.fragment.NearbyFragment;
import com.example.android.task4.service.MyBindService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    private TabLayout mainTabLayout;
    private ViewPager mainViewPager;

    private MyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);



        List<Fragment> list = new ArrayList<Fragment>();

        list.add(new FirstFragment(this));
        list.add(new MyMusicFragment());
        list.add(new NearbyFragment());

        mainTabLayout = (TabLayout) findViewById(R.id.main_tab_bar);
        mainViewPager = (ViewPager) findViewById(R.id.main_view_pager);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        mainViewPager.setAdapter(adapter);

        mainTabLayout.setupWithViewPager(mainViewPager);

//        connection = new ServiceConnection() {
//            @Override       //当启动源跟Service成功连接之后将会自动调用这个方法
//            public void onServiceConnected(ComponentName name, IBinder binder) {
//                service = ((MyBindService.MyBinder)binder).getService();
//            }
//
//            @Override       //当启动源跟Service连接意外丢失时调用此方法  比如崩溃或者被强行杀死
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        };
//
//        intent = new Intent(MainActivity.this, MyBindService.class);
//        bindService(intent,connection, Service.BIND_AUTO_CREATE);
//

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
