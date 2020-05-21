package com.crazyandcoder.android.lib.base.mvp.base;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;

import java.lang.ref.WeakReference;

/**
 * @ClassName: ICrazyBasePresenter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 7:18 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 7:18 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyBasePresenter<V extends ICrazyView> implements ICrazyPresenter<V> {


    private WeakReference<V> mViewRef;


    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);

    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }


    protected final void ifCrazyViewAttached(CrazyViewAction<V> crazyViewAction) {
        final V view = mViewRef == null ? null : mViewRef.get();
        if (view != null) {
            crazyViewAction.run(view);
        } else {

        }

    }

    public interface CrazyViewAction<V> {
        void run(V view);
    }
}
