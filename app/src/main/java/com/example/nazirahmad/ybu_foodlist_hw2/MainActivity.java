package com.example.nazirahmad.ybu_foodlist_hw2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment1.FragmentFood;
import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment2.FragmentAnnouncement;
import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment3.FragmentNews;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
- https://stackoverflow.com/questions/33597986/android-actionbar-always-null?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
- https://stackoverflow.com/questions/36925765/butter-knife-return-null-pointer?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initViewPager();
        initTabLayout();
    }

    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void initViewPager() {
        String[] TITLES = getResources().getStringArray(R.array.tab_items);
        ViewPagerFragmentsAdapter adapterViewPager = new ViewPagerFragmentsAdapter(getSupportFragmentManager(), getFragments(), TITLES);
        mViewPager.setAdapter(adapterViewPager);
    }

    public void initTabLayout() {
        try {
            mTabLayout.setupWithViewPager(mViewPager);

            mTabLayout.getTabAt(0).setCustomView(R.layout.myfragment2).setIcon(R.mipmap.ic_foods_black);
            mTabLayout.getTabAt(1).setCustomView(R.layout.myfragment2).setIcon(R.mipmap.ic_microphone_black);
            mTabLayout.getTabAt(2).setCustomView(R.layout.myfragment2).setIcon(R.mipmap.ic_news_black);

            mTabLayout.getTabAt(0).getCustomView().setSelected(true);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(FragmentFood.newInstance());
        fragments.add(FragmentAnnouncement.newInstance());
        fragments.add(FragmentNews.newInstance());
        return fragments;
    }
}