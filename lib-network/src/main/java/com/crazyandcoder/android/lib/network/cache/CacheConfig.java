package com.crazyandcoder.android.lib.network.cache;

import com.crazyandcoder.android.lib.network.cache.model.CacheMode;

import java.io.File;

import okhttp3.Cache;

/**
 * @ClassName: CacheConfig
 * @Description: 缓存相关配置类
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/22 4:37 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/22 4:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CacheConfig {

    /**
     * Okhttp缓存对象
     */
    private Cache mCache = null;

    /**
     * 缓存类型
     */
    private CacheMode mCacheMode = CacheMode.NO_CACHE;

    /**
     * 缓存时间
     */
    private long mCacheTime = -1;

    /**
     * 缓存目录
     */
    private File mCacheDirectory;

    /**
     * 缓存大小
     */
    private long mCacheMaxSize;

    public Cache getCache() {
        return mCache;
    }

    public void setCache(Cache mCache) {
        this.mCache = mCache;
    }

    public CacheMode getCacheMode() {
        return mCacheMode;
    }

    public void setCacheMode(CacheMode mCacheMode) {
        this.mCacheMode = mCacheMode;
    }

    public long getCacheTime() {
        return mCacheTime;
    }

    public void setCacheTime(long mCacheTime) {
        this.mCacheTime = mCacheTime;
    }

    public File getCacheDirectory() {
        return mCacheDirectory;
    }

    public void setCacheDirectory(File mCacheDirectory) {
        this.mCacheDirectory = mCacheDirectory;
    }

    public long getCacheMaxSize() {
        return mCacheMaxSize;
    }

    public void setCacheMaxSize(long mCacheMaxSize) {
        this.mCacheMaxSize = mCacheMaxSize;
    }
}
