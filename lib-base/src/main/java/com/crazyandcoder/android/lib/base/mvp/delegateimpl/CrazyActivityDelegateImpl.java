package com.crazyandcoder.android.lib.base.mvp.delegateimpl;

import android.app.Activity;
import android.os.Bundle;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyActivityDelegate;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyMvpDelegateCallback;

/**
 * @ClassName: CrazyActivityDelegateImpl
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 7:00 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 7:00 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyActivityDelegateImpl<V extends ICrazyView, P extends ICrazyPresenter<V>> implements ICrazyActivityDelegate<V, P> {


    private ICrazyMvpDelegateCallback<V, P> mICrazyMvpDelegateCallback;
    protected Activity mActivity;

    public CrazyActivityDelegateImpl(Activity activity, ICrazyMvpDelegateCallback<V, P> iCrazyMvpDelegateCallback) {


        if (activity == null) {
            throw new NullPointerException("Activity is null");
        }


        if (iCrazyMvpDelegateCallback == null) {
            throw new NullPointerException("ICrazyMvpDelegateCallback is null");
        }

        this.mActivity = activity;
        this.mICrazyMvpDelegateCallback = iCrazyMvpDelegateCallback;
    }

    @Override
    public void onCreate(Bundle bundle) {
        P presenter = createLocalPresenter();
        if (presenter == null) {
            throw new IllegalStateException("Presenter is null,internal exception,please restart and retry again or contact author:QQ:275137657!!!");
        }
        mICrazyMvpDelegateCallback.setCrazyPresenter(presenter);
        getCrazyPresenter().attachView(getCrazyView());
    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        getCrazyPresenter().detachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    private P createLocalPresenter() {
        P presenter = this.mICrazyMvpDelegateCallback.createCrazyPresenter();
        if (presenter == null) {
            throw new NullPointerException(
                    "Presenter returned from createCrazyPresenter() is null. Activity is " + mActivity);
        }
        return presenter;
    }

    private P getCrazyPresenter() {
        P presenter = mICrazyMvpDelegateCallback.getCrazyPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter returned from getCrazyPresenter() is null");
        }
        return presenter;
    }

    private V getCrazyView() {
        V view = mICrazyMvpDelegateCallback.getCrazyView();
        if (view == null) {
            throw new NullPointerException("View returned from getCrazyView() is null");
        }
        return view;
    }

}
