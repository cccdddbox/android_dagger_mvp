package com.harry.android.mymvpdagger.dagger;

/**
 * Created by Zhang on 2017-12-06.
 */

import com.harry.android.mymvpdagger.ui.MainActivity;

import dagger.Component;


@Component(modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(MainActivity mainActivity);

}
