package com.crazyandcoder.android.lib.network.request;

import android.content.Context;
import android.text.TextUtils;

import com.crazyandcoder.android.lib.network.CrazyNet;
import com.crazyandcoder.android.lib.network.api.ApiService;
import com.crazyandcoder.android.lib.network.cache.RxCache;
import com.crazyandcoder.android.lib.network.cache.constant.Const;
import com.crazyandcoder.android.lib.network.cache.converter.IDiskConverter;
import com.crazyandcoder.android.lib.network.cache.model.CacheMode;
import com.crazyandcoder.android.lib.network.interceptor.BaseDynamicInterceptor;
import com.crazyandcoder.android.lib.network.interceptor.CacheInterceptor;
import com.crazyandcoder.android.lib.network.interceptor.CacheInterceptorOffline;
import com.crazyandcoder.android.lib.network.interceptor.HeadersInterceptor;
import com.crazyandcoder.android.lib.network.interceptor.NoCacheInterceptor;
import com.crazyandcoder.android.lib.network.model.HttpHeaders;
import com.crazyandcoder.android.lib.network.model.HttpParams;
import com.crazyandcoder.android.lib.network.utils.HttpLog;
import com.crazyandcoder.android.lib.network.utils.HttpsUtils;
import com.crazyandcoder.android.lib.network.utils.RxUtil;
import com.crazyandcoder.android.lib.network.utils.Utils;

import java.io.File;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static com.crazyandcoder.android.lib.network.CrazyNet.getOkHttpClentBuilder;
import static com.crazyandcoder.android.lib.network.CrazyNet.getRetrofitBuilder;
import static com.crazyandcoder.android.lib.network.CrazyNet.getRxCache;

