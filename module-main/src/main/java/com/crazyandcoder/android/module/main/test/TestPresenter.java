package com.crazyandcoder.android.module.main.test;

import com.crazyandcoder.android.lib.base.mvp.base.CrazyBasePresenter;

/**
 * @ClassName: TestPresenter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 7:16 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 7:16 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TestPresenter extends CrazyBasePresenter<ITestView> implements ITest {

    @Override
    public void login(String name, String password) {
        ifCrazyViewAttached(new CrazyViewAction<ITestView>() {
            @Override
            public void run(ITestView view) {
                view.loginSuccess();
            }
        });

    }
}
