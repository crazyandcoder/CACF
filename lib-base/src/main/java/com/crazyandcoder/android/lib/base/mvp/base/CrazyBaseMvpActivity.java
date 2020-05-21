package com.crazyandcoder.android.lib.base.mvp.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyActivityDelegate;
import com.crazyandcoder.android.lib.base.mvp.delegate.ICrazyMvpDelegateCallback;
import com.crazyandcoder.android.lib.base.mvp.delegateimpl.CrazyActivityDelegateImpl;

/**
 * @ClassName: BaseMvpActivity
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 6:39 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 6:39 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class CrazyBaseMvpActivity<V extends ICrazyView, P extends ICrazyPresenter<V>>
        extends AppCompatActivity
        implements ICrazyView, ICrazyMvpDelegateCallback<V, P> {

    protected ICrazyActivityDelegate mCrazyActivityDelegate;
    protected P mCrazyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCrazyActivityDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCrazyActivityDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getCrazyActivityDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCrazyActivityDelegate().onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getCrazyActivityDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getCrazyActivityDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCrazyActivityDelegate().onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getCrazyActivityDelegate().onSaveInstanceState(outState);
    }

    /**
     * implement this method for each activity while use presenter
     *
     * @return
     */
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

    protected ICrazyActivityDelegate<V, P> getCrazyActivityDelegate() {
        if (mCrazyActivityDelegate == null) {
            mCrazyActivityDelegate = new CrazyActivityDelegateImpl(this, this);
        }
        return mCrazyActivityDelegate;
    }

}
