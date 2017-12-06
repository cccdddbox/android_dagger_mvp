package com.harry.android.mymvpdagger.dagger;

/**
 * Created by Zhang on 2017-12-06.
 */

import com.harry.android.mymvpdagger.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private final MainView mMainView;

    public  PresenterModule(MainView view) {
        mMainView = view;
    }

    @Provides
    MainView provideMainView() {
        return mMainView;
    }

}
