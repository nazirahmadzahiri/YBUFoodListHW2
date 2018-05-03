package com.example.nazirahmad.ybu_foodlist_hw2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentsAdapter extends FragmentStatePagerAdapter {
    private String[] TITLES = null;
    private List<Fragment> fragments = null;

    public ViewPagerFragmentsAdapter(FragmentManager fragmentManager, List<Fragment> fragments, String[] TITLES) {
        super(fragmentManager);
        this.TITLES = TITLES;
        this.fragments = new ArrayList<Fragment>(fragments) ;
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  TITLES[position];
    }
}

