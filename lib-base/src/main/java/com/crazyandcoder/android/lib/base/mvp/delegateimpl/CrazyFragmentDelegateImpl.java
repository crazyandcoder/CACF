package com.crazyandcoder.android.lib.base.mvp.delegateimpl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyFragmentDelegate;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyMvpDelegateCallback;

/**
 * @ClassName: CrazyFragmentDelegateImpl
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 11:09 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 11:09 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyFragmentDelegateImpl<V extends ICrazyView, P extends ICrazyPresenter<V>> implements ICrazyFragmentDelegate<V, P> {


    private ICrazyMvpDelegateCallback<V, P> mICrazyMvpDelegateCallback;
    protected Fragment mFragment;

    public CrazyFragmentDelegateImpl(ICrazyMvpDelegateCallback<V, P> iCrazyMvpDelegateCallback, Fragment mFragment) {

        if (iCrazyMvpDelegateCallback == null) {
            throw new NullPointerException("ICrazyMvpDelegateCallback is null!");
        }

        if (mFragment == null) {
            throw new NullPointerException("Fragment is null!");
        }

        this.mICrazyMvpDelegateCallback = iCrazyMvpDelegateCallback;
        this.mFragment = mFragment;
    }

    private P createLocalPresenter() {
        P presenter = this.mICrazyMvpDelegateCallback.createCrazyPresenter();
//        if (presenter == null) {
//            throw new NullPointerException(
//                    "Presenter returned from createPresenter() is null. Activity is " + getActivity());
//        }
        return presenter;
    }

    private P getPresenter() {
        P presenter = this.mICrazyMvpDelegateCallback.getCrazyPresenter();
//        if (presenter == null) {
//            throw new NullPointerException("Presenter returned from getCrazyPresenter() is null");
//        }
        return presenter;
    }


    @NonNull
    private Activity getActivity() {
        Activity activity = mFragment.getActivity();
        if (activity == null) {
            throw new NullPointerException(
                    "Activity returned by Fragment.getActivity() is null. Fragment is " + mFragment);
        }

        return activity;
    }

    private V getCrazyView() {
        V view = mICrazyMvpDelegateCallback.getCrazyView();
//        if (view == null) {
//            throw new NullPointerException("View returned from getCrazyView() is null");
//        }
        return view;
    }


    @Override
    public void onCreate(Bundle saved) {

        P presenter = createLocalPresenter();
//        if (presenter == null) {
//            throw new IllegalStateException("Presenter is null,internal exception,please restart and retry again or contact author:QQ:275137657!!!");
//        }
        this.mICrazyMvpDelegateCallback.setCrazyPresenter(presenter);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.attachView(getCrazyView());
        }
    }

    @Override
    public void onDestroyView() {
        P presenter = getPresenter();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Activity activity) {

    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
