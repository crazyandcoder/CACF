package com.crazyandcoder.android.module.main.application;

import com.crazyandcoder.android.lib.base.BaseApplication;
import com.crazyandcoder.android.lib.common.application.ICrazyModuleInit;

/**
 * @ClassName: MainModuleInit
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/19 4:01 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 4:01 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainModuleInit implements ICrazyModuleInit {

    @Override
    public boolean onInitAhead(BaseApplication application) {
        return false;
    }

    @Override
    public boolean onInitLater(BaseApplication application) {
        return false;
    }
}
