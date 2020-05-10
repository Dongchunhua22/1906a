package com.example.daxiang.home.view;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

import com.example.daxiang.R;
import com.example.daxiang.base.BaseFragment;
import com.example.daxiang.bean.ColunmBean;
import com.example.daxiang.bean.NewsBean;
import com.example.daxiang.home.constract.RecommendContract;
import com.example.daxiang.home.presenter.RecommendPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.IRecommendView  {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<NewsFragment> fragments = new ArrayList<>();

    private NewsFragmentAdapter newsFragmentAdapter;

    @Override
    protected RecommendPresenter initPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected void initData() {
        mPresenter.getColumList();

    }



    @Override
    protected void initView(View view) {
        tabLayout = view.findViewById(R.id.mytablayout);

        viewPager = view.findViewById(R.id.myviewpage);

    }

    private void initTab(final ColunmBean columList){
        newsFragmentAdapter = new NewsFragmentAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(newsFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(columList.getData().getList().size());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < columList.getData().getList().size(); i++) {
                    TextView tabAt = (TextView) tabLayout.getTabAt(i).getCustomView();
                    tabAt.setBackgroundResource(R.color.color_WHITE);
                }
                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(50);

                TextView customView = (TextView) tab.getCustomView();
                drawable.setStroke(1, Color.parseColor("#ff00ff"));
                drawable.setColor(Color.parseColor("#"+columList.getData().getList().get(tab.getPosition()).getBack_color()));
              //  drawable.setColor(Color.parseColor("#"+columList.getData().getList().get(tab.getPosition()).getBack_color()));
                customView.setBackground(drawable);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

    @Override
    public int getLayoutID() {
        return R.layout.recommend_fragment;
    }
//p层回调接口
    @Override
    public void setRecommendList(NewsBean newsBean) {
        Log.e("TAG","对应栏目新闻请求i成功："+newsBean.toString());

    }

    @Override
    public void setColumList(ColunmBean columList) {
        Log.e("TAG","当前TabLayout的栏目数据："+columList.toString());
//        栏目请求成功了
        if(columList.getCode() ==1){
           // mPresenter.getRecommendList(columList.getData().getList().get(0).getId());

            for (int i = 0; i < columList.getData().getList().size(); i++) {
//                创建咱们得Fragment
                NewsFragment  newsFragment = new NewsFragment(columList.getData().getList().get(i).getId());
                fragments.add(newsFragment);
            }

            initTab(columList);

            for (int i = 0; i < columList.getData().getList().size(); i++) {
                TextView textView =(TextView) LayoutInflater.from(getContext()).inflate(R.layout.recommend_tab_item,null);
                textView.setGravity(Gravity.CENTER);
                textView.setText(i+"个");
                tabLayout.addTab(tabLayout.newTab().setCustomView(textView),i);
            }

//            newsFragmentAdapter.notifyDataSetChanged();

        }
    }
}
