package com.crazyandcoder.android.lib.common.config;

/**
 * @ClassName: ModuleLifecycleNames
 * @Description: 组件生命周期初始化类的类目管理者, 在这里注册需要初始化的组件, 通过反射动态调用各个组件的初始化方法
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/19 4:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 4:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ModuleLifecycleNames {

    /**
     * main组件初始化
     */
    private static final String sMainInit = "com.crazyandcoder.android.module.main.application.MainModuleInit";


    private static final String sCommonInit = "com.crazyandcoder.android.lib.common.app.CommonModultInit";

    /**
     * 所有需要初始化的组件
     */
    public static final String[] sInitModuleNames = {sCommonInit, sMainInit};


}
