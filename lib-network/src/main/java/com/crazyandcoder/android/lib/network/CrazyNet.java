package com.crazyandcoder.android.lib.network;

import android.app.Application;

import com.crazyandcoder.android.lib.network.cache.CacheConfig;
import com.crazyandcoder.android.lib.network.cache.RxCache;
import com.crazyandcoder.android.lib.network.common.DefaultHostNameVerifier;
import com.crazyandcoder.android.lib.network.model.HttpHeaders;
import com.crazyandcoder.android.lib.network.model.HttpParams;

import java.util.concurrent.TimeUnit;

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
}
