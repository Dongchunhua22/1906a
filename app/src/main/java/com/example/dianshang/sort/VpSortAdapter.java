package com.example.dianshang.sort;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dianshang.basr.BaseFragment;

import java.util.ArrayList;

public class VpSortAdapter extends FragmentStateAdapter {
    private  ArrayList<BaseFragment> mList;

    public VpSortAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<BaseFragment> mList) {
        super(fragmentActivity);
        this.mList = mList;
    }

    public VpSortAdapter(@NonNull Fragment fragment, ArrayList<BaseFragment> mList) {
        super(fragment);
        this.mList = mList;
    }

    public VpSortAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<BaseFragment> mList) {
        super(fragmentManager, lifecycle);
        this.mList = mList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
