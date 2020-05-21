package com.crazyandcoder.android.lib.base.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyFragmentDelegate;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyMvpDelegateCallback;
import com.crazyandcoder.android.lib.base.mvp.delegateimpl.CrazyFragmentDelegateImpl;

/**
 * @ClassName: CrazyBaseMvpFragment
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 11:29 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 11:29 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class CrazyBaseMvpFragment<V extends ICrazyView, P extends ICrazyPresenter<V>>
        extends Fragment
        implements ICrazyView, ICrazyMvpDelegateCallback<V, P> {


    private ICrazyFragmentDelegate<V, P> mICrazyFragmentDelegate;
    protected P mCrazyPresenter;

    protected ICrazyFragmentDelegate<V, P> getCrazyBaseMvpFragment() {
        if (mICrazyFragmentDelegate == null) {
            mICrazyFragmentDelegate = new CrazyFragmentDelegateImpl<>(this, this);
        }
        return mICrazyFragmentDelegate;
    }

    @Override
    public abstract P createCrazyPresenter();

    @Override
    public P getCrazyPresenter() {
        return mCrazyPresenter;
    }

    @Override
    public void setCrazyPresenter(P crazyPresenter) {
        this.mCrazyPresenter = crazyPresenter;

    }

    @Override
    public V getCrazyView() {
        return (V) this;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCrazyBaseMvpFragment().onViewCreated(view, savedInstanceState);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        getCrazyBaseMvpFragment().onDestroyView();
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCrazyBaseMvpFragment().onCreate(savedInstanceState);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        getCrazyBaseMvpFragment().onDestroy();
    }

    @Override public void onPause() {
        super.onPause();
        getCrazyBaseMvpFragment().onPause();
    }

    @Override public void onResume() {
        super.onResume();
        getCrazyBaseMvpFragment().onResume();
    }

    @Override public void onStart() {
        super.onStart();
        getCrazyBaseMvpFragment().onStart();
    }

    @Override public void onStop() {
        super.onStop();
        getCrazyBaseMvpFragment().onStop();
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getCrazyBaseMvpFragment().onActivityCreated(savedInstanceState);
    }

    @Deprecated @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        getCrazyBaseMvpFragment().onAttach(activity);
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        getCrazyBaseMvpFragment().onAttach(context);
    }

    @Override public void onDetach() {
        super.onDetach();
        getCrazyBaseMvpFragment().onDetach();
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getCrazyBaseMvpFragment().onSaveInstanceState(outState);
    }
}
