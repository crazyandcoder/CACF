package com.crazyandcoder.android.lib.base.mvp.delegate;

import com.crazyandcoder.android.lib.base.mvp.common.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.mvp.common.ICrazyView;

/**
 * @ClassName: ICrazyMvpDelegateCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 6:43 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 6:43 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyMvpDelegateCallback<V extends ICrazyView, P extends ICrazyPresenter<V>> {

    /**
     * Creates the presenter instance
     *
     * @return
     */
    P createCrazyPresenter();


    /**
     * Gets the presenter.
     *
     * @return
     */
    P getCrazyPresenter();

    /**
     * Sets the presenter instance
     *
     * @param crazyPresenter
     */
    void setCrazyPresenter(P crazyPresenter);


    /**
     * Gets the MvpView for the presenter
     *
     * @return
     */
    V getCrazyView();

}
