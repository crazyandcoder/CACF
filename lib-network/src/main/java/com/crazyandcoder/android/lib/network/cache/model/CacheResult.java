package com.crazyandcoder.android.lib.network.cache.model;

import java.io.Serializable;

/**
 * @ClassName: CacheResult
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 4:51 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 4:51 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CacheResult<T> implements Serializable {
    public boolean isFromCache;
    public T data;

    public CacheResult() {
    }

    public CacheResult(boolean isFromCache) {
        this.isFromCache = isFromCache;
    }

    public CacheResult(boolean isFromCache, T data) {
        this.isFromCache = isFromCache;
        this.data = data;
    }

    public boolean isCache() {
        return isFromCache;
    }

    public void setCache(boolean cache) {
        isFromCache = cache;
    }

    @Override
    public String toString() {
        return "CacheResult{" +
                "isCache=" + isFromCache +
                ", data=" + data +
                '}';
    }
}
