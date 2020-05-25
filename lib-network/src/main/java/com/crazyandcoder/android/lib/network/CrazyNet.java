package com.crazyandcoder.android.lib.network;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.crazyandcoder.android.lib.network.cache.CacheConfig;
import com.crazyandcoder.android.lib.network.cache.RxCache;
import com.crazyandcoder.android.lib.network.common.DefaultHostNameVerifier;
import com.crazyandcoder.android.lib.network.interceptor.HttpLoggingInterceptor;
import com.crazyandcoder.android.lib.network.model.HttpHeaders;
import com.crazyandcoder.android.lib.network.model.HttpParams;
import com.crazyandcoder.android.lib.network.utils.HttpLog;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.crazyandcoder.android.lib.network.cache.constant.Const.DEFAULT_RETRY_COUNT;
import static com.crazyandcoder.android.lib.network.cache.constant.Const.DEFAULT_RETRY_DELAY;
import static com.crazyandcoder.android.lib.network.cache.constant.Const.DEFAULT_RETRY_INCREASEDELAY;
import static com.crazyandcoder.android.lib.network.cache.constant.Const.DEFAULT_TIME_MILLISECONDS;

/**
 * @ClassName: CrazyNet
 * @Description: 网络请求封装类，封装okhttp3和retrofit
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/22 4:31 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/22 4:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public final class CrazyNet {


    private static Application sApplication;

    /**
     * 缓存策略
     */
    private CacheConfig mCacheConfig = null;


    /**
     * 重试次数默认3次
     */
    private int mRetryCount = DEFAULT_RETRY_COUNT;

    /**
     * 延迟xxms重试
     */
    private int mRetryDelay = DEFAULT_RETRY_DELAY;

    /**
     * 叠加延迟
     */
    private int mRetryIncreaseDelay = DEFAULT_RETRY_INCREASEDELAY;


    /**
     * 全局公共请求头
     */
    private HttpHeaders mCommonHeaders;

    /**
     * 全局公共请求参数
     */
    private HttpParams mCommonParams;

    /**
     * okhttp请求的客户端
     */
    private OkHttpClient.Builder mOkHttpClientBuilder;

    /**
     * Retrofit请求的builder
     */
    private Retrofit.Builder mRetrofitBuilder;

    /**
     * RxCache请求的builder
     */
    private RxCache.Builder mRxCacheBuilder;

    private volatile static CrazyNet singleInstance = null;

    private CrazyNet() {
        mCacheConfig = new CacheConfig();
        mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.hostnameVerifier(new DefaultHostNameVerifier());
        mOkHttpClientBuilder.connectTimeout(DEFAULT_TIME_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkHttpClientBuilder.readTimeout(DEFAULT_TIME_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkHttpClientBuilder.writeTimeout(DEFAULT_TIME_MILLISECONDS, TimeUnit.MILLISECONDS);

        mRetrofitBuilder = new Retrofit.Builder();
        mRetrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        mRxCacheBuilder = new RxCache.Builder();

    }

    /**
     * 单例实现，获取CrazyNet
     *
     * @return
     */
    public static CrazyNet getInstance() {
        if (singleInstance == null) {
            synchronized (CrazyNet.class) {
                if (singleInstance == null) {
                    singleInstance = new CrazyNet();
                }
            }
        }
        return singleInstance;
    }


    /**
     * 初始化需要调用该方法
     *
     * @param application
     */
    public static void init(Application application) {
        sApplication = application;
    }

    /**
     * 检查是否以及初始化即，调用init方法
     */
    private static void checkInitialize() {
        if (sApplication == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 CrazyNet.init() 初始化！");
        }
    }

    /**
     * 返回全局的Application
     *
     * @return
     */
    public static Context getContext() {
        checkInitialize();
        return sApplication;
    }

    /**
     * 返回OkHttpClient
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        return getInstance().mOkHttpClientBuilder.build();
    }

    /**
     * 对外暴露 OkHttpClient,方便自定义
     *
     * @return
     */
    public static OkHttpClient.Builder getOkHttpClentBuilder() {
        return getInstance().mOkHttpClientBuilder;
    }


    /**
     * 返回 Retrofit
     *
     * @return
     */
    public static Retrofit getRetrofit() {
        return getInstance().mRetrofitBuilder.build();
    }

    /**
     * 对外暴露 Retrofit,方便自定义
     *
     * @return
     */
    public static Retrofit.Builder getRetrofitBuilder() {
        return getInstance().mRetrofitBuilder;
    }


    public static RxCache getRxCache() {
        return getInstance().mRxCacheBuilder.build();
    }

    /**
     * 对外暴露 RxCache,方便自定义
     *
     * @return
     */
    public static RxCache.Builder getRxCacheBuilder() {
        return getInstance().mRxCacheBuilder;
    }

    /**
     * @param tag
     * @return
     */
    public CrazyNet debug(String tag) {
        debug(tag, true);
        return this;
    }

    /**
     * @param tag
     * @param isPrintException
     * @return
     */
    public CrazyNet debug(String tag, boolean isPrintException) {
        String tempTag = TextUtils.isEmpty(tag) ? "CrazyNet_" : tag;
        if (isPrintException) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(tempTag, isPrintException);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
        HttpLog.customTagPrefix = tempTag;
        HttpLog.allowE = isPrintException;
        HttpLog.allowD = isPrintException;
        HttpLog.allowI = isPrintException;
        HttpLog.allowV = isPrintException;
        return this;
    }


    /**
     * https的全局访问规则
     *
     * @param hostNameVerifier
     * @return
     */
    public CrazyNet setHostnameVerifier(HostnameVerifier hostNameVerifier) {
        mOkHttpClientBuilder.hostnameVerifier(hostNameVerifier);
        return this;
    }


}
