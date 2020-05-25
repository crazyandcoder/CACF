package com.crazyandcoder.android.lib.network.cache.core;

import com.crazyandcoder.android.lib.network.cache.converter.IDiskConverter;
import com.crazyandcoder.android.lib.network.utils.Utils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @ClassName: LruDiskCache
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 4:47 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 4:47 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LruDiskCache extends BaseCache {

    private IDiskConverter mDiskConverter;
    private DiskLruCache mDiskLruCache;


    public LruDiskCache(IDiskConverter diskConverter, File diskDir, int appVersion, long diskMaxSize) {
        this.mDiskConverter = Utils.checkNotNull(diskConverter, "diskConverter ==null");
        try {
            mDiskLruCache = DiskLruCache.open(diskDir, appVersion, 1, diskMaxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
