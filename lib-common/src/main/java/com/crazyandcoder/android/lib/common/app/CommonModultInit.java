package com.crazyandcoder.android.lib.common.app;

import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.crazyandcoder.android.lib.base.BaseApplication;
import com.crazyandcoder.android.lib.common.application.ICrazyModuleInit;
import com.crazyandcoder.android.lib.common.utils.log.CrazyLog;
import com.crazyandcoder.android.lib.network.CrazyNet;

/**
 * @ClassName: CommonModultInit
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 10:19 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 10:19 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CommonModultInit implements ICrazyModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {

        //初始化ARouter路由框架
        if (application.issDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(application);


        //初始化网络框架
        CrazyNet.init(application);
        CrazyNet.getInstance().debug("CrazyAndPackHttpLog", true);

        CrazyLog.init(true,"CrazyAndPack");

        return true;
    }

    @Override
    public boolean onInitLater(BaseApplication application) {
        return false;
    }
}
