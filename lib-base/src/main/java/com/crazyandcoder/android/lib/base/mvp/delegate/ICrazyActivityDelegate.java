package com.crazyandcoder.android.lib.base.mvp.delegate;

import android.app.Activity;
import android.os.Bundle;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;

/**
 * @ClassName: ICrazyActivityDelegate
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 6:49 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 6:49 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyActivityDelegate<V extends ICrazyView, P extends ICrazyPresenter<V>> {

    /**
     * This method must be called from {@link Activity#onCreate(Bundle)}.
     * This method internally creates the presenter and attaches the view to it.
     * @param bundle
     */
    void onCreate(Bundle bundle);

    /**
     * This method must be called from {@link Activity#onRestart()}
     */
    void onRestart();

    /**
     * This method must be called from {@link Activity#onStart()}
     */
    void onStart();

    /**
     * This method must be called from {@link Activity#onResume()}
     */
    void onResume();

    /**
     * This method must be called from {@link Activity#onPause()}
     */
    void onPause();



    /**
     * This method must be called from {@link Activity#onStop()}
     */
    void onStop();

    /**
     * This method must be called from {@link Activity#onDestroy()}}.
     * This method internally detaches the view from presenter
     */
    void onDestroy();


    /**
     * This method must be called from {@link Activity#onSaveInstanceState(Bundle)}
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);


}
