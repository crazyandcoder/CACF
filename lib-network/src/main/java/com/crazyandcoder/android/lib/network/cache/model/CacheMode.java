package com.crazyandcoder.android.lib.network.cache.model;

/**
 * @ClassName: CacheMode
 * @Description: 网络请求策略
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/22 4:40 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/22 4:40 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public enum CacheMode {

    /**
     * 不使用缓存,该模式下,cacheKey,cacheMaxAge 参数均无效
     */
    NO_CACHE("NoStrategy"),


    /**
     * 完全按照HTTP协议的默认缓存规则，走OKhttp的Cache缓存
     */
    DEFALUT("NoStrategy"),

    /**
     * 先请求网络，请求网络失败后再加载缓存
     */
    FIRSTREMOTE("FirstRemoteStrategy"),

    /**
     * 先加载缓存，缓存没有再去请求网络
     */
    FIRSTCACHE("FirstCacheStategy"),

    /**
     * 仅加载网络，但数据依然会被缓存
     */
    ONLYREMOTE("OnlyRemoteStrategy"),

    /**
     * 只读取缓存
     */
    ONLYCACHE("OnlyCacheStrategy"),

    /**
     * 先使用缓存，不管是否存在，仍然请求网络，会回调两次
     */
    CACHEANDREMOTE("CacheAndRemoteStrategy"),
    /**
     * 先使用缓存，不管是否存在，仍然请求网络，会先把缓存回调给你，
     * 等网络请求回来发现数据是一样的就不会再返回，否则再返回
     * （这样做的目的是防止数据是一样的你也需要刷新界面）
     */
    CACHEANDREMOTEDISTINCT("CacheAndRemoteDistinctStrategy");

    private final String className;

    CacheMode(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }


}
