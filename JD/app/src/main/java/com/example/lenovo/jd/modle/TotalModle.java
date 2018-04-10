package com.example.lenovo.jd.modle;

import com.example.lenovo.jd.presenter.IClassifyPresenter;
import com.example.lenovo.jd.presenter.IHomePagePresenter;
import com.example.lenovo.jd.view.api.ApiService;
import com.example.lenovo.jd.view.bean.ClassifyLeftSuperClass;
import com.example.lenovo.jd.view.bean.ClassifyRightSuperClass;
import com.example.lenovo.jd.view.bean.HomePageSuperClass;
import com.example.lenovo.jd.view.utils.RetrofitUtils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2018/4/9.
 */

public class TotalModle implements ITotalModle {

    private RetrofitUtils retrofitUtils;

    @Override
    public void left(String path, final IClassifyPresenter iClassifyPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ClassifyLeftSuperClass> observable = apiService.getLeftData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyLeftSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iClassifyPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyLeftSuperClass classifyLeftSuperClass) {
                        iClassifyPresenter.onLeftSuccess(classifyLeftSuperClass.getData());
                    }
                });
    }

    @Override
    public void right(String path, String cid, final IClassifyPresenter iClassifyPresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ClassifyRightSuperClass> observable = apiService.getRightData(cid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyRightSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iClassifyPresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyRightSuperClass classifyRightSuperClass) {
                        iClassifyPresenter.onRightSuccess(classifyRightSuperClass.getData());
                    }
                });
    }

    @Override
    public void homePage(String path, final IHomePagePresenter iHomePagePresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<HomePageSuperClass> observable = apiService.getHomePageData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePageSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iHomePagePresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(HomePageSuperClass homePageSuperClass) {
                        iHomePagePresenter.onSuccess(homePageSuperClass);
                    }
                });
    }

    @Override
    public void middle(String path, final IHomePagePresenter iHomePagePresenter) {
        retrofitUtils = RetrofitUtils.getInData();
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Observable<ClassifyLeftSuperClass> observable = apiService.getLeftData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyLeftSuperClass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iHomePagePresenter.onFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(ClassifyLeftSuperClass classifyLeftSuperClass) {
                        iHomePagePresenter.onMiddleSuccess(classifyLeftSuperClass.getData());
                    }
                });
    }
}
