package com.harry.android.mymvpdagger.presenter;

import com.harry.android.mymvpdagger.model.MainModel;
import com.harry.android.mymvpdagger.model.MainModelBean;
import com.harry.android.mymvpdagger.view.MainView;
import javax.inject.Inject;
/**
 * Created by Zhang on 2017-11-26.
 */

public class MainPresenter implements Presenter<MainView>, IMainPresenter {
    private MainView mMainView;
    private MainModel mMainModel;

    @Inject
    public MainPresenter(MainView view) {
        attachView(view);
        mMainModel = new MainModel(this);
    }
    @Override
    public void attachView(MainView view) {
        this.mMainView = view;
    }
    @Override
    public void detachView() {
        this.mMainView = null;
    }
    public void loadData() {
        mMainView.showProgress();
        mMainModel.loadData();
    }
    @Override
    public void loadDataSuccess(MainModelBean mainModelBean) {
        mMainView.showData(mainModelBean);
        mMainView.hideProgress();
    }
    @Override
    public void loadDataFailure() {
        mMainView.hideProgress();
    }
}