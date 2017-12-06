package com.harry.android.mymvpdagger.view;

import com.harry.android.mymvpdagger.model.MainModelBean;

/**
 * Created by Zhang on 2017-11-26.
 */

public interface MainView {
    void showData(MainModelBean mainModelBean);
    void showProgress();
    void hideProgress();
}
