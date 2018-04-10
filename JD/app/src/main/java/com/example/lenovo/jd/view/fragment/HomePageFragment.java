package com.example.lenovo.jd.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.jd.R;
import com.example.lenovo.jd.presenter.HomePagePresenter;
import com.example.lenovo.jd.view.activity.IHomePageView;
import com.example.lenovo.jd.view.adapter.MiddleAdapter;
import com.example.lenovo.jd.view.api.Api;
import com.example.lenovo.jd.view.banner.AccordionTransformer;
import com.example.lenovo.jd.view.banner.AutoBanner;
import com.example.lenovo.jd.view.base.BaseFragment;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 */

public class HomePageFragment extends BaseFragment<HomePagePresenter> implements AutoBanner.AutoBannerListener,IHomePageView {
    private AutoBanner mAutoBanner;
    private List<String> mImgUrls;
    private RecyclerView middle_recycle;
    private MiddleAdapter middleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.home_page_fragment;
    }

    @Override
    protected HomePagePresenter getPresenter() {
        presenter = new HomePagePresenter(this);
        return presenter;
    }

    @Override
    protected void initView(View view) {
        mAutoBanner = (AutoBanner) view.findViewById(R.id.auto_banner);
        middle_recycle = view.findViewById(R.id.middle_recycle);
        middleAdapter = new MiddleAdapter(getContext());
        mImgUrls = new ArrayList<>();
    }

    @Override
    protected void getData() {
        presenter.homePage(Api.HOME_NAME);
        presenter.middle(Api.HOME_NAME);

        middle_recycle.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false));

        middle_recycle.setAdapter(middleAdapter);
    }

    @Override
    public void onClickListener(int postion) {
        Toast.makeText(getContext(), "点击了：" + postion + "位置", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(HomePageSuperClass homePageSuperClass) {
        List<HomePageSuperClass.DataBean> data = homePageSuperClass.getData();
        for (int i = 0; i < data.size() ; i++){
            mImgUrls.add(data.get(i).getIcon());
        }
        //不设置setAuto，不会自动轮播，不设置监听，无法点击,动画转换器可以根据需要设置
        mAutoBanner.load(mImgUrls).setOnAutoBannerListener(this).setAuto(3000).setAutoBannerAnimation(AccordionTransformer.class).display();


    }

    @Override
    public void onMiddleSuccess(List<ClassifyLeftSuperClass.DataBean> data) {
//        Toast.makeText(getContext(), data.size() + "===", Toast.LENGTH_SHORT).show();
        if (data != null){
            middleAdapter.setList(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAutoBanner.startAuto();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAutoBanner.stopAuto();
        presenter.onDestory();
    }
}
