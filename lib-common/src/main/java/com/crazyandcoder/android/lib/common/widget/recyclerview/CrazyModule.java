package com.crazyandcoder.android.lib.common.widget.recyclerview;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GetSRVModule
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:17 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:17 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyModule {
    private static final String SRV_CONFIG_VALUE = "IRecyclerViewModule";
    private static IRecyclerViewModule config;

    CrazyModule(Context context) {
        List<IRecyclerViewModule> configs = parse(context);
        config = configs.size() > 0 ? configs.get(0) : null;
    }

    IRecyclerViewModule getConfig() {
        return config;
    }

    private List<IRecyclerViewModule> parse(Context context) {
        List<IRecyclerViewModule> modules = new ArrayList<>();
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                for (String key : appInfo.metaData.keySet()) {
                    if (SRV_CONFIG_VALUE.equals(appInfo.metaData.get(key))) {
                        modules.add(parseConfig(key));
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return modules;
    }

    private static IRecyclerViewModule parseConfig(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (Exception e) {
            return null;
        }
        Object module;
        try {
            module = clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
        if (!(module instanceof IRecyclerViewModule)) {
            return null;
        }
        return (IRecyclerViewModule) module;
    }

}
