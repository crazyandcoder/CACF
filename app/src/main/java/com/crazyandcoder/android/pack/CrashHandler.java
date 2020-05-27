package com.crazyandcoder.android.pack;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.util.concurrent.TimeoutException;

/**
 * @ClassName: CrashHandler
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 9:34 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 9:34 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() +
            "/CrashTest/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".txt";
    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getsInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }


    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息
     *
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.d("crazyAndPack", "uncaughtException: " + ex.toString());

    }
}