package com.example.lenovo.jd.modle;

import com.example.lenovo.jd.presenter.IClassifyPresenter;
import com.example.lenovo.jd.presenter.IHomePagePresenter;

/**
 * Created by lenovo on 2018/4/9.
 */

public interface ITotalModle {
    void left(String path, IClassifyPresenter iClassifyPresenter);

    void right(String path,String cid, IClassifyPresenter iClassifyPresenter);

    void homePage(String path,IHomePagePresenter iHomePagePresenter);

    void middle(String path,IHomePagePresenter iHomePagePresenter);
}