/**
 * @ClassName: BaseRequest
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 6:31 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 6:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseRequest<R extends BaseRequest> {

    protected Cache cache = null;
    /**
     * 默认无缓存
     */
    protected CacheMode cacheMode = CacheMode.NO_CACHE;
    /**
     * 缓存时间
     */
    protected long cacheTime = -1;
    /**
     * 缓存Key
     */
    protected String cacheKey;
    /**
     * 设置Rxcache磁盘转换器
     */
    protected IDiskConverter diskConverter;
    /**
     * BaseUrl
     */
    protected String baseUrl;
    /**
     * 请求url
     */
    protected String url;
    /**
     * 读超时
     */
    protected long readTimeOut;
    /**
     * 写超时
     */
    protected long writeTimeOut;
    /**
     * 链接超时
     */
    protected long connectTimeout;
    /**
     * 重试次数默认3次
     */
    protected int retryCount;
    /**
     * 延迟xxms重试
     */
    protected int retryDelay;
    /**
     * 叠加延迟
     */
    protected int retryIncreaseDelay;
    /**
     * 是否是同步请求
     */
    protected boolean isSyncRequest;
    /**
     * 用户手动添加的Cookie
     */
    protected List<Cookie> cookies = new ArrayList<>();

    protected final List<Interceptor> networkInterceptors = new ArrayList<>();
    /**
     * 添加的header
     */
    protected HttpHeaders headers = new HttpHeaders();
    /**
     * 添加的param
     */
    protected Retrofit retrofit;
    protected HttpParams params = new HttpParams();
    /**
     * rxcache缓存
     */
    protected RxCache rxCache;
    /**
     * 通用的的api接口
     */
    protected ApiService apiManager;
    protected OkHttpClient okHttpClient;
    protected Context context;
    /**
     * 是否需要签名
     */
    private boolean sign = false;
    private boolean timeStamp = false;
    /**
     * 是否需要追加时间戳
     */
    private boolean accessToken = false;
    /**
     * 是否需要追加token
     */
    protected HttpUrl httpUrl;
    protected Proxy proxy;
    protected HttpsUtils.SSLParams sslParams;
    protected HostnameVerifier hostnameVerifier;
    protected List<Converter.Factory> converterFactories = new ArrayList<>();
    protected List<CallAdapter.Factory> adapterFactories = new ArrayList<>();
    protected final List<Interceptor> interceptors = new ArrayList<>();

    public BaseRequest(String url) {
        this.url = url;
        context = CrazyNet.getContext();
        CrazyNet config = CrazyNet.getInstance();
        this.baseUrl = CrazyNet.getBaseUrl();
        if (!TextUtils.isEmpty(this.baseUrl)) {
            httpUrl = HttpUrl.parse(baseUrl);
        }
        if (baseUrl == null && url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
            httpUrl = HttpUrl.parse(url);
            baseUrl = httpUrl.url().getProtocol() + "://" + httpUrl.url().getHost() + "/";
        }
        //添加缓存模式
        cacheMode = config.getCacheConfig().getCacheMode();
        //缓存时间
        cacheTime = config.getCacheConfig().getCacheTime();
        //超时重试次数
        retryCount = CrazyNet.getRetryCount();
        //超时重试延时
        retryDelay = CrazyNet.getRetryDelay();
        //超时重试叠加延时
        retryIncreaseDelay = CrazyNet.getRetryIncreaseDelay();
        //Okhttp  cache
        cache = config.getHttpCache();
        //默认添加 Accept-Language
        String acceptLanguage = HttpHeaders.getAcceptLanguage();
        if (!TextUtils.isEmpty(acceptLanguage)) {
            headers(HttpHeaders.HEAD_KEY_ACCEPT_LANGUAGE, acceptLanguage);
        }
        //默认添加 User-Agent
        String userAgent = HttpHeaders.getUserAgent();
        if (!TextUtils.isEmpty(userAgent)) {
            headers(HttpHeaders.HEAD_KEY_USER_AGENT, userAgent);
        }
        //添加公共请求参数
        if (config.getCommonParams() != null) {
            params.put(config.getCommonParams());
        }
        if (config.getCommonHeaders() != null) {
            headers.put(config.getCommonHeaders());
        }
    }


    public HttpParams getParams() {
        return this.params;
    }

    public R readTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
        return (R) this;
    }

    public R writeTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return (R) this;
    }

    public R connectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
        return (R) this;
    }

    public R okCache(Cache cache) {
        this.cache = cache;
        return (R) this;
    }

    public R cacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
        return (R) this;
    }

    public R cacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
        return (R) this;
    }

    public R cacheTime(long cacheTime) {
        if (cacheTime <= -1) cacheTime = Const.DEFAULT_CACHE_NEVER_EXPIRE;
        this.cacheTime = cacheTime;
        return (R) this;
    }

    public R baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        if (!TextUtils.isEmpty(this.baseUrl)) {
            httpUrl = HttpUrl.parse(baseUrl);
        }
        return (R) this;
    }

    public R retryCount(int retryCount) {
        if (retryCount < 0) throw new IllegalArgumentException("retryCount must > 0");
        this.retryCount = retryCount;
        return (R) this;
    }

    public R retryDelay(int retryDelay) {
        if (retryDelay < 0) throw new IllegalArgumentException("retryDelay must > 0");
        this.retryDelay = retryDelay;
        return (R) this;
    }

    public R retryIncreaseDelay(int retryIncreaseDelay) {
        if (retryIncreaseDelay < 0)
            throw new IllegalArgumentException("retryIncreaseDelay must > 0");
        this.retryIncreaseDelay = retryIncreaseDelay;
        return (R) this;
    }

    public R addInterceptor(Interceptor interceptor) {
        interceptors.add(Utils.checkNotNull(interceptor, "interceptor == null"));
        return (R) this;
    }

    public R addNetworkInterceptor(Interceptor interceptor) {
        networkInterceptors.add(Utils.checkNotNull(interceptor, "interceptor == null"));
        return (R) this;
    }

    public R addCookie(String name, String value) {
        Cookie.Builder builder = new Cookie.Builder();
        Cookie cookie = builder.name(name).value(value).domain(httpUrl.host()).build();
        this.cookies.add(cookie);
        return (R) this;
    }

    public R addCookie(Cookie cookie) {
        this.cookies.add(cookie);
        return (R) this;
    }

    public R addCookies(List<Cookie> cookies) {
        this.cookies.addAll(cookies);
        return (R) this;
    }

    /**
     * 设置Converter.Factory,默认GsonConverterFactory.create()
     */
    public R addConverterFactory(Converter.Factory factory) {
        converterFactories.add(factory);
        return (R) this;
    }

    /**
     * 设置CallAdapter.Factory,默认RxJavaCallAdapterFactory.create()
     */
    public R addCallAdapterFactory(CallAdapter.Factory factory) {
        adapterFactories.add(factory);
        return (R) this;
    }

    /**
     * 设置代理
     */
    public R okproxy(Proxy proxy) {
        this.proxy = proxy;
        return (R) this;
    }

    /**
     * 设置缓存的转换器
     */
    public R cacheDiskConverter(IDiskConverter converter) {
        this.diskConverter = Utils.checkNotNull(converter, "converter == null");
        return (R) this;
    }

    /**
     * https的全局访问规则
     */
    public R hostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.hostnameVerifier = hostnameVerifier;
        return (R) this;
    }

    /**
     * https的全局自签名证书
     */
    public R certificates(InputStream... certificates) {
        this.sslParams = HttpsUtils.getSslSocketFactory(null, null, certificates);
        return (R) this;
    }

    /**
     * https双向认证证书
     */
    public R certificates(InputStream bksFile, String password, InputStream... certificates) {
        this.sslParams = HttpsUtils.getSslSocketFactory(bksFile, password, certificates);
        return (R) this;
    }

    /**
     * 添加头信息
     */
    public R headers(HttpHeaders headers) {
        this.headers.put(headers);
        return (R) this;
    }

    /**
     * 添加头信息
     */
    public R headers(String key, String value) {
        headers.put(key, value);
        return (R) this;
    }

    /**
     * 移除头信息
     */
    public R removeHeader(String key) {
        headers.remove(key);
        return (R) this;
    }

    /**
     * 移除所有头信息
     */
    public R removeAllHeaders() {
        headers.clear();
        return (R) this;
    }

    /**
     * 设置参数
     */
    public R params(HttpParams params) {
        this.params.put(params);
        return (R) this;
    }

    public R params(String key, String value) {
        params.put(key, value);
        return (R) this;
    }

    public R params(Map<String, String> keyValues) {
        params.put(keyValues);
        return (R) this;
    }

    public R removeParam(String key) {
        params.remove(key);
        return (R) this;
    }

    public R removeAllParams() {
        params.clear();
        return (R) this;
    }

    public R sign(boolean sign) {
        this.sign = sign;
        return (R) this;
    }

    public R timeStamp(boolean timeStamp) {
        this.timeStamp = timeStamp;
        return (R) this;
    }

    public R accessToken(boolean accessToken) {
        this.accessToken = accessToken;
        return (R) this;
    }

    public R syncRequest(boolean syncRequest) {
        this.isSyncRequest = syncRequest;
        return (R) this;
    }

    /**
     * 移除缓存（key）
     */
    public void removeCache(String key) {
        getRxCache().remove(key).compose(RxUtil.<Boolean>io_main())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        HttpLog.i("removeCache success!!!");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        HttpLog.i("removeCache err!!!" + throwable);
                    }
                });
    }

    /**
     * 根据当前的请求参数，生成对应的OkClient
     */
    private OkHttpClient.Builder generateOkClient() {
        if (readTimeOut <= 0 && writeTimeOut <= 0 && connectTimeout <= 0 && sslParams == null
                && cookies.size() == 0 && hostnameVerifier == null && proxy == null && headers.isEmpty()) {
            OkHttpClient.Builder builder = getOkHttpClentBuilder();
            for (Interceptor interceptor : builder.interceptors()) {
                if (interceptor instanceof BaseDynamicInterceptor) {
                    ((BaseDynamicInterceptor) interceptor).sign(sign).timeStamp(timeStamp).accessToken(accessToken);
                }
            }
            return builder;
        } else {
            final OkHttpClient.Builder newClientBuilder = CrazyNet.getOkHttpClient().newBuilder();
            if (readTimeOut > 0)
                newClientBuilder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS);
            if (writeTimeOut > 0)
                newClientBuilder.writeTimeout(writeTimeOut, TimeUnit.MILLISECONDS);
            if (connectTimeout > 0)
                newClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
            if (hostnameVerifier != null) newClientBuilder.hostnameVerifier(hostnameVerifier);
            if (sslParams != null)
                newClientBuilder.sslSocketFactory(sslParams.sslSocketFactory, sslParams.trustManager);
            if (proxy != null) newClientBuilder.proxy(proxy);
            if (cookies.size() > 0) CrazyNet.getCookieJar().addCookies(cookies);

            //添加头  头添加放在最前面方便其他拦截器可能会用到
            newClientBuilder.addInterceptor(new HeadersInterceptor(headers));

            for (Interceptor interceptor : interceptors) {
                if (interceptor instanceof BaseDynamicInterceptor) {
                    ((BaseDynamicInterceptor) interceptor).sign(sign).timeStamp(timeStamp).accessToken(accessToken);
                }
                newClientBuilder.addInterceptor(interceptor);
            }
            for (Interceptor interceptor : newClientBuilder.interceptors()) {
                if (interceptor instanceof BaseDynamicInterceptor) {
                    ((BaseDynamicInterceptor) interceptor).sign(sign).timeStamp(timeStamp).accessToken(accessToken);
                }
            }
            if (networkInterceptors.size() > 0) {
                for (Interceptor interceptor : networkInterceptors) {
                    newClientBuilder.addNetworkInterceptor(interceptor);
                }
            }
            return newClientBuilder;
        }
    }

    /**
     * 根据当前的请求参数，生成对应的Retrofit
     */
    private Retrofit.Builder generateRetrofit() {
        if (converterFactories.isEmpty() && adapterFactories.isEmpty()) {
            Retrofit.Builder builder = getRetrofitBuilder();
            if (!TextUtils.isEmpty(baseUrl)) {
                builder.baseUrl(baseUrl);
            }
            return builder;
        } else {
            final Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            if (!TextUtils.isEmpty(baseUrl)) {
                retrofitBuilder.baseUrl(baseUrl);
            }
            if (!converterFactories.isEmpty()) {
                for (Converter.Factory converterFactory : converterFactories) {
                    retrofitBuilder.addConverterFactory(converterFactory);
                }
            } else {
                //获取全局的对象重新设置
                Retrofit.Builder newBuilder = getRetrofitBuilder();
                if (!TextUtils.isEmpty(baseUrl)) {
                    newBuilder.baseUrl(baseUrl);
                }
                List<Converter.Factory> listConverterFactory = newBuilder.build().converterFactories();
                for (Converter.Factory factory : listConverterFactory) {
                    retrofitBuilder.addConverterFactory(factory);
                }
            }
            if (!adapterFactories.isEmpty()) {
                for (CallAdapter.Factory adapterFactory : adapterFactories) {
                    retrofitBuilder.addCallAdapterFactory(adapterFactory);
                }
            } else {
                //获取全局的对象重新设置
                Retrofit.Builder newBuilder = getRetrofitBuilder();
                List<CallAdapter.Factory> listAdapterFactory = newBuilder.baseUrl(baseUrl).build().callAdapterFactories();
                for (CallAdapter.Factory factory : listAdapterFactory) {
                    retrofitBuilder.addCallAdapterFactory(factory);
                }
            }
            return retrofitBuilder;
        }
    }

    /**
     * 根据当前的请求参数，生成对应的RxCache和Cache
     */
    private RxCache.Builder generateRxCache() {
        final RxCache.Builder rxCacheBuilder = CrazyNet.getRxCacheBuilder();
        switch (cacheMode) {
            case NO_CACHE://不使用缓存
                final NoCacheInterceptor NOCACHEINTERCEPTOR = new NoCacheInterceptor();
                interceptors.add(NOCACHEINTERCEPTOR);
                networkInterceptors.add(NOCACHEINTERCEPTOR);
                break;
            case DEFAULT://使用Okhttp的缓存
                if (this.cache == null) {
                    File cacheDirectory = CrazyNet.getCacheDirectory();
                    if (cacheDirectory == null) {
                        cacheDirectory = new File(CrazyNet.getContext().getCacheDir(), "okhttp-cache");
                    } else {
                        if (cacheDirectory.isDirectory() && !cacheDirectory.exists()) {
                            cacheDirectory.mkdirs();
                        }
                    }
                    this.cache = new Cache(cacheDirectory, Math.max(5 * 1024 * 1024, CrazyNet.getCacheMaxSize()));
                }
                String cacheControlValue = String.format("max-age=%d", Math.max(-1, cacheTime));
                final CacheInterceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new CacheInterceptor(CrazyNet.getContext(), cacheControlValue);
                final CacheInterceptorOffline REWRITE_CACHE_CONTROL_INTERCEPTOR_OFFLINE = new CacheInterceptorOffline(CrazyNet.getContext(), cacheControlValue);
                networkInterceptors.add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
                networkInterceptors.add(REWRITE_CACHE_CONTROL_INTERCEPTOR_OFFLINE);
                interceptors.add(REWRITE_CACHE_CONTROL_INTERCEPTOR_OFFLINE);
                break;
            case FIRSTREMOTE:
            case FIRSTCACHE:
            case ONLYREMOTE:
            case ONLYCACHE:
            case CACHEANDREMOTE:
            case CACHEANDREMOTEDISTINCT:
                interceptors.add(new NoCacheInterceptor());
                if (diskConverter == null) {
                    final RxCache.Builder tempRxCacheBuilder = rxCacheBuilder;
                    tempRxCacheBuilder.cachekey(Utils.checkNotNull(cacheKey, "cacheKey == null"))
                            .cacheTime(cacheTime);
                    return tempRxCacheBuilder;
                } else {
                    final RxCache.Builder cacheBuilder = getRxCache().newBuilder();
                    cacheBuilder.diskConverter(diskConverter)
                            .cachekey(Utils.checkNotNull(cacheKey, "cacheKey == null"))
                            .cacheTime(cacheTime);
                    return cacheBuilder;
                }
            default:
                break;
        }
        return rxCacheBuilder;
    }

    protected R build() {
        final RxCache.Builder rxCacheBuilder = generateRxCache();
        OkHttpClient.Builder okHttpClientBuilder = generateOkClient();
        if (cacheMode == CacheMode.DEFAULT) {
            //okhttp缓存
            okHttpClientBuilder.cache(cache);
        }
        final Retrofit.Builder retrofitBuilder = generateRetrofit();
        okHttpClient = okHttpClientBuilder.build();
        retrofitBuilder.client(okHttpClient);
        retrofit = retrofitBuilder.build();
        rxCache = rxCacheBuilder.build();
        apiManager = retrofit.create(ApiService.class);
        return (R) this;
    }

    protected abstract Observable<ResponseBody> generateRequest();
}
