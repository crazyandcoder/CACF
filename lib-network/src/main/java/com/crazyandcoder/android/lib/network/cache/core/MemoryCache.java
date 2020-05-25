package com.crazyandcoder.android.lib.network.cache.core;

import java.lang.reflect.Type;

/**
 * @ClassName: MemoryCache
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 4:49 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 4:49 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MemoryCache extends BaseCache {
    @Override
    protected boolean doContainsKey(String key) {
        return false;
    }

    @Override
    protected boolean isExpiry(String key, long existTime) {
        return false;
    }

    @Override
    protected <T> T doLoad(Type type, String key) {
        return null;
    }

    @Override
    protected <T> boolean doSave(String key, T value) {
        return false;
    }

    @Override
    protected boolean doRemove(String key) {
        return false;
    }

    @Override
    protected boolean doClear() {
        return false;
    }
}
