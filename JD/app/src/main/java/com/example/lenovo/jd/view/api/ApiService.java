package com.example.lenovo.jd.view.api;

import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface ApiService {
    //首页
    @GET("ad/getAd")
    Observable<HomePageSuperClass> getHomePageData();
    //分类左侧列表
    @GET("product/getCatagory")
    Observable<ClassifyLeftSuperClass> getLeftData();
    //分类右侧数据
    @GET("product/getProductCatagory")
    Observable<ClassifyRightSuperClass> getRightData(@Query("cid")String cid);


}
