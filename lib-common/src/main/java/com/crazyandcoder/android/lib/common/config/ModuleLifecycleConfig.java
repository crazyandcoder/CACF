package com.crazyandcoder.android.lib.common.config;

import com.crazyandcoder.android.lib.base.BaseApplication;
import com.crazyandcoder.android.lib.common.application.ICrazyModuleInit;

/**
 * @ClassName: ModuleLifecycleConfig
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/19 3:56 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 3:56 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ModuleLifecycleConfig {

    private ModuleLifecycleConfig() {

    }

    private static class SingleHolder {
        private static ModuleLifecycleConfig instance = new ModuleLifecycleConfig();
    }

    /**
     * 单例模式获取实例
     *
     * @return
     */
    public static ModuleLifecycleConfig getInstance() {
        return SingleHolder.instance;
    }

    /**
     * 提前初始化各个module
     * 通过反射调用每个module的application进行初始化
     *
     * @param application
     */
    public void initModuleAhead(BaseApplication application) {
        for (String moduleNames : ModuleLifecycleNames.sInitModuleNames) {

            try {
                Class<?> cls = Class.forName(moduleNames);
                ICrazyModuleInit moduleInit = (ICrazyModuleInit) cls.newInstance();
                moduleInit.onInitAhead(application);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 延迟初始化各个模块
     * 通过反射调用每个module的application进行初始化
     *
     * @param application
     */
    public void initModuleLater(BaseApplication application) {
        for (String moduleNames : ModuleLifecycleNames.sInitModuleNames) {

            try {
                Class<?> cls = Class.forName(moduleNames);
                ICrazyModuleInit moduleInit = (ICrazyModuleInit) cls.newInstance();
                moduleInit.onInitLater(application);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


}
