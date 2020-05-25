package com.crazyandcoder.android.lib.network.func;

import com.crazyandcoder.android.lib.network.exception.ApiException;
import com.crazyandcoder.android.lib.network.exception.ServerException;
import com.crazyandcoder.android.lib.network.model.ApiResult;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @ClassName: HandleFuc
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/25 6:24 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/25 6:24 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HandleFuc<T> implements Function<ApiResult<T>, T> {
    @Override
    public T apply(@NonNull ApiResult<T> tApiResult) throws Exception {
        if (ApiException.isOk(tApiResult)) {
            return tApiResult.getData();
        } else {
            throw new ServerException(tApiResult.getCode(), tApiResult.getMsg());
        }
    }
}
