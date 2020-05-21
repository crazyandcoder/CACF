package com.crazyandcoder.android.lib.common.application;

import com.crazyandcoder.android.lib.base.BaseApplication;

/**
 * @ClassName: IModuleInit
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/19 4:02 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 4:02 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyModuleInit {


    /**
     * 提前初始化
     * @param application
     * @return
     */
    boolean onInitAhead(BaseApplication application);




    /**
     * 延迟初始化
     * @param application
     * @return
     */
    boolean onInitLater(BaseApplication application);



}
