package com.crazyandcoder.android.lib.network.func;

import com.crazyandcoder.android.lib.network.exception.ApiException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @ClassName: HttpResponseFunc
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 6:29 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 6:29 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ApiException.handleException(throwable));
    }
}
