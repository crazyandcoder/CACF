package com.crazyandcoder.android.lib.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName: BaseApplication
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/19 3:37 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 3:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    private static boolean sDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
    }


    /**
     * 初始化，监听activity的生命周期
     *
     * @param application
     */
    private void setApplication(BaseApplication application) {
        sInstance = application;
        application.registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
    }


    /**
     * 获取BaseApplication实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (sInstance == null) {
            throw new RuntimeException("please inherit BaseApplication or call setApplication()");
        }

        return sInstance;
    }

    public void setsDebug(boolean isDebug) {
        sDebug = isDebug;
    }

    public boolean issDebug() {
        return sDebug;
    }


    /**
     * activity生命周期监控
     */
    private class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    }
}
