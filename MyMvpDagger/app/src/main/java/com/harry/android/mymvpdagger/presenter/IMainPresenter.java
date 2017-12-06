package com.harry.android.mymvpdagger.presenter;

import com.harry.android.mymvpdagger.model.MainModelBean;

/**
 * Created by Zhang on 2017-11-26.
 */

public interface IMainPresenter {
    void loadDataSuccess(MainModelBean mainModelBean);
    void loadDataFailure();
}