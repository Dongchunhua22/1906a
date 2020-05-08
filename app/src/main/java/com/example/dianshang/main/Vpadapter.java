package com.example.dianshang.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Vpadapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private ArrayList<String> mtitles;

    public Vpadapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> mtitles) {
        super(fm);
        this.fragments = fragments;
        this.mtitles = mtitles;
    }

    public Vpadapter(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> fragments, ArrayList<String> mtitles) {
        super(fm, behavior);
        this.fragments = fragments;
        this.mtitles = mtitles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles.get(position);
    }
}
