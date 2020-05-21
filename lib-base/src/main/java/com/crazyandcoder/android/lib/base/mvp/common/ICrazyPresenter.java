package com.crazyandcoder.android.lib.base.mvp.common;

/**
 * @ClassName: IPresenter
 * @Description: The base interface for each mvp presenter.
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 6:35 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 6:35 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyPresenter<V extends ICrazyView> {

    /**
     * attach the view to this presenter
     *
     * @param view
     */
    void attachView(V view);


    /**
     * Will be called if the view has been detached from the Presenter.
     */
    void detachView();

}
