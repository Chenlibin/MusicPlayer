package com.example.android.task4.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.example.android.task4.R;
import com.example.android.task4.adapter.MyFragmentPagerAdapter;
import com.example.android.task4.fragment.FirstFragment;
import com.example.android.task4.fragment.MyMusicFragment;
import com.example.android.task4.fragment.NearbyFragment;
import com.example.android.task4.service.MyBindService;
import com.example.android.task4.utils.MySQLite;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    private TabLayout mainTabLayout;
    private ViewPager mainViewPager;

    private MyFragmentPagerAdapter adapter;

    //启动服务
    private Intent serviceIntent;

    //关闭软件
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

//        //创建“喜欢”这个数据库
//        mySQLite = new MySQLite(this,"likeList.db",null,1);
//        db = mySQLite.getWritableDatabase();

        //启动服务
        serviceIntent = new Intent(this, MyBindService.class);
        startService(serviceIntent);

        List<Fragment> list = new ArrayList<Fragment>();

        list.add(new FirstFragment());
        list.add(new MyMusicFragment());
        list.add(new NearbyFragment());

        mainTabLayout = (TabLayout) findViewById(R.id.main_tab_bar);
        mainViewPager = (ViewPager) findViewById(R.id.main_view_pager);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list);
        mainViewPager.setAdapter(adapter);

        mainTabLayout.setupWithViewPager(mainViewPager);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {

        stopService(serviceIntent);

        super.onDestroy();
    }

}
