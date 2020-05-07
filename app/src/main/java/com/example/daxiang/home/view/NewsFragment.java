package com.example.daxiang.home.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.daxiang.Banner_Indicator;
import com.example.daxiang.R;
import com.example.daxiang.base.BaseFragment;
import com.example.daxiang.bean.NewsBean;
import com.example.daxiang.home.constract.NewsFragmentContract;
import com.example.daxiang.home.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsFragmentContract.INewsView{
    private  String tabID;
    private ViewPager banner_viewPager;

    private List<View> banner_views = new ArrayList<>();

    private Banner_Indicator banner_indicator;

    private int viewpage_Current_Pos = 0;
    public NewsFragment(String tabID) {
        this.tabID = tabID;
    }
    @Override
    protected NewsPresenter initPresenter() {
        return new NewsPresenter();
    }

    @Override
    protected void initLinstener() {

    }

    @Override
    protected void initData() {
        mPresenter.getRecommendList(tabID);

    }


    @Override
    protected void initView(View view) {
        banner_viewPager = view.findViewById(R.id.banner_viewpage);
        banner_indicator =view.findViewById(R.id.banner_indicator);

    }






    @Override
    public int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    public void setRecommendList(NewsBean newsBean) {
        initBanner(newsBean);

    }
    int current_banner_item;

    private void initBanner(final NewsBean newsBean){

        for (int i = 0; i <newsBean.getData().getBanner_list().size(); i++) {
            current_banner_item = i;
            View ban_view = LayoutInflater.from(getContext()).inflate(R.layout.news_banner_item,null,false);
            TextView bannerContent = ban_view.findViewById(R.id.banner_content);
            ImageView bannerImage =  ban_view.findViewById(R.id.benner_image);
            bannerContent.setText(newsBean.getData().getBanner_list().get(i).getDescription());
            Glide.with(getContext()).load(newsBean.getData().getBanner_list().get(i).getImage_url()).into(bannerImage);
            banner_views.add(ban_view);
            ban_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "点击了"+current_banner_item+"个view", Toast.LENGTH_SHORT).show();
                }
            });
        };

        NewsBannerAdapter bannerAdapter = new NewsBannerAdapter(banner_views);
        banner_viewPager.setAdapter(bannerAdapter);

//        设置图片数量，总数
        banner_indicator.setBannerImageSize(newsBean.getData().getBanner_list().size());
//        设置当前轮播图位置，默认0
        banner_indicator.setCurrentBannerItem(0);

//        viewPage监听
        banner_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current_banner_item = position;
//                在监听过程中，更改指示器种轮播图得当前位置，重绘指示器
                banner_indicator.setCurrentBannerItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        倒计时
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewpage_Current_Pos+=1;
                Log.e("TAG","当前位置"+viewpage_Current_Pos%(newsBean.getData().getBanner_list().size()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner_viewPager.setCurrentItem(viewpage_Current_Pos%(newsBean.getData().getBanner_list().size()));
                    }
                });
            }
        };
        timer.schedule(timerTask,2000,2000);
    }

}
