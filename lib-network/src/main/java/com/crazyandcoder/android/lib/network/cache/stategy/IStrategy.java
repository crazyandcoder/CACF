package com.crazyandcoder.android.lib.network.cache.stategy;

import com.crazyandcoder.android.lib.network.cache.RxCache;
import com.crazyandcoder.android.lib.network.cache.model.CacheResult;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * @ClassName: IStrategy
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 4:53 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 4:53 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IStrategy {
    /**
     * 执行缓存
     *
     * @param rxCache   缓存管理对象
     * @param cacheKey  缓存key
     * @param cacheTime 缓存时间
     * @param source    网络请求对象
     * @param type      要转换的目标对象
     * @return 返回带缓存的Observable流对象
     */
    <T> Observable<CacheResult<T>> execute(RxCache rxCache, String cacheKey, long cacheTime, Observable<T> source, Type type);

}
