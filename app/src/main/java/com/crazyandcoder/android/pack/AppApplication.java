package com.crazyandcoder.android.pack;

import com.crazyandcoder.android.lib.base.BaseApplication;
import com.crazyandcoder.android.lib.common.config.ModuleLifecycleConfig;

/**
 * @ClassName: AppApplication
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/19 3:49 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 3:49 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setsDebug(BuildConfig.DEBUG);
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
    }
}
