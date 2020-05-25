package com.crazyandcoder.android.lib.network;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.crazyandcoder.android.lib.network.cache.CacheConfig;
import com.crazyandcoder.android.lib.network.cache.RxCache;
import com.crazyandcoder.android.lib.network.cache.converter.IDiskConverter;
import com.crazyandcoder.android.lib.network.cache.model.CacheMode;
import com.crazyandcoder.android.lib.network.common.DefaultHostNameVerifier;
import com.crazyandcoder.android.lib.network.cookie.CookieManger;
import com.crazyandcoder.android.lib.network.interceptor.HttpLoggingInterceptor;
import com.crazyandcoder.android.lib.network.model.HttpHeaders;
import com.crazyandcoder.android.lib.network.model.HttpParams;
import com.crazyandcoder.android.lib.network.utils.HttpLog;
import com.crazyandcoder.android.lib.network.utils.HttpsUtils;
import com.crazyandcoder.android.lib.network.utils.RxUtil;
import com.crazyandcoder.android.lib.network.utils.Utils;

import java.io.File;
import java.io.InputStream;
import java.net.Proxy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.crazyandcoder.android.lib.network.cache.constant.Const.DEFAULT_CACHE_NEVER_EXPIRE;
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

    /**
     * Cookie管理
     */
    private CookieManger cookieJar;

    /**
     * 全局BaseUrl
     */
    private String mBaseUrl;

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

    public CacheConfig getCacheConfig() {
        return mCacheConfig;
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

    /**
     * https的全局自签名证书
     *
     * @param certificates
     * @return
     */
    public CrazyNet setCertificates(InputStream... certificates) {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, certificates);
        mOkHttpClientBuilder.sslSocketFactory(sslParams.sslSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * https双向认证证书
     *
     * @param bksFile
     * @param password
     * @param certificates
     * @return
     */
    public CrazyNet setCertificates(InputStream bksFile, String password, InputStream... certificates) {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(bksFile, password, certificates);
        mOkHttpClientBuilder.sslSocketFactory(sslParams.sslSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 全局cookie存取规则
     *
     * @param cookieStore
     * @return
     */
    public CrazyNet setCookieStore(CookieManger cookieStore) {
        cookieJar = cookieStore;
        mOkHttpClientBuilder.cookieJar(cookieJar);
        return this;
    }

    /**
     * 获取全局的cookie实例
     *
     * @return
     */
    public static CookieManger getCookieJar() {
        return getInstance().cookieJar;
    }

    /**
     * 全局读取超时时间
     *
     * @param readTimeOut
     * @return
     */
    public CrazyNet setReadTimeOut(long readTimeOut) {
        mOkHttpClientBuilder.readTimeout(readTimeOut, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 全局写入超时时间
     *
     * @param readTimeOut
     * @return
     */
    public CrazyNet setWriteTimeOut(long readTimeOut) {
        mOkHttpClientBuilder.writeTimeout(readTimeOut, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 全局连接超时时间
     *
     * @param readTimeOut
     * @return
     */
    public CrazyNet setConnectTimeOut(long readTimeOut) {
        mOkHttpClientBuilder.connectTimeout(readTimeOut, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 超时重试次数
     *
     * @param retryCount
     * @return
     */
    public CrazyNet setRetryCount(int retryCount) {
        if (retryCount < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        mRetryCount = retryCount;
        return this;
    }

    /**
     * 超时重试次数
     *
     * @return
     */
    public static int getRetryCount() {
        return getInstance().mRetryCount;
    }

    /**
     * 超时重试延迟时间
     */
    public CrazyNet setRetryDelay(int retryDelay) {
        if (retryDelay < 0) {
            throw new IllegalArgumentException("retryDelay must > 0");
        }
        mRetryDelay = retryDelay;
        return this;
    }

    /**
     * 超时重试延迟时间
     */
    public static int getRetryDelay() {
        return getInstance().mRetryDelay;
    }

    /**
     * 超时重试延迟叠加时间
     */
    public CrazyNet setRetryIncreaseDelay(int retryIncreaseDelay) {
        if (retryIncreaseDelay < 0) {
            throw new IllegalArgumentException("retryIncreaseDelay must > 0");
        }
        mRetryIncreaseDelay = retryIncreaseDelay;
        return this;
    }

    /**
     * 超时重试延迟叠加时间
     */
    public static int getRetryIncreaseDelay() {
        return getInstance().mRetryIncreaseDelay;

    }

    /**
     * 全局的缓存模式
     *
     * @param cacheMode
     * @return
     */
    public CrazyNet setCacheMode(CacheMode cacheMode) {
        mCacheConfig.setCacheMode(cacheMode);
        return this;
    }

    /**
     * 获取全局的缓存模式
     *
     * @return
     */
    public static CacheMode getCacheMode() {
        if (getInstance().mCacheConfig == null) {
            return null;
        }
        return getInstance().mCacheConfig.getCacheMode();
    }


    /**
     * 全局的缓存过期时间
     */
    public CrazyNet setCacheTime(long cacheTime) {
        if (cacheTime <= -1) {
            cacheTime = DEFAULT_CACHE_NEVER_EXPIRE;
        }
        mCacheConfig.setCacheTime(cacheTime);
        return this;
    }

    /**
     * 获取全局的缓存过期时间
     */
    public static long getCacheTime() {
        return getInstance().mCacheConfig.getCacheTime();
    }

    /**
     * 全局的缓存大小,默认50M
     */
    public CrazyNet setCacheMaxSize(long maxSize) {
        mCacheConfig.setCacheMaxSize(maxSize);
        return this;
    }

    /**
     * 获取全局的缓存大小
     */
    public static long getCacheMaxSize() {
        return getInstance().mCacheConfig.getCacheMaxSize();
    }


    /**
     * 全局设置缓存的版本，默认为1，缓存的版本号
     */
    public CrazyNet setCacheVersion(int cacheersion) {
        if (cacheersion < 0) {
            throw new IllegalArgumentException("cacheersion must > 0");
        }
        mRxCacheBuilder.appVersion(cacheersion);
        return this;
    }

    /**
     * 全局设置缓存的路径，默认是应用包下面的缓存
     */
    public CrazyNet setCacheDirectory(File directory) {
        mCacheConfig.setCacheDirectory(Utils.checkNotNull(directory, "directory == null"));
        mRxCacheBuilder.diskDir(directory);
        return this;
    }

    /**
     * 获取缓存的路劲
     */
    public static File getCacheDirectory() {
        return getInstance().mCacheConfig.getCacheDirectory();
    }

    /**
     * 全局设置缓存的转换器
     */
    public CrazyNet setCacheDiskConverter(IDiskConverter converter) {
        mRxCacheBuilder.diskConverter(Utils.checkNotNull(converter, "converter == null"));
        return this;
    }

    /**
     * 全局设置OkHttp的缓存,默认是3天
     */
    public CrazyNet setHttpCache(Cache cache) {
        mCacheConfig.setCache(cache);
        return this;
    }

    /**
     * 获取OkHttp的缓存<br>
     */
    public static Cache getHttpCache() {
        return getInstance().mCacheConfig.getCache();
    }

    /**
     * 添加全局公共请求参数
     */
    public CrazyNet addCommonParams(HttpParams commonParams) {
        if (mCommonParams == null) {
            mCommonParams = new HttpParams();
        }
        mCommonParams.put(commonParams);
        return this;
    }

    /**
     * 获取全局公共请求参数
     */
    public HttpParams getCommonParams() {
        return mCommonParams;
    }

    /**
     * 获取全局公共请求头
     */
    public HttpHeaders getCommonHeaders() {
        return mCommonHeaders;
    }

    /**
     * 添加全局公共请求参数
     */
    public CrazyNet addCommonHeaders(HttpHeaders commonHeaders) {
        if (mCommonHeaders == null) {
            mCommonHeaders = new HttpHeaders();
        }
        mCommonHeaders.put(commonHeaders);
        return this;
    }

    /**
     * 添加全局拦截器
     */
    public CrazyNet addInterceptor(Interceptor interceptor) {
        mOkHttpClientBuilder.addInterceptor(Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    /**
     * 添加全局网络拦截器
     */
    public CrazyNet addNetworkInterceptor(Interceptor interceptor) {
        mOkHttpClientBuilder.addNetworkInterceptor(Utils.checkNotNull(interceptor, "interceptor == null"));
        return this;
    }

    /**
     * 全局设置代理
     */
    public CrazyNet setOkproxy(Proxy proxy) {
        mOkHttpClientBuilder.proxy(Utils.checkNotNull(proxy, "proxy == null"));
        return this;
    }

    /**
     * 全局设置请求的连接池
     */
    public CrazyNet setOkconnectionPool(ConnectionPool connectionPool) {
        mOkHttpClientBuilder.connectionPool(Utils.checkNotNull(connectionPool, "connectionPool == null"));
        return this;
    }

    /**
     * 全局为Retrofit设置自定义的OkHttpClient
     */
    public CrazyNet setOkclient(OkHttpClient client) {
        mRetrofitBuilder.client(Utils.checkNotNull(client, "client == null"));
        return this;
    }

    /**
     * 全局设置Converter.Factory,默认GsonConverterFactory.create()
     */
    public CrazyNet addConverterFactory(Converter.Factory factory) {
        mRetrofitBuilder.addConverterFactory(Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    /**
     * 全局设置CallAdapter.Factory,默认RxJavaCallAdapterFactory.create()
     */
    public CrazyNet addCallAdapterFactory(CallAdapter.Factory factory) {
        mRetrofitBuilder.addCallAdapterFactory(Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    /**
     * 全局设置Retrofit callbackExecutor
     */
    public CrazyNet setCallbackExecutor(Executor executor) {
        mRetrofitBuilder.callbackExecutor(Utils.checkNotNull(executor, "executor == null"));
        return this;
    }

    /**
     * 全局设置Retrofit对象Factory
     */
    public CrazyNet setCallFactory(okhttp3.Call.Factory factory) {
        mRetrofitBuilder.callFactory(Utils.checkNotNull(factory, "factory == null"));
        return this;
    }

    /**
     * 全局设置baseurl
     */
    public CrazyNet setBaseUrl(String baseUrl) {
        mBaseUrl = Utils.checkNotNull(baseUrl, "baseUrl == null");
        return this;
    }

    /**
     * 获取全局baseurl
     */
    public static String getBaseUrl() {
        return getInstance().mBaseUrl;
    }

    /**
     * get请求
     */
//    public static GetRequest get(String url) {
//        return new GetRequest(url);
//    }


    /**
     * 取消订阅
     */
    public static void cancelSubscription(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    /**
     * 清空缓存
     */
    public static void clearCache() {
        getRxCache().clear().compose(RxUtil.<Boolean>io_main())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        HttpLog.i("clearCache success!!!");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        HttpLog.i("clearCache err!!!");
                    }
                });
    }

    /**
     * 移除缓存（key）
     */
    public static void removeCache(String key) {
        getRxCache().remove(key).compose(RxUtil.<Boolean>io_main()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                HttpLog.i("removeCache success!!!");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                HttpLog.i("removeCache err!!!");
            }
        });
    }


}
