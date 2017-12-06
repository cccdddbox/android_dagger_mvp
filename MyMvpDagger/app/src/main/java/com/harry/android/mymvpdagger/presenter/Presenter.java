package com.harry.android.mymvpdagger.presenter;

/**
 * Created by Zhang on 2017-11-26.
 */

public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
