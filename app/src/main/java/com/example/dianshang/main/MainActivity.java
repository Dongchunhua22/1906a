package com.example.dianshang.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dianshang.MainView;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseActivity;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.bean.SortTabBean;
import com.example.dianshang.cart.CartFragment;
import com.example.dianshang.mainpage.MainpageFragment;
import com.example.dianshang.me.MeFragment;
import com.example.dianshang.presenter.MainPresenter;
import com.example.dianshang.sort.SortFragment;
import com.example.dianshang.topic.TopicFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//public class MainActivity extends BaseActivity<MainPresenter> implements MainView
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {


    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    private ArrayList<String> mtitles;
    private ArrayList<Fragment> fragments;
     private ArrayList<Integer> mImages;


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        initTitles();
        initImages();
        initFragment();
        Vpadapter vpadapter = new Vpadapter(getSupportFragmentManager(), fragments, mtitles);
        mVp.setAdapter(vpadapter);
        mTabLayout.setupWithViewPager(mVp);

        for (int i = 0; i < mtitles.size(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));

        }




    }

    private View getTabView(int position) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.top, null);
        TextView tv = inflate.findViewById(R.id.tv);
        ImageView iv = inflate.findViewById(R.id.iv);
        tv.setText(mtitles.get(position));
        iv.setImageResource(mImages.get(position));


        return inflate;
    }

    private void initImages() {
        mImages = new ArrayList<>();
        mImages.add(R.drawable.sc_main_page);
        mImages.add(R.drawable.sc_topc);
        mImages.add(R.drawable.sc_sort);
        mImages.add(R.drawable.sc_cart);
        mImages.add(R.drawable.se_me);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(MainpageFragment.newInstance());
        fragments.add(TopicFragment.newInstance());
        fragments.add(SortFragment.newInstance());
        fragments.add(CartFragment.newInstance());
        fragments.add(MeFragment.newInstance());

    }

    private void initTitles() {
        mtitles = new ArrayList<>();
        mtitles.add(BaseApp.getRes().getString(R.string.main_page));
        mtitles.add(BaseApp.getRes().getString(R.string.main_topic));
        mtitles.add(BaseApp.getRes().getString(R.string.main_sort));
        mtitles.add(BaseApp.getRes().getString(R.string.main_cart));
        mtitles.add(BaseApp.getRes().getString(R.string.main_me));

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }



    @OnClick({R.id.vp, R.id.tabLayout})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.vp:
                break;
            case R.id.tabLayout:
                break;
        }
    }


}
